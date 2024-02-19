package com.jeluchu.tioxime.core.connection

import com.jeluchu.tioxime.core.client.TioximeClient
import com.jeluchu.tioxime.core.exception.TioximeException
import com.jeluchu.tioxime.core.utils.Log
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.headers

/**
 * Class that handle request.
 * @param isDebug: a boolean that indicate if you run it on debug or not. If yes, it'll throw exception if something happen.
 */
class RestClient(private val isDebug: Boolean = false) : TioximeClient() {
    private val client = httpClient

    suspend fun request(endPoint: String): String {
        runCatching {
            val response = client.get("$baseUrl/$endPoint") {
                headers {
                    append(HttpHeaders.Accept, "text/html")
                }
            }

            val body = response.bodyAsText()
            if (response.status.value !in 200..299) {
                if (response.status.value in 500..599) {
                    val ex = Exception("An internal server error has occurred, code: ${response.status.value}")
                    if (isDebug) throw ex else exceptionHandler(ex)
                } else {
                    val ex = TioximeException(
                        "Monkx API returns code ${response.status.value} and body $body",
                        response.status.value
                    )

                    if (isDebug) throw ex
                    else exceptionHandler(ex)
                }
            }

            return body
        }.getOrElse { throwable -> throw throwable }
    }

    suspend fun request(): String {
        runCatching {
            val response = client.get(baseUrl) {
                headers {
                    append(HttpHeaders.Accept, "text/html")
                }
            }

            val body = response.bodyAsText()
            if (response.status.value !in 200..299) {
                if (response.status.value in 500..599) {
                    val ex = Exception("An internal server error has occurred, code: ${response.status.value}")
                    if (isDebug) throw ex else exceptionHandler(ex)
                } else {
                    val ex = TioximeException(
                        "Monkx returns code ${response.status.value} and body $body",
                        response.status.value
                    )

                    if (isDebug) throw ex
                    else exceptionHandler(ex)
                }
            }

            return body
        }.getOrElse { throwable -> throw throwable }
    }

    private fun exceptionHandler(ex: Exception, message: String? = null) {
        if (message.isNullOrEmpty()) Log.error("Something went wrong! Exception: ${ex.localizedMessage}")
        else Log.error(ex, "Something went wrong! Exception: ${ex.localizedMessage}")
    }

    companion object {
        const val baseUrl = "https://tioanime.com"
    }
}