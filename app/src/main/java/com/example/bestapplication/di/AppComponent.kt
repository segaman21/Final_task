package com.example.bestapplication.di

import android.app.Application
import android.content.Context
import com.example.bestapplication.App
import com.example.bestapplication.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        MainActivityModule::class,
        AndroidInjectionModule::class,
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}