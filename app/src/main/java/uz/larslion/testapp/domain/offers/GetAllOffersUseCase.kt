package uz.larslion.testapp.domain.offers

import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.withContext
import uz.larslion.testapp.data.repositories.OffersRepository
import uz.larslion.testapp.domain.result.GetOffersResult
import uz.larslion.testapp.presentation.utils.dispatchers.DispatchersProvider
import javax.inject.Inject

@ViewModelScoped
class GetAllOffersUseCase @Inject constructor(
    private val repository: OffersRepository, private val dispatchersProvider: DispatchersProvider
) {
    suspend operator fun invoke(): GetOffersResult = withContext(dispatchersProvider.io) {
        return@withContext when (val result = repository.getAllOffers()) {
            is NetworkResponse.Success -> {
                GetOffersResult.Success(result.body.offers.map { it.toDomainModel() })
            }

            is NetworkResponse.ServerError -> {
                GetOffersResult.Error(
                    result.body?.error ?: "Server error"
                )
            }

            is NetworkResponse.NetworkError -> {
                GetOffersResult.Error("Network error")
            }

            is NetworkResponse.UnknownError -> {
                GetOffersResult.Error("Unknown error")
            }

            else -> {
                GetOffersResult.Error("Unknown error")
            }
        }
    }
}
