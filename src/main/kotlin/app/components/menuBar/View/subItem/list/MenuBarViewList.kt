package assets.menuBarExt.child.dialog

import app.components.panel.ContentPaneParentImpl
import assets.button.addActionListenerAsync
import assets.frame.Frame
import assets.progressBar.StatusBar
import javax.swing.JMenu
import javax.swing.JMenuItem

/**
 * Created by vicboma on 12/12/16.
 */
fun JMenu.MenuBarViewList(frame: Frame, statusBar : StatusBar, contentPaneParentImpl: ContentPaneParentImpl) =
        JMenuItem("List").apply{
            addActionListenerAsync {
                frame.jMenuBar.isEnabled = false
                contentPaneParentImpl.visiblePanelListView(true)
                contentPaneParentImpl.visiblePanelGridView(false)
                frame.jMenuBar.isEnabled = true
            }

    }


