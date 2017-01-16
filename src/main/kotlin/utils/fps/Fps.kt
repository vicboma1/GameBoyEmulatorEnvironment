package utils.fps

import java.util.concurrent.CompletableFuture

/**
 * Created by vicboma on 07/12/16.
 */
class Fps(private val runnable: Runnable) : Runnable {

    var interpolation = 0.0
        private set

    companion object {
        private val TICKS_PER_SECOND = 21
        private val SKIP_TICKS = 1000 / TICKS_PER_SECOND
        private val MAX_FRAMESKIP = 5
    }

    init {

    }

    override fun run() {
        CompletableFuture.runAsync {

            var nextGameTick = System.currentTimeMillis().toDouble()
            var loops: Int

            while (true) {
                loops = 0
                while (System.currentTimeMillis() > nextGameTick && loops < MAX_FRAMESKIP) {

                    runnable.run()

                    nextGameTick += SKIP_TICKS.toDouble()
                    loops++
                }

                interpolation = (System.currentTimeMillis() + SKIP_TICKS - nextGameTick / SKIP_TICKS.toDouble())
            }
        }

    }

}
