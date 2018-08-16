package com.dreddi.android.githublist.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Repo implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("description")
    private String description;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("stargazers_count")
    private long stargazersCount;

    @SerializedName("watchers_count")
    private long watchersCount;

    @SerializedName("forks_count")
    private long forksCount;

    @SerializedName("language")
    private String language;

    @SerializedName("owner")
    private RepoOwner owner;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public long getStargazersCount() {
        return stargazersCount;
    }

    public long getWatchersCount() {
        return watchersCount;
    }

    public long getForksCount() {
        return forksCount;
    }

    public String getLanguage() {
        return language;
    }

    public RepoOwner getOwner() {
        return owner;
    }
}
