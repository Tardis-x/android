package devfest.controller;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import devfest.controller.fragments.NewsFragment;
import devfest.controller.fragments.ScheduleFragment;
import devfest.controller.fragments.SpeakersFragment;
import devfest.controller.model.User;
import devfest.controller.utils.FB;
import devfest.controller.utils.Utils;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();

    private FB fb;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private FrameLayout frameLayout;

    private TextView mUserName;
    private TextView mUserEmail;
    private ImageView mUserPicture;
    private View mNavigationHeader;


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

        initNavigationView();

        showFragment(NewsFragment.newInstance());
    }

    private void initNavigationView() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mNavigationHeader = navigationView.inflateHeaderView(R.layout.nav_header_main);
    }

    private void initUserData(View navigationHeader) {
        Log.d(TAG, "initUserData: ");
        mUserName = (TextView) navigationHeader.findViewById(R.id.tvUserName);
        mUserEmail = (TextView) navigationHeader.findViewById(R.id.tvUserEmail);
        mUserPicture = (ImageView) navigationHeader.findViewById(R.id.ivUserImage);
        fb.getUserRef().child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Log.d(TAG, "onDataChange: user: " + user);
                mUserName.setText(Utils.capitalizeName(user.getUserName()));
                mUserEmail.setText(user.getEmail());
                Glide.with(MainActivity.this)
                        .load(user.imageURL)
                        .asBitmap()
                        .centerCrop()
                        .into(new BitmapImageViewTarget(mUserPicture) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                mUserPicture.setImageDrawable(circularBitmapDrawable);
                            }
                        });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        user = fb.mAuth.getCurrentUser();
        if (user != null) {
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            if (user.isAnonymous()) {

            } else {
                initUserData(mNavigationHeader);
            }
        } else {
            goToLoginScreen();
        }
    }

    private void showFragment(Fragment newFragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_holder, newFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_schedule:
                showFragment(ScheduleFragment.newInstance());
                break;
            case R.id.nav_blog:
                showFragment(NewsFragment.newInstance());
                break;
            case R.id.nav_speakers:
                showFragment(SpeakersFragment.newInstance());
                break;
            case R.id.nav_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=devfest2016.gdg");
                startActivity(Intent.createChooser(intent, "Share with"));
                break;
            case R.id.nav_invite:
                // todo implement invite
                break;
            case R.id.nav_logout:
                fb.mAuth.signOut();
                goToLoginScreen();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
