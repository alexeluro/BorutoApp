package com.inspiredcoda.boruto.domain.use_cases

import com.inspiredcoda.boruto.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.inspiredcoda.boruto.domain.use_cases.save_onboarding.SaveOnBoardingUseCase

data class UseCase(
    private val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    private val readOnBoardingUseCase: ReadOnBoardingUseCase
)
