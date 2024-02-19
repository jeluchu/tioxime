package com.jeluchu.tioxime.models.episodes

import com.jeluchu.tioxime.core.utils.empty
import com.jeluchu.tioxime.core.utils.zero

/**
 * Episode data class.
 */
data class Episode(
    /**
     * Name for anime.
     */
    val title: String = String.empty(),

    /**
     * Number of episode.
     */
    val episodeNumber: Int = Int.zero(),

    /**
     * Type for video (Anime, Movie, etc).
     */
    val type: String = String.empty(),

    /**
     * Image for anime.
     */
    val image: String = String.empty(),

    /**
     * url of episode.
     */
    val url: String = String.empty(),

    /**
     * Id of episode.
     */
    val id: String = String.empty()
)