package uz.larslion.testapp.presentation.home

import uz.larslion.testapp.data.resource.remoteSource.response.offers.AttributeDataModel

sealed class HomeScreenNavigationCommands {
    data class ShowToast(val attrs: List<AttributeDataModel>) : HomeScreenNavigationCommands()
}