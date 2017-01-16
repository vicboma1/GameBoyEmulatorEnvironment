package app.components

import components.dialog.message.EnumDialog
import components.dialog.message.MessageImpl
import components.frame.Frame
import components.progressBar.*
import utils.thread.CustomExecutor
import java.awt.Color
import java.awt.event.KeyEvent

/**
 * Created by vicboma on 12/12/16.
 */
fun MenuBarImpl.Companion.MenuChooser(frame : Frame): Menu {
    return MenuImpl.create("Chooser")
            .apply{
                addMenuItem(MenuItemImpl.create("Color", KeyEvent.VK_C,{
                    frame.jMenuBar.visibility(false)

                    CustomExecutor.instance.addPriority {
                        ColorChooserImpl.create("Choose a Color", Color.black).showDialog {
                            MessageImpl.create(frame, Pair("Basic Message", "Selected color : ".plus(it)), EnumDialog.PLAIN_MESSAGE).showDialog()
                            frame.jMenuBar.visibility(true)

                        }
                    }
                }))
                addMenuItem(MenuItemImpl.create("File", KeyEvent.VK_F, {
                    frame.jMenuBar.visibility(false)

                    CustomExecutor.instance.addPriority {
                        FileChooserImpl.create(frame).showDialog({
                            MessageImpl.create(frame, Pair("Basic Message", "Selected : ".plus(it)), EnumDialog.PLAIN_MESSAGE).showDialog()
                            frame.jMenuBar.visibility(true)

                        })
                    }
                })
                )
            }

}