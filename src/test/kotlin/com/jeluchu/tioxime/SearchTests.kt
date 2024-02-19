package com.jeluchu.tioxime

import com.jeluchu.tioxime.models.search.AnimeSearch
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class SearchTests {
    @Test
    fun `on searchAnime anime with correct ID`() {
        val expected = listOf(
            AnimeSearch(
                id = "shigatsu-wa-kimi-no-uso",
                title = "Shigatsu wa Kimi no Uso",
                image = "https://tioanime.com/uploads/portadas/1639.jpg",
                link = "https://tioanime.com/anime/shigatsu-wa-kimi-no-uso"
            )
        )

        val result = runBlocking { Tioxime.getSearchAnime("Shigatsu wa Kimi no Uso") }
        assertEquals(expected.first().id, result.first().id)
        assertEquals(expected.first().link, result.first().link)
        assertEquals(expected.first().title, result.first().title)
        assertEquals(expected.first().image, result.first().image)
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