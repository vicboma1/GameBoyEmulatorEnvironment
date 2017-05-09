package utils.thread

import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.concurrent.Semaphore


/**
 * Created by vicboma on 02/05/17.
 */
@Deprecated("Replacing by Mutex -> Coroutine")
class DynamicSemaphore constructor(permits: Int, val fair:Boolean)  {

    private val semaphore  = CustomSemaphore(permits,fair)

    private var maxPermits = 0

    @Throws(IllegalArgumentException::class)
    @Synchronized
    fun setMaxPermits(newMax: Int) {
        if (newMax < 1)
            throw IllegalArgumentException("Semaphore size must be at least 1 was $newMax")

        var delta = newMax - this.maxPermits

        when {
            delta == 0 -> return
            delta > 0  -> this.semaphore.release(delta)
            delta < 0  -> {
                delta *= -1
                this.semaphore.reducePermits(delta)
            }
            else -> throw Exception("Error! Delta value not support")
        }

        this.maxPermits = newMax
    }

    fun release() =  this.semaphore.release()

    @Throws(InterruptedException::class)
    fun acquire() = this.semaphore.acquire()


    private class CustomSemaphore constructor( permits: Int,  fair:Boolean)  : Semaphore(permits, fair) {
        public override fun reducePermits(reduction: Int) = super.reducePermits(reduction)
    }
}


