package uz.larslion.testapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.larslion.testapp.data.resource.remoteSource.service.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideApiService(
        gson: Gson
    ): ApiService {
        return ApiService.buildApi("https://www.kattabozor.uz/hh/test/", gson)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

}