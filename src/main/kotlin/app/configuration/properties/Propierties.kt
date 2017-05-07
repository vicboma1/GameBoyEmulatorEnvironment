package app.configuration.properties

/**
 * Created by vicboma on 07/05/17.
 */
interface Properties {
    fun <T> get(str: String): T
    fun <T> put(str: String, value: T)
}