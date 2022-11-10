package com.adrienmandroid.datastore.useCase

import com.micheldr.spendingtracker.data.AutoDeleteRepository
import com.micheldr.spendingtracker.domain.arch.ExecuteResponse
import com.micheldr.spendingtracker.domain.useCase.ISetAutoDeleteActivatedUseCase

class SetAutoDeleteActivatedUseCaseImpl(
    private val autoDeleteRepository: AutoDeleteRepository
) : ISetAutoDeleteActivatedUseCase {
    override suspend fun execute(request: Boolean): ExecuteResponse<Nothing?> =
        autoDeleteRepository.setActivated(request)
}