package com.dreddi.android.githublist.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dreddi.android.githublist.R;
import com.dreddi.android.githublist.data.model.Repo;
import com.dreddi.android.githublist.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RepoItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOADING = 1;

    private boolean isLoading;
    private List<Repo> repoItemsList;
    private OnRepoClickListener onRepoClickListener;

    public RepoItemAdapter() {
        repoItemsList = new ArrayList<>();
    }

    public void setOnRepoClickListener(OnRepoClickListener onRepoClickListener) {
        this.onRepoClickListener = onRepoClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.view_repo_list_item, parent, false);
            viewHolder = new RepoItemViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.view_repo_list_loading, parent, false);
            viewHolder = new RepoItemLoadingViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            ((RepoItemViewHolder) holder).bind(repoItemsList.get(position));
        };
    }

    @Override
    public int getItemCount() {
        return isLoading ? repoItemsList.size() + 1: repoItemsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if ((isLoading) && (position == repoItemsList.size())) {
            return TYPE_LOADING;
        }
        return TYPE_ITEM;
    }

    public void add(Repo repo) {
        if (repo == null) {
            return;
        }
        repoItemsList.add(repo);
        notifyItemInserted(repoItemsList.size() - 1);
    }

    public void addAll(List<Repo> repoItemsList) {
        if (repoItemsList == null) {
            return;
        }
        for (Repo repo : repoItemsList) {
            add(repo);
        }
    }

    public List<Repo> getAll() {
        return repoItemsList;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
        notifyDataSetChanged();
    }

    private class RepoItemViewHolder extends RecyclerView.ViewHolder {

        private TextView repoName;
        private TextView repoDescr;
        private TextView repoStars;
        private LinearLayout layout;

        public RepoItemViewHolder(View view) {
            super(view);
            repoName = view.findViewById(R.id.view_repo_list_item_name);
            repoDescr = view.findViewById(R.id.view_repo_list_item_descr);
            repoStars = view.findViewById(R.id.view_repo_list_item_stars);
            layout = view.findViewById(R.id.view_repo_list_item_layout);
        }

        public void bind(final Repo repo) {

            if (repo == null) {
                return;
            }

            repoName.setText(repo.getName());
            repoDescr.setText(repo.getDescription());
            repoStars.setText(StringUtils.formatCount(
                    itemView.getContext(), repo.getStargazersCount()));

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRepoClickListener != null) {
                        onRepoClickListener.onRepoClicked(repo);
                    }
                }
            });
        }
    }

    private class RepoItemLoadingViewHolder extends RecyclerView.ViewHolder {
        public RepoItemLoadingViewHolder(View view) {
            super(view);
        }
    }
}