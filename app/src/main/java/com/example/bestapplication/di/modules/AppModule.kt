package com.example.bestapplication.di.modules

import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        DataModule::class,
        DomainModule::class
    ]
)
class AppModule