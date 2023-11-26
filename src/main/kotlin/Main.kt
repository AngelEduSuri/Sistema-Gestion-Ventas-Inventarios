import com.aesuriagasalazar.vista.Login
import com.aesuriagasalazar.vista.Menu
import java.awt.EventQueue
import java.util.logging.Level
import java.util.logging.Logger
import javax.swing.UIManager

fun main() {
    try {
        UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel")
    } catch (e: Exception) {
        Logger.getLogger(Menu::class.java.getName()).log(Level.SEVERE, null, e)
    }
    EventQueue.invokeLater { Login().isVisible = true }
}