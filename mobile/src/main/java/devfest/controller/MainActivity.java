package devfest.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import devfest.controller.fragments.NewsFragment;
import devfest.controller.fragments.ScheduleFragment;
import devfest.controller.fragments.SpeakersFragment;
import devfest.controller.utils.FB;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();

    private FB fb;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        fb = FB.getInstance();
        user = fb.mAuth.getCurrentUser();
        Log.e("WHF?", "MainActivity onResume");

        appBarLayout = (AppBarLayout) findViewById(R.id.appBar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        addFragment(NewsFragment.newInstance());

    }

    private void addFragment(Fragment newFragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_holder, newFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = fb.mAuth.getCurrentUser();
        if (user != null) {
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        } else {
            goToLoginScreen();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_schedule) {
//            addFragment(ScheduleFragment.newInstance());
        } else if (id == R.id.blog_category) {
            addFragment(NewsFragment.newInstance());
        } else if (id == R.id.nav_speakers) {
//            addFragment(SpeakersFragment.newInstance());
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=devfest2016.gdg");
            startActivity(Intent.createChooser(intent, "Share with"));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            fb.mAuth.signOut();
            goToLoginScreen();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
