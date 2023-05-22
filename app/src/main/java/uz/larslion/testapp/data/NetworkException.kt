package uz.larslion.testapp.data

import com.haroldadmin.cnradapter.NetworkResponse
import uz.larslion.testapp.data.resource.remoteSource.response.ResponseErrorMessage

class NetworkException(
    message: String?,
    cause: Throwable?
) : Throwable(message, cause)

@Throws(NetworkException::class)
fun <T> NetworkResponse<T, ResponseErrorMessage>.proceedNetworkResponse(): T = when (this) {
    is NetworkResponse.Success -> this.body
    is NetworkResponse.Error -> {
        throw NetworkException(
            message = this.body?.error ?: this.error?.message,
            cause = this.error
        )
    }
}