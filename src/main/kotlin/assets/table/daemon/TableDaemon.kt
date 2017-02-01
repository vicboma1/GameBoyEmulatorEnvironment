package assets.table.daemon

import assets.table.TableImpl
import assets.table.model.TableModelImpl
import utils.thread.CustomExecutor
import java.awt.Component
import java.io.File
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentLinkedDeque
import javax.swing.Timer
import javax.swing.table.TableCellRenderer

/**
 * Created by vicboma on 20/01/17.
 */
class TableDaemon internal constructor(private val classLoader: ClassLoader) {

    private var table : TableImpl? = null
    private var queue: ConcurrentLinkedDeque<Map<String,*>> = ConcurrentLinkedDeque()

    companion object{
        fun create(classLoader: ClassLoader) = TableDaemon(classLoader)
    }

    init {

        Timer(50, {

            CustomExecutor.instance.addPriority {

            //    taleCellRenrender?.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)

                try {

                        while(queue.isNotEmpty()){


                            var poll: Map<String,*> ? = null
                            try {
                                    val size = queue.size
                                    CompletableFuture.runAsync {
                                        for (i in 0..size - 1) {
                                            poll = queue.poll()

                                            val completable = (poll?.get("completable") as CompletableFuture<Pair<Component,Boolean>>)
                                            val component = (poll?.get("component") as Component)
                                            val row = (poll?.get("rowIndex") as Int)
                                            val column = (poll?.get("columnIndex") as Int)
                                            val tableModel = table?.model as TableModelImpl

                                            tableModel.isEditable = true
                                            tableModel.isCellEditable(row, column)

                                            val nameGame = tableModel.getValueAt(row, 1).toString().toLowerCase().trim()
                                            val file = classLoader.getResource("rom/$nameGame")
                                            var setter = false
                                            when (file) {
                                                null -> {
                                                    if(column == 0)
                                                        tableModel.setValueAt(false, row, 0)
                                           //         component.isEnabled = false
                                                }
                                                else -> {
                                                    val f = File(file.toURI())

                                                    if (f.exists()) {
                                                        setter = true
                                                        if(column == 0)
                                                            tableModel.setValueAt(true, row, 0)
                                                    }
                                        //            component.isEnabled = setter
                                                }
                                            }

                                            tableModel.isEditable = false
                                            completable.complete(Pair(component,setter))
                                        }
                                    }
                            } catch(e: Exception) {
                               // queue.add(poll)
                                e.printStackTrace()
                            } finally {

                            }
                    }
                }catch(e:Exception){
                    e.printStackTrace()
                }
                finally{
                  //  println("Execute!!!")
                  table?.invalidate()
                   // newModel.fireTableDataChanged()
                   //table?.repaint()

                }
            }
        }
        ).apply{
            start()
        }
    }

    fun setTable(table : TableImpl){
        this.table = table
    }

    fun addQueue(component: Component, taleCellRenrender: TableCellRenderer, rowIndex: Int, columnIndex: Int) : CompletableFuture<Pair<Component,Boolean>> {
        val completable = CompletableFuture<Pair<Component,Boolean>>()
        queue.add(
                mapOf(
                        Pair("completable",completable),
                        Pair("component",component),
                        Pair("taleCellRenrender",taleCellRenrender),
                        Pair("rowIndex",rowIndex),
                        Pair("columnIndex",columnIndex)
                ))

        return completable
    }

}