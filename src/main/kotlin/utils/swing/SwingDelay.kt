package utils.swing

import javax.swing.Timer
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Created by vicboma on 09/05/17.
 */
suspend fun Swing.delay(millis: Int): Unit = suspendCoroutine { cont ->
    Timer(millis) {
        cont.resume(Unit) }.apply {
        isRepeats = false
        start()
    }
}