package com.jeluchu.tioxime

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class BroadcastTests {
    @Test
    fun `on getAnime pass a query and return anime info`() {
        val result = runBlocking { Tioxime.getBroadcast() }
        assertTrue(result.isNotEmpty())
        runBlocking { delay(3000) }
    }

    companion object {
        @BeforeAll
        @JvmStatic
        internal fun setup() {
            Tioxime
        }
    }
}