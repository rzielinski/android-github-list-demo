package com.dreddi.android.githublist.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dreddi.android.githublist.R;
import com.dreddi.android.githublist.fragment.repolist.RepoListFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private boolean isDetailsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
    }

    private void setView() {
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.activity_main_toolbar);
        isDetailsVisible = findViewById(R.id.activity_main_fragment_detail) != null;
        setFragments();
        setSupportActionBar(toolbar);
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

    }
}
