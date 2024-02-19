package com.jeluchu.tioxime

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ServersTests {
    @Test
    fun `on getAnime pass a query and return anime info`() {
        val result = runBlocking { Tioxime.getServers("yubisaki-to-renren-7") }
        assertTrue(result.isNotEmpty())
        runBlocking { delay(3000) }
    }
}