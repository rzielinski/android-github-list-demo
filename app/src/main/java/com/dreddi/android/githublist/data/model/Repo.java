package com.dreddi.android.githublist.data.model;

import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("description")
    private String description;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("stargazers_count")
    private long stargazersCount;

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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public long getStargazersCount() {
        return stargazersCount;
    }

    public String getLanguage() {
        return language;
    }

    public RepoOwner getOwner() {
        return owner;
    }
}
