package devfest.controller.utils;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Brusd on 7/16/2016.
 */

public class FB  {
//    private FirebaseDatabase database;
    private static FB fb;
    public FirebaseAuth mAuth;



    public FB(){
//        database = FirebaseDatabase.getInstance();
//        database.setPersistenceEnabled(true);
        mAuth = FirebaseAuth.getInstance();


    }

    public static FB getInstance(){
        if(fb == null)
            fb = new FB();
        return fb;
    }

//    public DatabaseReference getCategoryRef(){
//        return database.getReference(Constant.CATEGORY);
//    }
//
//    public DatabaseReference getChannelsRef(){
//        return database.getReference(Constant.CHANNEL);
//    }
//
//    public DatabaseReference getTVProgramRef(){
//        return database.getReference(Constant.PROGRAM);
//    }

}
