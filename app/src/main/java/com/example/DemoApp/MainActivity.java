package com.example.DemoApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.MenuItem;


import com.example.DemoApp.models.FRAGMENT_ACTIONS;
import com.example.DemoApp.views.ContentFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FragmentTransaction fragmentTransaction;
    private ContentFragment mButtonsContentFragment, mGoogleContentFragment, mKotlinProjectsContentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_menu, R.string.close_menu);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void openButtonFragment() {
        if (mButtonsContentFragment == null) {
            mButtonsContentFragment = new ContentFragment();
        }
        Bundle arguments = new Bundle();
        arguments.putSerializable( ContentFragment.FRAGMENT_ACTION , FRAGMENT_ACTIONS.OPEN_BUTTONS_VIEW);
        mButtonsContentFragment.setArguments(arguments);
        fragmentTransaction.replace(R.id.fragment_container, mButtonsContentFragment);
    }

    private void openWebGoogleFragment() {
        if (mGoogleContentFragment == null) {
            mGoogleContentFragment = new ContentFragment();
        }
        Bundle arguments = new Bundle();
        arguments.putSerializable(  ContentFragment.FRAGMENT_ACTION , FRAGMENT_ACTIONS.OPEN_GOOGLE);
        arguments.putString( "WEB" , "http://google.com");
        mGoogleContentFragment.setArguments(arguments);
        fragmentTransaction.replace(R.id.fragment_container, mGoogleContentFragment);
    }

    private void openGitHutFragment() {
        if (mKotlinProjectsContentFragment == null) {
            mKotlinProjectsContentFragment = new ContentFragment();
        }
        Bundle arguments = new Bundle();
        arguments.putSerializable(  ContentFragment.FRAGMENT_ACTION , FRAGMENT_ACTIONS.OPEN_KOTLIN_PROJECTS);
        fragmentTransaction.replace(R.id.fragment_container,mKotlinProjectsContentFragment);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.nav_item_1:
                openWebGoogleFragment();
                break;
            case R.id.nav_item_2:
                openButtonFragment();
                break;
            case R.id.nav_item_3:
                openGitHutFragment();
                break;
            default:
                return false;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        return false;
    }
}