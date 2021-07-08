import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Image
import java.awt.image.IndexColorModel
import java.awt.image.MemoryImageSource
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or

@Suppress("DEPRECATION")
class Digger : AppletCompat(), Runnable {
    var intWidth = 320
    var intHeight = 200
    var frametime = 66
    var gamethread: Thread? = null
    var subaddr: String? = null
    var pic: Image? = null
    var picg: Graphics? = null
    internal var Bags: Bags
    internal var Main: Main
    internal var Sound: Sound
    internal var Monster: Monster
    internal var Scores: Scores
    internal var Sprite: Sprite
    internal var Drawing: Drawing
    internal var Input: Input
    internal var Pc: Pc

    // -----
    var diggerx = 0
    var diggery = 0
    var diggerh = 0
    var diggerv = 0
    var diggerrx = 0
    var diggerry = 0
    var digmdir = 0
    var digdir = 0
    var digtime = 0
    var rechargetime = 0
    var firex = 0
    var firey = 0
    var firedir = 0
    var expsn = 0
    var deathstage = 0
    var deathbag = 0
    var deathani = 0
    var deathtime = 0
    var startbonustimeleft = 0
    var bonustimeleft = 0
    var eatmsc = 0
    var emocttime = 0
    var emmask: Byte = 0
    var emfield = byteArrayOf( // [150]
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0
    )
    var digonscr = false
    var notfiring = false
    var bonusvisible = false
    var bonusmode = false
    var diggervisible = false
    var time: Long = 0
    var ftime: Long = 50
    var embox = intArrayOf(8, 12, 12, 9, 16, 12, 6, 9) // [8]
    var deatharc = intArrayOf(3, 5, 6, 6, 5, 3, 0) // [7]
    fun checkdiggerunderbag(h: Int, v: Int): Boolean {
        if (digmdir == 2 || digmdir == 6) if ((diggerx - 12) / 20 == h) if ((diggery - 18) / 18 == v || (diggery - 18) / 18 + 1 == v) return true
        return false
    }

    fun countem(): Int {
        var x: Int
        var y: Int
        var n = 0
        x = 0
        while (x < 15) {
            y = 0
            while (y < 10) {
                if (emfield[y * 15 + x] and emmask != 0.toByte()) n++
                y++
            }
            x++
        }
        return n
    }

    fun createbonus() {
        bonusvisible = true
        Drawing.drawbonus(292, 18)
    }

    fun destroy() {
        gamethread?.stop()
    }

    fun diggerdie() {
        val clbits: Int
        when (deathstage) {
            1 -> {
                if (Bags.bagy(deathbag) + 6 > diggery) diggery = Bags.bagy(deathbag) + 6
                Drawing.drawdigger(15, diggerx, diggery, false)
                Main.incpenalty()
                if (Bags.getbagdir(deathbag) + 1 == 0) {
                    Sound.soundddie()
                    deathtime = 5
                    deathstage = 2
                    deathani = 0
                    diggery -= 6
                }
            }
            2 -> {
                if (deathtime != 0) {
                    deathtime--
                    return // break TODO
                }
                if (deathani == 0) Sound.music(2)
                clbits = Drawing.drawdigger(14 - deathani, diggerx, diggery, false)
                Main.incpenalty()
                if (deathani == 0 && clbits and 0x3f00 != 0) Monster.killmonsters(clbits)
                if (deathani < 4) {
                    deathani++
                    deathtime = 2
                } else {
                    deathstage = 4
                    deathtime = if (Sound.musicflag) 60 else 10
                }
            }
            3 -> {
                deathstage = 5
                deathani = 0
                deathtime = 0
            }
            5 -> if (deathani >= 0 && deathani <= 6) {
                Drawing.drawdigger(15, diggerx, diggery - deatharc[deathani], false)
                if (deathani == 6) Sound.musicoff()
                Main.incpenalty()
                deathani++
                if (deathani == 1) Sound.soundddie()
                if (deathani == 7) {
                    deathtime = 5
                    deathani = 0
                    deathstage = 2
                }
            }
            4 -> if (deathtime != 0) deathtime-- else Main.setdead(true)
        }
    }

