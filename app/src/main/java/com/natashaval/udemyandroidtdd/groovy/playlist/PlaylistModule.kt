package com.natashaval.udemyandroidtdd.groovy.playlist

import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_BASE_URL = "http://localhost.charlesproxy.com:3000/"

val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

  @Provides
  fun playlistApi(retrofit: Retrofit): PlaylistApi = retrofit.create(PlaylistApi::class.java)

  @Provides
  fun retrofit(): Retrofit = Retrofit.Builder()
      .baseUrl(API_BASE_URL) // please check that it matches your local ip
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
}