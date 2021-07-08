internal class Drawing(d: Digger) {
    var dig: Digger
    var field1 = intArrayOf( // [150]
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
    )
    var field2 = intArrayOf( // [150]
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
    )
    var field = intArrayOf( // [150]
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
    )
    var diggerbuf = ShortArray(480)
    var bagbuf1 = ShortArray(480)
    var bagbuf2 = ShortArray(480)
    var bagbuf3 = ShortArray(480)
    var bagbuf4 = ShortArray(480)
    var bagbuf5 = ShortArray(480)
    var bagbuf6 = ShortArray(480)
    var bagbuf7 = ShortArray(480)
    var monbuf1 = ShortArray(480)
    var monbuf2 = ShortArray(480)
    var monbuf3 = ShortArray(480)
    var monbuf4 = ShortArray(480)
    var monbuf5 = ShortArray(480)
    var monbuf6 = ShortArray(480)
    var bonusbuf = ShortArray(480)
    var firebuf = ShortArray(128)
    var bitmasks = intArrayOf(
        0xfffe,
        0xfffd,
        0xfffb,
        0xfff7,
        0xffef,
        0xffdf,
        0xffbf,
        0xff7f,
        0xfeff,
        0xfdff,
        0xfbff,
        0xf7ff
    ) // [12]
    var monspr = intArrayOf(0, 0, 0, 0, 0, 0) // [6]
    var monspd = intArrayOf(0, 0, 0, 0, 0, 0) // [6]
    var digspr = 0
    var digspd = 0
    var firespr = 0
    var fireheight = 8
    fun createdbfspr() {
        digspd = 1
        digspr = 0
        firespr = 0
        dig.Sprite.createspr(0, 0, diggerbuf, 4, 15, 0, 0)
        dig.Sprite.createspr(14, 81, bonusbuf, 4, 15, 0, 0)
        dig.Sprite.createspr(15, 82, firebuf, 2, fireheight, 0, 0)
    }

    fun creatembspr() {
        var i: Int
        dig.Sprite.createspr(1, 62, bagbuf1, 4, 15, 0, 0)
        dig.Sprite.createspr(2, 62, bagbuf2, 4, 15, 0, 0)
        dig.Sprite.createspr(3, 62, bagbuf3, 4, 15, 0, 0)
        dig.Sprite.createspr(4, 62, bagbuf4, 4, 15, 0, 0)
        dig.Sprite.createspr(5, 62, bagbuf5, 4, 15, 0, 0)
        dig.Sprite.createspr(6, 62, bagbuf6, 4, 15, 0, 0)
        dig.Sprite.createspr(7, 62, bagbuf7, 4, 15, 0, 0)
        dig.Sprite.createspr(8, 71, monbuf1, 4, 15, 0, 0)
        dig.Sprite.createspr(9, 71, monbuf2, 4, 15, 0, 0)
        dig.Sprite.createspr(10, 71, monbuf3, 4, 15, 0, 0)
        dig.Sprite.createspr(11, 71, monbuf4, 4, 15, 0, 0)
        dig.Sprite.createspr(12, 71, monbuf5, 4, 15, 0, 0)
        dig.Sprite.createspr(13, 71, monbuf6, 4, 15, 0, 0)
        createdbfspr()
        i = 0
        while (i < 6) {
            monspr[i] = 0
            monspd[i] = 1
            i++
        }
    }

    fun drawbackg(l: Int) {
        var x: Int
        var y: Int
        y = 14
        while (y < 200) {
            x = 0
            while (x < 320) {
                dig.Sprite.drawmiscspr(x, y, 93 + l, 5, 4)
                x += 20
            }
            y += 4
        }
    }

    fun drawbonus(x: Int, y: Int) {
        dig.Sprite.initspr(14, 81, 4, 15, 0, 0)
        dig.Sprite.movedrawspr(14, x, y)
    }

    fun drawbottomblob(x: Int, y: Int) {
        dig.Sprite.initmiscspr(x - 4, y + 15, 6, 6)
        dig.Sprite.drawmiscspr(x - 4, y + 15, 105, 6, 6)
        dig.Sprite.getis()
    }

    fun drawdigger(t: Int, x: Int, y: Int, f: Boolean): Int {
        digspr += digspd
        if (digspr == 2 || digspr == 0) digspd = -digspd
        if (digspr > 2) digspr = 2
        if (digspr < 0) digspr = 0
        if (t >= 0 && t <= 6 && t and 1 == 0) {
            dig.Sprite.initspr(0, (t + if (f) 0 else 1) * 3 + digspr + 1, 4, 15, 0, 0)
            return dig.Sprite.drawspr(0, x, y)
        }
        if (t >= 10 && t <= 15) {
            dig.Sprite.initspr(0, 40 - t, 4, 15, 0, 0)
            return dig.Sprite.drawspr(0, x, y)
        }
        return 0
    }

    fun drawemerald(x: Int, y: Int) {
        dig.Sprite.initmiscspr(x, y, 4, 10)
        dig.Sprite.drawmiscspr(x, y, 108, 4, 10)
        dig.Sprite.getis()
    }

    fun drawfield() {
        var x: Int
        var y: Int
        var xp: Int
        var yp: Int
        x = 0
        while (x < 15) {
            y = 0
            while (y < 10) {
                if (field[y * 15 + x] and 0x2000 == 0) {
                    xp = x * 20 + 12
                    yp = y * 18 + 18
                    if (field[y * 15 + x] and 0xfc0 != 0xfc0) {
                        field[y * 15 + x] = field[y * 15 + x] and 0xd03f
                        drawbottomblob(xp, yp - 15)
                        drawbottomblob(xp, yp - 12)
                        drawbottomblob(xp, yp - 9)
                        drawbottomblob(xp, yp - 6)
                        drawbottomblob(xp, yp - 3)
                        drawtopblob(xp, yp + 3)
                    }
                    if (field[y * 15 + x] and 0x1f != 0x1f) {
                        field[y * 15 + x] = field[y * 15 + x] and 0xdfe0
                        drawrightblob(xp - 16, yp)
                        drawrightblob(xp - 12, yp)
                        drawrightblob(xp - 8, yp)
                        drawrightblob(xp - 4, yp)
                        drawleftblob(xp + 4, yp)
                    }
                    if (x < 14) if (field[y * 15 + x + 1] and 0xfdf != 0xfdf) drawrightblob(xp, yp)
                    if (y < 9) if (field[(y + 1) * 15 + x] and 0xfdf != 0xfdf) drawbottomblob(xp, yp)
                }
                y++
            }
            x++
        }
    }

    fun drawfire(x: Int, y: Int, t: Int): Int {
        if (t == 0) {
            firespr++
            if (firespr > 2) firespr = 0
            dig.Sprite.initspr(15, 82 + firespr, 2, fireheight, 0, 0)
        } else dig.Sprite.initspr(15, 84 + t, 2, fireheight, 0, 0)
        return dig.Sprite.drawspr(15, x, y)
    }

    fun drawfurryblob(x: Int, y: Int) {
        dig.Sprite.initmiscspr(x - 4, y + 15, 6, 8)
        dig.Sprite.drawmiscspr(x - 4, y + 15, 107, 6, 8)
        dig.Sprite.getis()
    }

    fun drawgold(n: Int, t: Int, x: Int, y: Int): Int {
        dig.Sprite.initspr(n, t + 62, 4, 15, 0, 0)
        return dig.Sprite.drawspr(n, x, y)
    }

    fun drawleftblob(x: Int, y: Int) {
        dig.Sprite.initmiscspr(x - 8, y - 1, 2, 18)
        dig.Sprite.drawmiscspr(x - 8, y - 1, 104, 2, 18)
        dig.Sprite.getis()
    }

    fun drawlife(t: Int, x: Int, y: Int) {
        dig.Sprite.drawmiscspr(x, y, t + 110, 4, 12)
    }

    fun drawlives() {
        var l: Int
        var n: Int
        n = dig.Main.getlives(1) - 1
        l = 1
        while (l < 5) {
            drawlife(if (n > 0) 0 else 2, l * 20 + 60, 0)
            n--
            l++
        }
        if (dig.Main.nplayers === 2) {
            n = dig.Main.getlives(2) - 1
            l = 1
            while (l < 5) {
                drawlife(if (n > 0) 1 else 2, 244 - l * 20, 0)
                n--
                l++
            }
        }
    }

    fun drawmon(n: Int, nobf: Boolean, dir: Int, x: Int, y: Int): Int {
        monspr[n] += monspd[n]
        if (monspr[n] == 2 || monspr[n] == 0) monspd[n] = -monspd[n]
        if (monspr[n] > 2) monspr[n] = 2
        if (monspr[n] < 0) monspr[n] = 0
        if (nobf) dig.Sprite.initspr(n + 8, monspr[n] + 69, 4, 15, 0, 0) else when (dir) {
            0 -> dig.Sprite.initspr(n + 8, monspr[n] + 73, 4, 15, 0, 0)
            4 -> dig.Sprite.initspr(n + 8, monspr[n] + 77, 4, 15, 0, 0)
        }
        return dig.Sprite.drawspr(n + 8, x, y)
    }

    fun drawmondie(n: Int, nobf: Boolean, dir: Int, x: Int, y: Int): Int {
        if (nobf) dig.Sprite.initspr(n + 8, 72, 4, 15, 0, 0) else when (dir) {
            0 -> dig.Sprite.initspr(n + 8, 76, 4, 15, 0, 0)
            4 -> dig.Sprite.initspr(n + 8, 80, 4, 14, 0, 0)
        }
        return dig.Sprite.drawspr(n + 8, x, y)
    }

    fun drawrightblob(x: Int, y: Int) {
        dig.Sprite.initmiscspr(x + 16, y - 1, 2, 18)
        dig.Sprite.drawmiscspr(x + 16, y - 1, 102, 2, 18)
        dig.Sprite.getis()
    }

    fun drawsquareblob(x: Int, y: Int) {
        dig.Sprite.initmiscspr(x - 4, y + 17, 6, 6)
        dig.Sprite.drawmiscspr(x - 4, y + 17, 106, 6, 6)
        dig.Sprite.getis()
    }

    fun drawstatics() {
        var x: Int
        var y: Int
        x = 0
        while (x < 15) {
            y = 0
            while (y < 10) {
                if (dig.Main.getcplayer() === 0) field[y * 15 + x] = field1[y * 15 + x] else field[y * 15 + x] =
                    field2[y * 15 + x]
                y++
            }
            x++
        }
        dig.Sprite.setretr(true)
        dig.Pc.gpal(0)
        dig.Pc.ginten(0)
        drawbackg(dig.Main.levplan())
        drawfield()
        dig.Pc.currentSource?.newPixels(0, 0, dig.Pc.width, dig.Pc.height)
    }

    fun drawtopblob(x: Int, y: Int) {
        dig.Sprite.initmiscspr(x - 4, y - 6, 6, 6)
        dig.Sprite.drawmiscspr(x - 4, y - 6, 103, 6, 6)
        dig.Sprite.getis()
    }

    fun eatfield(x: Int, y: Int, dir: Int) {
        var h = (x - 12) / 20
        var xr = (x - 12) % 20 / 4
        var v = (y - 18) / 18
        var yr = (y - 18) % 18 / 3
        dig.Main.incpenalty()
        when (dir) {
            0 -> {
                h++
                field[v * 15 + h] = field[v * 15 + h] and bitmasks[xr]
                if (field[v * 15 + h] and 0x1f != 0) return // break // TODO
                field[v * 15 + h] = field[v * 15 + h] and 0xdfff
            }
            4 -> {
                xr--
                if (xr < 0) {
                    xr += 5
                    h--
                }
                field[v * 15 + h] = field[v * 15 + h] and bitmasks[xr]
                if (field[v * 15 + h] and 0x1f != 0) return // break // TODO
                field[v * 15 + h] = field[v * 15 + h] and 0xdfff
            }
            2 -> {
                yr--
                if (yr < 0) {
                    yr += 6
                    v--
                }
                field[v * 15 + h] = field[v * 15 + h] and bitmasks[6 + yr]
                if (field[v * 15 + h] and 0xfc0 != 0) return // break // TODO
                field[v * 15 + h] = field[v * 15 + h] and 0xdfff
            }
            6 -> {
                v++
                field[v * 15 + h] = field[v * 15 + h] and bitmasks[6 + yr]
                if (field[v * 15 + h] and 0xfc0 != 0) return // break // TODO
                field[v * 15 + h] = field[v * 15 + h] and 0xdfff
            }
        }
    }

    fun eraseemerald(x: Int, y: Int) {
        dig.Sprite.initmiscspr(x, y, 4, 10)
        dig.Sprite.drawmiscspr(x, y, 109, 4, 10)
        dig.Sprite.getis()
    }

    fun initdbfspr() {
        digspd = 1
        digspr = 0
        firespr = 0
        dig.Sprite.initspr(0, 0, 4, 15, 0, 0)
        dig.Sprite.initspr(14, 81, 4, 15, 0, 0)
        dig.Sprite.initspr(15, 82, 2, fireheight, 0, 0)
    }

    fun initmbspr() {
        dig.Sprite.initspr(1, 62, 4, 15, 0, 0)
        dig.Sprite.initspr(2, 62, 4, 15, 0, 0)
        dig.Sprite.initspr(3, 62, 4, 15, 0, 0)
        dig.Sprite.initspr(4, 62, 4, 15, 0, 0)
        dig.Sprite.initspr(5, 62, 4, 15, 0, 0)
        dig.Sprite.initspr(6, 62, 4, 15, 0, 0)
        dig.Sprite.initspr(7, 62, 4, 15, 0, 0)
        dig.Sprite.initspr(8, 71, 4, 15, 0, 0)
        dig.Sprite.initspr(9, 71, 4, 15, 0, 0)
        dig.Sprite.initspr(10, 71, 4, 15, 0, 0)
        dig.Sprite.initspr(11, 71, 4, 15, 0, 0)
        dig.Sprite.initspr(12, 71, 4, 15, 0, 0)
        dig.Sprite.initspr(13, 71, 4, 15, 0, 0)
        initdbfspr()
    }

    fun makefield() {
        var c: Int
        var x: Int
        var y: Int
        x = 0
        while (x < 15) {
            y = 0
            while (y < 10) {
                field[y * 15 + x] = -1
                c = dig.Main.getlevch(x, y, dig.Main.levplan())
                if (c == 'S'.toInt() || c == 'V'.toInt()) field[y * 15 + x] = field[y * 15 + x] and 0xd03f
                if (c == 'S'.toInt() || c == 'H'.toInt()) field[y * 15 + x] = field[y * 15 + x] and 0xdfe0
                if (dig.Main.getcplayer() === 0) field1[y * 15 + x] = field[y * 15 + x] else field2[y * 15 + x] =
                    field[y * 15 + x]
                y++
            }
            x++
        }
    }

    @JvmOverloads
    fun outtext(p: String, x: Int, y: Int, c: Int, b: Boolean = false) {
        var x = x
        var i: Int
        val rx = x
        i = 0
        while (i < p.length) {
            dig.Pc.gwrite(x, y, p[i].toInt(), c)
            x += 12
            i++
        }
        if (b) dig.Pc.currentSource?.newPixels(rx, y, p.length * 12, 12)
    }

    fun savefield() {
        var x: Int
        var y: Int
        x = 0
        while (x < 15) {
            y = 0
            while (y < 10) {
                if (dig.Main.getcplayer() === 0) field1[y * 15 + x] = field[y * 15 + x] else field2[y * 15 + x] =
                    field[y * 15 + x]
                y++
            }
            x++
        }
    }

    init {
        dig = d
    }
}