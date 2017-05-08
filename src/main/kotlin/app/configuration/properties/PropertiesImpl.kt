package src.configuration.properties

/**
 * Created by vicboma on 02/12/16.
 */
import app.configuration.properties.Properties
import app.configuration.properties.PropertiesEnum
import java.util.concurrent.ConcurrentHashMap

class PropertiesImpl () : Properties {

    override fun <T> put(key: PropertiesEnum, value: T) = map.put(key, value)

    override fun <T> get(str: PropertiesEnum) = map[str] as (T)

    private val map: ConcurrentHashMap<PropertiesEnum, Any?>

    companion object {
        fun create() = PropertiesImpl()
    }

    init {
        map = ConcurrentHashMap<PropertiesEnum, Any?>(
                mapOf(
                        Pair(PropertiesEnum.SLIDER_ASYNC_TIME_LOAD, 1),
                        Pair(PropertiesEnum.SLIDER_PERMITS, 11),
                        Pair(PropertiesEnum.SLIDER_INCREMENT_SCROLL_GRID,16)
                )
        )

    }
}
