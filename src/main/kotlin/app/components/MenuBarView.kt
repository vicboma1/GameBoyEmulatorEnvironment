package app.components

import assets.frame.Frame
import assets.progressBar.*
import utils.thread.CustomExecutor
import java.awt.Container
import java.awt.event.KeyEvent

/**
 * Created by vicboma on 02/01/17.
 */
fun MenuBarImpl.Companion.MenuView(frame : Frame,panelListView : SplitImpl<Container>): Menu {

    val radioButtonList = listOf(
                    RadioItemImpl.create("Grid", false, KeyEvent.VK_G, {
                        frame.jMenuBar.visibility(false)

                        CustomExecutor.instance.addPriority {
                            panelListView.isVisible = false
                            frame.jMenuBar.visibility(true)
                        }
                    }),
                    RadioItemImpl.create("List", true,  KeyEvent.VK_L, {
                        frame.jMenuBar.visibility(false)

                        CustomExecutor.instance.addPriority {
                            panelListView.isVisible = true
                            frame.jMenuBar.visibility(true)
                        }
                    })
            )

    val radioGroup = GroupImpl.create(radioButtonList)

    return MenuImpl.create("View")
            .apply{
                addMenuItem(radioButtonList)
            }

}