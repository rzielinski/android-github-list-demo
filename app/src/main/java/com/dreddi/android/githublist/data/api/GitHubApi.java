package com.dreddi.android.githublist.data.api;

import com.dreddi.android.githublist.data.model.RepoList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubApi {

    @GET("/search/repositories?q=android+in:name&sort=stars&order=desc")
    Observable<RepoList> getAndroidTrendingRepoList(
            @Query("page") int page,
            @Query("per_page") int perPage);
}
