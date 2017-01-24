package assets.panel.tab

import assets.scroll.ScrollPane
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.Icon
import javax.swing.JComponent
import javax.swing.JTabbedPane
import javax.swing.event.ChangeListener

/**
 * Created by vicboma on 15/01/17.
 */
class TabPane internal constructor(changeListener: ChangeListener, private val dimension: Dimension = Dimension(0,0)) : JTabbedPane(){

    companion object {
        fun create(changeListener: ChangeListener, dimension: Dimension) = TabPane(changeListener, dimension)
    }

    init {
            layout = BorderLayout()
            addChangeListener(changeListener)
            setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        }

    fun add(name:String, image: Icon, pane: JComponent, index:Int, key : Int =  -1){
        addTab(name,image , pane, "")
        setMnemonicAt(index, key)
    }

    fun scrollPane() : ScrollPane = ScrollPane.create(this, dimension)

}