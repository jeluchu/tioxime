package com.jeluchu.tioxime

import com.jeluchu.tioxime.core.connection.RestClient
import com.jeluchu.tioxime.models.anime.AnimeEpisode
import com.jeluchu.tioxime.models.anime.AnimeInfo
import com.jeluchu.tioxime.models.broadcast.Broadcast
import com.jeluchu.tioxime.models.episodes.Episode
import com.jeluchu.tioxime.models.search.AnimeSearch
import com.jeluchu.tioxime.models.servers.Server
import com.jeluchu.tioxime.scrapper.extractAnime
import com.jeluchu.tioxime.scrapper.extractBroadcast
import com.jeluchu.tioxime.scrapper.extractEpisodes
import com.jeluchu.tioxime.scrapper.extractLastEpisodes
import com.jeluchu.tioxime.scrapper.extractSearch
import com.jeluchu.tioxime.scrapper.extractServers

object Tioxime {
    private var restClient = RestClient()

    /**
     * Function to get all anime returned after a search.
     * @return List of anime that have a similar title to the one in the query
     * @see AnimeSearch
     */
    suspend fun getSearchAnime(anime: String): List<AnimeSearch> =
        restClient.request("directorio?q=${anime.replace(" ", "+")}").extractSearch()

    /**
     * Function to get all information of anime.
     * @return Anime information
     * @see AnimeInfo
     */
    suspend fun getAnime(anime: String): AnimeInfo =
        restClient.request("anime/${anime}").extractAnime()

    /**
     * Function to get only episodes of anime.
     * @return List of Episodes information
     * @see AnimeEpisode
     */
    suspend fun getEpisodes(anime: String): List<AnimeEpisode> =
        restClient.request("anime/${anime}").extractEpisodes()

    /**
     * Function to get links of servers for anime episodes.
     * @return List of servers
     * @see Server
     */
    suspend fun getServers(id: String): List<Server> =
        restClient.request("ver/${id}").extractServers()

    /**
     * Function to get the latest uploaded episodes.
     * @return List of episodes
     * @see Episode
     */
    suspend fun getLastEpisodes(): List<Episode> =
        restClient.request().extractLastEpisodes()

    /**
     * Function to get the anime that are currently on air.
     * @return Links of animes
     * @see Broadcast
     */
    suspend fun getBroadcast(): List<Broadcast> =
        restClient.request("programacion").extractBroadcast()
}