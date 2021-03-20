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

package io.iamjosephmj.clean.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.willowtreeapps.spruce.Spruce.SpruceBuilder
import com.willowtreeapps.spruce.animation.DefaultAnimations
import com.willowtreeapps.spruce.sort.LinearSort
import io.iamjosephmj.clean.R
import io.iamjosephmj.clean.di.component.ActivityComponent
import io.iamjosephmj.clean.ui.base.BaseActivity
import io.iamjosephmj.clean.ui.screens.actions.Actions
import io.iamjosephmj.clean.ui.screens.adapter.jobsAdapter
import io.iamjosephmj.clean.ui.viewmodels.JobsViewModel
import kotlinx.android.synthetic.main.activity_main.*


/**
 * This is the Activity that displays the list of jobs.
 */
class JobsActivity : BaseActivity<JobsViewModel>() {

    lateinit var animationBuilder: SpruceBuilder

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        initRecyclerView()
        initSpruce()
        initObservers()
    }

    private fun initSpruce() {
        animationBuilder =
            SpruceBuilder(jobsRecyclerView)
                .sortWith(
                    LinearSort( /*interObjectDelay=*/100L,  /*reversed=*/
                        false,
                        LinearSort.Direction.TOP_TO_BOTTOM
                    )
                )
                .animateWith(DefaultAnimations.dynamicTranslationUpwards(jobsRecyclerView),
                    DefaultAnimations.dynamicFadeIn(jobsRecyclerView))
    }

    /**
     * This method is used to register livedata with viewModels to do UI operation.
     */
    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewModel.jobsLiveData.observe(this, {
            when (it.type) {
                Actions.SUCCESS -> {
                    loadingView.visibility = View.INVISIBLE
                    jobsRecyclerView.visibility = View.VISIBLE
                    errorView.visibility = View.INVISIBLE
                    jobsAdapter.items = it.data
                    jobsAdapter.notifyDataSetChanged()
                }
                Actions.LOADING -> {
                    loadingView.visibility = View.VISIBLE
                    jobsRecyclerView.visibility = View.INVISIBLE
                    errorView.visibility = View.INVISIBLE
                }
                Actions.ERROR -> {
                    errorView.frame = 0
                    loadingView.visibility = View.INVISIBLE
                    jobsRecyclerView.visibility = View.INVISIBLE
                    errorView.visibility = View.VISIBLE
                    errorView.playAnimation()
                }
            }
        })
    }

    /**
     * This method is used to initialize recycler view.
     */
    private fun initRecyclerView() {
        jobsRecyclerView.layoutManager = object : LinearLayoutManager(this) {
            override fun onLayoutChildren(
                recycler: RecyclerView.Recycler,
                state: RecyclerView.State
            ) {
                super.onLayoutChildren(recycler, state)
                if (jobsRecyclerView.childCount > 0)
                    animationBuilder.start()
            }
        }
        jobsRecyclerView.adapter = jobsAdapter
    }


}