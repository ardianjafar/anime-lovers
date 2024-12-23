package com.manyan.anime.core.di

import androidx.room.Room
import com.manyan.anime.core.BuildConfig.DEBUG
import com.manyan.anime.core.data.local.room.FavoriteAnimeDatabase
import com.manyan.anime.core.data.remote.RemoteDataSource
import com.manyan.anime.core.data.remote.network.ApiService
import com.manyan.anime.core.domain.repository.IAnimeRepository
import com.manyan.anime.core.utils.NetworkInfo.BASE_URL
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FavoriteAnimeDatabase>().favoriteAnimeDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("manyan".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            FavoriteAnimeDatabase::class.java,
            "FavoriteAnime.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostname = "kitsu.io"
        val certificatePinner = CertificatePinner.Builder()
              .add(hostname, "sha256/+QmGzyLvy8/MD9ssSDLu86WKaB11d6+rNoDqvrkH6Ds=")
              .build()
        val loggingInterceptor =
            if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(loggingInterceptor))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.manyan.anime.core.data.local.LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IAnimeRepository> { com.manyan.anime.core.data.AnimeRepository(get(), get()) }
}