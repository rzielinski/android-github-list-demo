package com.dreddi.android.githublist.views;

public interface OnRepoScrollListener {
    void loadMoreItems();
    boolean isLastPage();
    boolean isLoading();
}
