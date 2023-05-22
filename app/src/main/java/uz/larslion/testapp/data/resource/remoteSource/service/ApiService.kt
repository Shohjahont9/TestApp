package uz.larslion.testapp.data.resource.remoteSource.service

import com.google.gson.Gson
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import uz.larslion.testapp.data.resource.remoteSource.response.ResponseErrorMessage
import uz.larslion.testapp.data.resource.remoteSource.response.offers.GetAllOffersDataModel

interface ApiService {

    @GET("api/v1/offers")
    fun getAllOffers(): Call<NetworkResponse<GetAllOffersDataModel, ResponseErrorMessage>>

    companion object {
        fun buildApi(
            baseUrl: String,
            gson: Gson
        ): ApiService {

            val okHttpClient = OkHttpClient.Builder()
                .also { client ->
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }.build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .client(okHttpClient)
                .build()
                .create(ApiService::class.java)
        }
    }
}