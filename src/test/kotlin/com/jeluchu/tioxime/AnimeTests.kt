package com.jeluchu.tioxime

import com.jeluchu.tioxime.models.anime.AnimeEpisode
import com.jeluchu.tioxime.models.anime.AnimeInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class AnimeTests {
    @Test
    fun `on getAnime pass a query and return anime info`() {
        val anime = AnimeInfo(
            title = "Shigatsu wa Kimi no Uso",
            image = "https://tioanime.com/uploads/portadas/1639.jpg",
            cover = "https://tioanime.com/uploads/portadas/1639.jpg",
            synopsis = "El Prodigio del piano, Arima Kosei domin&oacute; la competencia y todos los ni&ntilde;os m&uacute;sicos sab&iacute;an su nombre. Pero luego de que su madre, que a la vez era su instructora, muriera, &eacute;l tuvo un colapso mental mientras tocaba en un recital que result&oacute; en la incapacidad de poder escuchar el sonido de su piano otra vez, aun cuando no ten&iacute;a problema alguno de audici&oacute;n. Incluso despu&eacute;s de dos a&ntilde;os, Kosei no ha tocado el piano y ve al mundo mon&oacute;tono, sin colores. &Eacute;l estaba contento de vivir su vida con sus amigos Tsubaki y Watari hasta que, un d&iacute;a, una chica lo cambi&oacute; todo. Miyazono Kaori es una linda violinista sin remordimientos cuyo estilo para tocar el viol&iacute;n refleja su personalidad. Kaori ayuda a Kosei a volver al mundo de la m&uacute;sica, y le muestra que deber&iacute;a ser libre y romper los esquemas a diferencia del estructurado y r&iacute;gido estilo al que Kosei estaba acostumbrado. ",
            genres= listOf(
                "Drama",
                "Escolares",
                "Música",
                "Romance",
                "Shounen"
            ),
            episodesCount = 23,
            episodes = listOf(
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-1", number = 1),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-2", number = 2),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-3", number = 3),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-4", number = 4),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-5", number = 5),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-6", number = 6),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-7", number = 7),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-8", number = 8),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-9", number = 9),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-10", number = 10),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-11", number = 11),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-12", number = 12),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-13", number = 13),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-14", number = 14),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-15", number = 15),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-16", number = 16),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-17", number = 17),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-18", number = 18),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-19", number = 19),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-20", number = 20),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-21", number = 21),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-22", number = 22),
                AnimeEpisode(id="shigatsu-wa-kimi-no-uso-23", number = 23)
            )
        )

        val result = runBlocking { Tioxime.getAnime("shigatsu-wa-kimi-no-uso") }
        assertEquals(anime.type, result.type)
        assertEquals(anime.title, result.title)
        assertEquals(anime.genres, result.genres)
        runBlocking { delay(3000) }
    }

    @Test
    fun `on getEpisodes pass a query and return episodes list`() {
        val result = runBlocking { Tioxime.getEpisodes("shigatsu-wa-kimi-no-uso") }
        assertEquals(23, result.size)
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