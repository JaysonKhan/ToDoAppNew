package uz.gita.todoappnew.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.todoappnew.domain.repository.TaskRepository
import uz.gita.todoappnew.domain.repository.TaskRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @[Binds Singleton]
    fun bindTaskRepository(impl:TaskRepositoryImpl):TaskRepository
}