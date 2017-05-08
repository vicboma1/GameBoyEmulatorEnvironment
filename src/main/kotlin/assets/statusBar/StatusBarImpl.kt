package assets.progressBar

import kotlinx.coroutines.experimental.launch
import utils.swing.Swing
import utils.swing.delay
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*
import javax.swing.border.BevelBorder

/**
 * Created by vicboma on 05/12/16.
 */
class StatusBarImpl internal constructor(private val _width : Int) : JPanel() , StatusBar {


    private var labelAEST = JLabel("  ")
    private var labelWEST = JLabel("    adios")


    companion object {
        fun create(_width: Int) =  StatusBarImpl(_width)
    }

    init{
        border = BevelBorder(BevelBorder.LOWERED)
        preferredSize = Dimension(_width, 19)
        layout = BoxLayout(this, BoxLayout.X_AXIS)
        this.add(JPanel().apply {
            layout = BorderLayout()
            add(labelWEST)
        } ,BorderLayout.WEST)

        this.add(JSeparator(SwingConstants.VERTICAL))
        this.add(JPanel().apply { add(labelAEST) },BorderLayout.EAST)

    }

    override fun  text (str:String) {
        launch(Swing) {
            labelWEST.text = "    $str"
            Swing.delay(1)
        }
    }
}


