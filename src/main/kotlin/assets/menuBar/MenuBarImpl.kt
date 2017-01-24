package assets.progressBar

import javax.swing.JComponent
import javax.swing.JMenu
import javax.swing.JMenuBar


/**
 * Created by vicboma on 05/12/16.
 */
class MenuBarImpl internal constructor(_name : String) : JMenuBar(), MenuBar {

    companion object {
        fun create(name: String) = MenuBarImpl(name)

        fun create() =  MenuBarImpl("")
    }

    init {
        this.name = _name
    }

    override fun createSubMenu(parent: MenuBar?, child: JComponent?)  {
        (parent as JMenu).add(child)
    }

    override fun createSubMenu(parent: MenuBar?, child: MutableList<JComponent?>) {
        for (it in child)
            (parent as JMenu).add(it)
    }

    override fun addMenu(menu: Menu) {
        this.add(menu as MenuImpl)
    }

    override fun addMenu(menuList: List<Menu>) {
        for (it in menuList)
            this.addMenu(it)
    }

}


fun JMenuBar.visibility(opc:Boolean) =  this.components.forEach {
    it.isEnabled = opc
}

