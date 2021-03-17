package io.iamjosephmj.networking.service.interfaces

import io.iamjosephmj.core.data.models.GitHubJobDescription
import io.iamjosephmj.networking.endpoints.GithubJobsEndpoints
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is the API endpoints interface class.
 */
interface GithubJobAPIInterface {

    @GET(GithubJobsEndpoints.JOBS_ENDPOINT)
    fun fetchJob(
        @Query(GithubJobsEndpoints.JOBS_ENDPOINT_KEY_PAGE) pageNumber: Int,
        @Query(GithubJobsEndpoints.JOBS_ENDPOINT_KEY_DOMAIN) domain: String
    ): Single<Response<List<GitHubJobDescription>>>
}