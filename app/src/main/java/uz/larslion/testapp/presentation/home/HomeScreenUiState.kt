package uz.larslion.testapp.presentation.home

import uz.larslion.testapp.domain.offers.OfferDomainModel

data class HomeScreenUiState(
    val screenState: ScreenState = ScreenState.Loading,
    val errorMessage: String = "",
    val data: List<OfferDomainModel> = listOf()
)

enum class ScreenState {
    IDLE, Loading, Error
}