package com.jeluchu.tioxime.models.broadcast

import com.jeluchu.tioxime.core.utils.empty

/**
 * Broadcast data class.
 */
data class Broadcast(
    /**
     * Name for anime.
     */
    val title: String = String.empty(),

    /**
     * Link of anime.
     */
    val url: String = String.empty(),

    /**
     * Image for anime.
     */
    val image: String = String.empty(),

    /**
     * Id for anime.
     */
    val id: String = String.empty(),

    /**
     * Day for anime.
     */
    val day: String = String.empty(),

    /**
     * Status of whether it has been published or
     * how much time is left to be published.
     */
    val onTime: String = String.empty(),
)
