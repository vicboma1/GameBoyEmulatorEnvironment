package assets.table

import assets.table.model.TableModelImpl
import main.kotlin.utils.listGames.ListGames
import java.awt.Component
import javax.swing.JTable
import javax.swing.table.TableCellRenderer

/**
 * Created by vbolinch on 02/01/2017.
 */
class TableGridImpl internal constructor(val classLoader: ClassLoader, dm: TableModelImpl?, val listGame : ListGames) : JTable(dm) {

    companion object{
        fun create(classLoader: ClassLoader,  dm :  TableModelImpl?, listGame: ListGames) = TableGridImpl(classLoader,dm ,listGame)
    }

    init {

        this.apply {
            showVerticalLines = false
            showHorizontalLines = false
            autoResizeMode = JTable.AUTO_RESIZE_ALL_COLUMNS
            setRowHeight(250)
        }
    }

    override fun prepareRenderer(renderer: TableCellRenderer, row: Int, column: Int): Component {
        return super@TableGridImpl.prepareRenderer(renderer, row, column)

        //val nameGame = this@TableGridImpl.model.getValueAt(row, 1).toString().toLowerCase().trim()
       // val key = this.listGame.rowNames!![row][1].toString().trim()
        //return CacheGrid.map[key] as Component

    }

}
