class alpha {
    companion object Static {
        var copyright = "Portions Copyright(c) 1983 Windmill Software Inc."
        var textoffdat = intArrayOf( // [16]
            72, 0, -72, -72, 144, 0, -288, 144, 144, -216, 144, -72, 144, -216, -72, 144
        )
        var cgaletA = shortArrayOf(
            0x0f,
            0xff,
            0x00,
            0x3f,
            0xff,
            0xc0,
            0x3c,
            0x03,
            0xc0,
            0x3c,
            0x03,
            0xc0,
            0x3c,
            0x03,
            0xc0,
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xfc,
            0x00,
            0xf0,
            0xfc,
            0x00,
            0xf0,
            0xfc,
            0x00,
            0xf0,
            0xfc,
            0x00,
            0xf0,
            0xfc,
            0x00,
            0xf0
        )
        var cgaletB = shortArrayOf(
            0x3f,
            0xfc,
            0x00,
            0xff,
            0xff,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xff,
            0xff,
            0x00,
            0xff,
            0xff,
            0xf0,
            0xfc,
            0x00,
            0xf0,
            0xfc,
            0x00,
            0xf0,
            0xfc,
            0x00,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cgaletC = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0xf0,
            0xfc,
            0x00,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cgaletD = shortArrayOf(
            0xff,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0xff,
            0xff,
            0xc0
        )
        var cgaletE = shortArrayOf(
            0x3f,
            0xff,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xff,
            0xff,
            0x00,
            0xff,
            0xff,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xf0
        )
        var cgaletF = shortArrayOf(
            0x3f,
            0xff,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xff,
            0xff,
            0x00,
            0xff,
            0xff,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00
        )
        var cgaletG = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xfc,
            0x0f,
            0xc0,
            0xff,
            0x0f,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cgaletH = shortArrayOf(
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0
        )
        var cgaletI = shortArrayOf(
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00
        )
        var cgaletJ = shortArrayOf(
            0x00,
            0x0f,
            0x00,
            0x00,
            0x0f,
            0x00,
            0x00,
            0x0f,
            0x00,
            0x00,
            0x0f,
            0x00,
            0x00,
            0x0f,
            0x00,
            0x00,
            0x0f,
            0xf0,
            0x00,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cgaletK = shortArrayOf(
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xff,
            0xff,
            0x00,
            0xff,
            0xff,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0
        )
        var cgaletL = shortArrayOf(
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xf0
        )
        var cgaletM = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xfc,
            0xf0,
            0xf0,
            0xfc,
            0xf0,
            0xf0,
            0xfc,
            0xf0,
            0xf0,
            0xfc,
            0xf0,
            0xf0,
            0xfc,
            0xf0,
            0xf0,
            0xfc,
            0xf0,
            0xf0
        )
        var cgaletN = shortArrayOf(
            0x0f,
            0xff,
            0xc0,
            0x3f,
            0xff,
            0xf0,
            0x3c,
            0x00,
            0xf0,
            0x3c,
            0x00,
            0xf0,
            0x3c,
            0x00,
            0xf0,
            0x3c,
            0x00,
            0xf0,
            0x3f,
            0x00,
            0xf0,
            0x3f,
            0x00,
            0xf0,
            0x3f,
            0x00,
            0xf0,
            0x3f,
            0x00,
            0xf0,
            0x3f,
            0x00,
            0xf0,
            0x3f,
            0x00,
            0xf0
        )
        var cgaletO = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cgaletP = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0xff,
            0xff,
            0xc0,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00
        )
        var cgaletQ = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x3f,
            0xf0,
            0xf0,
            0x3f,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cgaletR = shortArrayOf(
            0x3f,
            0xfc,
            0x00,
            0xff,
            0xff,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xff,
            0xff,
            0x00,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0
        )
        var cgaletS = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xff,
            0xff,
            0xc0,
            0x3f,
            0xff,
            0xf0,
            0x00,
            0x0f,
            0xf0,
            0x00,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cgaletT = shortArrayOf(
            0xff,
            0xff,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00
        )
        var cgaletU = shortArrayOf(
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cgaletV = shortArrayOf(
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0x3c,
            0x0f,
            0x00,
            0x3c,
            0x0f,
            0x00,
            0x3f,
            0xff,
            0x00,
            0x0f,
            0xfc,
            0x00
        )
        var cgaletW = shortArrayOf(
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xf0,
            0xfc,
            0xf0,
            0xf0,
            0xfc,
            0xf0,
            0xf0,
            0xfc,
            0xf0,
            0xf0,
            0xfc,
            0xf0,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cgaletX = shortArrayOf(
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0x0f,
            0xff,
            0x00,
            0x0f,
            0xff,
            0x00,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0,
            0xff,
            0x00,
            0xf0
        )
        var cgaletY = shortArrayOf(
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00
        )
        var cgaletZ = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x3f,
            0xff,
            0xf0,
            0xff,
            0xff,
            0xc0,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cganum0 = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cganum1 = shortArrayOf(
            0x00,
            0x3c,
            0x00,
            0x00,
            0x3c,
            0x00,
            0x00,
            0x3c,
            0x00,
            0x00,
            0x3c,
            0x00,
            0x00,
            0x3c,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00,
            0x00,
            0xfc,
            0x00
        )
        var cganum2 = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x3f,
            0xff,
            0xf0,
            0xff,
            0xff,
            0xc0,
            0xff,
            0x00,
            0x00,
            0xff,
            0x00,
            0x00,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xf0
        )
        var cganum3 = shortArrayOf(
            0x3f,
            0xfc,
            0x00,
            0xff,
            0xff,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0x00,
            0x0f,
            0x00,
            0x0f,
            0xff,
            0xc0,
            0x0f,
            0xff,
            0xf0,
            0x00,
            0x03,
            0xf0,
            0xf0,
            0x03,
            0xf0,
            0xf0,
            0x03,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cganum4 = shortArrayOf(
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xf0,
            0x0f,
            0x00,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xf0,
            0x00,
            0x3f,
            0x00,
            0x00,
            0x3f,
            0x00,
            0x00,
            0x3f,
            0x00
        )
        var cganum5 = shortArrayOf(
            0x3f,
            0xff,
            0x00,
            0xff,
            0xff,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xff,
            0xff,
            0xc0,
            0x3f,
            0xff,
            0xf0,
            0x00,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cganum6 = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0x00,
            0xff,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xf0,
            0x0f,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cganum7 = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0x3f,
            0xff,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x00,
            0xf0,
            0x00,
            0x03,
            0xf0,
            0x00,
            0x03,
            0xf0,
            0x00,
            0x03,
            0xf0,
            0x00,
            0x03,
            0xf0,
            0x00,
            0x03,
            0xf0,
            0x00,
            0x03,
            0xf0
        )
        var cganum8 = shortArrayOf(
            0x03,
            0xff,
            0x00,
            0x0f,
            0xff,
            0xc0,
            0x0f,
            0x03,
            0xc0,
            0x0f,
            0x03,
            0xc0,
            0x0f,
            0x03,
            0xc0,
            0x0f,
            0xff,
            0xc0,
            0x3f,
            0xff,
            0xf0,
            0xf0,
            0x03,
            0xf0,
            0xf0,
            0x03,
            0xf0,
            0xf0,
            0x03,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xc0
        )
        var cganum9 = shortArrayOf(
            0x3f,
            0xff,
            0xc0,
            0xff,
            0xff,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xf0,
            0x00,
            0xf0,
            0xff,
            0xff,
            0xf0,
            0x3f,
            0xff,
            0xf0,
            0x00,
            0x0f,
            0xf0,
            0x00,
            0x0f,
            0xf0,
            0x00,
            0x0f,
            0xf0,
            0x00,
            0x0f,
            0xf0,
            0x00,
            0x0f,
            0xf0
        )
        var cgasymdot = shortArrayOf(
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x03,
            0xc0,
            0x00,
            0x03,
            0xc0,
            0x00
        )
        var cgasymline = shortArrayOf(
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x0f,
            0xff,
            0xf0
        )
        var cgasymspace = shortArrayOf(
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00,
            0x00
        )
        var ascii2cga = arrayOf( // [0x5f]
            /* !"#$%&'()*+,-./ */
            cgasymspace, null, null, null, null, null, null, null, null, null, null, null, null, null, cgasymdot, null,
            cganum0, cganum1, cganum2, cganum3, cganum4, cganum5, cganum6,  /* 0123456 */
            cganum7, cganum8, cganum9, null, null, null, null, null, null, null, cgaletA, cgaletB,  /* 789:;<=>?:AB */
            cgaletC, cgaletD, cgaletE, cgaletF, cgaletG, cgaletH, cgaletI,  /* CDEFGHI */
            cgaletJ, cgaletK, cgaletL, cgaletM, cgaletN, cgaletO, cgaletP,  /* JKLMNOP */
            cgaletQ, cgaletR, cgaletS, cgaletT, cgaletU, cgaletV, cgaletW,  /* QRSTUVW */
            cgaletX, cgaletY, cgaletZ, null, null, null, null, cgasymline, null, cgaletA,  /* XYZ[\]^_`a */
            cgaletB, cgaletC, cgaletD, cgaletE, cgaletF, cgaletG, cgaletH,  /* bcdefgh */
            cgaletI, cgaletJ, cgaletK, cgaletL, cgaletM, cgaletN, cgaletO,  /* ijklmno */
            cgaletP, cgaletQ, cgaletR, cgaletS, cgaletT, cgaletU, cgaletV,  /* pqrstuv */
            cgaletW, cgaletX, cgaletY, cgaletZ, null, null, null, null
        ) /* wxyz{|}~ */
    }
}