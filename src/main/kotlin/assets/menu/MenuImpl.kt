package assets.progressBar

import java.awt.Component
import javax.swing.JComponent
import javax.swing.JMenu

/**
 * Created by vicboma on 15/12/16.
 */

fun JMenu.addMenuItem(name : Component?)  =   this.add(name)
fun JMenu.addMenuItem(name : JComponent?) =   this.add(name)
fun JMenu.addMenuItem(name : MenuItem?) =   this.addMenuItem(name as JComponent?)
fun JMenu.addMenuItem(list : List<MenuItem?>) {
    for (it in list)
        this.addMenuItem(it)
}
