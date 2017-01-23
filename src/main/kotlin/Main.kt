package src

import components.frame.Frame
import src.app.ApplicationImpl
import src.configuration.ConfigurationImpl
import utils.ThreadMain
import java.util.concurrent.ExecutionException

/**
 * Created by vicboma on 02/12/16.
 */
object Main {

    val version = "v.0.1.1"

    @JvmStatic fun main(args: Array<String>) {
        ThreadMain.asyncUI {

            try {

                //Frame
                val mainFrame = Frame.create()
                //configuration
                val configure = ConfigurationImpl.create(mainFrame)
                //Application
                val app = ApplicationImpl
                            .create(mainFrame)
                            .startAsync(configure)

            } catch (e: InterruptedException) {
                    e.printStackTrace()
                } catch (e: ExecutionException) {
                    e.printStackTrace()
                } finally {

                }
            }
        }


}