    fun dodigger() {
        newframe()
        if (expsn != 0) drawexplosion() else updatefire()
        if (diggervisible) if (digonscr) if (digtime != 0) {
            Drawing.drawdigger(digmdir, diggerx, diggery, notfiring && rechargetime == 0)
            Main.incpenalty()
            digtime--
        } else updatedigger() else diggerdie()
        if (bonusmode && digonscr) {
            if (bonustimeleft != 0) {
                bonustimeleft--
                if (startbonustimeleft != 0 || bonustimeleft < 20) {
                    startbonustimeleft--
                    if (bonustimeleft and 1 != 0) {
                        Pc.ginten(0)
                        Sound.soundbonus()
                    } else {
                        Pc.ginten(1)
                        Sound.soundbonus()
                    }
                    if (startbonustimeleft == 0) {
                        Sound.music(0)
                        Sound.soundbonusoff()
                        Pc.ginten(1)
                    }
                }
            } else {
                endbonusmode()
                Sound.soundbonusoff()
                Sound.music(1)
            }
        }
        if (bonusmode && !digonscr) {
            endbonusmode()
            Sound.soundbonusoff()
            Sound.music(1)
        }
        if (emocttime > 0) emocttime--
    }

    fun drawemeralds() {
        var x: Int
        var y: Int
        emmask = (1 shl Main.getcplayer()).toByte()
        x = 0
        while (x < 15) {
            y = 0
            while (y < 10) {
                if (emfield[y * 15 + x] and emmask != 0.toByte()) Drawing.drawemerald(x * 20 + 12, y * 18 + 21)
                y++
            }
            x++
        }
    }

    fun drawexplosion() {
        when (expsn) {
            1 -> {
                Sound.soundexplode()
                Drawing.drawfire(firex, firey, expsn)
                Main.incpenalty()
                expsn++
            }
            2, 3 -> {
                Drawing.drawfire(firex, firey, expsn)
                Main.incpenalty()
                expsn++
            }
            else -> {
                killfire()
                expsn = 0
            }
        }
    }

    fun endbonusmode() {
        bonusmode = false
        Pc.ginten(0)
    }

    fun erasebonus() {
        if (bonusvisible) {
            bonusvisible = false
            Sprite.erasespr(14)
        }
        Pc.ginten(0)
    }

    fun erasedigger() {
        Sprite.erasespr(0)
        diggervisible = false
    }

    fun getAppletInfo(): String {
        return "The Digger Remastered -- http://www.digger.org, Copyright (c) Andrew Jenner & Marek Futrega / MAF"
    }

    fun getfirepflag(): Boolean {
        return Input.firepflag
    }

    fun hitemerald(xRaw: Int, yRaw: Int, rx: Int, ry: Int, dir: Int): Boolean {
        var x = xRaw
        var y = yRaw
        var hit = false
        val r: Int
        if (dir < 0 || dir > 6 || dir and 1 != 0) return hit
        if (dir == 0 && rx != 0) x++
        if (dir == 6 && ry != 0) y++
        r = if (dir == 0 || dir == 4) rx else ry
        if (emfield[y * 15 + x] and emmask != 0.toByte()) {
            if (r == embox[dir]) {
                Drawing.drawemerald(x * 20 + 12, y * 18 + 21)
                Main.incpenalty()
            }
            if (r == embox[dir + 1]) {
                Drawing.eraseemerald(x * 20 + 12, y * 18 + 21)
                Main.incpenalty()
                hit = true
                emfield[y * 15 + x] = emfield[y * 15 + x] and emmask.inv()
            }
        }
        return hit
    }

