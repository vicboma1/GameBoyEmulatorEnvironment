package src.configuration.properties

/**
 * Created by vicboma on 02/12/16.
 */
import app.configuration.properties.Properties
import java.util.concurrent.ConcurrentHashMap

class PropertiesImpl () : Properties {

    override fun <T> put(key: String, value: T) {
        map.put(key, value)
    }

    override fun <T> get(str: String) : T {
       return map[str] as (T)
    }

    private val map: ConcurrentHashMap<String, Any?>

    companion object {
        fun create() = PropertiesImpl()
    }

    init {
        map = ConcurrentHashMap<String, Any?>(
                mapOf(
                        Pair("sliderAsync", 1),
                        Pair("sliderPermits", 11)
                )
        )
    }
}
