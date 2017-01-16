package utils.thread

import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentLinkedDeque
/**
 * Created by vicboma on 29/12/16.
 */
class CustomExecutor<K>{

    private val MILLISECONDS_THREAD_SLEEP  = 75
    private val MILLISECOND_THREAD_SLEEP_TASK_RESOLVED = 25

    private var queue: ConcurrentLinkedDeque<Pair<CompletableFuture<K>,() -> K >>? = null

    private val thread by lazy {

        Thread {

            while (!Thread.currentThread().isInterrupted) {
                val _priorityQueue = queue

                if (_priorityQueue!!.isEmpty()) {
                    try {
                        Thread.sleep(MILLISECONDS_THREAD_SLEEP.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                } else {
                    var poll: Pair<CompletableFuture<K>, () -> K> ? = null
                    try {
                        CompletableFuture.runAsync {
                            val size = _priorityQueue.size
                            for (i in 0..size - 1) {
                                poll = _priorityQueue.poll()
                                // System.out.println("Invoke $count : ${poll!!.second.invoke().toString()}")
                                val res = poll!!.second.invoke()
                                poll!!.first.complete(res)
                                //   System.out.println("Completed ${poll!!.first.toString()}")
                                count++
                            }
                        }
                    } catch(e: Exception) {
                        _priorityQueue.add(poll)
                    } finally {

                        try {
                            Thread.sleep(MILLISECOND_THREAD_SLEEP_TASK_RESOLVED.toLong())
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                    }
                }
            }
        }
    }


    init {
        queue = ConcurrentLinkedDeque()
        thread.apply{
            isDaemon = true
            start()
        }

    }

    private object Holder { val INSTANCE = CustomExecutor<Any?>() }

    companion object {
        var count = 0
        val instance: CustomExecutor<Any?> by lazy { Holder.INSTANCE }
    }

    fun add(processSingle : () -> K)  : CompletableFuture<K> {
        val completableFuture = CompletableFuture<K>()
        var _pair = Pair(completableFuture,processSingle)
        queue?.addLast(_pair)
        return completableFuture
    }

    fun addPriority(processSingle : () -> K)  : CompletableFuture<K> {
        val completableFuture = CompletableFuture<K>()
        var _pair = Pair(completableFuture,processSingle)
        queue?.addFirst(_pair)
        return completableFuture
    }
}