package com.jeluchu.tioxime.models.anime

import com.jeluchu.tioxime.core.utils.empty
import com.jeluchu.tioxime.core.utils.zero

/**
 * AnimeEpisode data class.
 */
data class AnimeEpisode(
    /**
     * Id for episode.
     */
    val id: String = String.empty(),

    /**
     * Episode number.
     */
    val number: Int = Int.zero()
)