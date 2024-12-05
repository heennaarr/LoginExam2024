package edu.iesam.loginexam1eval.app

import android.app.Application
import edu.iesam.loginexam1eval.app.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

class UserApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@UserApplication)
            modules(AppModule().module)
        }
    }
}