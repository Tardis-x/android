package devfest.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Brusd on 7/16/2016.
 */

public class BaseActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    public FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


    }
    public void goToLoginScreen(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        Log.e("WHF?", "goToLoginScreen");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
