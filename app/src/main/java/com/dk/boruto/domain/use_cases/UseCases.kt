package com.dk.boruto.domain.use_cases

import com.dk.boruto.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.dk.boruto.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.dk.boruto.domain.use_cases.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val savedOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase
)