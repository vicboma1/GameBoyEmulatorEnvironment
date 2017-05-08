package src.configuration

import app.components.MenuChooser
import app.components.MenuDialog
import app.components.MenuFile
import app.components.menuBar.view.MenuOptions
import app.components.menuBar.view.MenuView
import app.components.panel.ContentPaneParentImpl
import app.configuration.properties.Properties
import assets.frame.Frame
import assets.progressBar.JMenuBarImpl
import assets.progressBar.StatusBarImpl
import src.configuration.properties.PropertiesImpl
import java.awt.BorderLayout
import java.awt.Menu
import java.awt.MenuBar

/**
 * Created by vicboma on 02/12/16.
 */
class ConfigurationImpl internal constructor(classLoader: ClassLoader, frame : Frame) {

    val properties : Properties
    val statusBar : StatusBarImpl
    val contentPaneParent : ContentPaneParentImpl
    val jmenuBar: JMenuBarImpl
    val display : Display

    val menuBar: MenuBar


    companion object {
        fun create(classLoader: ClassLoader, frame: Frame) = ConfigurationImpl(classLoader, frame)
    }

    init {

        properties = PropertiesImpl.create()

        display =  DisplayImpl.create(Display.KFRAME_JAVA, Display.WIDHT, Display.HEIGTH, Display.VISIBLE, BorderLayout())

        statusBar = StatusBarImpl.create(Display.WIDHT)

        contentPaneParent = ContentPaneParentImpl(
                classLoader,
                frame,
                statusBar,
                properties
        )

        jmenuBar = JMenuBarImpl.create()
                .addMenu(
                        listOf(
                                JMenuBarImpl.MenuFile(frame),
                                JMenuBarImpl.MenuDialog(frame),
                                JMenuBarImpl.MenuChooser(frame),
                                JMenuBarImpl.MenuView(frame, contentPaneParent),
                                JMenuBarImpl.MenuOptions(frame, properties)
                        )
                )

        menuBar  = MenuBar().apply {
            add(Menu("File"))
        }

    }
}



