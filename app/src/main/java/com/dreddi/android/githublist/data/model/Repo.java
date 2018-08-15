package com.dreddi.android.githublist.data.model;

import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String full_name;

    @SerializedName("owner")
    private RepoOwner owner;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public RepoOwner getOwner() {
        return owner;
    }
}
