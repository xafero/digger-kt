internal class Input(d: Digger) {
    var dig: Digger
    var leftpressed = false
    var rightpressed = false
    var uppressed = false
    var downpressed = false
    var f1pressed = false
    var firepressed = false
    var minuspressed = false
    var pluspressed = false
    var f10pressed = false
    var escape = false
    var keypressed = 0
    var akeypressed = 0
    var dynamicdir = -1
    var staticdir = -1
    var joyx = 0
    var joyy = 0
    var joybut1 = false
    var joybut2 = false
    var keydir = 0
    var jleftthresh = 0
    var jupthresh = 0
    var jrightthresh = 0
    var jdownthresh = 0
    var joyanax = 0
    var joyanay = 0
    var firepflag = false
    var joyflag = false
    fun checkkeyb() {
        if (pluspressed) {
            if (dig.frametime > Digger.MIN_RATE) dig.frametime -= 5
        }
        if (minuspressed) {
            if (dig.frametime < Digger.MAX_RATE) dig.frametime += 5
        }
        if (f10pressed) escape = true
    }

    fun detectjoy() {
        joyflag = false
        dynamicdir = -1
        staticdir = dynamicdir
    }

    fun getasciikey(make: Int): Int {
        var k: Int
        return if (make == ' '.toInt() || make >= 'a'.toInt() && make <= 'z'.toInt() || make >= '0'.toInt() && make <= '9'.toInt()) make else 0
    }

    fun getdir(): Int {
        return keydir
    }

    fun initkeyb() {}
    fun Key_downpressed() {
        downpressed = true
        staticdir = 6
        dynamicdir = staticdir
    }

    fun Key_downreleased() {
        downpressed = false
        if (dynamicdir == 6) setdirec()
    }

    fun Key_f1pressed() {
        firepressed = true
        f1pressed = true
    }

    fun Key_f1released() {
        f1pressed = false
    }

    fun Key_leftpressed() {
        leftpressed = true
        staticdir = 4
        dynamicdir = staticdir
    }

    fun Key_leftreleased() {
        leftpressed = false
        if (dynamicdir == 4) setdirec()
    }

    fun Key_rightpressed() {
        rightpressed = true
        staticdir = 0
        dynamicdir = staticdir
    }

    fun Key_rightreleased() {
        rightpressed = false
        if (dynamicdir == 0) setdirec()
    }

    fun Key_uppressed() {
        uppressed = true
        staticdir = 2
        dynamicdir = staticdir
    }

    fun Key_upreleased() {
        uppressed = false
        if (dynamicdir == 2) setdirec()
    }

    fun processkey(key: Int) {
        keypressed = key
        if (key > 0x80) akeypressed = key and 0x7f
        when (key) {
            0x4b -> Key_leftpressed()
            0xcb -> Key_leftreleased()
            0x4d -> Key_rightpressed()
            0xcd -> Key_rightreleased()
            0x48 -> Key_uppressed()
            0xc8 -> Key_upreleased()
            0x50 -> Key_downpressed()
            0xd0 -> Key_downreleased()
            0x3b -> Key_f1pressed()
            0xbb -> Key_f1released()
            0x78 -> f10pressed = true
            0xf8 -> f10pressed = false
            0x2b -> pluspressed = true
            0xab -> pluspressed = false
            0x2d -> minuspressed = true
            0xad -> minuspressed = false
        }
    }

    fun readdir() {
        keydir = staticdir
        if (dynamicdir != -1) keydir = dynamicdir
        staticdir = -1
        firepflag = if (f1pressed || firepressed) true else false
        firepressed = false
    }

    fun readjoy() {}
    fun setdirec() {
        dynamicdir = -1
        if (uppressed) {
            staticdir = 2
            dynamicdir = staticdir
        }
        if (downpressed) {
            staticdir = 6
            dynamicdir = staticdir
        }
        if (leftpressed) {
            staticdir = 4
            dynamicdir = staticdir
        }
        if (rightpressed) {
            staticdir = 0
            dynamicdir = staticdir
        }
    }

    fun teststart(): Boolean {
        var startf = false
        if (keypressed != 0 && keypressed and 0x80 == 0 && keypressed != 27) {
            startf = true
            joyflag = false
            keypressed = 0
        }
        return if (!startf) false else true
    }

    init {
        dig = d
    }
}