package assets.menuBarExt.child.dialog

import app.components.panel.ContentPaneParentImpl
import assets.frame.Frame
import assets.progressBar.MenuItem
import assets.progressBar.MenuItemImpl
import assets.progressBar.StatusBar
import assets.progressBar.visibility
import java.awt.event.KeyEvent
import javax.swing.JMenu

/**
 * Created by vicboma on 12/12/16.
 */
fun JMenu.MenuBarViewList(frame: Frame, statusBar : StatusBar, contentPaneParentImpl: ContentPaneParentImpl): MenuItem {

    return MenuItemImpl.create("List", KeyEvent.VK_L, statusBar, {
        frame.jMenuBar.visibility(false)
        contentPaneParentImpl.visiblePanelListView(true)
        contentPaneParentImpl.visiblePanelGridView(false)
        frame.jMenuBar.visibility(true)
    })

}

