import java.io.*

class ScoreStorage {
    companion object Static {
        internal fun createInStorage(mem: Scores) {
            try {
                writeToStorage(mem)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        internal fun writeToStorage(mem: Scores) {
            try {
                val scoFile = scoreFile
                val fileOut = FileOutputStream(scoFile)
                val bw = BufferedWriter(OutputStreamWriter(fileOut))
                val scoreinit = mem.scoreinit
                val scorehigh = mem.scorehigh
                for (i in 0..9) {
                    bw.append(scoreinit[i + 1])
                    bw.newLine()
                    bw.append(java.lang.Long.toString(scorehigh[i + 2]))
                    bw.newLine()
                }
                bw.flush()
                bw.close()
                fileOut.flush()
                fileOut.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private val scoreFile: File
            private get() {
                val fileName = "digger.sco"
                val filePath = File(fileName)
                return filePath.absoluteFile
            }

        internal fun readFromStorage(mem: Scores): Boolean {
            try {
                val scoFile = scoreFile
                if (!scoFile.exists() || !scoFile.canRead()) return false
                val fileIn = FileInputStream(scoFile)
                val br = BufferedReader(InputStreamReader(fileIn))
                val sc = arrayOfNulls<ScoreTuple>(10)
                for (i in 0..9) {
                    val name = br.readLine()
                    val score = br.readLine().toInt()
                    sc[i] = ScoreTuple(name, score)
                }
                br.close()
                fileIn.close()
                mem.scores = sc.requireNoNulls()
                return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return false
        }
    }
}