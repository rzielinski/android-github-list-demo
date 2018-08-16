package com.dreddi.android.githublist.fragment.repolist;

import android.arch.lifecycle.ViewModel;

import com.dreddi.android.githublist.data.model.Repo;

import java.util.ArrayList;
import java.util.List;

public class RepoListViewModel extends ViewModel {

    private int currentPage;
    private Repo selectedItem;
    private List<Repo> repoItemList;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void addPage() {
        currentPage++;
    }

    public List<Repo> getItems() {
        return repoItemList;
    }

    public void setItems(List<Repo> repoItemList) {
        this.repoItemList = repoItemList;
    }

    public void addItems(List<Repo> repoItemList) {
        if (this.repoItemList == null) {
            this.repoItemList = new ArrayList<>();
        }
        this.repoItemList.addAll(repoItemList);
    }

    public Repo getFirstItem() {
        if ((repoItemList != null) && (repoItemList.size() > 0)) {
            return repoItemList.get(0);
        } else {
            return null;
        }
    }

    public Repo getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Repo repo) {
        this.selectedItem = repo;
    }
}
