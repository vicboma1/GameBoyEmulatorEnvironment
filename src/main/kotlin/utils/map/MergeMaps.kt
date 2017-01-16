package utils.map

import java.util.*

/**
 * Created by vicboma on 12/12/16.
 */
public fun <K, V> Map<K, V>.mergeReduce(other: Map<K, V>, reduce: (V, V) -> V = { a, b -> b } ) : Map<K, V> {
    val newMap = LinkedHashMap<K, V>(this.size + other.size)
    newMap.putAll(this)
    for(entry in other.entries) {
        val key = newMap.get(entry.key)
        when(key) {
            null ->  newMap.put(entry.key, entry.value)
            else  ->  newMap.put(entry.key, reduce(entry.value, key))
        }
    }
    return newMap
}