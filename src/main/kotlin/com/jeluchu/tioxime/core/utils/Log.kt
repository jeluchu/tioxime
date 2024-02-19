package com.jeluchu.tioxime.core.utils

import io.github.oshai.kotlinlogging.KotlinLogging

object Log {
    private val logger = KotlinLogging.logger {}

    fun error(message: String) = logger.error { message }
    fun error(throwable: Throwable, message: String) = logger.error(throwable) { message }
}