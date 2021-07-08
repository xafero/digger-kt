internal class Bags(var dig: Digger) {
    var bagdat1 = arrayOf(_bag(), _bag(), _bag(), _bag(), _bag(), _bag(), _bag(), _bag())
    var bagdat2 = arrayOf(
        _bag(), _bag(), _bag(), _bag(), _bag(), _bag(), _bag(),
        _bag()
    )
    var bagdat = arrayOf(_bag(), _bag(), _bag(), _bag(), _bag(), _bag(), _bag(), _bag())
    var pushcount = 0
    var goldtime = 0
    var wblanim = intArrayOf(2, 0, 1, 0) // [4]
    fun bagbits(): Int {
        var bag: Int
        var b: Int
        var bags = 0
        bag = 1
        b = 2
        while (bag < 8) {
            if (bagdat[bag].exist) bags = bags or b
            bag++
            b = b shl 1
        }
        return bags
    }

    fun baghitground(bag: Int) {
        var bn: Int
        var b: Int
        val clbits: Int
        if (bagdat[bag].dir == 6 && bagdat[bag].fallh > 1) bagdat[bag].gt = 1 else bagdat[bag].fallh = 0
        bagdat[bag].dir = -1
        bagdat[bag].wt = 15
        bagdat[bag].wobbling = false
        clbits = dig.Drawing.drawgold(bag, 0, bagdat[bag].x, bagdat[bag].y)
        dig.Main.incpenalty()
        bn = 1
        b = 2
        while (bn < 8) {
            if (b and clbits != 0) removebag(bn)
            bn++
            b = b shl 1
        }
    }

    fun bagy(bag: Int): Int {
        return bagdat[bag].y
    }

    fun cleanupbags() {
        var bpa: Int
        dig.Sound.soundfalloff()
        bpa = 1
        while (bpa < 8) {
            if (bagdat[bpa].exist && (bagdat[bpa].h == 7 && bagdat[bpa].v == 9 || bagdat[bpa].xr != 0 || bagdat[bpa].yr != 0 || bagdat[bpa].gt != 0 || bagdat[bpa].fallh != 0 || bagdat[bpa].wobbling)) {
                bagdat[bpa].exist = false
                dig.Sprite.erasespr(bpa)
            }
            if (dig.Main.getcplayer() == 0) bagdat1[bpa].copyFrom(bagdat[bpa]) else bagdat2[bpa].copyFrom(bagdat[bpa])
            bpa++
        }
    }

    fun dobags() {
        var bag: Int
        var soundfalloffflag = true
        var soundwobbleoffflag = true
        bag = 1
        while (bag < 8) {
            if (bagdat[bag].exist) {
                if (bagdat[bag].gt != 0) {
                    if (bagdat[bag].gt == 1) {
                        dig.Sound.soundbreak()
                        dig.Drawing.drawgold(bag, 4, bagdat[bag].x, bagdat[bag].y)
                        dig.Main.incpenalty()
                    }
                    if (bagdat[bag].gt == 3) {
                        dig.Drawing.drawgold(bag, 5, bagdat[bag].x, bagdat[bag].y)
                        dig.Main.incpenalty()
                    }
                    if (bagdat[bag].gt == 5) {
                        dig.Drawing.drawgold(bag, 6, bagdat[bag].x, bagdat[bag].y)
                        dig.Main.incpenalty()
                    }
                    bagdat[bag].gt++
                    if (bagdat[bag].gt == goldtime) removebag(bag) else if (bagdat[bag].v < 9 && bagdat[bag].gt < goldtime - 10) if (dig.Monster.getfield(
                            bagdat[bag].h, bagdat[bag].v + 1
                        ) and 0x2000 == 0
                    ) bagdat[bag].gt = goldtime - 10
                } else updatebag(bag)
            }
            bag++
        }
        bag = 1
        while (bag < 8) {
            if (bagdat[bag].dir == 6 && bagdat[bag].exist) soundfalloffflag = false
            if (bagdat[bag].dir != 6 && bagdat[bag].wobbling && bagdat[bag].exist) soundwobbleoffflag = false
            bag++
        }
        if (soundfalloffflag) dig.Sound.soundfalloff()
        if (soundwobbleoffflag) dig.Sound.soundwobbleoff()
    }

    fun drawbags() {
        var bag: Int
        bag = 1
        while (bag < 8) {
            if (dig.Main.getcplayer() == 0) bagdat[bag].copyFrom(bagdat1[bag]) else bagdat[bag].copyFrom(bagdat2[bag])
            if (bagdat[bag].exist) dig.Sprite.movedrawspr(bag, bagdat[bag].x, bagdat[bag].y)
            bag++
        }
    }

    fun getbagdir(bag: Int): Int {
        return if (bagdat[bag].exist) bagdat[bag].dir else -1
    }

    fun getgold(bag: Int) {
        val clbits: Int
        clbits = dig.Drawing.drawgold(bag, 6, bagdat[bag].x, bagdat[bag].y)
        dig.Main.incpenalty()
        if (clbits and 1 != 0) {
            dig.Scores.scoregold()
            dig.Sound.soundgold()
            dig.digtime = 0
        } else dig.Monster.mongold()
        removebag(bag)
    }

    fun getnmovingbags(): Int {
        var bag: Int
        var n = 0
        bag = 1
        while (bag < 8) {
            if (bagdat[bag].exist && bagdat[bag].gt < 10 && (bagdat[bag].gt != 0 || bagdat[bag].wobbling)) n++
            bag++
        }
        return n
    }

    fun initbags() {
        var bag: Int
        var x: Int
        var y: Int
        pushcount = 0
        goldtime = 150 - dig.Main.levof10() * 10
        bag = 1
        while (bag < 8) {
            bagdat[bag].exist = false
            bag++
        }
        bag = 1
        x = 0
        while (x < 15) {
            y = 0
            while (y < 10) {
                if (dig.Main.getlevch(x, y, dig.Main.levplan()) == 'B'.toInt()) if (bag < 8) {
                    bagdat[bag].exist = true
                    bagdat[bag].gt = 0
                    bagdat[bag].fallh = 0
                    bagdat[bag].dir = -1
                    bagdat[bag].wobbling = false
                    bagdat[bag].wt = 15
                    bagdat[bag].unfallen = true
                    bagdat[bag].x = x * 20 + 12
                    bagdat[bag].y = y * 18 + 18
                    bagdat[bag].h = x
                    bagdat[bag].v = y
                    bagdat[bag].xr = 0
                    bagdat[bag++].yr = 0
                }
                y++
            }
            x++
        }
        if (dig.Main.getcplayer() == 0) for (i in 1..7) bagdat1[i].copyFrom(bagdat[i]) else for (i in 1..7) bagdat2[i].copyFrom(
            bagdat[i]
        )
    }

    fun pushbag(bag: Int, dir: Int): Boolean {
        var x: Int
        var y: Int
        val h: Int
        val v: Int
        val ox: Int
        val oy: Int
        val clbits: Int
        var push = true
        x = bagdat[bag].x
        ox = x
        y = bagdat[bag].y
        oy = y
        h = bagdat[bag].h
        v = bagdat[bag].v
        if (bagdat[bag].gt != 0) {
            getgold(bag)
            return true
        }
        if (bagdat[bag].dir == 6 && (dir == 4 || dir == 0)) {
            clbits = dig.Drawing.drawgold(bag, 3, x, y)
            dig.Main.incpenalty()
            if (clbits and 1 != 0 && dig.diggery >= y) dig.killdigger(1, bag)
            if (clbits and 0x3f00 != 0) dig.Monster.squashmonsters(bag, clbits)
            return true
        }
        if (x == 292 && dir == 0 || x == 12 && dir == 4 || y == 180 && dir == 6 || y == 18 && dir == 2) push = false
        if (push) {
            when (dir) {
                0 -> x += 4
                4 -> x -= 4
                6 -> {
                    if (bagdat[bag].unfallen) {
                        bagdat[bag].unfallen = false
                        dig.Drawing.drawsquareblob(x, y)
                        dig.Drawing.drawtopblob(x, y + 21)
                    } else dig.Drawing.drawfurryblob(x, y)
                    dig.Drawing.eatfield(x, y, dir)
                    dig.killemerald(h, v)
                    y += 6
                }
            }
            when (dir) {
                6 -> {
                    clbits = dig.Drawing.drawgold(bag, 3, x, y)
                    dig.Main.incpenalty()
                    if (clbits and 1 != 0 && dig.diggery >= y) dig.killdigger(1, bag)
                    if (clbits and 0x3f00 != 0) dig.Monster.squashmonsters(bag, clbits)
                }
                0, 4 -> {
                    bagdat[bag].wt = 15
                    bagdat[bag].wobbling = false
                    clbits = dig.Drawing.drawgold(bag, 0, x, y)
                    dig.Main.incpenalty()
                    pushcount = 1
                    if (clbits and 0xfe != 0) if (!pushbags(dir, clbits)) {
                        x = ox
                        y = oy
                        dig.Drawing.drawgold(bag, 0, ox, oy)
                        dig.Main.incpenalty()
                        push = false
                    }
                    if (clbits and 1 != 0 || clbits and 0x3f00 != 0) {
                        x = ox
                        y = oy
                        dig.Drawing.drawgold(bag, 0, ox, oy)
                        dig.Main.incpenalty()
                        push = false
                    }
                }
            }
            if (push) bagdat[bag].dir = dir else bagdat[bag].dir = dig.reversedir(dir)
            bagdat[bag].x = x
            bagdat[bag].y = y
            bagdat[bag].h = (x - 12) / 20
            bagdat[bag].v = (y - 18) / 18
            bagdat[bag].xr = (x - 12) % 20
            bagdat[bag].yr = (y - 18) % 18
        }
        return push
    }

    fun pushbags(dir: Int, bits: Int): Boolean {
        var bag: Int
        var bit: Int
        var push = true
        bag = 1
        bit = 2
        while (bag < 8) {
            if (bits and bit != 0) if (!pushbag(bag, dir)) push = false
            bag++
            bit = bit shl 1
        }
        return push
    }

    fun pushudbags(bits: Int): Boolean {
        var bag: Int
        var b: Int
        var push = true
        bag = 1
        b = 2
        while (bag < 8) {
            if (bits and b != 0) if (bagdat[bag].gt != 0) getgold(bag) else push = false
            bag++
            b = b shl 1
        }
        return push
    }

    fun removebag(bag: Int) {
        if (bagdat[bag].exist) {
            bagdat[bag].exist = false
            dig.Sprite.erasespr(bag)
        }
    }

    fun removebags(bits: Int) {
        var bag: Int
        var b: Int
        bag = 1
        b = 2
        while (bag < 8) {
            if (bagdat[bag].exist && bits and b != 0) removebag(bag)
            bag++
            b = b shl 1
        }
    }

    fun updatebag(bag: Int) {
        val x: Int
        val h: Int
        val xr: Int
        val y: Int
        val v: Int
        val yr: Int
        val wbl: Int
        x = bagdat[bag].x
        h = bagdat[bag].h
        xr = bagdat[bag].xr
        y = bagdat[bag].y
        v = bagdat[bag].v
        yr = bagdat[bag].yr
        when (bagdat[bag].dir) {
            -1 -> if (y < 180 && xr == 0) {
                if (bagdat[bag].wobbling) {
                    if (bagdat[bag].wt == 0) {
                        bagdat[bag].dir = 6
                        dig.Sound.soundfall()
                        return // break TODO
                    }
                    bagdat[bag].wt--
                    wbl = bagdat[bag].wt % 8
                    if (wbl and 1 == 0) {
                        dig.Drawing.drawgold(bag, wblanim[wbl shr 1], x, y)
                        dig.Main.incpenalty()
                        dig.Sound.soundwobble()
                    }
                } else if (dig.Monster.getfield(h, v + 1) and 0xfdf != 0xfdf) if (!dig.checkdiggerunderbag(
                        h,
                        v + 1
                    )
                ) bagdat[bag].wobbling = true
            } else {
                bagdat[bag].wt = 15
                bagdat[bag].wobbling = false
            }
            0, 4 -> if (xr == 0) if (y < 180 && dig.Monster.getfield(h, v + 1) and 0xfdf != 0xfdf) {
                bagdat[bag].dir = 6
                bagdat[bag].wt = 0
                dig.Sound.soundfall()
            } else baghitground(bag)
            6 -> {
                if (yr == 0) bagdat[bag].fallh++
                if (y >= 180) baghitground(bag) else if (dig.Monster.getfield(
                        h,
                        v + 1
                    ) and 0xfdf == 0xfdf
                ) if (yr == 0) baghitground(bag)
                dig.Monster.checkmonscared(bagdat[bag].h)
            }
        }
        if (bagdat[bag].dir != -1) if (bagdat[bag].dir != 6 && pushcount != 0) pushcount-- else pushbag(
            bag,
            bagdat[bag].dir
        )
    }
}