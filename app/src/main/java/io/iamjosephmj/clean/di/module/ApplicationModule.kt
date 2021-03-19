/*
* MIT License
*
* Copyright (c) 2021 Joseph James
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
*/

package io.iamjosephmj.clean.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.iamjosephmj.clean.application.RxCleanApplication
import io.iamjosephmj.clean.di.ApplicationContext
import io.iamjosephmj.clean.util.CleanRxSchedulerProvider
import io.iamjosephmj.clean.util.SchedulerProvider
import io.iamjosephmj.core.data.repo.GitHubJobsRepository
import io.iamjosephmj.core.interactors.Interactors
import io.iamjosephmj.core.interactors.SearchForJobs
import io.iamjosephmj.networking.service.GitHubJobAPIService
import io.iamjosephmj.networking.service.base.RetrofitService
import io.iamjosephmj.presentation.framework.GitHubJobsDataSourceImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: RxCleanApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun providesRetrofitService(@ApplicationContext context: Context): RetrofitService {
        return RetrofitService().init(context)
    }

    @Singleton
    @Provides
    fun providesGitHubJobAPIService(retrofitService: RetrofitService): GitHubJobAPIService {
        return GitHubJobAPIService(retrofitService)
    }

    @Singleton
    @Provides
    fun providesGitHubJobsDataSourceImpl(gitHubJobAPIService: GitHubJobAPIService): GitHubJobsDataSourceImpl {
        return GitHubJobsDataSourceImpl(gitHubJobAPIService)
    }

    @Singleton
    @Provides
    fun providesGitHubJobsRepository(gitHubJobsDataSourceImpl: GitHubJobsDataSourceImpl): GitHubJobsRepository {
        return GitHubJobsRepository(gitHubJobsDataSourceImpl)
    }

    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = CleanRxSchedulerProvider()

    @Singleton
    @Provides
    fun providesJobsInteractor(repository: GitHubJobsRepository): Interactors {
        return Interactors(
            SearchForJobs(repository)
        )
    }
}