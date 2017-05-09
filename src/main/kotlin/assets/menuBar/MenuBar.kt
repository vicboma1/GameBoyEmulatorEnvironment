package assets.progressBar

import javax.swing.JComponent

/**
 * Created by vicboma on 05/12/16.
 */
interface IMenuBar {
    fun createSubMenu(parent: IMenuBar?, child: JComponent?)
    fun createSubMenu(parent: IMenuBar?, child: MutableList<JComponent?>)
    fun addMenu(menuList: List<Menu>) : JMenuBarImpl
    fun addMenu(menu: Menu)
}