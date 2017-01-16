package utils.monitor

import com.sun.management.OperatingSystemMXBean
import java.lang.management.ManagementFactory

/**
 * Created by vicboma on 07/12/16.
 */
class PerformanceMonitor internal constructor(val availableProcessors : Int, var lastSystemTime : Long ,var lastProcessCpuTime : Long) {

    val cpuUsage: Double
        @Synchronized get() {
            if (lastSystemTime == 0L) {
                baselineCounters()
                return 0.0
            }

            val systemTime = System.nanoTime()
            var processCpuTime: Long = 0

            if (ManagementFactory.getOperatingSystemMXBean() is OperatingSystemMXBean)
                processCpuTime = (ManagementFactory.getOperatingSystemMXBean() as OperatingSystemMXBean).processCpuTime


            val cpuUsage = (processCpuTime - lastProcessCpuTime).toDouble() / (systemTime - lastSystemTime)

            lastSystemTime = systemTime
            lastProcessCpuTime = processCpuTime

            return cpuUsage / availableProcessors
        }

    private fun baselineCounters() {
        lastSystemTime = System.nanoTime()

        if (ManagementFactory.getOperatingSystemMXBean() is OperatingSystemMXBean)
            lastProcessCpuTime = (ManagementFactory.getOperatingSystemMXBean() as OperatingSystemMXBean).processCpuTime
    }
}