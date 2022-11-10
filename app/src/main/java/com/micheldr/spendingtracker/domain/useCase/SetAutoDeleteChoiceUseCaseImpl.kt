package com.micheldr.spendingtracker.domain.useCase

import com.micheldr.spendingtracker.data.AutoDeleteChoice
import com.micheldr.spendingtracker.data.AutoDeleteRepository
import com.micheldr.spendingtracker.domain.arch.ExecuteResponse

class SetAutoDeleteChoiceUseCaseImpl(
    private val autoDeleteRepository: AutoDeleteRepository
) : ISetAutoDeleteChoiceUseCase {
    override suspend fun execute(request: AutoDeleteChoice): ExecuteResponse<Nothing?> =
        autoDeleteRepository.setChoice(request)
}