package components.progressBar

import javax.swing.JComponent

/**
 * Created by vicboma on 05/12/16.
 */
interface MenuBar {
    fun createSubMenu(parent: MenuBar?, child: JComponent?)
    fun createSubMenu(parent: MenuBar?, child: MutableList<JComponent?>)
    fun addMenu(menuList: List<Menu>)
    fun addMenu(menu: Menu)
}