package com.jeluchu.tioxime.models.servers

import com.jeluchu.tioxime.core.utils.empty

/**
 * Server data class.
 */
data class Server(
    /**
     * Id for server.
     */
    val id: String = String.empty(),

    /**
     * Link for episode.
     */
    val url: String = String.empty()
)
