import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

internal class Scores(d: Digger) {
    var dig: Digger
    var scores: Array<ScoreTuple> = Array(10) { _ -> ScoreTuple("...", 0) }
    var substr: String? = null
    var highbuf = CharArray(10)
    var scorehigh = longArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [12]
    var scoreinit = arrayOfNulls<String>(11)
    var scoret: Long = 0
    var score1: Long = 0
    var score2: Long = 0
    var nextbs1: Long = 0
    var nextbs2: Long = 0
    var hsbuf: String? = null
    var scorebuf = CharArray(512)
    var bonusscore = 20000
    var gotinitflag = false

    fun _submit(n: String?, s: Int): Array<ScoreTuple> {
        if (dig.subaddr != null) {
            val ms = 16 + (System.currentTimeMillis() % (65536 - 16)).toInt()
            substr = n + '+' + s + '+' + ms + '+' + (ms + 32768) * s % 65536
            // Thread(this).start()
        }
        return scores
    }

    fun _updatescores(o: Array<ScoreTuple>?) {
        if (o == null)
            return

        try {
            val `in` = arrayOfNulls<String>(10)
            val sc = IntArray(10)
            for (i in 0..9) {
                `in`[i] = o[i].key
                sc[i] = o[i].value!!
            }
            for (i in 0..9) {
                scoreinit[i + 1] = `in`[i]
                scorehigh[i + 2] = sc[i].toLong()
            }
        } catch (e: Exception) {
        }
    }

    fun addscore(score: Int) {
        if (dig.Main.getcplayer() === 0) {
            score1 += score.toLong()
            if (score1 > 999999L) score1 = 0
            writenum(score1, 0, 0, 6, 1)
            if (score1 >= nextbs1) {
                if (dig.Main.getlives(1) < 5) {
                    dig.Main.addlife(1)
                    dig.Drawing.drawlives()
                }
                nextbs1 += bonusscore.toLong()
            }
        } else {
            score2 += score.toLong()
            if (score2 > 999999L) score2 = 0
            if (score2 < 100000L) writenum(score2, 236, 0, 6, 1) else writenum(score2, 248, 0, 6, 1)
            if (score2 > nextbs2) { /* Player 2 doesn't get the life until >20,000 ! */
                if (dig.Main.getlives(2) < 5) {
                    dig.Main.addlife(2)
                    dig.Drawing.drawlives()
                }
                nextbs2 += bonusscore.toLong()
            }
        }
        dig.Main.incpenalty()
        dig.Main.incpenalty()
        dig.Main.incpenalty()
    }

    fun drawscores() {
        writenum(score1, 0, 0, 6, 3)
        if (dig.Main.nplayers === 2) if (score2 < 100000L) writenum(score2, 236, 0, 6, 3) else writenum(
            score2,
            248,
            0,
            6,
            3
        )
    }

    fun endofgame() {
        var i: Int
        var j: Int
        var z: Int
        addscore(0)
        scoret = if (dig.Main.getcplayer() === 0) score1 else score2
        if (scoret > scorehigh[11]) {
            dig.Pc.gclear()
            drawscores()
            dig.Main.pldispbuf = "PLAYER "
            if (dig.Main.getcplayer() === 0) dig.Main.pldispbuf += "1" else dig.Main.pldispbuf += "2"
            dig.Drawing.outtext(dig.Main.pldispbuf, 108, 0, 2, true)
            dig.Drawing.outtext(" NEW HIGH SCORE ", 64, 40, 2, true)
            getinitials()
            _updatescores(_submit(scoreinit[0], scoret.toInt()))
            shufflehigh()
            ScoreStorage.writeToStorage(this);
        } else {
            dig.Main.cleartopline()
            dig.Drawing.outtext("GAME OVER", 104, 0, 3, true)
            _updatescores(_submit("...", scoret.toInt()))
            dig.Sound.killsound()
            j = 0
            while (j < 20) {
                /* Number of times screen flashes * 2 */i = 0
                while (i < 2) {
                    // i<8;i++) {
                    dig.Sprite.setretr(true)
                    dig.Pc.gpal(1 - (j and 1))
                    dig.Sprite.setretr(false)
                    z = 0
                    while (z < 111) {
                        /* A delay loop */
                        z++
                    }
                    dig.Pc.gpal(0)
                    dig.Pc.ginten(1 - i and 1)
                    dig.newframe()
                    i++
                }
                j++
            }
            dig.Sound.setupsound()
            dig.Drawing.outtext("         ", 104, 0, 3, true)
            dig.Sprite.setretr(true)
        }
    }

    fun flashywait(n: Int) {
        try {
            Thread.sleep((n * 2).toLong())
        } catch (e: Exception) {
        }
    }

