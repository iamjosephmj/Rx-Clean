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

package io.iamjosephmj.clean.ui.base

import androidx.lifecycle.ViewModel
import io.iamjosephmj.clean.application.RxCleanApplication
import io.iamjosephmj.clean.di.component.DaggerViewModelComponent
import io.iamjosephmj.clean.di.component.ViewModelComponent
import io.iamjosephmj.clean.di.module.ViewModelModule
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * This is the base viewModel class, mainly used for DI.
 */
abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var compositeDisposable: CompositeDisposable


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    init {
        injectDependencies()
    }

    //Dependency injection
    protected abstract fun injectDependencies(viewModelComponent: ViewModelComponent)


    private fun injectDependencies() {
        injectDependencies(buildActivityComponent())
    }

    /**
     * Prepare for DI
     */
    private fun buildActivityComponent() =
        DaggerViewModelComponent
            .builder()
            .applicationComponent(RxCleanApplication.instance.applicationComponent)
            .viewModelModule(ViewModelModule())
            .build()

    abstract fun onStart()

}