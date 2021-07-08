import java.net.URL

object Resources {
    fun findResource(name: String?): URL {
        return Resources::class.java.getResource(name)
    }
}