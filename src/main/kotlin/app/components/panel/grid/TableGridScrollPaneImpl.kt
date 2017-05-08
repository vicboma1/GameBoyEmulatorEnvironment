package app.components.panel.grid

import app.configuration.properties.Properties
import app.configuration.properties.PropertiesEnum
import javax.swing.JScrollPane
import javax.swing.JTable

/**
 * Created by vicboma on 14/04/17.
 */
class TableGridScrollPaneImpl internal constructor(table : JTable, properties: Properties) : JScrollPane(table) {

    init {
        val slideIncrement = properties.get<Int>(PropertiesEnum.SLIDER_INCREMENT_SCROLL_GRID)
        verticalScrollBar.setUnitIncrement(slideIncrement)
    }
}