    fun init() {
        if (gamethread != null) gamethread!!.stop()
        subaddr = getSubmitParameter()
        try {
            frametime = getSpeedParameter()
            if (frametime > MAX_RATE) frametime = MAX_RATE else if (frametime < MIN_RATE) frametime = MIN_RATE
        } catch (e: Exception) {
        }
        Pc.pixels = IntArray(65536)
        for (i in 0..1) {
            Pc.source[i] = MemoryImageSource(
                Pc.width, Pc.height,
                IndexColorModel(8, 4, Pc.pal[i][0], Pc.pal[i][1], Pc.pal[i][2]), Pc.pixels, 0, Pc.width
            )
            Pc.source[i]!!.setAnimated(true)
            Pc.image[i] = createImage(Pc.source[i])
            Pc.source[i]!!.newPixels()
        }
        Pc.currentImage = Pc.image[0]
        Pc.currentSource = Pc.source[0]
        gamethread = Thread(this)
        gamethread!!.start()
    }

    fun initbonusmode() {
        bonusmode = true
        erasebonus()
        Pc.ginten(1)
        bonustimeleft = 250 - Main.levof10() * 20
        startbonustimeleft = 20
        eatmsc = 1
    }

    fun initdigger() {
        diggerv = 9
        digmdir = 4
        diggerh = 7
        diggerx = diggerh * 20 + 12
        digdir = 0
        diggerrx = 0
        diggerry = 0
        digtime = 0
        digonscr = true
        deathstage = 1
        diggervisible = true
        diggery = diggerv * 18 + 18
        Sprite.movedrawspr(0, diggerx, diggery)
        notfiring = true
        emocttime = 0
        bonusmode = false
        bonusvisible = bonusmode
        Input.firepressed = false
        expsn = 0
        rechargetime = 0
    }

    override fun keyDown(keyRaw: Int): Boolean {
        var key = keyRaw
        when (key) {
            1006 -> Input.processkey(0x4b)
            1007 -> Input.processkey(0x4d)
            1004 -> Input.processkey(0x48)
            1005 -> Input.processkey(0x50)
            1008 -> Input.processkey(0x3b)
            else -> {
                key = key and 0x7f
                if (key >= 65 && key <= 90) key += 97 - 65
                Input.processkey(key)
            }
        }
        return true
    }

    override fun keyUp(keyRaw: Int): Boolean {
        var key = keyRaw
        when (key) {
            1006 -> Input.processkey(0xcb)
            1007 -> Input.processkey(0xcd)
            1004 -> Input.processkey(0xc8)
            1005 -> Input.processkey(0xd0)
            1008 -> Input.processkey(0xbb)
            else -> {
                key = key and 0x7f
                if (key >= 65 && key <= 90) key += 97 - 65
                Input.processkey(0x80 or key)
            }
        }
        return true
    }

    fun killdigger(stage: Int, bag: Int) {
        if (deathstage < 2 || deathstage > 4) {
            digonscr = false
            deathstage = stage
            deathbag = bag
        }
    }

    fun killemerald(x: Int, y: Int) {
        if (emfield[y * 15 + x + 15] and emmask != 0.toByte()) {
            emfield[y * 15 + x + 15] = emfield[y * 15 + x + 15] and emmask.inv()
            Drawing.eraseemerald(x * 20 + 12, (y + 1) * 18 + 21)
        }
    }

    fun killfire() {
        if (!notfiring) {
            notfiring = true
            Sprite.erasespr(15)
            Sound.soundfireoff()
        }
    }

    fun makeemfield() {
        var x: Int
        var y: Int
        emmask = (1 shl Main.getcplayer()).toByte()
        x = 0
        while (x < 15) {
            y = 0
            while (y < 10) {
                if (Main.getlevch(x, y, Main.levplan()) == 'C'.toInt()) emfield[y * 15 + x] =
                    emfield[y * 15 + x] or emmask else emfield[y * 15 + x] = emfield[y * 15 + x] and emmask.inv()
                y++
            }
            x++
        }
    }

