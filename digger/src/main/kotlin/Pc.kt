import java.awt.Image
import java.awt.image.MemoryImageSource
import kotlin.experimental.and

internal class Pc(d: Digger) {
    var dig: Digger
    var image = arrayOfNulls<Image>(2)
    var currentImage: Image? = null
    var source = arrayOfNulls<MemoryImageSource>(2)
    var currentSource: MemoryImageSource? = null
    var width = 320
    var height = 200
    var size = width * height
    var pixels: IntArray = IntArray(0)
    var pal = arrayOf(
        arrayOf(
            byteArrayOf(
                0,
                0x00.toByte(), 0xAA.toByte(), 0xAA.toByte()
            ), byteArrayOf(0, 0xAA.toByte(), 0x00.toByte(), 0x54.toByte()), byteArrayOf(
                0,
                0x00.toByte(), 0x00.toByte(), 0x00.toByte()
            )
        ), arrayOf(
            byteArrayOf(0, 0x54.toByte(), 0xFF.toByte(), 0xFF.toByte()), byteArrayOf(
                0,
                0xFF.toByte(), 0x54.toByte(), 0xFF.toByte()
            ), byteArrayOf(0, 0x54.toByte(), 0x54.toByte(), 0x54.toByte())
        )
    )

    fun gclear() {
        for (i in 0 until size) pixels[i] = 0
        currentSource!!.newPixels()
    }

    fun gethrt(): Long {
        return System.currentTimeMillis()
    }

    fun getkips(): Int {
        return 0
    }

    fun ggeti(x: Int, y: Int, p: ShortArray?, w: Int, h: Int) {
        var src = 0
        var dest = y * width + (x and 0xfffc)
        for (i in 0 until h) {
            var d = dest
            for (j in 0 until w) {
                if (p == null)
                    continue
                p[src++] = (pixels[d] shl 2 or pixels[d + 1] shl 2 or pixels[d + 2] shl 2 or pixels[d + 3]).toShort()
                d += 4
                if (src == p.size) return
            }
            dest += width
        }
    }

    fun ggetpix(x: Int, y: Int): Int {
        val ofs = width * y + x and 0xfffc
        return pixels[ofs] shl 2 or pixels[ofs + 1] shl 2 or pixels[ofs + 2] shl 2 or pixels[ofs + 3]
    }

    fun ginit() {}
    fun ginten(inten: Int) {
        currentSource = source[inten and 1]
        currentImage = image[inten and 1]
        currentSource!!.newPixels()
    }

    fun gpal(pal: Int) {}

    @JvmOverloads
    fun gputi(x: Int, y: Int, p: ShortArray?, w: Int, h: Int, b: Boolean = true) {
        var src = 0
        var dest = y * width + (x and 0xfffc)
        for (i in 0 until h) {
            var d = dest
            for (j in 0 until w) {
                if (p == null)
                    continue
                var px = p[src++].toInt()
                pixels[d + 3] = px and 3
                px = px shr 2
                pixels[d + 2] = px and 3
                px = px shr 2
                pixels[d + 1] = px and 3
                px = px shr 2
                pixels[d] = px and 3
                d += 4
                if (src == p.size) return
            }
            dest += width
        }
    }

    fun gputim(x: Int, y: Int, ch: Int, w: Int, h: Int) {
        val spr = cgagrafx.cgatable[ch * 2]
        val msk = cgagrafx.cgatable[ch * 2 + 1]
        var src = 0
        var dest = y * width + (x and 0xfffc)
        for (i in 0 until h) {
            var d = dest
            for (j in 0 until w) {
                var px = spr[src].toInt()
                val mx = msk[src].toInt()
                src++
                if (mx and 3 == 0) pixels[d + 3] = px and 3
                px = px shr 2
                if (mx and (3 shl 2) == 0) pixels[d + 2] = px and 3
                px = px shr 2
                if (mx and (3 shl 4) == 0) pixels[d + 1] = px and 3
                px = px shr 2
                if (mx and (3 shl 6) == 0) pixels[d] = px and 3
                d += 4
                if (src == spr.size || src == msk.size) {
                    return
                }
            }
            dest += width
        }
    }

    fun gtitle() {
        var src = 0
        var dest = 0
        val plus = 0
        while (true) {
            if (src >= cgagrafx.cgatitledat.size) break
            val b = cgagrafx.cgatitledat[src++].toInt()
            var l: Int
            var c: Int
            if (b == 0xfe) {
                l = cgagrafx.cgatitledat[src++].toInt()
                if (l == 0) l = 256
                c = cgagrafx.cgatitledat[src++].toInt()
            } else {
                l = 1
                c = b
            }
            for (i in 0 until l) {
                var px = c
                var adst = 0
                adst =
                    if (dest < 32768) dest / 320 * 640 + dest % 320 else 320 + (dest - 32768) / 320 * 640 + (dest - 32768) % 320
                pixels[adst + 3] = px and 3
                px = px shr 2
                pixels[adst + 2] = px and 3
                px = px shr 2
                pixels[adst + 1] = px and 3
                px = px shr 2
                pixels[adst + 0] = px and 3
                dest += 4
                if (dest >= 65535) break
            }
            if (dest >= 65535) break
        }
    }

    @JvmOverloads
    fun gwrite(x: Int, y: Int, ch: Int, c: Int, upd: Boolean = false) {
        var ch = ch
        var dest = x + y * width
        var ofs = 0
        val color = c and 3
        ch -= 32
        if (ch < 0 || ch > 0x5f) return
        val chartab = alpha.ascii2cga[ch] ?: return
        for (i in 0..11) {
            var d = dest
            for (j in 0..2) {
                var px = chartab[ofs++].toInt()
                pixels[d + 3] = px and color
                px = px shr 2
                pixels[d + 2] = px and color
                px = px shr 2
                pixels[d + 1] = px and color
                px = px shr 2
                pixels[d] = px and color
                d += 4
            }
            dest += width
        }
        if (upd) currentSource!!.newPixels(x, y, 12, 12)
    }

    init {
        dig = d
    }
}