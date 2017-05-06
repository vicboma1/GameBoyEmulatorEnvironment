package app.components.panel.grid

import assets.progressBar.StatusBar
import assets.table.GRID_COVER
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.sync.Mutex
import main.kotlin.utils.image.BufferedImageMemoryFromComponent
import main.kotlin.utils.image.createBufferedImage
import main.kotlin.utils.image.scale
import main.kotlin.utils.listGames.ListGames
import utils.thread.DynamicSemaphore
import java.awt.Color
import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.lang.Exception
import java.security.SecureRandom
import java.util.*
import java.util.concurrent.CompletableFuture
import javax.swing.*


/**
 * Created by vicboma on 13/04/17.
 */
object CacheGrid {

    val mapSizeImageCover = mapOf(Pair(GRID_COVER.FOUR,Pair(240,200)),Pair(GRID_COVER.THREE,Pair(320,300)),Pair(GRID_COVER.TWO,Pair(550,500)),Pair(GRID_COVER.ONE,Pair(680,650)))
    val random = SecureRandom()
    var state = CacheState.STOP
    val mutex = Mutex()

    fun createRefImage(statusBar : StatusBar,listGames: ListGames, classLoader: ClassLoader, bufferedDefault : BufferedImage, jTable:JTable, coverSize : GRID_COVER , properties : Map<String,Any?>) : CompletableFuture<Void> {
        var job : CompletableFuture<Void>

        return runBlocking<CompletableFuture<Void>> {

            println("****** INIT LOAD ASYNC *******")

            val semaphore = DynamicSemaphore(0, true)
            val futures = ArrayList<CompletableFuture<Deferred<Boolean>>>()

            state = CacheState.LOADING

            val imageDefault = ImageIcon().scale(bufferedDefault, classLoader.getResource("cover/_gbNotFound.png").file.toString())

            val timeInit = System.currentTimeMillis()

            try {

                val rows = listGames.rowNames?.size
                var cols = jTable.model.columnCount
                for (row in 0..rows!!) {
                    for (col in 0..cols - 1) {

                        val index = (row * cols) + col
                        if (index > rows || state == CacheState.STOP)
                            break

                        futures.add(CompletableFuture.supplyAsync {

                            async(CommonPool) {
                                val permits = properties["sliderPermits"] as Int
                                val delay = properties["sliderAsync"] as Int

                                mutex.lock(this@async)
                   /*             semaphore.apply {
                                    setMaxPermits(permits)
                                    acquire()
                                }
                    */
                                try {

                                    val pairSize = mapSizeImageCover.get(coverSize)
                                    val bufferImage = ImageIcon().createBufferedImage(pairSize!!.first, pairSize!!.second, BufferedImage.TYPE_INT_ARGB)
                                    val nameRom = listGames.rowNames!![(row * cols) + col][1].toString()
                                    val nameImage = nameRom.toLowerCase().split(".")[0].toString().plus(".png")
                                    val resource = classLoader.getResource("cover/$nameImage")
                                    val image = when (resource) {
                                        null -> imageDefault
                                        else -> ImageIcon().scale(bufferImage, resource.file.toString())
                                    }

                                    //                  println(StringBuffer("($row * $cols) + $col = ${(row * cols) + col}").append(" $nameRom - $nameImage ").toString())

                                    val imageIcon = ImageIcon(
                                            BufferedImageMemoryFromComponent.invoke(
                                                    JPanel().apply {
                                                        size = Dimension(bufferImage.width, bufferImage.height)
                                                        isOpaque = false
                                                        setBackground(Color(0, 0, 0))

                                                        layout = boxLayout(this).apply {
                                                            setBackground(Color(0, 0, 0))
                                                            isOpaque = false
                                                        }

                                                        add(jLabelFactory(" "))
                                                        add(jLabelFactory(ImageIcon(image)))
                                                        add(jLabelFactory(" "))
                                                        add(jLabelFactory(nameRom))
                                                    }
                                            )
                                    )

                                    // println(delayLoadAsync)
                                   // Thread.sleep((random.nextInt(delay + 1)).toLong())
                                    kotlinx.coroutines.experimental.delay((random.nextInt(delay + 1)).toLong())
                                    //yield()
                                    if (state != CacheState.STOP)
                                        jTable.setValueAt(imageIcon, row, col)

                                    true
                                } catch(e: Exception) {
                                    false
                                } finally {
                                    mutex.unlock(this@async)
                        //                semaphore.release()
                                }
                            }

                        })

                    }

                }

            } catch(e: Exception) {
                println(e.message)
                e.stackTrace
            } finally {
                state = CacheState.FINISH
                job = CompletableFuture.allOf(*futures.toTypedArray())
                job.thenRunAsync {
                    println("****** ${System.currentTimeMillis() - timeInit} ms ******")
                    statusBar.text("****** ${System.currentTimeMillis() - timeInit} ms ******")
                }
            }

            job
        }

    }

    private fun boxLayout(container : Container) = BoxLayout(container, BoxLayout.Y_AXIS)

    private fun jLabelFactory(name : String ) = JLabel(name).apply {
                    setBackground(Color(0, 0, 0))
                    isOpaque = false
                    setAlignmentX(Component.CENTER_ALIGNMENT)
                }

    private fun jLabelFactory(imageIcon : ImageIcon ) = JLabel(imageIcon).apply {
                    setBackground(Color(0, 0, 0))
                    isOpaque = false
                    setAlignmentX(Component.CENTER_ALIGNMENT)
                }

}



