package components.progressBar

import java.awt.Container
import java.awt.LayoutManager
import javax.swing.BoxLayout
import javax.swing.JPanel

/**
 * Created by vicboma on 05/12/16.
 */
class PanelImpl<K : Container> internal constructor(private val layoutManager: LayoutManager, private val pair: Pair<K,K>) : JPanel(layoutManager) , Panel {

    companion object {
        fun <K : Container> create(layout: LayoutManager, pair: Pair<K, K>) = PanelImpl(layout, pair)
    }

    init {
        //setBorder(BorderFactory.createTitledBorder(""))
        setAlignmentX(java.awt.Component.CENTER_ALIGNMENT)
        setLayout(BoxLayout(this, BoxLayout.Y_AXIS))


        this.add(pair.first)
        this.add(pair.second)

    }
}
