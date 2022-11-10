package com.micheldr.spendingtracker.domain.arch

interface UseCase<in Request, out Response> {
    suspend fun execute(request: Request): ExecuteResponse<Response>
}

//typealias UseCaseSimple<I, O> = UseCase<I, O>