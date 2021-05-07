package com.meetup.app

import android.app.Application
import android.content.Context
import com.meetup.app.http.appService
import com.meetup.app.repository.MainRepository

class MeetUpApplication : Application() {

    companion object {
        lateinit var appContext: Context
            private set

        lateinit var mainRepository: MainRepository
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        mainRepository = MainRepository(service = appService)
    }

}