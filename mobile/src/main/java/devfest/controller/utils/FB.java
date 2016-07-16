package devfest.controller.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Brusd on 7/16/2016.
 */

public class FB  {
    private FirebaseDatabase database;
    private static FB fb;
    public FirebaseAuth mAuth;



    public FB(){
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        mAuth = FirebaseAuth.getInstance();


    }

    public static FB getInstance(){
        if(fb == null)
            fb = new FB();
        return fb;
    }

    public DatabaseReference getUserRef(){
        return database.getReference(Constant.USER);
    }

    public DatabaseReference getNewsRef(){
        return database.getReference(Constant.NEWS);
    }
//
//    public DatabaseReference getTVProgramRef(){
//        return database.getReference(Constant.PROGRAM);
//    }

}
