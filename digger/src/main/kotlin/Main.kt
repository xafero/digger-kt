internal class Main(d: Digger) {
    var dig: Digger
    var digsprorder = intArrayOf(14, 13, 7, 6, 5, 4, 3, 2, 1, 12, 11, 10, 9, 8, 15, 0) // [16]
    var gamedat = arrayOf(_game(), _game())
    var pldispbuf = ""
    var curplayer = 0
    var nplayers = 0
    var penalty = 0
    var levnotdrawn = false
    var flashplayer = false
    var levfflag = false
    var biosflag = false
    var speedmul = 40
    var delaytime = 0
    var randv: Long = 0
    var leveldat = arrayOf(
        arrayOf(
            "S   B     HHHHS", "V  CC  C  V B  ", "VB CC  C  V    ", "V  CCB CB V CCC", "V  CC  C  V CCC",
            "HH CC  C  V CCC", " V    B B V    ", " HHHH     V    ", "C   V     V   C", "CC  HHHHHHH  CC"
        ), arrayOf(
            "SHHHHH  B B  HS", " CC  V       V ", " CC  V CCCCC V ", "BCCB V CCCCC V ", "CCCC V       V ",
            "CCCC V B  HHHH ", " CC  V CC V    ", " BB  VCCCCV CC ", "C    V CC V CC ",
            "CC   HHHHHH    "
        ), arrayOf(
            "SHHHHB B BHHHHS", "CC  V C C V BB ", "C   V C C V CC ", " BB V C C VCCCC", "CCCCV C C VCCCC",
            "CCCCHHHHHHH CC ", " CC  C V C  CC ", " CC  C V C     ", "C    C V C    C",
            "CC   C H C   CC"
        ), arrayOf(
            "SHBCCCCBCCCCBHS", "CV  CCCCCCC  VC", "CHHH CCCCC HHHC", "C  V  CCC  V  C", "   HHH C HHH   ",
            "  B  V B V  B  ", "  C  VCCCV  C  ", " CCC HHHHH CCC ", "CCCCC CVC CCCCC",
            "CCCCC CHC CCCCC"
        ), arrayOf(
            "SHHHHHHHHHHHHHS", "VBCCCCBVCCCCCCV", "VCCCCCCV CCBC V", "V CCCC VCCBCCCV", "VCCCCCCV CCCC V",
            "V CCCC VBCCCCCV", "VCCBCCCV CCCC V", "V CCBC VCCCCCCV", "VCCCCCCVCCCCCCV",
            "HHHHHHHHHHHHHHH"
        ), arrayOf(
            "SHHHHHHHHHHHHHS", "VCBCCV V VCCBCV", "VCCC VBVBV CCCV", "VCCCHH V HHCCCV", "VCC V CVC V CCV",
            "VCCHH CVC HHCCV", "VC V CCVCC V CV", "VCHHBCCVCCBHHCV", "VCVCCCCVCCCCVCV",
            "HHHHHHHHHHHHHHH"
        ), arrayOf(
            "SHCCCCCVCCCCCHS", " VCBCBCVCBCBCV ", "BVCCCCCVCCCCCVB", "CHHCCCCVCCCCHHC", "CCV CCCVCCC VCC",
            "CCHHHCCVCCHHHCC", "CCCCV CVC VCCCC", "CCCCHH V HHCCCC", "CCCCCV V VCCCCC",
            "CCCCCHHHHHCCCCC"
        ), arrayOf(
            "HHHHHHHHHHHHHHS", "V CCBCCCCCBCC V", "HHHCCCCBCCCCHHH", "VBV CCCCCCC VBV", "VCHHHCCCCCHHHCV",
            "VCCBV CCC VBCCV", "VCCCHHHCHHHCCCV", "VCCCC V V CCCCV", "VCCCCCV VCCCCCV",
            "HHHHHHHHHHHHHHH"
        )
    )

    fun addlife(pl: Int) {
        gamedat[pl - 1].lives++
        dig.Sound.sound1up()
    }

    fun calibrate() {
        dig.Sound.volume = (dig.Pc.getkips() / 291)
        if (dig.Sound.volume === 0) dig.Sound.volume = 1
    }

    fun checklevdone() {
        if ((dig.countem() === 0 || dig.Monster.monleft() === 0) && dig.digonscr) gamedat[curplayer].levdone =
            true else gamedat[curplayer].levdone = false
    }

    fun cleartopline() {
        dig.Drawing.outtext("                          ", 0, 0, 3)
        dig.Drawing.outtext(" ", 308, 0, 3)
    }

    fun drawscreen() {
        dig.Drawing.creatembspr()
        dig.Drawing.drawstatics()
        dig.Bags.drawbags()
        dig.drawemeralds()
        dig.initdigger()
        dig.Monster.initmonsters()
    }

    fun getcplayer(): Int {
        return curplayer
    }

    fun getlevch(x: Int, y: Int, lRaw: Int): Int {
        var l = lRaw
        if (l == 0) l++
        return leveldat[l - 1][y][x].toInt()
    }

    fun getlives(pl: Int): Int {
        return gamedat[pl - 1].lives
    }

    fun incpenalty() {
        penalty++
    }

    fun initchars() {
        dig.Drawing.initmbspr()
        dig.initdigger()
        dig.Monster.initmonsters()
    }

    fun initlevel() {
        gamedat[curplayer].levdone = false
        dig.Drawing.makefield()
        dig.makeemfield()
        dig.Bags.initbags()
        levnotdrawn = true
    }

    fun levno(): Int {
        return gamedat[curplayer].level
    }

    fun levof10(): Int {
        return if (gamedat[curplayer].level > 10) 10 else gamedat[curplayer].level
    }

    fun levplan(): Int {
        var l = levno()
        if (l > 8) l = (l and 3) + 5 /* Level plan: 12345678, 678, (5678) 247 times, 5 forever */
        return l
    }

    fun main() {
        var frame: Int
        var t: Int
        var x = 0
        var start: Boolean
        randv = dig.Pc.gethrt()
        calibrate()
        dig.ftime = speedmul * 2000L
        dig.Sprite.setretr(false)
        dig.Pc.ginit()
        dig.Sprite.setretr(true)
        dig.Pc.gpal(0)
        dig.Input.initkeyb()
        dig.Input.detectjoy()
        dig.Scores.loadscores()
        dig.Sound.initsound()

        dig.Scores.init()
        dig.Scores._updatescores(dig.Scores.scores)

        nplayers = 1
        do {
            dig.Sound.soundstop()
            dig.Sprite.setsprorder(digsprorder)
            dig.Drawing.creatembspr()
            dig.Input.detectjoy()
            dig.Pc.gclear()
            dig.Pc.gtitle()
            dig.Drawing.outtext("D I G G E R", 100, 0, 3)
            shownplayers()
            dig.Scores.showtable()
            start = false
            frame = 0
            dig.time = dig.Pc.gethrt()
            while (!start) {
                start = dig.Input.teststart()
                if (dig.Input.akeypressed === 27) { // esc
                    switchnplayers()
                    shownplayers()
                    dig.Input.akeypressed = 0
                    dig.Input.keypressed = 0
                }
                if (frame == 0) {
                    t = 54
                    while (t < 174) {
                        dig.Drawing.outtext("            ", 164, t, 0)
                        t += 12
                    }
                }
                if (frame == 50) {
                    dig.Sprite.movedrawspr(8, 292, 63)
                    x = 292
                }
                if (frame > 50 && frame <= 77) {
                    x -= 4
                    dig.Drawing.drawmon(0, true, 4, x, 63)
                }
                if (frame > 77) dig.Drawing.drawmon(0, true, 0, 184, 63)
                if (frame == 83) dig.Drawing.outtext("NOBBIN", 216, 64, 2)
                if (frame == 90) {
                    dig.Sprite.movedrawspr(9, 292, 82)
                    dig.Drawing.drawmon(1, false, 4, 292, 82)
                    x = 292
                }
                if (frame > 90 && frame <= 117) {
                    x -= 4
                    dig.Drawing.drawmon(1, false, 4, x, 82)
                }
                if (frame > 117) dig.Drawing.drawmon(1, false, 0, 184, 82)
                if (frame == 123) dig.Drawing.outtext("HOBBIN", 216, 83, 2)
                if (frame == 130) {
                    dig.Sprite.movedrawspr(0, 292, 101)
                    dig.Drawing.drawdigger(4, 292, 101, true)
                    x = 292
                }
                if (frame > 130 && frame <= 157) {
                    x -= 4
                    dig.Drawing.drawdigger(4, x, 101, true)
                }
                if (frame > 157) dig.Drawing.drawdigger(0, 184, 101, true)
                if (frame == 163) dig.Drawing.outtext("DIGGER", 216, 102, 2)
                if (frame == 178) {
                    dig.Sprite.movedrawspr(1, 184, 120)
                    dig.Drawing.drawgold(1, 0, 184, 120)
                }
                if (frame == 183) dig.Drawing.outtext("GOLD", 216, 121, 2)
                if (frame == 198) dig.Drawing.drawemerald(184, 141)
                if (frame == 203) dig.Drawing.outtext("EMERALD", 216, 140, 2)
                if (frame == 218) dig.Drawing.drawbonus(184, 158)
                if (frame == 223) dig.Drawing.outtext("BONUS", 216, 159, 2)
                dig.newframe()
                frame++
                if (frame > 250) frame = 0
            }
            gamedat[0].level = 1
            gamedat[0].lives = 3
            if (nplayers == 2) {
                gamedat[1].level = 1
                gamedat[1].lives = 3
            } else gamedat[1].lives = 0
            dig.Pc.gclear()
            curplayer = 0
            initlevel()
            curplayer = 1
            initlevel()
            dig.Scores.zeroscores()
            dig.bonusvisible = true
            if (nplayers == 2) flashplayer = true
            curplayer = 0
            while ((gamedat[0].lives != 0 || gamedat[1].lives != 0) && !dig.Input.escape) {
                gamedat[curplayer].dead = false
                while (!gamedat[curplayer].dead && gamedat[curplayer].lives != 0 && !dig.Input.escape) {
                    dig.Drawing.initmbspr()
                    play()
                }
                if (gamedat[1 - curplayer].lives != 0) {
                    curplayer = 1 - curplayer
                    levnotdrawn = true
                    flashplayer = levnotdrawn
                }
            }
            dig.Input.escape = false
        } while (!false)
    }

    fun play() {
        var t: Int
        var c: Int
        if (levnotdrawn) {
            levnotdrawn = false
            drawscreen()
            dig.time = dig.Pc.gethrt()
            if (flashplayer) {
                flashplayer = false
                pldispbuf = "PLAYER "
                pldispbuf += if (curplayer == 0) "1" else "2"
                cleartopline()
                t = 0
                while (t < 15) {
                    c = 1
                    while (c <= 3) {
                        dig.Drawing.outtext(pldispbuf, 108, 0, c)
                        dig.Scores.writecurscore(c)
                        /* olddelay(20); */dig.newframe()
                        if (dig.Input.escape) return
                        c++
                    }
                    t++
                }
                dig.Scores.drawscores()
                dig.Scores.addscore(0)
            }
        } else initchars()
        dig.Input.keypressed = 0
        dig.Drawing.outtext("        ", 108, 0, 3)
        dig.Scores.initscores()
        dig.Drawing.drawlives()
        dig.Sound.music(1)
        dig.Input.readdir()
        dig.time = dig.Pc.gethrt()
        while (!gamedat[curplayer].dead && !gamedat[curplayer].levdone && !dig.Input.escape) {
            penalty = 0
            dig.dodigger()
            dig.Monster.domonsters()
            dig.Bags.dobags()
            if (penalty > 8) dig.Monster.incmont(penalty - 8)
            testpause()
            checklevdone()
        }
        dig.erasedigger()
        dig.Sound.musicoff()
        t = 20
        while ((dig.Bags.getnmovingbags() !== 0 || t != 0) && !dig.Input.escape) {
            if (t != 0) t--
            penalty = 0
            dig.Bags.dobags()
            dig.dodigger()
            dig.Monster.domonsters()
            if (penalty < 8) t = 0
        }
        dig.Sound.soundstop()
        dig.killfire()
        dig.erasebonus()
        dig.Bags.cleanupbags()
        dig.Drawing.savefield()
        dig.Monster.erasemonsters()
        dig.newframe()
        if (gamedat[curplayer].levdone) dig.Sound.soundlevdone()
        if (dig.countem() === 0) {
            gamedat[curplayer].level++
            if (gamedat[curplayer].level > 1000) gamedat[curplayer].level = 1000
            initlevel()
        }
        if (gamedat[curplayer].dead) {
            gamedat[curplayer].lives--
            dig.Drawing.drawlives()
            if (gamedat[curplayer].lives == 0 && !dig.Input.escape) dig.Scores.endofgame()
        }
        if (gamedat[curplayer].levdone) {
            gamedat[curplayer].level++
            if (gamedat[curplayer].level > 1000) gamedat[curplayer].level = 1000
            initlevel()
        }
    }

    fun randno(n: Int): Int {
        randv = randv * 0x15a4e35 + 1
        return ((randv and 0x7fffffff) % n).toInt()
    }

    fun setdead(bp6: Boolean) {
        gamedat[curplayer].dead = bp6
    }

    fun shownplayers() {
        if (nplayers == 1) {
            dig.Drawing.outtext("ONE", 220, 25, 3)
            dig.Drawing.outtext(" PLAYER ", 192, 39, 3)
        } else {
            dig.Drawing.outtext("TWO", 220, 25, 3)
            dig.Drawing.outtext(" PLAYERS", 184, 39, 3)
        }
    }

    fun switchnplayers() {
        nplayers = 3 - nplayers
    }

    fun testpause() {
        if (dig.Input.akeypressed === 32) { /* Space bar */
            dig.Input.akeypressed = 0
            dig.Sound.soundpause()
            dig.Sound.sett2val(40)
            dig.Sound.setsoundt2()
            cleartopline()
            dig.Drawing.outtext("PRESS ANY KEY", 80, 0, 1)
            dig.newframe()
            dig.Input.keypressed = 0
            while (true) {
                try {
                    Thread.sleep(50)
                } catch (e: Exception) {
                }
                if (dig.Input.keypressed !== 0) break
            }
            cleartopline()
            dig.Scores.drawscores()
            dig.Scores.addscore(0)
            dig.Drawing.drawlives()
            dig.newframe()
            dig.time = dig.Pc.gethrt() - dig.frametime
            dig.Input.keypressed = 0
        } else dig.Sound.soundpauseoff()
    }

    init {
        dig = d
    }
}