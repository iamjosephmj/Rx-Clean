package io.iamjosephmj.clean.ui.screens

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.iamjosephmj.clean.R
import io.iamjosephmj.core.domain.SearchRequest
import io.iamjosephmj.core.data.repo.GitHubJobsRepository
import io.iamjosephmj.core.interactors.SearchForJobs
import io.iamjosephmj.networking.service.GitHubJobAPIService
import io.iamjosephmj.networking.service.base.RetrofitService
import io.iamjosephmj.presentation.framework.GitHubJobsDataSourceImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class JobsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val disposable = CompositeDisposable()

        val retro = RetrofitService()
        retro.init(this)
        val serv = GitHubJobAPIService(retro)
        val impl = GitHubJobsDataSourceImpl(serv)
        val req = SearchRequest(1, "android")
        val repo = GitHubJobsRepository(impl)


        disposable.add(
            SearchForJobs(repo).invoke(req)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { result ->
                        Toast.makeText(this, result.joinToString { " " }, Toast.LENGTH_SHORT).show()
                    },
                    onError = {
                        Log.e("error is", it.message.toString())
                    }
                )
        )
    }
}