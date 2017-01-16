package main.kotlin.utils.tween

import main.kotlin.utils.completable.CompletableCancelable
import utils.ThreadMain
import java.awt.Color
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import javax.swing.JLabel

/**
 * Created by vbolinch on 04/01/2017.
 */
fun JLabel.asyncSpel(str: String, delay: Long, sequence:Long) : CompletableCancelable<Boolean> {

    var completableCancelable = CompletableCancelable<Boolean>(CompletableFuture<Boolean>(), CopyOnWriteArrayList<Boolean>().apply { add(false) })

    ThreadMain.asyncUI {
        CompletableFuture.runAsync {
            completableCancelable.completable
                    .thenAcceptAsync {
                        Thread.sleep(delay)
                        this.text = ""
                        var i = 0
                        while (i < str.length - 1 && !completableCancelable.cancelable[0]) {
                            i++
                            Thread.sleep(sequence)
                            this.text += str[i].toString()
                        }
                        if(completableCancelable.cancelable[0])
                            this.text = ""
                    }
        }
    }

    return completableCancelable
}

private val COLOR_DEFAULT_UNMASK = Color(0x00313131.toInt(), true)
private val COLOR_DEFAULT_MASK = Color(0xFF313131.toInt(), true)


fun JLabel.asyncFadeOn(str: String, increment : Int = 2, opacityTarget_255: Int = 255, delay: Long = 50, sequence:Long = 5) : CompletableCancelable<Boolean> {

    var completableCancelable = CompletableCancelable<Boolean>(CompletableFuture<Boolean>(), CopyOnWriteArrayList<Boolean>().apply { add(false) })

    ThreadMain.asyncUI {
        CompletableFuture.runAsync {
            completableCancelable.completable
                    .thenAcceptAsync {
                        Thread.sleep(delay)
                        this.text = str
                        this.foreground = COLOR_DEFAULT_UNMASK
                        while ( this.foreground.alpha <= opacityTarget_255) {
                            Thread.sleep(sequence)
                            val colorAlpha =  this.foreground.alpha.plus(increment)
                            this.foreground = Color( this.foreground.red,  this.foreground.green,  this.foreground.blue, colorAlpha)
                        }
                        if(completableCancelable.cancelable[0])
                            this.foreground = COLOR_DEFAULT_UNMASK
                    }
        }
    }

    return completableCancelable
}

fun JLabel.asyncFadeOff(str: String, decrement : Int = 2, opacityTarget_0: Int = 0, delay: Long = 50, sequence:Long = 5 ) : CompletableCancelable<Boolean> {

    var completableCancelable = CompletableCancelable<Boolean>(CompletableFuture<Boolean>(), CopyOnWriteArrayList<Boolean>().apply { add(false) })

    ThreadMain.asyncUI {
        CompletableFuture.runAsync {
            completableCancelable.completable
                    .thenAcceptAsync {
                        Thread.sleep(delay)
                        this.text = str
                        this.foreground = COLOR_DEFAULT_MASK
                        while ( this.foreground.alpha >= opacityTarget_0) {
                            Thread.sleep(sequence)
                            val colorAlpha =  this.foreground.alpha.minus(decrement)
                            this.foreground = Color( this.foreground.red,  this.foreground.green,  this.foreground.blue, colorAlpha)
                        }
                        if(completableCancelable.cancelable[0])
                            this.foreground = COLOR_DEFAULT_MASK
                    }
        }
    }

    return completableCancelable
}