package com.evilstan.starwarsuniverse.domain.usecase

import com.evilstan.starwarsuniverse.domain.models.CharacterUi
import com.evilstan.starwarsuniverse.domain.repository.PersonRepository

class SaveToFavoritesUseCase(private val personRepository: PersonRepository) {

    suspend fun execute(characterUi: CharacterUi) {
        if (characterUi.favorite) personRepository.delete(characterUi)
        else personRepository.insert(characterUi)
    }
}