package com.dk.boruto.domain.use_cases.get_selected_hero

import com.dk.boruto.data.repository.Repository
import com.dk.boruto.domain.model.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getSelectedHero(heroId = heroId)
    }

}