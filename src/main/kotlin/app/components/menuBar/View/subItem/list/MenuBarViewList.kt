package assets.menuBar.child.dialog

import app.components.panel.ContentPaneParentImpl
import assets.frame.Frame
import assets.progressBar.*
import java.awt.event.KeyEvent


/**
 * Created by vicboma on 12/12/16.
 */
fun JMenuBarImpl.Companion.MenuBarViewList(frame: Frame, statusBar : StatusBar, contentPaneParentImpl: ContentPaneParentImpl): MenuItem {

    return MenuItemImpl.create("List", KeyEvent.VK_L, statusBar, {
        frame.jMenuBar.visibility(false)
        contentPaneParentImpl.visiblePanelListView(true)
        contentPaneParentImpl.visiblePanelGridView(false)
        frame.jMenuBar.visibility(true)
    })

}

