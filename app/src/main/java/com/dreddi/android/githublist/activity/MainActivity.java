package com.dreddi.android.githublist.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dreddi.android.githublist.R;
import com.dreddi.android.githublist.data.model.Repo;
import com.dreddi.android.githublist.fragment.repodetails.RepoDetailsFragment;
import com.dreddi.android.githublist.fragment.repodetails.RepoDetailsView;
import com.dreddi.android.githublist.fragment.repolist.RepoListFragment;
import com.dreddi.android.githublist.fragment.repolist.RepoListView;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private Toolbar toolbar;
    private boolean isDetailsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
    }

    @Override
    public void showRepoDetails(Repo repo) {

        if (repo == null) {
            return;
        }

        FragmentManager fm = getSupportFragmentManager();

        if (isDetailsVisible) {

            Fragment fragment = fm.findFragmentByTag(RepoDetailsFragment.TAG);
            if (fragment instanceof RepoDetailsView) {
                ((RepoDetailsView) fragment).setRepo(repo);
            }

        } else {

            fm.beginTransaction()
                    .replace(R.id.activity_main_fragment,
                            RepoDetailsFragment.newInstance(repo), RepoDetailsFragment.TAG)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void setView() {
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.activity_main_toolbar);
        isDetailsVisible = findViewById(R.id.activity_main_fragment_detail) != null;
        setSupportActionBar(toolbar);
        setFragments();
    }

    private void setFragments() {

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragmentList = fm.findFragmentByTag(RepoListFragment.TAG);
        if (fragmentList == null) {
            fragmentList = RepoListFragment.newInstance();
            fm.beginTransaction()
                    .replace(R.id.activity_main_fragment, fragmentList, RepoListFragment.TAG)
                    .commit();
        }

        if (isDetailsVisible) {

            Fragment fragmentDetails = fm.findFragmentByTag(RepoDetailsFragment.TAG);
            if (fragmentDetails != null) {
                fm.popBackStack();
            }

            fm.beginTransaction()
                    .replace(R.id.activity_main_fragment_detail,
                            RepoDetailsFragment.newInstance(), RepoDetailsFragment.TAG)
                    .commit();

            ((RepoListView)fragmentList).setAutoSelect();
        }
    }
}
