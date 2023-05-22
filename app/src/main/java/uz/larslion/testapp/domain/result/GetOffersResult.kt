package uz.larslion.testapp.domain.result

import uz.larslion.testapp.domain.offers.OfferDomainModel


sealed class GetOffersResult {
    data class Success(val data: List<OfferDomainModel>) : GetOffersResult()
    data class Error(val error: String) : GetOffersResult()
}