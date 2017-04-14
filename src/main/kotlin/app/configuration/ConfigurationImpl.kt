package src.configuration

import app.components.MenuChooser
import app.components.MenuDialog
import app.components.MenuFile
import app.components.menuBar.view.MenuView
import app.components.panel.ContentPaneParentImpl
import app.components.panel.grid.CacheGrid
import assets.frame.Frame
import assets.progressBar.MenuBarImpl
import assets.progressBar.StatusBarImpl
import main.kotlin.utils.listGames.ListGames
import utils.thread.CustomExecutor
import java.awt.BorderLayout

/**
 * Created by vicboma on 02/12/16.
 */
class ConfigurationImpl internal constructor(private val classLoader: ClassLoader, private val frame : Frame) {

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
                CustomExecutor.instance.add {
                            CacheGrid.createRefImage(listGames, classLoader,240,200)
                }
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
                                    MenuBarImpl.MenuView(frame, contentPaneParent )
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

