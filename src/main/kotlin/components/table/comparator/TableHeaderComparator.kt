package components.table.comparator

import java.util.*

/**
 * Created by vbolinch on 02/01/2017.
 */
class TableHeaderComparator internal constructor() : Comparator<Array<Any>> {

    var isSortAsc : Boolean
    var columnIndex : Int

    companion object {
        fun create() = TableHeaderComparator()
    }

    init {
         isSortAsc = true
         columnIndex = 0
    }


    override fun compare(o1: Array<Any>?, o2: Array<Any>?): Int {
        var param1 = o1?.get(columnIndex)
        var param2 = o2?.get(columnIndex)

        var result = 0
        try {
             result = when (param1) {
                is Double -> param1.compareTo(param2 as Double)
                is Int -> param1.compareTo(param2 as Int)
                is String -> param1.compareTo(param2.toString())
                //is Boolean -> {
                else ->{
                    val b1 = param1
                    val b2 = param2
                    if (b1.toString().equals(b2.toString())) {
                        0
                    } else if (b1.toString().equals("true")) {
                        1
                    } else {
                        -1
                    }
                }
               // else -> 0
            }
        }catch(e: Exception){
            println(e.message)
        }

        if (!isSortAsc)
            result = result * -1

        return result
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        var res = false
        if (obj is TableHeaderComparator) {
            res = (obj.isSortAsc == isSortAsc)
        }
        return res
    }
}