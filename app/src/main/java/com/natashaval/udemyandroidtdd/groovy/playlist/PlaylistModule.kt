package com.natashaval.udemyandroidtdd.groovy.playlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_BASE_URL = "http://localhost.charlesproxy.com:3000/"

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

  @Provides
  fun playlistApi(retrofit: Retrofit): PlaylistApi = retrofit.create(PlaylistApi::class.java)

  @Provides
  fun retrofit(): Retrofit = Retrofit.Builder()
      .baseUrl(API_BASE_URL) // please check that it matches your local ip
      .client(OkHttpClient())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
}