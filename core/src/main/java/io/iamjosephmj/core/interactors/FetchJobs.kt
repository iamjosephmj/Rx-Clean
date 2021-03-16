package io.iamjosephmj.core.interactors

import io.iamjosephmj.core.data.models.GitHubJobDescription
import io.iamjosephmj.core.data.models.SearchRequest
import io.iamjosephmj.core.data.repo.GitHubJobsRepository
import io.reactivex.rxjava3.core.Single

/**
 * This is the user action for fetching the jobs from github.
 */
class FetchJobs(private val gitHubJobsRepository: GitHubJobsRepository) {
    operator fun invoke(searchRequest: SearchRequest): Single<List<GitHubJobDescription>> {
        return gitHubJobsRepository.searchJobs(searchRequest)
    }
}