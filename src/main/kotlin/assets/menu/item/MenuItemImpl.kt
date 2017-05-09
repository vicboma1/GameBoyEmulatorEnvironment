package assets.progressBar

import kotlinx.coroutines.experimental.CoroutineScope
import utils.swing.Swing
import java.awt.Toolkit
import javax.swing.JMenuItem
import javax.swing.KeyStroke

/**
 * Created by vicboma on 15/12/16.
 */
class MenuItemImpl internal constructor(_name: String, shortCut : Int, statusBar: StatusBar, block: suspend CoroutineScope.() -> Unit) : JMenuItem(_name) , MenuItem {

    companion object {
        fun create(name: String, shortCut : Int ,statusBar: StatusBar, block: suspend CoroutineScope.() -> Unit) : MenuItem =  MenuItemImpl(name, shortCut,statusBar, block)

        fun create(name: String ,statusBar: StatusBar, block: suspend CoroutineScope.() -> Unit) : MenuItem = MenuItemImpl(name, MenuItem.NONE,statusBar, block)

        fun create(name: String ,statusBar: StatusBar) : MenuItem = MenuItemImpl(name, MenuItem.NONE,statusBar,  {  })

    }

    init{

        if(shortCut != MenuItem.NONE)
            this.setAccelerator(KeyStroke.getKeyStroke(shortCut, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()))

        this.addActionListener({
            val init = System.currentTimeMillis()
            kotlinx.coroutines.experimental.runBlocking {
                kotlinx.coroutines.experimental.launch(Swing) {
                    block.invoke(this@launch)
                    statusBar.text("Action item : ${System.currentTimeMillis() - init} ms")
                }
            }
        })
    }

    override fun text(text: String){
        super.setText(text)
    }

}
