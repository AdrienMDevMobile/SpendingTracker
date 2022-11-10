package com.micheldr.spendingtracker.domain.useCase

import com.micheldr.spendingtracker.data.AutoDeleteChoice
import com.micheldr.spendingtracker.domain.arch.UseCase

interface ISetAutoDeleteChoiceUseCase : UseCase<AutoDeleteChoice, Nothing?>