package src


import assets.frame.Frame
import kotlinx.coroutines.experimental.launch
import src.app.ApplicationImpl
import src.configuration.ConfigurationImpl
import utils.swing.Swing
import java.util.concurrent.ExecutionException

/**
 * Created by vicboma on 02/12/16.
 */
object Main {

    val version = "v.0.2.3"

  /*  fun log(msg: Any) = println("[${Thread.currentThread().name}] ${msg.toString()}")


    @JvmStatic fun main(args: Array<String>) = runBlocking<Unit> {
        var init = System.currentTimeMillis()
        val jobs = List(100_000) { // create a lot of coroutines and list their jobs
            launch(CommonPool + CoroutineName("launch")) {
                delay(1L)
            }
        }
        jobs.forEach { it.join() } // wait for all jobs to complete

        log("**** END delay "+(System.currentTimeMillis() - init))
        init = System.currentTimeMillis()




        val completables = mutableListOf<CompletableFuture<Job>>()
        List(100_000) {
            completables.add(
                    CompletableFuture.supplyAsync {
                        async(CommonPool + CoroutineName("async")) {
                            delay(1L)
                            true
                        }
                    }
            )
        }

        CompletableFuture.allOf(*completables.toTypedArray())
                .thenAcceptAsync {
                    log("********** END Completable<Job> "+(System.currentTimeMillis() - init))
                }

        init = System.currentTimeMillis()




        val comDe = mutableListOf<CompletableFuture<Deferred<Boolean>>>()
        List(100_000) {
            comDe.add(
                    CompletableFuture.supplyAsync {
                        async(CommonPool + CoroutineName("async")) {
                            delay(1L)
                            true
                        }
                    }
            )
        }

        CompletableFuture.allOf(*comDe.toTypedArray())
                .thenAcceptAsync {
                    log("********** END Completable<Deferred> "+(System.currentTimeMillis() - init))
                }

        init = System.currentTimeMillis()



        val compe = mutableListOf<CompletableFuture<Boolean>>()
        List(100_000) {
            compe.add(
                    CompletableFuture.supplyAsync {
                            Thread.sleep(1L)
                            true
                        }
            )
        }

        CompletableFuture.allOf(*compe.toTypedArray())
                .thenAcceptAsync {
                    log("********** END Completable<Boolean> "+(System.currentTimeMillis() - init))
                }


        init = System.currentTimeMillis()

        val list = List(100_000) {
            async(CommonPool + CoroutineName("async")) {
                delay(1L)
                true
            }
        }

        list.forEach {
            it.await()
        }

        log("********** END Async.await " +(System.currentTimeMillis() - init))



    }


    */
  @JvmStatic fun main(args: Array<String>) {
      launch(Swing) {

            try {

                //loader
                val classLoader = Thread.currentThread().getContextClassLoader()
                //Frame
                val mainFrame = Frame.create()
                //configuration
                val configure = ConfigurationImpl.create(classLoader,mainFrame)
                //Application
                val app = ApplicationImpl
                            .create(mainFrame)
                            .startAsync(configure)

            } catch (e: InterruptedException) {
                    e.printStackTrace()
                } catch (e: ExecutionException) {
                    e.printStackTrace()
                } finally {

                }
            }
        }


}