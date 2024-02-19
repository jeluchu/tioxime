package com.jeluchu.tioxime.scrapper

import com.jeluchu.tioxime.core.connection.RestClient
import com.jeluchu.tioxime.core.utils.toIdFromAnime
import com.jeluchu.tioxime.models.search.AnimeSearch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Extract the information from the following website
 * @see <a href="https://tioanime.com/directorio?q=Shigatsu+wa+kimi+no+uso"></a>
 *
 * Shigatsu wa Kimi no Uso is the example
 *
 */
fun String.extractSearch(): List<AnimeSearch> {
    val document: Document = Jsoup.parse(this)
    return mutableListOf<AnimeSearch>().apply {
        val results = document.select("article.anime")
        results.forEach { result ->
            val title = result.select("h3.title").text().orEmpty()
            val image = "${RestClient.baseUrl}${result.select("div.thumb img").attr("src").orEmpty()}"
            val link = "${RestClient.baseUrl}${result.select("a").attr("href").orEmpty()}"

            add(
                AnimeSearch(
                    title = title,
                    image = image,
                    link = link,
                    id = link.toIdFromAnime()
                )
            )
        }
    }
}