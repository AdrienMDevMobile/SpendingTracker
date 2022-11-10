package com.micheldr.spendingtracker.domain.useCase

import com.micheldr.spendingtracker.data.AutoDeleteRepository

class GetAutoDeleteUseCaseImpl(
    autoDeleteRepository: AutoDeleteRepository
) : IGetAutoDeleteUseCase {
    override val flow = autoDeleteRepository.userAutoDeletePreferencesFlow
}