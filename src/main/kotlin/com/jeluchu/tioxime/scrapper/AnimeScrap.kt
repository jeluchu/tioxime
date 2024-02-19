package com.jeluchu.tioxime.scrapper

import com.jeluchu.tioxime.core.connection.RestClient
import com.jeluchu.tioxime.models.anime.AnimeEpisode
import com.jeluchu.tioxime.models.anime.AnimeInfo
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Extract the information from the following website
 * @see <a href="https://tioanime.com/anime/shigatsu-wa-kimi-no-uso"></a>
 *
 * shigatsu-wa-kimi-no-uso is the example
 *
 */
fun String.extractAnime(): AnimeInfo {
    val document: Document = Jsoup.parse(this)
    val title = document.select("h1.title").text().orEmpty()
    val image = "${RestClient.baseUrl}${document.select("div.thumb img").attr("src").orEmpty()}"
    val genres = document.select("p.genres a").text().split(" ")
    val synopsis = document.select("p.sinopsis").text().orEmpty()

    lateinit var id: String
    val episodes = mutableListOf<AnimeEpisode>()
    document.select("script:containsData(var anime_info)").first()?.let { element ->
        Regex("""var anime_info = (\[.*?]);""").find(element.data())?.let { matchResult ->
            val stringsList = matchResult.groupValues[1]
                .removeSurrounding("[", "]")
                .replace("\"", "")
                .split(",")
            id = stringsList.getOrNull(1).orEmpty()
        }

        Regex("""var episodes = (\[.*?]);""").find(element.data())?.let { matchResult ->
            val animeInfoJson = matchResult.groupValues[1].removeSurrounding("[", "]")
            episodes.addAll(
                animeInfoJson.split(",").map { number ->
                    AnimeEpisode(
                        id = "$id-${number.trim().toInt()}",
                        number = number.trim().toInt()
                    )
                }
            )
        }
    }

    return AnimeInfo(
        title = title,
        image = image,
        cover = image,
        synopsis = synopsis,
        genres = genres,
        episodesCount = episodes.count(),
        episodes = episodes
    )
}

/**
 * Extract the information from the following website
 * @see <a href="https://tioanime.com/anime/shigatsu-wa-kimi-no-uso"></a>
 *
 * shigatsu-wa-kimi-no-uso is the example
 *
 */
fun String.extractEpisodes(): List<AnimeEpisode> {
    lateinit var id: String
    val document: Document = Jsoup.parse(this)
    return mutableListOf<AnimeEpisode>().apply {
        document.select("script:containsData(var anime_info)").first()?.let { element ->
            Regex("""var anime_info = (\[.*?]);""").find(element.data())?.let { matchResult ->
                val stringsList = matchResult.groupValues[1]
                    .removeSurrounding("[", "]")
                    .replace("\"", "")
                    .split(",")
                id = stringsList.getOrNull(1).orEmpty()
            }

            Regex("""var episodes = (\[.*?]);""").find(element.data())?.let { matchResult ->
                val animeInfoJson = matchResult.groupValues[1].removeSurrounding("[", "]")
                addAll(
                    animeInfoJson.split(",").map { number ->
                        AnimeEpisode(
                            id = "$id-${number.trim().toInt()}",
                            number = number.trim().toInt()
                        )
                    }
                )
            }
        }
    }
}