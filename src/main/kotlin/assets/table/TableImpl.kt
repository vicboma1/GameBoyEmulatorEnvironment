package assets.table

import assets.scroll.ScrollPane
import assets.table.model.TableModelImpl
import java.awt.Component
import java.awt.Dimension
import java.awt.event.KeyListener
import java.awt.event.MouseAdapter
import java.io.File
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.table.TableCellRenderer

/**
 * Created by vbolinch on 02/01/2017.
 */
class TableImpl internal constructor(private val classLoader: ClassLoader,/*private val timer: TableDaemon, */private val dm: TableModelImpl?, private val dimension : Dimension) : JTable(dm) {

    private val scrollSize : ScrollPane

    companion object{
        fun create(classLoader: ClassLoader,  dm: TableModelImpl?, dimenasion : Dimension) = TableImpl(classLoader,/*timer,*/dm , dimenasion)
    }

    init {

        scrollSize = ScrollPane.create(this, dimension)

        showVerticalLines = false
        showHorizontalLines = false
        autoResizeMode = JTable.AUTO_RESIZE_ALL_COLUMNS

    }

    override fun prepareRenderer(renderer: TableCellRenderer, row: Int, column: Int): Component {
        return super@TableImpl.prepareRenderer(renderer, row, column)
                .apply {
                    val nameGame = this@TableImpl.model.getValueAt(row, 1).toString().toLowerCase().trim()
                    val file = classLoader.getResource("rom/$nameGame")
                    when (file) {
                        null -> {
                            isEnabled = false
                        }
                        else -> {
                            val f = File(file.toURI())
                            var setter = false

                            if (f.exists() && !f.isDirectory())
                                setter = true

                            isEnabled = setter
                        }
                    }
                }
    }

    fun addMouseListenerColumn(mouseAdapter : MouseAdapter ) =
            tableHeader.apply {
                updateTableInRealTime = true
                addMouseListener(mouseAdapter)
                reorderingAllowed = true
            }

    fun addMouseListenerRow(mouseAdapter : MouseAdapter ) =  this.addMouseListener(mouseAdapter)

    fun addKeyListenerInput(keyListener : KeyListener) = this.addKeyListener(keyListener)

    fun scrollPane() : JScrollPane = scrollSize

}
