package assets.progressBar

import java.awt.MenuBar
import javax.swing.JComponent
import javax.swing.JMenu


/**
 * Created by vicboma on 05/12/16.
 */
class MenuBarImpl internal constructor(_name : String) : MenuBar() {

    companion object {
        fun create(name: String) = JMenuBarImpl(name)

        fun create() =  JMenuBarImpl("")
    }

    init {
        this.name = _name
    }

     fun createSubMenu(parent: IMenuBar?, child: JComponent?)  {
        (parent as JMenu).add(child)
    }

    fun createSubMenu(parent: IMenuBar?, child: MutableList<JComponent?>) {
        for (it in child)
            (parent as JMenu).add(it)
    }

    fun addMenu(menu: java.awt.Menu) {
        this.add(menu)
    }

    fun addMenu(menuList: List<java.awt.Menu>) : MenuBarImpl {
        for (it in menuList)
            this.addMenu(it)

        return this
    }

}

