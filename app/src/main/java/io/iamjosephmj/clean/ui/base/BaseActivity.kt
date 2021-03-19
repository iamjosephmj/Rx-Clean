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

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import io.iamjosephmj.clean.application.RxCleanApplication
import io.iamjosephmj.clean.di.component.ActivityComponent
import io.iamjosephmj.clean.di.component.DaggerActivityComponent
import io.iamjosephmj.clean.di.module.ActivityModule
import javax.inject.Inject

/**
 * This class represents the base class for all activities.
 *
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupView(savedInstanceState)
        viewModel.onStart()
    }

    /**
     * Prepare for DI
     */
    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as RxCleanApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    open fun goBack() = onBackPressed()

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    //Dependency injection
    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    //start UI initialization
    protected abstract fun setupView(savedInstanceState: Bundle?)
}