package utils

import utils.thread.CustomExecutor
import javax.swing.SwingUtilities

/**
 * Created by vicboma on 05/12/16.
 */
object ThreadMain {

    fun asyncUI(runnable: () -> Unit) {

        if (SwingUtilities.isEventDispatchThread()) {
            async(runnable)
        } else {
            SwingUtilities.invokeLater {
                async(runnable)
            }
        }
    }

    private fun async(runnable: () -> Unit) = CustomExecutor.instance.add{
        SwingUtilities.invokeLater {
            runnable.invoke()
        }
    }
}
