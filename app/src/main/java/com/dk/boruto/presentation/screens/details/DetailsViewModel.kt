package com.dk.boruto.presentation.screens.details

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dk.boruto.domain.model.Hero
import com.dk.boruto.domain.use_cases.UseCases
import com.dk.boruto.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DetailsViewModel"

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedHero : MutableStateFlow<Hero?> = MutableStateFlow(null)
    val selectedHero: StateFlow<Hero?> = _selectedHero

    init {
        viewModelScope.launch(Dispatchers.IO){
            val heroId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            _selectedHero.value = heroId?.let{
                useCases.getSelectedHeroUseCase(heroId = heroId)
            }

            _selectedHero.value?.name?.let{
                Log.d(TAG, "Hero name $it")
            }
        }
    }

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _colourPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colourPalette: State<Map<String, String>> = _colourPalette

    fun generateColourPalette(){
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColourPalette)
        }
    }

    fun setColourPalette(colours: Map<String, String>){
        _colourPalette.value = colours
    }
}

sealed class UiEvent{
    object GenerateColourPalette: UiEvent()
}