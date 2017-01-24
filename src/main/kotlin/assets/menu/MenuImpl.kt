package assets.progressBar

import java.awt.Component
import javax.swing.JComponent
import javax.swing.JMenu

/**
 * Created by vicboma on 15/12/16.
 */
class MenuImpl internal constructor(private val _name: String ) : JMenu(_name) , Menu {

    companion object {
        fun create() : Menu {
            return MenuImpl("")
        }

       fun create(name : String) : Menu {
           return MenuImpl(name)
       }
    }

    init{

    }

    override fun addMenuItem(name : Component?) {
        this.add(name)
    }

    override fun addMenuItem(name : JComponent?){
        this.add(name)
    }

    override fun addMenuItem(name : MenuItem?) {
        this.add(name as JComponent)
    }

    override fun addMenuItem(list : List<MenuItem?>) {
        for( it in list)
            this.addMenuItem(it)
    }

    override fun putSeparator()  {
        this.addSeparator()
    }
}
