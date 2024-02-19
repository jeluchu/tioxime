package com.jeluchu.tioxime.scrapper

import com.jeluchu.tioxime.core.connection.RestClient
import com.jeluchu.tioxime.core.utils.toIdFromView
import com.jeluchu.tioxime.models.episodes.Episode
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

/**
 * Extract the information from the following website
 * @see <a href="https://monoschinos2.com/"></a>
 *
 */
fun String.extractLastEpisodes(): List<Episode> {
    val document: Document = Jsoup.parse(this)
    val episodeElements: List<Element> = document.select("ul.episodes li article")

    return episodeElements.map { episodeElement ->
        val title = episodeElement.select("h3.title").text().orEmpty()
        val link = episodeElement.select("a[href]").attr("href").orEmpty()
        val imageUrl = "${RestClient.baseUrl}${episodeElement.select("figure img[src]").attr("src").orEmpty()}"

        val regex = """-(\d+)$""".toRegex()
        val matchResult = regex.find(episodeElement.select("a[href]").attr("href").orEmpty())

        Episode(
            id = link.toIdFromView(),
            title = title,
            episodeNumber = matchResult?.groupValues?.get(1)?.toInt() ?: 0,
            image = imageUrl,
            url = "${RestClient.baseUrl}$link"
        )
    }
}