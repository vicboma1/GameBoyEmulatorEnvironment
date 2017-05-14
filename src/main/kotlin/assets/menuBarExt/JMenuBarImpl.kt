package assets.progressBar

import javax.swing.JComponent
import javax.swing.JMenu
import javax.swing.JMenuBar


/**
 * Created by vicboma on 05/12/16.
 */

fun JMenuBar.createSubMenu(parent: JMenuBar?, child: JComponent?)  = (parent as JMenu).add(child)

fun JMenuBar.createSubMenu(parent: JMenuBar?, child: MutableList<JComponent?>) {
    for (it in child)
        (parent as JMenu).add(it)
}

fun JMenuBar.addMenu(menu: JMenu)  = this.add(menu)

fun JMenuBar.addMenu(menuList: List<JMenu>) : JMenuBar {
    for (it in menuList)
        this.addMenu(it)

    return this
}

fun JMenuBar.visibility(opc:Boolean) =  this.components.forEach {
    it.isEnabled = opc
}

