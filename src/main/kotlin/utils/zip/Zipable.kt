package utils.zip

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.zip.ZipInputStream

/**
 * Created by vicboma on 01/02/17.
 */
class Zipable internal constructor(private val origin : String , private val target : String ){

    companion object {

    }

    init {

    }


    fun compress(){

    }

    fun unCompress() {
        val buffer = ByteArray(1024)

        try {

            val folder = File(target)
            if (!folder.exists()) {
                folder.mkdir()
            }

            val zis = ZipInputStream(FileInputStream(origin))
            var ze = zis.getNextEntry()

            while (ze != null) {

                val fileName = ze?.getName()
                val newFile = File(target + File.separator + fileName)

                System.out.println("file unzip : " + newFile.getAbsoluteFile())

                File(newFile.getParent()).mkdirs()

                val fos = FileOutputStream(newFile)

                var len: Int = 0
                while ({ len = zis.read(buffer); len }() > 0) {
                    fos.write(buffer, 0, len)
                }

                fos.close()
                ze = zis.getNextEntry()
            }

            zis.closeEntry()
            zis.close()

            println("Done")

        } catch (ex: IOException) {
            ex.printStackTrace()
        }

    }
}