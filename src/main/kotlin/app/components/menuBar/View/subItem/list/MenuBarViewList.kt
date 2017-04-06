package assets.menuBar.child.dialog

import app.components.panel.ContentPaneParentImpl
import assets.frame.Frame
import assets.progressBar.*
import utils.ThreadMain
import java.awt.event.KeyEvent


/**
 * Created by vicboma on 12/12/16.
 */
fun MenuBarImpl.Companion.MenuBarViewList(frame: Frame,contentPaneParentImpl : ContentPaneParentImpl): MenuItem {

    return MenuItemImpl.create("List",  KeyEvent.VK_L, {
                            frame.jMenuBar.visibility(false)
                            ThreadMain.asyncUI {
                                contentPaneParentImpl.visiblePanelListView(true)
                                contentPaneParentImpl.visiblePanelGridView(false)
                                frame.jMenuBar.visibility(true)
                            }
                        })

}

