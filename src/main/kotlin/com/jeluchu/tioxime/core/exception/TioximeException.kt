package com.jeluchu.tioxime.core.exception

open class TioximeException(override val message: String?, val code: Int? = null) : Exception()