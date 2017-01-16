package components.table.comparator

import java.util.*

/**
 * Created by vbolinch on 02/01/2017.
 */
class TableHeaderComparator internal constructor() : Comparator<Any> {

    var isSortAsc : Boolean
    var columnIndex : Int

    companion object {
        fun create() = TableHeaderComparator()
    }

    init {
         isSortAsc = true
         columnIndex = 0
    }

    override fun compare(o1: Any, o2: Any): Int {
        var param1 = (o1 as Array<Any>)[columnIndex]
        var param2 = (o2 as Array<Any>)[columnIndex]

        var result = when(param1){
            is Double ->   param1.compareTo(param2 as Double)
            is String ->   param1.compareTo(param2.toString())
            is Boolean ->  param1.toString().compareTo(param2.toString())
            else ->  0 //nothing
        }

        if (!isSortAsc)
            result = result * -1

        return result
    }

    override fun equals(obj: Any?): Boolean {
        var res = false
        if (obj is TableHeaderComparator) {
            res = (obj.isSortAsc == isSortAsc)
        }
        return res
    }
}