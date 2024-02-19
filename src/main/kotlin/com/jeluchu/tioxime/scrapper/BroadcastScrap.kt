package com.jeluchu.tioxime.scrapper

import com.jeluchu.tioxime.core.connection.RestClient
import com.jeluchu.tioxime.core.utils.toIdFromAnime
import com.jeluchu.tioxime.core.utils.toIdFromView
import com.jeluchu.tioxime.models.broadcast.Broadcast
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Extract the information from the following website
 * @see <a href="https://tioanime.com/programacion"></a>
 *
 */
fun String.extractBroadcast(): MutableList<Broadcast> {
    val document: Document = Jsoup.parse(this)
    val days = document.select(".day")
    return mutableListOf<Broadcast>().apply {
        days.forEach { day ->
            val dayName = day.selectFirst("button")?.text().orEmpty()
            day.select(".episode").forEach { episode ->
                val title = episode.selectFirst(".title")?.text().orEmpty()
                val time = episode.selectFirst(".time")?.text().orEmpty()
                val image = "${RestClient.baseUrl}${episode.selectFirst("img")?.attr("src").orEmpty()}"
                val link = episode.selectFirst("a")?.attr("href").orEmpty().trim()

                add(
                    Broadcast(
                        url = if (link.contains("#")) ""
                        else "${RestClient.baseUrl}${link.trim()}",
                        title = title,
                        image = image,
                        day = dayName,
                        onTime = time,
                        id = if (link.contains("#")) "" else link.toIdFromView()
                    )
                )
            }
        }
    }
}