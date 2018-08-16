package com.dreddi.android.githublist.data;

import com.dreddi.android.githublist.data.api.ApiClient;
import com.dreddi.android.githublist.data.api.GitHubApi;
import com.dreddi.android.githublist.data.model.RepoList;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class NetworkService {

    private static NetworkService instance;
    private GitHubApi gitHubApi;

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    private NetworkService() {
        Retrofit retrofit = ApiClient.getClient();
        gitHubApi = retrofit.create(GitHubApi.class);
    }

    public Observable<RepoList> getAndroidTrendingRepoList(int page, int perPage) {
        return gitHubApi.getAndroidTrendingRepoList(page, perPage);
    }
}
