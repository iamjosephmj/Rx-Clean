package io.iamjosephmj.core.data.datasource

import io.iamjosephmj.core.data.models.GitHubJobDescription
import io.reactivex.rxjava3.core.Single

interface GitHubJobsDataSource {
    /**
     * This method is used to fetch a paginated list of jobs posted in github
     * @param domainName This is the domain name for the job.
     * @param pageNumber page number of the specific domainName
     */
    fun searchJobs(domainName: String, pageNumber: Int): Single<List<GitHubJobDescription>>
}