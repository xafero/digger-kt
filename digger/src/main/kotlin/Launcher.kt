import java.awt.Color
import javax.swing.ImageIcon
import javax.swing.JFrame

object Launcher {
    @JvmStatic
    fun main(args: Array<String>) {
        val game = Digger()
        game.isFocusable = true
        game.init()
        game.start()

        val frame = JFrame()
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.title = "Digger Remastered"
        frame.setSize((game.intWidth * 4.03).toInt(), (game.intHeight * 4.17).toInt())
        frame.setLocationRelativeTo(null)

        val icon = ImageIcon(Resources.findResource("/icons/digger.png"))
        frame.iconImage = icon.image

        frame.contentPane.add(game)
        frame.isVisible = true
    }
}