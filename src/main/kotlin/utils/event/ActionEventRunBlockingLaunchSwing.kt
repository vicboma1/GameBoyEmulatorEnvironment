package utils.event

import kotlinx.coroutines.experimental.Job
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * Created by vicboma on 09/05/17.
 */
class ActionEventRunBlockingLaunchSwing(source: Any?, id: Int, command: String?) : ActionListener, () -> Job , ActionEvent(source, id, command) {
    override fun invoke(): Job {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun actionPerformed(e: ActionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

  /*  override fun invoke(p1: ActionEvent) {
        runBlocking {
            launch(Swing) {
                println("FDSFDSfs")
            }
        }
    }*/

}