package assets.frame

import java.awt.BorderLayout
import java.awt.Toolkit
import java.awt.event.ComponentListener
import javax.swing.ImageIcon
import javax.swing.JComponent
import javax.swing.JFrame

/**
 * Created by vicboma on 06/01/17.
 */
class Frame internal constructor() : JFrame() {

    private val APP_MAIN = "app/main.png"

    companion object{

        fun create() = Frame()

        fun create(component: JComponent, index: String) =
                Frame().apply {
                    contentPane.add(component, index)
                }
    }

    init {

        layout = BorderLayout()
        //if(utils.system.isMac())
        setIconImage(Toolkit.getDefaultToolkit().getImage(APP_MAIN));
        com.apple.eawt.Application.getApplication().setDockIconImage(ImageIcon(APP_MAIN).getImage());
        //    else

    }

    fun addComponent(componentListener : ComponentListener) {
        addComponentListener(componentListener)
    }


}