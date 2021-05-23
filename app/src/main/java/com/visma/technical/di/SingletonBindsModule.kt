package com.visma.technical.di

import com.visma.technical.data.source.RemoteDataSource
import com.visma.technical.data.source.VismaServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

abstract class SingletonBindsModule{

    @Binds
    abstract fun bindRemoteDatasource(vismaServerDataSource: VismaServerDataSource): RemoteDataSource
}