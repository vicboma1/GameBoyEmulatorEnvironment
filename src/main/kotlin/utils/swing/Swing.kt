package utils.swing


import javax.swing.SwingUtilities
import javax.swing.Timer
import kotlin.coroutines.experimental.AbstractCoroutineContextElement
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.ContinuationInterceptor
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Created by vicboma on 08/05/17.
 */
object Swing : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
            SwingContinuation(continuation)
}

suspend fun Swing.delay(millis: Int): Unit = suspendCoroutine { cont ->
    Timer(millis) { cont.resume(Unit) }.apply {
        isRepeats = false
        start()
    }
}

private class SwingContinuation<T>(val cont: Continuation<T>) : Continuation<T> by cont {
    override fun resume(value: T) {
        if (SwingUtilities.isEventDispatchThread()) cont.resume(value)
        else SwingUtilities.invokeLater { cont.resume(value) }
    }

    override fun resumeWithException(exception: Throwable) {
        if (SwingUtilities.isEventDispatchThread()) cont.resumeWithException(exception)
        else SwingUtilities.invokeLater { cont.resumeWithException(exception) }
    }
}