    fun newframe() {
        Input.checkkeyb()
        time += frametime.toLong()
        val l = time - Pc.gethrt()
        if (l > 0) {
            try {
                Thread.sleep(l)
            } catch (e: Exception) {
            }
        }
        Pc.currentSource!!.newPixels()
    }

    override fun paint(g: Graphics) {
        update(g)
    }

    fun reversedir(dir: Int): Int {
        when (dir) {
            0 -> return 4
            4 -> return 0
            2 -> return 6
            6 -> return 2
        }
        return dir
    }

    override fun run() {
        Main.main()
    }

    fun start() {
        requestFocus()
    }

    override fun update(g: Graphics) {
        if (g is Graphics2D) {
            g.color = Color.black
            g.fillRect(0, 0, width, height)

            g.scale(4.0, 4.0);
            g.drawImage(Pc.currentImage, 0, 0, this)
        }
    }

    fun updatedigger() {
        val dir: Int
        val ddir: Int
        val clbits: Int
        val diggerox: Int
        val diggeroy: Int
        var nmon: Int
        var push = false
        Input.readdir()
        dir = Input.getdir()
        ddir = if (dir == 0 || dir == 2 || dir == 4 || dir == 6) dir else -1
        if (diggerrx == 0 && (ddir == 2 || ddir == 6)) {
            digmdir = ddir
            digdir = digmdir
        }
        if (diggerry == 0 && (ddir == 4 || ddir == 0)) {
            digmdir = ddir
            digdir = digmdir
        }
        digmdir = if (dir == -1) -1 else digdir
        if (diggerx == 292 && digmdir == 0 || diggerx == 12 && digmdir == 4 || diggery == 180 && digmdir == 6
            || diggery == 18 && digmdir == 2
        ) digmdir = -1
        diggerox = diggerx
        diggeroy = diggery
        if (digmdir != -1) Drawing.eatfield(diggerox, diggeroy, digmdir)
        when (digmdir) {
            0 -> {
                Drawing.drawrightblob(diggerx, diggery)
                diggerx += 4
            }
            4 -> {
                Drawing.drawleftblob(diggerx, diggery)
                diggerx -= 4
            }
            2 -> {
                Drawing.drawtopblob(diggerx, diggery)
                diggery -= 3
            }
            6 -> {
                Drawing.drawbottomblob(diggerx, diggery)
                diggery += 3
            }
        }
        if (hitemerald((diggerx - 12) / 20, (diggery - 18) / 18, (diggerx - 12) % 20, (diggery - 18) % 18, digmdir)) {
            Scores.scoreemerald()
            Sound.soundem()
            Sound.soundemerald(emocttime)
            emocttime = 9
        }
        clbits = Drawing.drawdigger(digdir, diggerx, diggery, notfiring && rechargetime == 0)
        Main.incpenalty()
        if (Bags.bagbits() and clbits != 0) {
            if (digmdir == 0 || digmdir == 4) {
                push = Bags.pushbags(digmdir, clbits)
                digtime++
            } else if (!Bags.pushudbags(clbits)) push = false
            if (!push) { /* Strange, push not completely defined */
                diggerx = diggerox
                diggery = diggeroy
                Drawing.drawdigger(digmdir, diggerx, diggery, notfiring && rechargetime == 0)
                Main.incpenalty()
                digdir = reversedir(digmdir)
            }
        }
        if (clbits and 0x3f00 != 0 && bonusmode) {
            nmon = Monster.killmonsters(clbits)
            while (nmon != 0) {
                Sound.soundeatm()
                Scores.scoreeatm()
                nmon--
            }
        }
        if (clbits and 0x4000 != 0) {
            Scores.scorebonus()
            initbonusmode()
        }
        diggerh = (diggerx - 12) / 20
        diggerrx = (diggerx - 12) % 20
        diggerv = (diggery - 18) / 18
        diggerry = (diggery - 18) % 18
    }