    fun getinitial(x: Int, y: Int): Int {
        var i: Int
        var j: Int
        dig.Input.keypressed = 0
        dig.Pc.gwrite(x, y, '_'.toInt(), 3, true)
        j = 0
        while (j < 5) {
            i = 0
            while (i < 40) {
                if (dig.Input.keypressed and 0x80 === 0 && dig.Input.keypressed !== 0) return dig.Input.keypressed
                flashywait(15)
                i++
            }
            i = 0
            while (i < 40) {
                if (dig.Input.keypressed and 0x80 === 0 && dig.Input.keypressed !== 0) {
                    dig.Pc.gwrite(x, y, '_'.toInt(), 3, true)
                    return dig.Input.keypressed
                }
                flashywait(15)
                i++
            }
            j++
        }
        gotinitflag = true
        return 0
    }

    fun getinitials() {
        var k: Int
        var i: Int
        dig.Drawing.outtext("ENTER YOUR", 100, 70, 3, true)
        dig.Drawing.outtext(" INITIALS", 100, 90, 3, true)
        dig.Drawing.outtext("_ _ _", 128, 130, 3, true)
        scoreinit[0] = "..."
        dig.Sound.killsound()
        gotinitflag = false
        i = 0
        while (i < 3) {
            k = 0
            while (k == 0 && !gotinitflag) {
                k = getinitial(i * 24 + 128, 130)
                if (i != 0 && k == 8) i--
                k = dig.Input.getasciikey(k)
            }
            if (k != 0) {
                dig.Pc.gwrite(i * 24 + 128, 130, k, 3, true)
                val sb = StringBuffer(scoreinit[0])
                sb.setCharAt(i, k.toChar())
                scoreinit[0] = sb.toString()
            }
            i++
        }
        dig.Input.keypressed = 0
        i = 0
        while (i < 20) {
            flashywait(15)
            i++
        }
        dig.Sound.setupsound()
        dig.Pc.gclear()
        dig.Pc.gpal(0)
        dig.Pc.ginten(0)
        dig.newframe()
        dig.Sprite.setretr(true)
    }

    fun initscores() {
        addscore(0)
    }

    fun loadscores() {
        var p = 1
        var i: Int
        var x: Int
        i = 1
        while (i < 11) {
            x = 0
            while (x < 3) {
                scoreinit[i] = "..."
                x++
            }
            p += 2
            x = 0
            while (x < 6) {
                highbuf[x] = scorebuf[p++]
                x++
            }
            scorehigh[i + 1] = 0
            i++
        }
        if (scorebuf[0] != 's') {
            i = 0
            while (i < 11) {
                scorehigh[i + 1] = 0
                scoreinit[i] = "..."
                i++
            }
        }
    }

    fun numtostring(n: Long): String {
        var n = n
        var x: Int
        var p = ""
        x = 0
        while (x < 6) {
            p = (n % 10).toString() + p
            n /= 10
            if (n == 0L) {
                x++
                break
            }
            x++
        }
        while (x < 6) {
            p = " $p"
            x++
        }
        return p
    }

    fun init() {
        if (!ScoreStorage.readFromStorage(this))
            ScoreStorage.createInStorage(this);
    }

    fun scorebonus() {
        addscore(1000)
    }

    fun scoreeatm() {
        addscore(dig.eatmsc * 200)
        dig.eatmsc = dig.eatmsc shl 1
    }

    fun scoreemerald() {
        addscore(25)
    }

    fun scoregold() {
        addscore(500)
    }

    fun scorekill() {
        addscore(250)
    }

    fun scoreoctave() {
        addscore(250)
    }

    fun showtable() {
        var i: Int
        var col: Int
        dig.Drawing.outtext("HIGH SCORES", 16, 25, 3)
        col = 2
        i = 1
        while (i < 11) {
            hsbuf = scoreinit[i].toString() + "  " + numtostring(scorehigh[i + 1])
            var hsbufCopy = hsbuf.toString();
            dig.Drawing.outtext(hsbufCopy, 16, 31 + 13 * i, col)
            col = 1
            i++
        }
    }

    fun shufflehigh() {
        var i: Int
        var j: Int
        j = 10
        while (j > 1) {
            if (scoret < scorehigh[j]) break
            j--
        }
        i = 10
        while (i > j) {
            scorehigh[i + 1] = scorehigh[i]
            scoreinit[i] = scoreinit[i - 1]
            i--
        }
        scorehigh[j + 1] = scoret
        scoreinit[j] = scoreinit[0]
    }

    fun writecurscore(bp6: Int) {
        if (dig.Main.getcplayer() === 0) writenum(score1, 0, 0, 6, bp6) else if (score2 < 100000L) writenum(
            score2,
            236,
            0,
            6,
            bp6
        ) else writenum(score2, 248, 0, 6, bp6)
    }

    fun writenum(n: Long, x: Int, y: Int, w: Int, c: Int) {
        var n = n
        var w = w
        var d: Int
        var xp = (w - 1) * 12 + x
        while (w > 0) {
            d = (n % 10).toInt()
            if (w > 1 || d > 0) dig.Pc.gwrite(xp, y, d + '0'.toInt(), c, false)
            n /= 10
            w--
            xp -= 12
        }
    }

    fun zeroscores() {
        score2 = 0
        score1 = 0
        scoret = 0
        nextbs1 = bonusscore.toLong()
        nextbs2 = bonusscore.toLong()
    }

    init {
        dig = d
    }
}