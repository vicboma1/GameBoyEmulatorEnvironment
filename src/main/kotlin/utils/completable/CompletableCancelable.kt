package main.kotlin.utils.completable

import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by vbolinch on 04/01/2017.
 */
class CompletableCancelable<T> constructor(val completable : CompletableFuture<T>, val cancelable: CopyOnWriteArrayList<Boolean>) {

    fun setCancelable(value: Boolean) {
        cancelable[0] = value
    }
}