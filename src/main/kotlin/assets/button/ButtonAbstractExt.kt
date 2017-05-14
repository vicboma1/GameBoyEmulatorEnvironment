package assets.button

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineScope
import utils.swing.Swing
import javax.swing.AbstractButton

/**
 * Created by vicboma on 14/05/17.
 */
fun AbstractButton.addActionListenerAsync(block: suspend CoroutineScope.() -> Unit){
    addActionListener {
        kotlinx.coroutines.experimental.runBlocking {
            kotlinx.coroutines.experimental.async(CommonPool) {
                block.invoke(this@async)
            }
        }
    }
}

fun AbstractButton.addActionListernerSwing(block: suspend CoroutineScope.() -> Unit) {
    addActionListener {
        kotlinx.coroutines.experimental.runBlocking {
            kotlinx.coroutines.experimental.launch(Swing) {
                block.invoke(this@launch)
            }
        }
    }
}