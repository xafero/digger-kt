import java.awt.event.KeyEvent
import javax.swing.JPanel

abstract class AppletCompat : JPanel() {

    fun getSpeedParameter(): Int {
        return 66
    }

    fun getSubmitParameter(): String {
        return ""
    }

    override fun processKeyEvent(e: KeyEvent) {
        if (e.id == KeyEvent.KEY_RELEASED) {
            keyUp(convertToSwing(e.keyCode))
        } else if (e.id == KeyEvent.KEY_PRESSED) {
            keyDown(convertToSwing(e.keyCode))
        }
    }

    protected abstract fun keyUp(keyRaw: Int): Boolean
    protected abstract fun keyDown(keyRaw: Int): Boolean

    private fun convertToSwing(awtCode: Int): Int {
        when (awtCode) {
            KeyEvent.VK_LEFT -> return 1006
            KeyEvent.VK_RIGHT -> return 1007
            KeyEvent.VK_UP -> return 1004
            KeyEvent.VK_DOWN -> return 1005
            KeyEvent.VK_F1 -> return 1008
            KeyEvent.VK_F10 -> return 1021
            KeyEvent.VK_PLUS -> return 1031
            KeyEvent.VK_MINUS -> return 1032
        }
        return awtCode
    }
}