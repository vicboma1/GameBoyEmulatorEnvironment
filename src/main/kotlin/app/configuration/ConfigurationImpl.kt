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
import java.awt.BorderLayout
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by vicboma on 02/12/16.
 */
class ConfigurationImpl internal constructor(classLoader: ClassLoader, frame : Frame) {

    val properties : ConcurrentHashMap<String,Any?>
    val statusBar : StatusBarImpl
    val contentPaneParent : ContentPaneParentImpl
    val menuBar : MenuBarImpl
    val display : Display

    companion object {
        fun create(classLoader: ClassLoader, frame: Frame) = ConfigurationImpl(classLoader, frame)
    }


    init {

        properties = ConcurrentHashMap<String, Any?>(
                mapOf(
                        Pair("sliderAsync", 1),
                        Pair("sliderPermits", 11)
                )
        )

        display =  DisplayImpl.create(Display.KFRAME_JAVA, Display.WIDHT, Display.HEIGTH, Display.VISIBLE, BorderLayout())

        statusBar = StatusBarImpl.create(Display.WIDHT)

        contentPaneParent = ContentPaneParentImpl(
                classLoader,
                frame,
                statusBar,
                properties
        )

        menuBar = MenuBarImpl.create()
                .addMenu(
                        listOf(
                                MenuBarImpl.MenuFile(frame),
                                MenuBarImpl.MenuDialog(frame),
                                MenuBarImpl.MenuChooser(frame),
                                MenuBarImpl.MenuView(frame, contentPaneParent),
                                MenuBarImpl.MenuOptions(frame, properties)
                        )
                )

    }
}



