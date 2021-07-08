internal class Monster(d: Digger) {
    var dig: Digger
    var mondat = arrayOf(
        _monster(), _monster(), _monster(), _monster(), _monster(),
        _monster()
    ) // [6]
    var nextmonster = 0
    var totalmonsters = 0
    var maxmononscr = 0
    var nextmontime = 0
    var mongaptime = 0
    var unbonusflag = false
    var mongotgold = false
    fun checkcoincide(mon: Int, bits: Int) {
        var m: Int
        var b: Int
        m = 0
        b = 256
        while (m < 6) {
            if (bits and b != 0 && mondat[mon].dir == mondat[m].dir && mondat[m].stime == 0
                && mondat[mon].stime == 0
            ) mondat[m].dir = dig.reversedir(mondat[m].dir)
            m++
            b = b shl 1
        }
    }

    fun checkmonscared(h: Int) {
        var m: Int
        m = 0
        while (m < 6) {
            if (h == mondat[m].h && mondat[m].dir == 2) mondat[m].dir = 6
            m++
        }
    }

    fun createmonster() {
        var i: Int
        i = 0
        while (i < 6) {
            if (!mondat[i].flag) {
                mondat[i].flag = true
                mondat[i].alive = true
                mondat[i].t = 0
                mondat[i].nob = true
                mondat[i].hnt = 0
                mondat[i].h = 14
                mondat[i].v = 0
                mondat[i].x = 292
                mondat[i].y = 18
                mondat[i].xr = 0
                mondat[i].yr = 0
                mondat[i].dir = 4
                mondat[i].hdir = 4
                nextmonster++
                nextmontime = mongaptime
                mondat[i].stime = 5
                dig.Sprite.movedrawspr(i + 8, mondat[i].x, mondat[i].y)
                break
            }
            i++
        }
    }

    fun domonsters() {
        var i: Int
        if (nextmontime > 0) nextmontime-- else {
            if (nextmonster < totalmonsters && nmononscr() < maxmononscr && dig.digonscr && !dig.bonusmode) createmonster()
            if (unbonusflag && nextmonster == totalmonsters && nextmontime == 0) if (dig.digonscr) {
                unbonusflag = false
                dig.createbonus()
            }
        }
        i = 0
        while (i < 6) {
            if (mondat[i].flag) {
                if (mondat[i].hnt > 10 - dig.Main.levof10()) {
                    if (mondat[i].nob) {
                        mondat[i].nob = false
                        mondat[i].hnt = 0
                    }
                }
                if (mondat[i].alive) if (mondat[i].t == 0) {
                    monai(i)
                    if (dig.Main.randno(15 - dig.Main.levof10()) == 0 && mondat[i].nob) monai(i)
                } else mondat[i].t-- else mondie(i)
            }
            i++
        }
    }

    fun erasemonsters() {
        var i: Int
        i = 0
        while (i < 6) {
            if (mondat[i].flag) dig.Sprite.erasespr(i + 8)
            i++
        }
    }

    fun fieldclear(dir: Int, x: Int, y: Int): Boolean {
        when (dir) {
            0 -> if (x < 14) if (getfield(x + 1, y) and 0x2000 == 0) if (getfield(x + 1, y) and 1 == 0 || getfield(
                    x,
                    y
                ) and 0x10 == 0
            ) return true
            4 -> if (x > 0) if (getfield(x - 1, y) and 0x2000 == 0) if (getfield(x - 1, y) and 0x10 == 0 || getfield(
                    x,
                    y
                ) and 1 == 0
            ) return true
            2 -> if (y > 0) if (getfield(x, y - 1) and 0x2000 == 0) if (getfield(x, y - 1) and 0x800 == 0 || getfield(
                    x,
                    y
                ) and 0x40 == 0
            ) return true
            6 -> if (y < 9) if (getfield(x, y + 1) and 0x2000 == 0) if (getfield(x, y + 1) and 0x40 == 0 || getfield(
                    x,
                    y
                ) and 0x800 == 0
            ) return true
        }
        return false
    }

    fun getfield(x: Int, y: Int): Int {
        return dig.Drawing.field.get(y * 15 + x)
    }

    fun incmont(nRaw: Int) {
        var n = nRaw
        var m: Int
        if (n > 6) n = 6
        m = 1
        while (m < n) {
            mondat[m].t++
            m++
        }
    }

    fun incpenalties(bits: Int) {
        var m: Int
        var b: Int
        m = 0
        b = 256
        while (m < 6) {
            if (bits and b != 0) dig.Main.incpenalty()
            b = b shl 1
            m++
            b = b shl 1
        }
    }

    fun initmonsters() {
        var i: Int
        i = 0
        while (i < 6) {
            mondat[i].flag = false
            i++
        }
        nextmonster = 0
        mongaptime = 45 - (dig.Main.levof10() shl 1)
        totalmonsters = dig.Main.levof10() + 5
        when (dig.Main.levof10()) {
            1 -> maxmononscr = 3
            2, 3, 4, 5, 6, 7 -> maxmononscr = 4
            8, 9, 10 -> maxmononscr = 5
        }
        nextmontime = 10
        unbonusflag = true
    }

    fun killmon(mon: Int) {
        if (mondat[mon].flag) {
            mondat[mon].alive = false
            mondat[mon].flag = mondat[mon].alive
            dig.Sprite.erasespr(mon + 8)
            if (dig.bonusmode) totalmonsters++
        }
    }

    fun killmonsters(bits: Int): Int {
        var m: Int
        var b: Int
        var n = 0
        m = 0
        b = 256
        while (m < 6) {
            if (bits and b != 0) {
                killmon(m)
                n++
            }
            m++
            b = b shl 1
        }
        return n
    }

    fun monai(mon: Int) {
        val clbits: Int
        val monox: Int
        val monoy: Int
        var dir: Int
        var mdirp1: Int
        var mdirp2: Int
        var mdirp3: Int
        var mdirp4: Int
        var t: Int
        var push: Boolean
        monox = mondat[mon].x
        monoy = mondat[mon].y
        if (mondat[mon].xr == 0 && mondat[mon].yr == 0) {

            /* If we are here the monster needs to know which way to turn next. */

            /* Turn hobbin back into nobbin if it's had its time */
            if (mondat[mon].hnt > 30 + (dig.Main.levof10() shl 1)) if (!mondat[mon].nob) {
                mondat[mon].hnt = 0
                mondat[mon].nob = true
            }

            /* Set up monster direction properties to chase dig */if (Math.abs(dig.diggery - mondat[mon].y) > Math.abs(
                    dig.diggerx - mondat[mon].x
                )
            ) {
                if (dig.diggery < mondat[mon].y) {
                    mdirp1 = 2
                    mdirp4 = 6
                } else {
                    mdirp1 = 6
                    mdirp4 = 2
                }
                if (dig.diggerx < mondat[mon].x) {
                    mdirp2 = 4
                    mdirp3 = 0
                } else {
                    mdirp2 = 0
                    mdirp3 = 4
                }
            } else {
                if (dig.diggerx < mondat[mon].x) {
                    mdirp1 = 4
                    mdirp4 = 0
                } else {
                    mdirp1 = 0
                    mdirp4 = 4
                }
                if (dig.diggery < mondat[mon].y) {
                    mdirp2 = 2
                    mdirp3 = 6
                } else {
                    mdirp2 = 6
                    mdirp3 = 2
                }
            }

            /* In bonus mode, run away from digger */if (dig.bonusmode) {
                t = mdirp1
                mdirp1 = mdirp4
                mdirp4 = t
                t = mdirp2
                mdirp2 = mdirp3
                mdirp3 = t
            }

            /* Adjust priorities so that monsters don't reverse direction unless they really have to */
            dir = dig.reversedir(
                mondat[mon].dir
            )
            if (dir == mdirp1) {
                mdirp1 = mdirp2
                mdirp2 = mdirp3
                mdirp3 = mdirp4
                mdirp4 = dir
            }
            if (dir == mdirp2) {
                mdirp2 = mdirp3
                mdirp3 = mdirp4
                mdirp4 = dir
            }
            if (dir == mdirp3) {
                mdirp3 = mdirp4
                mdirp4 = dir
            }

            /* Introduce a randno element on levels <6 : occasionally swap p1 and p3 */
            if (dig.Main.randno(dig.Main.levof10() + 5) == 1 && dig.Main.levof10() < 6) {
                t = mdirp1
                mdirp1 = mdirp3
                mdirp3 = t
            }

            /* Check field and find direction */
            if (fieldclear(mdirp1, mondat[mon].h, mondat[mon].v)) dir =
                mdirp1 else if (fieldclear(mdirp2, mondat[mon].h, mondat[mon].v)) dir = mdirp2 else if (fieldclear(
                    mdirp3,
                    mondat[mon].h,
                    mondat[mon].v
                )
            ) dir = mdirp3 else if (fieldclear(mdirp4, mondat[mon].h, mondat[mon].v)) dir = mdirp4

            /* Hobbins don't care about the field: they go where they want. */
            if (!mondat[mon].nob) dir = mdirp1

            /* Monsters take a time penalty for changing direction */
            if (mondat[mon].dir != dir) mondat[mon].t++

            /* Save the new direction */
            mondat[mon].dir = dir
        }

        /* If monster is about to go off edge of screen, stop it. */
        if (mondat[mon].x == 292 && mondat[mon].dir == 0 || mondat[mon].x == 12 && mondat[mon].dir == 4
            || mondat[mon].y == 180 && mondat[mon].dir == 6 || mondat[mon].y == 18 && mondat[mon].dir == 2
        ) mondat[mon].dir = -1

        /* Change hdir for hobbin */
        if (mondat[mon].dir == 4 || mondat[mon].dir == 0) mondat[mon].hdir = mondat[mon].dir

        /* Hobbins digger */
        if (!mondat[mon].nob) dig.Drawing.eatfield(mondat[mon].x, mondat[mon].y, mondat[mon].dir)
        when (mondat[mon].dir) {
            0 -> {
                if (!mondat[mon].nob) dig.Drawing.drawrightblob(mondat[mon].x, mondat[mon].y)
                mondat[mon].x += 4
            }
            4 -> {
                if (!mondat[mon].nob) dig.Drawing.drawleftblob(mondat[mon].x, mondat[mon].y)
                mondat[mon].x -= 4
            }
            2 -> {
                if (!mondat[mon].nob) dig.Drawing.drawtopblob(mondat[mon].x, mondat[mon].y)
                mondat[mon].y -= 3
            }
            6 -> {
                if (!mondat[mon].nob) dig.Drawing.drawbottomblob(mondat[mon].x, mondat[mon].y)
                mondat[mon].y += 3
            }
        }

        /* Hobbins can eat emeralds */if (!mondat[mon].nob) dig.hitemerald(
            (mondat[mon].x - 12) / 20, (mondat[mon].y - 18) / 18, (mondat[mon].x - 12) % 20,
            (mondat[mon].y - 18) % 18, mondat[mon].dir
        )

        /* If digger's gone, don't bother */if (!dig.digonscr) {
            mondat[mon].x = monox
            mondat[mon].y = monoy
        }

        /* If monster's just started, don't move yet */if (mondat[mon].stime != 0) {
            mondat[mon].stime--
            mondat[mon].x = monox
            mondat[mon].y = monoy
        }

        /* Increase time counter for hobbin */
        if (!mondat[mon].nob && mondat[mon].hnt < 100) mondat[mon].hnt++

        /* Draw monster */
        push = true
        clbits = dig.Drawing.drawmon(mon, mondat[mon].nob, mondat[mon].hdir, mondat[mon].x, mondat[mon].y)
        dig.Main.incpenalty()

        /* Collision with another monster */if (clbits and 0x3f00 != 0) {
            mondat[mon].t++ /* Time penalty */
            checkcoincide(mon, clbits) /* Ensure both aren't moving in the same dir. */
            incpenalties(clbits)
        }

        /* Check for collision with bag */
        if (clbits and dig.Bags.bagbits() != 0) {
            mondat[mon].t++ /* Time penalty */
            mongotgold = false
            if (mondat[mon].dir == 4 || mondat[mon].dir == 0) { /* Horizontal push */
                push = dig.Bags.pushbags(mondat[mon].dir, clbits)
                mondat[mon].t++ /* Time penalty */
            } else if (!dig.Bags.pushudbags(clbits)) /* Vertical push */ push = false
            if (mongotgold) /* No time penalty if monster eats gold */ mondat[mon].t = 0
            if (!mondat[mon].nob && mondat[mon].hnt > 1) dig.Bags.removebags(clbits) /* Hobbins eat bags */
        }

        /* Increase hobbin cross counter */if (mondat[mon].nob && clbits and 0x3f00 != 0 && dig.digonscr) mondat[mon].hnt++

        /* See if bags push monster back */if (!push) {
            mondat[mon].x = monox
            mondat[mon].y = monoy
            dig.Drawing.drawmon(mon, mondat[mon].nob, mondat[mon].hdir, mondat[mon].x, mondat[mon].y)
            dig.Main.incpenalty()
            if (mondat[mon].nob) /* The other way to create hobbin: stuck on h-bag */ mondat[mon].hnt++
            if ((mondat[mon].dir == 2 || mondat[mon].dir == 6) && mondat[mon].nob) mondat[mon].dir = dig.reversedir(
                mondat[mon].dir
            ) /* If vertical, give up */
        }

        /* Collision with digger */if (clbits and 1 != 0 && dig.digonscr) if (dig.bonusmode) {
            killmon(mon)
            dig.Scores.scoreeatm()
            dig.Sound.soundeatm() /* Collision in bonus mode */
        } else dig.killdigger(3, 0) /* Kill digger */

        /* Update co-ordinates */mondat[mon].h = (mondat[mon].x - 12) / 20
        mondat[mon].v = (mondat[mon].y - 18) / 18
        mondat[mon].xr = (mondat[mon].x - 12) % 20
        mondat[mon].yr = (mondat[mon].y - 18) % 18
    }

    fun mondie(mon: Int) {
        when (mondat[mon].death) {
            1 -> {
                if (dig.Bags.bagy(mondat[mon].bag) + 6 > mondat[mon].y) mondat[mon].y = dig.Bags.bagy(mondat[mon].bag)
                dig.Drawing.drawmondie(mon, mondat[mon].nob, mondat[mon].hdir, mondat[mon].x, mondat[mon].y)
                dig.Main.incpenalty()
                if (dig.Bags.getbagdir(mondat[mon].bag) == -1) {
                    mondat[mon].dtime = 1
                    mondat[mon].death = 4
                }
            }
            4 -> if (mondat[mon].dtime != 0) mondat[mon].dtime-- else {
                killmon(mon)
                dig.Scores.scorekill()
            }
        }
    }

    fun mongold() {
        mongotgold = true
    }

    fun monleft(): Int {
        return nmononscr() + totalmonsters - nextmonster
    }

    fun nmononscr(): Int {
        var i: Int
        var n = 0
        i = 0
        while (i < 6) {
            if (mondat[i].flag) n++
            i++
        }
        return n
    }

    fun squashmonster(mon: Int, death: Int, bag: Int) {
        mondat[mon].alive = false
        mondat[mon].death = death
        mondat[mon].bag = bag
    }

    fun squashmonsters(bag: Int, bits: Int) {
        var m: Int
        var b: Int
        m = 0
        b = 256
        while (m < 6) {
            if (bits and b != 0) if (mondat[m].y >= dig.Bags.bagy(bag)) squashmonster(m, 1, bag)
            m++
            b = b shl 1
        }
    }

    init {
        dig = d
    }
}