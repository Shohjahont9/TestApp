package uz.larslion.testapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.larslion.testapp.domain.offers.GetAllOffersUseCase
import uz.larslion.testapp.domain.offers.OfferDomainModel
import uz.larslion.testapp.domain.result.GetOffersResult
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val allOffersUseCase: GetAllOffersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<HomeScreenNavigationCommands>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getAllOffersList()
    }

    fun setOneTimeEvent(event: HomeScreenNavigationCommands) {
        when(event){
            is HomeScreenNavigationCommands.ShowToast -> emitEvent(HomeScreenNavigationCommands.ShowToast(event.attrs))
        }
    }

    private fun emitEvent(event: HomeScreenNavigationCommands) {
        viewModelScope.launch { _eventFlow.emit(event) }
    }

    private fun getAllOffersList() = viewModelScope.launch {
        _uiState.update { it.copy(screenState = ScreenState.Loading) }
        when(val result = allOffersUseCase()) {
            is GetOffersResult.Success -> {
                _uiState.update {
                    it.copy(
                        screenState = ScreenState.IDLE,
                        data = result.data,
                        errorMessage = ""
                    )
                }
            }
            is GetOffersResult.Error -> {
                _uiState.update {
                    it.copy(
                        screenState = ScreenState.Error, errorMessage = result.error
                    )
                }
            }
        }
    }
}