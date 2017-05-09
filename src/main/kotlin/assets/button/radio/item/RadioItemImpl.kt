package assets.progressBar

import kotlinx.coroutines.experimental.CoroutineScope
import utils.swing.Swing
import java.awt.Toolkit
import javax.swing.KeyStroke

/**
 * Created by vicboma on 05/12/16.
 */
class RadioItemImpl internal constructor(_name: String,statusBar: StatusBar, _isSelected : Boolean,  shortCut : Int ,block: suspend CoroutineScope.() -> Unit) : javax.swing.JRadioButtonMenuItem(_name) , RadioItem {

    companion object {
        fun create(name: String, statusBar: StatusBar, isSelected : Boolean, shortCut : Int,block: suspend CoroutineScope.() -> Unit) = RadioItemImpl(name,statusBar, isSelected,shortCut,block) as RadioItem
        fun create(name: String, statusBar: StatusBar,isSelected : Boolean) = RadioItemImpl(name, statusBar,isSelected, MenuItem.NONE ,{})

    }

    init{
        this.isSelected = _isSelected

        if(shortCut != MenuItem.NONE)
            this.setAccelerator(KeyStroke.getKeyStroke(shortCut, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()))

        this.addActionListener({
            val init = System.currentTimeMillis()
            kotlinx.coroutines.experimental.runBlocking {
                kotlinx.coroutines.experimental.launch(Swing) {
                    block.invoke(this@launch)
                    statusBar.text("Action checkItem : ${System.currentTimeMillis() - init} ms")
                }
            }
        })
    }

    override fun text(text: String){
        super.setText(text)
    }
}
