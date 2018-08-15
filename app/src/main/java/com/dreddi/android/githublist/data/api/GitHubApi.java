package com.dreddi.android.githublist.data.api;

import com.dreddi.android.githublist.data.model.RepoList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubApi {

    @GET("/search/repositories?q=android+in:name&sort=stars&order=desc&per_page=10")
    Observable<RepoList> getAndroidTrendingRepoList(@Query("page") int page);
}
