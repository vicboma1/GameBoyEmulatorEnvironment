package components.progressBar

import components.Renderable
import utils.ThreadMain
import java.awt.Component
import java.util.concurrent.CompletableFuture
import javax.swing.JProgressBar

/**
 * Created by vicboma on 05/12/16.
 */
class ProgressBarImpl internal constructor(val min: Int, val max: Int) : JProgressBar(min,max) , ProgressBar {

    companion object {
        fun create(min: Int, max: Int): Renderable {
            return ProgressBarImpl(min,max)
        }
    }

    init{
        value = 0
        isStringPainted = true
        setAlignmentX(Component.CENTER_ALIGNMENT)
    }

    override fun asyncUI()  {
        ThreadMain.asyncUI {
            CompletableFuture.runAsync {
                for (i in min..max) {
                    Thread.sleep(30)
                    value = i * 1
                }
            }.thenAcceptAsync {
                Thread.sleep(500)
                value = 0
                asyncUI()
            }
        }
    }
}
