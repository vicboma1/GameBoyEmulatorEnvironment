package app.configuration.properties

/**
 * Created by vicboma on 07/05/17.
 */
interface Properties {
    fun <T> get(key: PropertiesEnum): T
    fun <T> put(key: PropertiesEnum, value: T) : Any?
}