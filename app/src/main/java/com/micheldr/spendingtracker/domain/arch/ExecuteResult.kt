package com.micheldr.spendingtracker.domain.arch

sealed class ExecuteResult<out S, out E> {
    data class Success<out S>(val value: S) : ExecuteResult<S, Nothing>()
    data class Error<out E>(val error: E) : ExecuteResult<Nothing, E>()
}

typealias ExecuteResponse<S> = ExecuteResult<S, Error>