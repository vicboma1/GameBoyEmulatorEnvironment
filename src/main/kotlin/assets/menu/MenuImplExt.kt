package assets.progressBar

import java.awt.Component
import java.awt.Menu
import java.awt.MenuItem
import javax.swing.JComponent
import javax.swing.JMenu
import javax.swing.JMenuItem

/**
 * Created by vicboma on 15/12/16.
 */

fun <T: Component>JMenu.addMenuItem(name : T)  =   this.add(name)
fun <T: JComponent>JMenu.addMenuItem(name : T) =   this.add(name)
fun <T: JMenuItem>JMenu.addMenuItem(name : T) =   this.addMenuItem(name as JComponent)
fun <T:JMenuItem>JMenu.addMenuItem(list : List<T>) {
    for (it in list)
        this.addMenuItem(it)
}

fun <T: MenuItem>JMenu.addMenuItem(name : MenuItem) = this.addMenuItem(name as JComponent)

fun <T: MenuItem>Menu.addMenuItem(name : T)  =   this.add(name)
fun <T: String>Menu.addMenuItem(name : T) =   this.add(name)

fun <T:MenuItem>Menu.addMenuItem(list : List<T>) {
    for (it in list)
        this.addMenuItem(it)
}