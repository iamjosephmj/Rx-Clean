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

package io.iamjosephmj.clean.ui.viewmodels

import io.iamjosephmj.clean.di.component.ViewModelComponent
import io.iamjosephmj.clean.ui.base.BaseViewModel
import io.iamjosephmj.clean.ui.screens.actions.Actions
import io.iamjosephmj.clean.ui.screens.actions.JobsLiveData
import io.iamjosephmj.clean.util.SchedulerProvider
import io.iamjosephmj.core.data.repo.GitHubJobsRepository
import io.iamjosephmj.core.domain.SearchRequest
import io.iamjosephmj.core.interactors.Interactors
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * This ViewModel is specifically used for implementing the jobsFetch-display operations in this project.
 */
class JobsViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: GitHubJobsRepository

    @Inject
    lateinit var interactor: Interactors

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    @Inject
    lateinit var jobsLiveData: JobsLiveData

    override fun injectDependencies(viewModelComponent: ViewModelComponent) {
        viewModelComponent.inject(this)
    }

    override fun onStart() {
        startApiCall()
    }

    private fun startApiCall() {
        val req = SearchRequest(1, "android")
        compositeDisposable.add(
            interactor.searchForJobs(req)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                    onSuccess = { result ->
                        jobsLiveData.type = Actions.SUCCESS
                        jobsLiveData.data = result
                        jobsLiveData.value = jobsLiveData
                    },
                    onError = {
                        jobsLiveData.type = Actions.ERROR
                        jobsLiveData.value = jobsLiveData
                    }
                )
        )
    }
}