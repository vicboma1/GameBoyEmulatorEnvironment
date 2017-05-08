package assets.progressBar

import java.awt.Toolkit
import java.awt.event.ActionEvent
import javax.swing.KeyStroke

/**
 * Created by vicboma on 05/12/16.
 */
class RadioItemImpl internal constructor(private val _name: String, private val _isSelected : Boolean, private val shortCut : Int ,private val action :(ActionEvent) -> Unit) : javax.swing.JRadioButtonMenuItem(_name) , RadioItem {

    companion object {
        fun create(name: String, isSelected : Boolean, shortCut : Int ,action :(ActionEvent) -> Unit) = RadioItemImpl(name, isSelected,shortCut,action) as RadioItem
        fun create(name: String, isSelected : Boolean) = RadioItemImpl(name, isSelected, MenuItem.NONE ,{})

    }

    init{
        this.isSelected = _isSelected

        if(shortCut != MenuItem.NONE)
            this.setAccelerator(KeyStroke.getKeyStroke(shortCut, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()))

        this.addActionListener(action)
    }

    override fun text(text: String){
        super.setText(text)
    }
}
