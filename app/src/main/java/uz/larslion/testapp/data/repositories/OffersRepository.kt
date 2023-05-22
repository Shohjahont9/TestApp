package uz.larslion.testapp.data.repositories

import com.haroldadmin.cnradapter.NetworkResponse
import uz.larslion.testapp.data.resource.remoteSource.response.ResponseErrorMessage
import uz.larslion.testapp.data.resource.remoteSource.response.offers.GetAllOffersDataModel
import uz.larslion.testapp.data.resource.remoteSource.service.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OffersRepository @Inject constructor(
    private val apiService: ApiService,
) {

    fun getAllOffers(): NetworkResponse<GetAllOffersDataModel, ResponseErrorMessage>? {
        return apiService.getAllOffers().execute().body()
    }
}