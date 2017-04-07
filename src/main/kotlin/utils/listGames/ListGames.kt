package main.kotlin.utils.listGames

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileReader
import java.util.*

/**
 * Created by vbolinch on 03/01/2017.
 */
class ListGames internal constructor(private val classLoader: ClassLoader, private val path: String) {

    var columnNames : Array<Any>? = null
    var rowNames : Array<Array<Any>>? = null

    companion object {
        fun create(classLoader: ClassLoader,path: String) = ListGames(classLoader,path)
    }

    init {
        val gson = Gson()
        val type = object : TypeToken<Array<Array<Any>>>() {}.type
        val resource = classLoader.getResource(path)
        val file = File(resource.toURI())
        val list = gson.fromJson<Array<Array<Any>>>(FileReader(file), type)

        columnNames = list[0]
        rowNames = Arrays.copyOfRange(list ,1,list.size)
    }
}