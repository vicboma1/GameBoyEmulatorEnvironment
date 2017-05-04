package src.configuration

import app.components.MenuChooser
import app.components.MenuDialog
import app.components.MenuFile
import app.components.menuBar.view.MenuOptions
import app.components.menuBar.view.MenuView
import app.components.panel.ContentPaneParentImpl
import assets.frame.Frame
import assets.progressBar.MenuBarImpl
import assets.progressBar.StatusBarImpl
import main.kotlin.utils.listGames.ListGames
import java.awt.BorderLayout
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by vicboma on 02/12/16.
 */
class ConfigurationImpl internal constructor(private val classLoader: ClassLoader, private val frame : Frame) {

    val properties = ConcurrentHashMap<String,Any?>(
            mapOf(
                    Pair("sliderAsync",5000),
                    Pair("sliderPermits",5)
            )
    )

    /*************** SCREEN ***************/

    val display: Display by lazy {
        DisplayImpl.create(Display.KFRAME_JAVA, Display.WIDHT, Display.HEIGTH, Display.VISIBLE, BorderLayout())
    }

    /*************** STATUS BAR ***************/

    val statusBar: StatusBarImpl by lazy {
         StatusBarImpl.create(Display.WIDHT)
     }

    /*************** CONTENT PANE PANEL ***************/

    val contentPaneParent : ContentPaneParentImpl by lazy {
        ContentPaneParentImpl.create(
                classLoader,
                frame,
                statusBar,
                properties
        )
    }

    /*************** MENU BAR ***************/

    val menuBar: MenuBarImpl by lazy {
        MenuBarImpl.create()
                    .addMenu(
                            listOf(
                                    MenuBarImpl.MenuFile(frame),
                                    MenuBarImpl.MenuDialog(frame),
                                    MenuBarImpl.MenuChooser(frame),
                                    MenuBarImpl.MenuView(frame, contentPaneParent ),
                                    MenuBarImpl.MenuOptions(frame, properties )
                            )
                    )

    }

    companion object {
        fun create(classLoader:ClassLoader,frame : Frame) = ConfigurationImpl(classLoader,frame)
    }

    private val listGames by lazy {
        ListGames.create(classLoader, "list/listGame.json")
    }

    init {

    }

}

