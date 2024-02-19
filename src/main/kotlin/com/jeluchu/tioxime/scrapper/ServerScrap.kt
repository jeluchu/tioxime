package com.jeluchu.tioxime.scrapper

import com.jeluchu.tioxime.models.servers.Server
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Extract the information from the following website
 * @see <a href="https://monoschinos2.com/ver/shigatsu-wa-kimi-no-uso-episodio-1"></a>
 *
 * shigatsu-wa-kimi-no-uso-episodio-1  is the example
 *
 */
fun String.extractServers(): List<Server> {
    val document: Document = Jsoup.parse(this)
    return mutableListOf<Server>().apply {
        document.select("script:containsData(var videos)").first()?.let { element ->
            Regex("""var videos = (\[.*?]);""").find(element.data())?.let { matchResult ->
                val servers = matchResult.groupValues[1].replace("[[", "").replace("]]", "").split("],[")
                servers.forEach { server ->
                    val cleanedEntry = server.replace("\\", "")
                    val values = cleanedEntry.split(",")
                    val url = values[1].replace("\"", "")

                    add(
                        Server(
                            id = values[0].lowercase(),
                            url = url
                        )
                    )
                }
            }
        }
    }
}