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

package io.iamjosephmj.clean.di.component

import android.app.Application
import android.content.Context
import dagger.Component
import io.iamjosephmj.clean.application.RxCleanApplication
import io.iamjosephmj.clean.di.ApplicationContext
import io.iamjosephmj.clean.di.module.ApplicationModule
import io.iamjosephmj.clean.util.SchedulerProvider
import io.iamjosephmj.core.data.repo.GitHubJobsRepository
import io.iamjosephmj.core.interactors.Interactors
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: RxCleanApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getSchedulerProvider(): SchedulerProvider

    fun getGitHubJobsRepository(): GitHubJobsRepository

    fun getCompositeDisposable(): CompositeDisposable

    fun getJobsInteractor(): Interactors
}