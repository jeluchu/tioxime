package com.jeluchu.tioxime.models.search

import com.jeluchu.tioxime.core.utils.empty

/**
 * AnimeSearch data class.
 */
data class AnimeSearch(
    /**
     * Name for anime.
     */
    val title: String = String.empty(),

    /**
     * Image for anime.
     */
    val image: String = String.empty(),

    /**
     * Type for video (Anime, Movie, etc).
     */
    val type: String = String.empty(),

    /**
     * Year for anime.
     */
    val year: String = String.empty(),

    /**
     * Link for anime.
     */
    val link: String = String.empty(),

    /**
     * Id for anime.
     */
    val id: String = String.empty()
)