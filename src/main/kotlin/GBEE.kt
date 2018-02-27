package src


import assets.frame.Frame
import kotlinx.coroutines.experimental.launch
import src.app.ApplicationImpl
import src.configuration.ConfigurationImpl
import utils.swing.Swing
import java.util.concurrent.ExecutionException

/**
 * Created by vicboma on 02/12/16.
 */
object GBEE {

    val version = "v.0.2.3"
    
    @JvmStatic fun main(args: Array<String>) {
      launch(Swing) {

          if (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)
          // Setter JmenuBar in MenuBar for iOS
              System.setProperty("apple.laf.useScreenMenuBar", "true")


          try {
              //loader
              val classLoader = Thread.currentThread().getContextClassLoader()
              //Frame
              val mainFrame = Frame.create()
              //configuration
              val configure = ConfigurationImpl.create(classLoader, mainFrame)
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
