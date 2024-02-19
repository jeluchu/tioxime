package com.jeluchu.tioxime.core.utils

fun Int.Companion.zero() = 0
fun String.Companion.empty() = ""
fun String.toIdFromView() = replace("https://tioanime.com/ver/", "")
fun String.toIdFromAnime() = replace("https://tioanime.com/anime/", "")