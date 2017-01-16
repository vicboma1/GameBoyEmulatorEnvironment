package components.table

import components.scroll.ScrollPane
import components.table.model.TableModelImpl
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
class TableImpl internal constructor(private val dm: TableModelImpl?,private val dimension : Dimension) : JTable(dm) {

    companion object{
        fun create( dm: TableModelImpl?, dimenasion : Dimension) : TableImpl{
            return TableImpl(dm , dimenasion )
        }
    }

    init {

        this.apply {
            fillsViewportHeight = true
            showVerticalLines = false
            showHorizontalLines = false
            autoResizeMode = JTable.AUTO_RESIZE_ALL_COLUMNS
        }
    }

    override fun prepareRenderer(renderer: TableCellRenderer, row: Int, column: Int): Component   =
        super@TableImpl.prepareRenderer(renderer, row, column)
            .apply {
                        val nameGame = this@TableImpl.model.getValueAt(row, 1).toString().toLowerCase().trim()
                        val file = Thread.currentThread().getContextClassLoader().getResource("rom/$nameGame")
                        when (file) {
                            null -> {
                                this@TableImpl.model.setValueAt(false, row, 0)
                                isEnabled = false
                            }
                            else -> {
                                val f = File(file.toURI())
                                var setter = false

                                if (f.exists() && !f.isDirectory()) {
                                    setter = true
                                    this@TableImpl.model.setValueAt(true, row, 0)
                                }

                                isEnabled = setter
                            }
                        }
                }

    fun addMouseListenerColumn(mouseAdater : MouseAdapter ) =
            tableHeader.apply {
                updateTableInRealTime = true
                addMouseListener(mouseAdater)
                reorderingAllowed = true
            }


    fun addMouseListenerRow(mouseAdater : MouseAdapter ) =  this.addMouseListener(mouseAdater)

    fun addKeyListenerInput(keyListener : KeyListener) = this.addKeyListener(keyListener)

    fun scrollPane() : JScrollPane = ScrollPane.create(this, dimension)

}
