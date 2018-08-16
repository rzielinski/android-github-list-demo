package com.dreddi.android.githublist.fragment.repolist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dreddi.android.githublist.R;
import com.dreddi.android.githublist.activity.MainActivityView;
import com.dreddi.android.githublist.data.NetworkService;
import com.dreddi.android.githublist.data.model.Repo;
import com.dreddi.android.githublist.data.model.RepoList;
import com.dreddi.android.githublist.views.OnRepoClickListener;
import com.dreddi.android.githublist.views.OnRepoScrollListener;
import com.dreddi.android.githublist.views.RepoListRecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RepoListFragment extends Fragment implements RepoListView, OnRepoClickListener, OnRepoScrollListener {

    public static final String TAG = RepoListFragment.class.getSimpleName();

    private static final int ITEMS_PER_PAGE = 10;

    private int currentPage;
    private boolean isLoading;
    private boolean isAutoSelectFirstSymbol;

    private List<Repo> repoItemList;
    private RepoListRecyclerView repoListRecyclerView;

    private CompositeDisposable disposables = new CompositeDisposable();

    public static RepoListFragment newInstance() {
        return new RepoListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repoItemList = new ArrayList<>();
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
    public void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }

    @Override
    public void setAutoSelectFirst() {
        isAutoSelectFirstSymbol = true;
    }

    @Override
    public void onRepoClicked(Repo repo) {
        notifyRepoSelected(repo);
    }

    @Override
    public void loadMoreItems() {
        currentPage++;
        fetchData();
    }

    @Override
    public boolean isLastPage() {
        return false;
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    private View getView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        repoListRecyclerView = view.findViewById(R.id.fragment_repo_list);
        repoListRecyclerView.setOnRepoClickListener(this);
        repoListRecyclerView.setOnRepoScrollListener(this);
        return view;
    }

    private void updateView() {
        if (repoItemList.size() > 0) {
            repoListRecyclerView.addItems(repoItemList);
        } else {
            fetchData();
        }
    }

    private void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
        repoListRecyclerView.setIsLoading(isLoading);
    }

    private void showError() {
        Toast.makeText(getActivity(), getString(R.string.fragment_repo_list_error), Toast.LENGTH_LONG).show();
    }

    private void appendData(RepoList repoList) {
        if ((repoList == null) || (repoList.getRepoItemsList() == null)) {
            return;
        }
        repoItemList.addAll(repoList.getRepoItemsList());
        repoListRecyclerView.addItems(repoList.getRepoItemsList());
        notifyAutoSelect();
    }

    private void notifyAutoSelect() {
        if (isAutoSelectFirstSymbol && (repoItemList.size() > 0)) {
            notifyRepoSelected(repoItemList.get(0));
            isAutoSelectFirstSymbol = false;
        }
    }

    private void notifyRepoSelected(Repo repo) {
        if (getActivity() instanceof MainActivityView) {
            ((MainActivityView)getActivity()).showRepoDetails(repo);
        }
    }

    private void fetchData() {
        setLoading(true);
        disposables.add(
                NetworkService.getInstance().getAndroidTrendingRepoList(currentPage, ITEMS_PER_PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<RepoList>() {
                            @Override
                            public void accept(RepoList repoList) {
                                appendData(repoList);
                                setLoading(false);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) {
                                setLoading(false);
                                showError();
                            }
                        })
        );
    }
}