    fun updatefire() {
        val clbits: Int
        var b: Int
        var mon: Int
        var pix = 0
        if (notfiring) {
            if (rechargetime != 0) rechargetime-- else if (getfirepflag()) if (digonscr) {
                rechargetime = Main.levof10() * 3 + 60
                notfiring = false
                when (digdir) {
                    0 -> {
                        firex = diggerx + 8
                        firey = diggery + 4
                    }
                    4 -> {
                        firex = diggerx
                        firey = diggery + 4
                    }
                    2 -> {
                        firex = diggerx + 4
                        firey = diggery
                    }
                    6 -> {
                        firex = diggerx + 4
                        firey = diggery + 8
                    }
                }
                firedir = digdir
                Sprite.movedrawspr(15, firex, firey)
                Sound.soundfire()
            }
        } else {
            when (firedir) {
                0 -> {
                    firex += 8
                    pix = Pc.ggetpix(firex, firey + 4) or Pc.ggetpix(firex + 4, firey + 4)
                }
                4 -> {
                    firex -= 8
                    pix = Pc.ggetpix(firex, firey + 4) or Pc.ggetpix(firex + 4, firey + 4)
                }
                2 -> {
                    firey -= 7
                    pix = (Pc.ggetpix(firex + 4, firey) or Pc.ggetpix(firex + 4, firey + 1)
                            or Pc.ggetpix(firex + 4, firey + 2) or Pc.ggetpix(firex + 4, firey + 3)
                            or Pc.ggetpix(firex + 4, firey + 4) or Pc.ggetpix(firex + 4, firey + 5)
                            or Pc.ggetpix(firex + 4, firey + 6)) and 0xc0
                }
                6 -> {
                    firey += 7
                    pix = (Pc.ggetpix(firex, firey) or Pc.ggetpix(firex, firey + 1) or Pc.ggetpix(firex, firey + 2)
                            or Pc.ggetpix(firex, firey + 3) or Pc.ggetpix(firex, firey + 4) or Pc.ggetpix(
                        firex,
                        firey + 5
                    )
                            or Pc.ggetpix(firex, firey + 6)) and 3
                }
            }
            clbits = Drawing.drawfire(firex, firey, 0)
            Main.incpenalty()
            if (clbits and 0x3f00 != 0) {
                mon = 0
                b = 256
                while (mon < 6) {
                    if (clbits and b != 0) {
                        Monster.killmon(mon)
                        Scores.scorekill()
                        expsn = 1
                    }
                    mon++
                    b = b shl 1
                }
            }
            if (clbits and 0x40fe != 0) expsn = 1
            when (firedir) {
                0 -> if (firex > 296) expsn = 1 else if (pix != 0 && clbits == 0) {
                    expsn = 1
                    firex -= 8
                    Drawing.drawfire(firex, firey, 0)
                }
                4 -> if (firex < 16) expsn = 1 else if (pix != 0 && clbits == 0) {
                    expsn = 1
                    firex += 8
                    Drawing.drawfire(firex, firey, 0)
                }
                2 -> if (firey < 15) expsn = 1 else if (pix != 0 && clbits == 0) {
                    expsn = 1
                    firey += 7
                    Drawing.drawfire(firex, firey, 0)
                }
                6 -> if (firey > 183) expsn = 1 else if (pix != 0 && clbits == 0) {
                    expsn = 1
                    firey -= 7
                    Drawing.drawfire(firex, firey, 0)
                }
            }
        }
    }

    companion object {
        var MAX_RATE = 200
        var MIN_RATE = 40
    }

    init {
        Bags = Bags(this)
        Main = Main(this)
        Sound = Sound(this)
        Monster = Monster(this)
        Scores = Scores(this)
        Sprite = Sprite(this)
        Drawing = Drawing(this)
        Input = Input(this)
        Pc = Pc(this)
    }
}