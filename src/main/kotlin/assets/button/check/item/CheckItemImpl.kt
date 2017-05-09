package assets.progressBar

import kotlinx.coroutines.experimental.CoroutineScope
import utils.swing.Swing

/**
 * Created by vicboma on 15/12/16.
 */
class CheckItemImpl internal constructor( _name: String, statusBar: StatusBar,  _isSelected : Boolean,block: suspend CoroutineScope.() -> Unit) : javax.swing.JCheckBoxMenuItem(_name) , CheckItem {

    companion object {
        fun create(name: String,statusBar: StatusBar, isSelected : Boolean,block: suspend CoroutineScope.() -> Unit) = CheckItemImpl(name, statusBar, isSelected,block)
        fun create(name: String,statusBar: StatusBar, isSelected : Boolean) = CheckItemImpl(name, statusBar, isSelected, {})

    }

    init{
        this.isSelected = _isSelected

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
