package com.inspiredcoda.boruto.domain.use_cases.save_onboarding

import com.inspiredcoda.boruto.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed)
    }

}