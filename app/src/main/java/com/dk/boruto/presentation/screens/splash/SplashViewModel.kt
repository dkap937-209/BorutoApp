package com.dk.boruto.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dk.boruto.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val usesCases: UseCases
): ViewModel() {

    private val _onBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted: StateFlow<Boolean> = _onBoardingCompleted.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingCompleted.value =
                usesCases.readOnBoardingUseCase().stateIn(viewModelScope).value
        }
    }
}