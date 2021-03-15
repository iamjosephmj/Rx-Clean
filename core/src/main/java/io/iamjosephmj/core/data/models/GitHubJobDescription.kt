package io.iamjosephmj.core.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubJobDescription(
    @Json(name = "id") val id: String = "",
    @Json(name = "type") val type: String = "",
    @Json(name = "url") val url: String = "",
    @Json(name = "created_at") val created_at: String = "",
    @Json(name = "company") val company: String = "",
    @Json(name = "company_url") val company_url: String = "",
    @Json(name = "location") val location: String = "",
    @Json(name = "title") val title: String = "",
    @Json(name = "description") val description: String = "",
    @Json(name = "how_to_apply") val how_to_apply: String = "",
    @Json(name = "company_logo") val company_logo: String = "",
)