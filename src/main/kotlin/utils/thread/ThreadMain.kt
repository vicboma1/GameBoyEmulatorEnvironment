package utils

import utils.thread.CustomExecutor
import javax.swing.SwingUtilities

/**
 * Created by vicboma on 05/12/16.
 */
@Deprecated("Replacing by launch(Swing) -> Coroutine")
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

    private fun asyncPriority(runnable: () -> Unit) = CustomExecutor.instance.addPriority{
        SwingUtilities.invokeLater {
            runnable.invoke()
        }
    }
}
