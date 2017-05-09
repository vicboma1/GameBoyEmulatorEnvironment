package main.kotlin.utils.tween

import kotlinx.coroutines.experimental.launch
import main.kotlin.utils.completable.CompletableCancelable
import utils.swing.Swing
import utils.swing.delay
import java.awt.Color
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import javax.swing.JLabel

/**
 * Created by vbolinch on 04/01/2017.
 */
fun JLabel.asyncSpel(str: String, delay: Long, sequence:Long) : CompletableCancelable<Boolean> {

    var completableCancelable = CompletableCancelable<Boolean>(CompletableFuture<Boolean>(), CopyOnWriteArrayList<Boolean>().apply { add(false) })

    launch(Swing) {
        CompletableFuture.runAsync {
            completableCancelable.completable
                    .thenAcceptAsync {
                        Thread.sleep(delay)
                        text = ""
                        var i = 0
                        while (i < str.length - 1 && !completableCancelable.cancelable[0]) {
                            i++
                            Thread.sleep(sequence)
                            text += str[i].toString()
                        }
                        if(completableCancelable.cancelable[0])
                            text = ""
                    }
        }
    }

    return completableCancelable
}

private val COLOR_DEFAULT_UNMASK = Color(0x00313131.toInt(), true)
private val COLOR_DEFAULT_MASK = Color(0xFF313131.toInt(), true)


fun JLabel.asyncFadeOn(str: String, increment : Int = 2, opacityTarget_255: Int = 255, delay: Long = 50, sequence:Long = 5) : CompletableCancelable<Boolean> {

    var completableCancelable = CompletableCancelable<Boolean>(CompletableFuture<Boolean>(), CopyOnWriteArrayList<Boolean>().apply { add(false) })

    launch(Swing) {
        CompletableFuture.runAsync {
            completableCancelable.completable
                    .thenAcceptAsync {
                        Thread.sleep(delay)
                        text = str
                        foreground = COLOR_DEFAULT_UNMASK
                        while ( foreground.alpha <= opacityTarget_255) {
                            Thread.sleep(sequence)
                            val colorAlpha =  foreground.alpha.plus(increment)
                            foreground = Color( foreground.red,  foreground.green,  foreground.blue, colorAlpha)
                        }
                        if(completableCancelable.cancelable[0])
                            foreground = COLOR_DEFAULT_UNMASK
                    }
        }
    }

    return completableCancelable
}

fun JLabel.asyncFadeOff(str: String, decrement : Int = 2, opacityTarget_0: Int = 0, delay: Long = 50, sequence:Long = 5 ) : CompletableCancelable<Boolean> {

    var completableCancelable = CompletableCancelable<Boolean>(CompletableFuture<Boolean>(), CopyOnWriteArrayList<Boolean>().apply { add(false) })

    launch(Swing) {
        CompletableFuture.runAsync {
            completableCancelable.completable
                    .thenAcceptAsync {
                        Thread.sleep(delay)
                        text = str
                        foreground = COLOR_DEFAULT_MASK
                        while ( foreground.alpha >= opacityTarget_0) {
                            Thread.sleep(sequence)
                            val colorAlpha =  foreground.alpha.minus(decrement)
                            foreground = Color( foreground.red,  foreground.green,  foreground.blue, colorAlpha)
                        }
                        if(completableCancelable.cancelable[0])
                            foreground = COLOR_DEFAULT_MASK
                    }
        }
    }

    return completableCancelable
}