package app.components.panel.grid

import javax.swing.JScrollPane
import javax.swing.JTable

/**
 * Created by vicboma on 14/04/17.
 */
class TableGridScrollPaneImpl internal constructor(table : JTable) : JScrollPane(table) {

    init {
        verticalScrollBar.setUnitIncrement(16)
    }
}