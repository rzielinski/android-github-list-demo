package com.dreddi.android.githublist.fragment.repodetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dreddi.android.githublist.R;
import com.dreddi.android.githublist.data.model.Repo;
import com.dreddi.android.githublist.utils.StringUtils;

public class RepoDetailsFragment extends Fragment implements RepoDetailsView {

    public static final String TAG = RepoDetailsFragment.class.getSimpleName();

    private static final String ARG_REPO = "repo";

    private Repo repo;
    private TextView repoName;
    private TextView repoDescr;
    private TextView repoWatch;
    private TextView repoStars;
    private TextView repoFork;
    private TextView seeMore;
    private ImageView avatar;

    public static RepoDetailsFragment newInstance(Repo repo) {
        RepoDetailsFragment fragment = new RepoDetailsFragment();
        Bundle params = new Bundle();
        if (repo != null) {
            params.putSerializable(ARG_REPO, repo);
        }
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateView();
    }

    @Override
    public void setRepo(Repo repo) {
        this.repo = repo;
        updateView();
    }

    private void readArguments() {
        if ((getArguments() != null) && getArguments().containsKey(ARG_REPO)) {
            repo = (Repo) getArguments().getSerializable(ARG_REPO);
        }
    }

    private View getView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repo_details, container, false);

        avatar = view.findViewById(R.id.fragment_repo_details_avatar);
        repoName = view.findViewById(R.id.fragment_repo_details_name);
        repoDescr = view.findViewById(R.id.fragment_repo_details_descr);
        repoWatch = view.findViewById(R.id.fragment_repo_details_watch);
        repoStars = view.findViewById(R.id.fragment_repo_details_stars);
        repoFork = view.findViewById(R.id.fragment_repo_details_fork);

        seeMore = view.findViewById(R.id.fragment_repo_details_see_more);
        seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRepoHome();
            }
        });

        return view;
    }

    private void updateView() {

        if (repo == null) {
            return;
        }

        repoName.setText(repo.getName());
        repoDescr.setText(repo.getDescription());

        repoWatch.setText(StringUtils.formatCount(
                getActivity(), repo.getWatchersCount()));
        repoStars.setText(StringUtils.formatCount(
                getActivity(), repo.getStargazersCount()));
        repoFork.setText(StringUtils.formatCount(
                getActivity(), repo.getForksCount()));

        if (repo.getOwner() != null) {
            Glide.with(this)
                    .load(repo.getOwner().getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform()
                            .placeholder(R.drawable.ic_person))
                    .into(avatar);
        }
    }

    private void showRepoHome() {
        if (repo == null) {
            return;
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(repo.getHtmlUrl())));
    }
}
