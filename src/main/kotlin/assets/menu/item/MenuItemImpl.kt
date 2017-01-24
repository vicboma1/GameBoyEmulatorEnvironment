package assets.progressBar

import java.awt.Toolkit
import java.awt.event.ActionEvent
import javax.swing.JMenuItem
import javax.swing.KeyStroke

/**
 * Created by vicboma on 15/12/16.
 */
class MenuItemImpl internal constructor(private val _name: String, private val shortCut : Int, private val action : (ActionEvent) -> Unit ) : JMenuItem(_name) , MenuItem {

    companion object {
        fun create(name: String, shortCut : Int ,action :(ActionEvent) -> Unit) : MenuItem =  MenuItemImpl(name, shortCut, action)

        fun create(name: String ,action :(ActionEvent) -> Unit) : MenuItem = MenuItemImpl(name, MenuItem.NONE, action)

        fun create(name: String ) : MenuItem = MenuItemImpl(name, MenuItem.NONE, {})

    }

    init{
        if(shortCut != MenuItem.NONE)
            this.setAccelerator(KeyStroke.getKeyStroke(shortCut, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()))

        this.addActionListener(action)
    }

}
