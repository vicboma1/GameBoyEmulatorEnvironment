package app.components

import assets.dialog.message.EnumDialog
import assets.dialog.message.MessageImpl
import assets.frame.Frame
import assets.progressBar.*
import java.awt.Color
import java.awt.event.KeyEvent

/**
 * Created by vicboma on 12/12/16.
 */
fun JMenuBarImpl.Companion.MenuChooser(frame: Frame, statusBar: StatusBar): Menu {
    return MenuImpl.create("Chooser")
            .apply {
                addMenuItem(
                        MenuItemImpl.create("Color", KeyEvent.VK_C, statusBar,
                                {
                                    frame.jMenuBar.visibility(false)
                                    ColorChooserImpl.create("Choose a Color", Color.black).showDialog {
                                        MessageImpl.create(frame, Pair("Basic Message", "Selected color : ".plus(it)), EnumDialog.PLAIN_MESSAGE).showDialog()
                                        frame.jMenuBar.visibility(true)

                                    }
                                }

                        ))
                addMenuItem(
                        MenuItemImpl.create("File", KeyEvent.VK_F,statusBar,
                                {
                                    frame.jMenuBar.visibility(false)
                                    FileChooserImpl.create(frame).showDialog({
                                        MessageImpl.create(frame, Pair("Basic Message", "Selected : ".plus(it)), EnumDialog.PLAIN_MESSAGE).showDialog()
                                        frame.jMenuBar.visibility(true)
                                    })

                                })
                )
            }

}