package components.scroll

import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JScrollPane

/**
 * Created by vicboma on 15/01/17.
 */
class ScrollPane internal constructor (private val component : JComponent, private val dimension : Dimension) : JScrollPane(component){


    companion object {
        fun create(component : JComponent , dimension: Dimension) = ScrollPane(component,dimension)
    }

    init {
        this.preferredSize = dimension
        this.verticalScrollBarPolicy = javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        this.horizontalScrollBarPolicy = javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
    }

}