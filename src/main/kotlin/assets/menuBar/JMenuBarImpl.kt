package assets.progressBar

import javax.swing.JComponent
import javax.swing.JMenu
import javax.swing.JMenuBar


/**
 * Created by vicboma on 05/12/16.
 */
class JMenuBarImpl internal constructor(_name : String) : JMenuBar(), IMenuBar {

    companion object {
        fun create(name: String) = JMenuBarImpl(name)

        fun create() =  JMenuBarImpl("")
    }

    init {
        this.name = _name
    }

    override fun createSubMenu(parent: IMenuBar?, child: JComponent?)  {
        (parent as JMenu).add(child)
    }

    override fun createSubMenu(parent: IMenuBar?, child: MutableList<JComponent?>) {
        for (it in child)
            (parent as JMenu).add(it)
    }

    override fun addMenu(menu: Menu) {
        this.add(menu as MenuImpl)
    }

    override fun addMenu(menuList: List<Menu>) : JMenuBarImpl {
        for (it in menuList)
            this.addMenu(it)

        return this
    }

}


fun JMenuBar.visibility(opc:Boolean) =  this.components.forEach {
    it.isEnabled = opc
}

