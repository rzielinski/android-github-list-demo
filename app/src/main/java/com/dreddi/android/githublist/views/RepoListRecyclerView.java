package com.dreddi.android.githublist.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.dreddi.android.githublist.data.model.Repo;

import java.util.List;

public class RepoListRecyclerView extends RecyclerView {

    private RepoItemAdapter adapter;
    private LinearLayoutManager layoutManager;
    private OnRepoScrollListener onRepoScrollListener;

    public RepoListRecyclerView(Context context) {
        this(context, null);
    }

    public RepoListRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RepoListRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setView(context);
    }

    public void addItems(List<Repo> repoItemsList) {
        adapter.addAll(repoItemsList);
    }

    public List<Repo> getItems() {
        return adapter.getAll();
    }

    public void setIsLoading(boolean isLoading) {
        adapter.setIsLoading(isLoading);
    }

    public void setOnRepoClickListener(OnRepoClickListener onRepoClickListener) {
        adapter.setOnRepoClickListener(onRepoClickListener);
    }

    public void setOnRepoScrollListener(OnRepoScrollListener onRepoScrollListener) {
        this.onRepoScrollListener = onRepoScrollListener;
    }

    private void setView(Context context) {

        layoutManager = new LinearLayoutManager(context);
        setLayoutManager(layoutManager);

        adapter = new RepoItemAdapter();
        setAdapter(adapter);

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (onRepoScrollListener == null) {
                    return;
                }

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!onRepoScrollListener.isLoading() && !onRepoScrollListener.isLastPage()) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        onRepoScrollListener.loadMoreItems();
                    }
                }
            }
        });
    }
}
