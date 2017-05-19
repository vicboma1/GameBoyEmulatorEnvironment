package assets.table

import assets.table.model.TableModelImpl
import javax.swing.JTable
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener



/**
 * Created by vbolinch on 02/01/2017.
 */
class TableGridImpl internal constructor(val classLoader: ClassLoader, dm: TableModelImpl?, coverIndex: GRID_COVER) : JTable(dm) {

    private val mapCover by lazy { mapOf(Pair(GRID_COVER.ONE,700),Pair(GRID_COVER.TWO,570),Pair(GRID_COVER.THREE,350),Pair(GRID_COVER.FOUR,250)) }

    companion object{
        fun create(classLoader: ClassLoader,  dm :  TableModelImpl?,coverIndex: GRID_COVER) = TableGridImpl(classLoader,dm,coverIndex)
    }

    init {

        this.apply {
            showVerticalLines = false
            showHorizontalLines = false
            autoResizeMode = JTable.AUTO_RESIZE_ALL_COLUMNS
            val rowH = getRowHeigthToCover(coverIndex)!!
            setRowHeight(rowH)
            rowSelectionAllowed = false
            cellSelectionEnabled = true

            selectionModel.addListSelectionListener(object: ListSelectionListener {
                override fun valueChanged(event: ListSelectionEvent) {
                    if(!selectionModel.isSelectionEmpty){
                        val rowIndex = selectedRow
                        val colIndex = selectedColumn
                        val nameRom = model.getValueAt(rowIndex,colIndex)
                        javax.swing.JOptionPane.showMessageDialog(null,nameRom)
                    }
                }
            })

            setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION)
        }
    }

   /* override fun prepareRenderer(renderer: TableCellRenderer, row: Int, column: Int): Component {
        return super@TableGridImpl.prepareRenderer(renderer, row, column)

        //val nameGame = this@TableGridImpl.model.getValueAt(row, 1).toString().toLowerCase().trim()
       // val key = this.listGame.rowNames!![row][1].toString().trim()
        //return CacheGrid.map[key] as Component

    }*/


    private fun getRowHeigthToCover(coverIndex: GRID_COVER) = mapCover.get(coverIndex)


}


enum class GRID_COVER { ONE, TWO, THREE, FOUR, NAME_ROM }