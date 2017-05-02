package app.components.panel.grid

import java.util.concurrent.Semaphore

/**
 * Created by vicboma on 02/05/17.
 */

class DynamicSemaphore  {

    private val semaphore  = CustomSemaphore()

    private var maxPermits = 0

    @Synchronized fun setMaxPermits(newMax: Int) {
        if (newMax < 1) {
            throw IllegalArgumentException("Semaphore size must be at least 1,"
                    + " was " + newMax)
        }

        var delta = newMax - this.maxPermits

        if (delta == 0) {
            return
        } else if (delta > 0) {
            // new max is higher, so release that many permits
            this.semaphore.release(delta)
        } else {
            delta *= -1
            // delta < 0.
            // reducePermits needs a positive #, though.
            this.semaphore.reducePermits(delta)
        }

        this.maxPermits = newMax
    }

    fun release() {
        this.semaphore.release()
    }

   @Throws(InterruptedException::class)
    fun acquire() {
        this.semaphore.acquire()
    }

    class CustomSemaphore  : Semaphore(0, true) {

        public override fun reducePermits(reduction: Int) {
            super.reducePermits(reduction)
        }
    }
}


