internal class Sprite(d: Digger) {
    var dig: Digger
    var retrflag = true
    var sprrdrwf = booleanArrayOf(
        false, false, false, false, false, false, false, false, false, false, false, false, false,
        false, false, false, false
    ) // [17]
    var sprrecf = booleanArrayOf(
        false, false, false, false, false, false, false, false, false, false, false, false, false,
        false, false, false, false
    ) // [17]
    var sprenf = booleanArrayOf(
        false, false, false, false, false, false, false, false, false, false, false, false, false, false,
        false, false
    ) // [16]
    var sprch = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [17]
    var sprmov = arrayOf<ShortArray?>(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    ) // [16]
    var sprx = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [17]
    var spry = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [17]
    var sprwid = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [17]
    var sprhei = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [17]
    var sprbwid = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [16]
    var sprbhei = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [16]
    var sprnch = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [16]
    var sprnwid = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [16]
    var sprnhei = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [16]
    var sprnbwid = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [16]
    var sprnbhei = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // [16]
    var defsprorder = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15) // [16]
    var sprorder = defsprorder
    fun bcollide(bx: Int, si: Int): Boolean {
        if (sprx[bx] >= sprx[si]) {
            if (sprx[bx] + sprbwid[bx] > sprwid[si] * 4 + sprx[si] - sprbwid[si] - 1) return false
        } else if (sprx[si] + sprbwid[si] > sprwid[bx] * 4 + sprx[bx] - sprbwid[bx] - 1) return false
        if (spry[bx] >= spry[si]) {
            return if (spry[bx] + sprbhei[bx] <= sprhei[si] + spry[si] - sprbhei[si] - 1) true else false
        }
        return if (spry[si] + sprbhei[si] <= sprhei[bx] + spry[bx] - sprbhei[bx] - 1) true else false
    }

    fun bcollides(bx: Int): Int {
        var bx = bx
        val si = bx
        var ax = 0
        var dx = 0
        bx = 0
        do {
            if (sprenf[bx] && bx != si) {
                if (bcollide(bx, si)) ax = ax or (1 shl dx)
                sprx[bx] += 320
                spry[bx] -= 2
                if (bcollide(bx, si)) ax = ax or (1 shl dx)
                sprx[bx] -= 640
                spry[bx] += 4
                if (bcollide(bx, si)) ax = ax or (1 shl dx)
                sprx[bx] += 320
                spry[bx] -= 2
            }
            bx++
            dx++
        } while (dx != 16)
        return ax
    }

    fun clearrdrwf() {
        var i: Int
        clearrecf()
        i = 0
        while (i < 17) {
            sprrdrwf[i] = false
            i++
        }
    }

    fun clearrecf() {
        var i: Int
        i = 0
        while (i < 17) {
            sprrecf[i] = false
            i++
        }
    }

    fun collide(bx: Int, si: Int): Boolean {
        if (sprx[bx] >= sprx[si]) {
            if (sprx[bx] > sprwid[si] * 4 + sprx[si] - 1) return false
        } else if (sprx[si] > sprwid[bx] * 4 + sprx[bx] - 1) return false
        if (spry[bx] >= spry[si]) {
            return if (spry[bx] <= sprhei[si] + spry[si] - 1) true else false
        }
        return if (spry[si] <= sprhei[bx] + spry[bx] - 1) true else false
    }

    fun createspr(n: Int, ch: Int, mov: ShortArray?, wid: Int, hei: Int, bwid: Int, bhei: Int) {
        sprch[n and 15] = ch
        sprnch[n and 15] = sprch[n and 15]
        sprmov[n and 15] = mov
        sprwid[n and 15] = wid
        sprnwid[n and 15] = sprwid[n and 15]
        sprhei[n and 15] = hei
        sprnhei[n and 15] = sprhei[n and 15]
        sprbwid[n and 15] = bwid
        sprnbwid[n and 15] = sprbwid[n and 15]
        sprbhei[n and 15] = bhei
        sprnbhei[n and 15] = sprbhei[n and 15]
        sprenf[n and 15] = false
    }

    fun drawmiscspr(x: Int, y: Int, ch: Int, wid: Int, hei: Int) {
        sprx[16] = x and -4
        spry[16] = y
        sprch[16] = ch
        sprwid[16] = wid
        sprhei[16] = hei
        dig.Pc.gputim(sprx[16], spry[16], sprch[16], sprwid[16], sprhei[16])
    }

    fun drawspr(n: Int, x: Int, y: Int): Int {
        var x = x
        val bx: Int
        val t1: Int
        val t2: Int
        val t3: Int
        val t4: Int
        bx = n and 15
        x = x and -4
        clearrdrwf()
        setrdrwflgs(bx)
        t1 = sprx[bx]
        t2 = spry[bx]
        t3 = sprwid[bx]
        t4 = sprhei[bx]
        sprx[bx] = x
        spry[bx] = y
        sprwid[bx] = sprnwid[bx]
        sprhei[bx] = sprnhei[bx]
        clearrecf()
        setrdrwflgs(bx)
        sprhei[bx] = t4
        sprwid[bx] = t3
        spry[bx] = t2
        sprx[bx] = t1
        sprrdrwf[bx] = true
        putis()
        sprx[bx] = x
        spry[bx] = y
        sprch[bx] = sprnch[bx]
        sprwid[bx] = sprnwid[bx]
        sprhei[bx] = sprnhei[bx]
        sprbwid[bx] = sprnbwid[bx]
        sprbhei[bx] = sprnbhei[bx]
        dig.Pc.ggeti(sprx[bx], spry[bx], sprmov[bx], sprwid[bx], sprhei[bx])
        putims()
        return bcollides(bx)
    }

    fun erasespr(n: Int) {
        val bx = n and 15
        dig.Pc.gputi(sprx[bx], spry[bx], sprmov[bx], sprwid[bx], sprhei[bx], true)
        sprenf[bx] = false
        clearrdrwf()
        setrdrwflgs(bx)
        putims()
    }

    fun getis() {
        var i: Int
        i = 0
        while (i < 16) {
            if (sprrdrwf[i]) dig.Pc.ggeti(sprx[i], spry[i], sprmov[i], sprwid[i], sprhei[i])
            i++
        }
        putims()
    }

    fun initmiscspr(x: Int, y: Int, wid: Int, hei: Int) {
        sprx[16] = x
        spry[16] = y
        sprwid[16] = wid
        sprhei[16] = hei
        clearrdrwf()
        setrdrwflgs(16)
        putis()
    }

    fun initspr(n: Int, ch: Int, wid: Int, hei: Int, bwid: Int, bhei: Int) {
        sprnch[n and 15] = ch
        sprnwid[n and 15] = wid
        sprnhei[n and 15] = hei
        sprnbwid[n and 15] = bwid
        sprnbhei[n and 15] = bhei
    }

    fun movedrawspr(n: Int, x: Int, y: Int): Int {
        val bx = n and 15
        sprx[bx] = x and -4
        spry[bx] = y
        sprch[bx] = sprnch[bx]
        sprwid[bx] = sprnwid[bx]
        sprhei[bx] = sprnhei[bx]
        sprbwid[bx] = sprnbwid[bx]
        sprbhei[bx] = sprnbhei[bx]
        clearrdrwf()
        setrdrwflgs(bx)
        putis()
        dig.Pc.ggeti(sprx[bx], spry[bx], sprmov[bx], sprwid[bx], sprhei[bx])
        sprenf[bx] = true
        sprrdrwf[bx] = true
        putims()
        return bcollides(bx)
    }

    fun putims() {
        var i: Int
        var j: Int
        i = 0
        while (i < 16) {
            j = sprorder[i]
            if (sprrdrwf[j]) dig.Pc.gputim(sprx[j], spry[j], sprch[j], sprwid[j], sprhei[j])
            i++
        }
    }

    fun putis() {
        var i: Int
        i = 0
        while (i < 16) {
            if (sprrdrwf[i]) dig.Pc.gputi(sprx[i], spry[i], sprmov[i], sprwid[i], sprhei[i])
            i++
        }
    }

    fun setrdrwflgs(n: Int) {
        var i: Int
        if (!sprrecf[n]) {
            sprrecf[n] = true
            i = 0
            while (i < 16) {
                if (sprenf[i] && i != n) {
                    if (collide(i, n)) {
                        sprrdrwf[i] = true
                        setrdrwflgs(i)
                    }
                    sprx[i] += 320
                    spry[i] -= 2
                    if (collide(i, n)) {
                        sprrdrwf[i] = true
                        setrdrwflgs(i)
                    }
                    sprx[i] -= 640
                    spry[i] += 4
                    if (collide(i, n)) {
                        sprrdrwf[i] = true
                        setrdrwflgs(i)
                    }
                    sprx[i] += 320
                    spry[i] -= 2
                }
                i++
            }
        }
    }

    fun setretr(f: Boolean) {
        retrflag = f
    }

    fun setsprorder(newsprorder: IntArray?) {
        sprorder = newsprorder ?: defsprorder
    }

    init {
        dig = d
    }
}