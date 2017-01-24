package assets.progressBar

import utils.ThreadMain
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*

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
       // border = BevelBorder(BevelBorder.RAISED)
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
        ThreadMain.asyncUI {
            this.labelWEST.text = "    $str"
        }
    }
}


