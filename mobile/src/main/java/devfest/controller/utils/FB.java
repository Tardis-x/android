package devfest.controller.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Brusd on 7/16/2016.
 */

public class FB {
    private FirebaseDatabase database;
    private static FB fb;
    public FirebaseAuth mAuth;
    private StorageReference storageRef;
    public String BASE_URL = "prod/";
//    public String BASE_URL = "team-dev/";


    public FB() {
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();

    }

    public static FB getInstance() {
        if (fb == null)
            fb = new FB();
        return fb;
    }

    public DatabaseReference getUserRef() {
        return database.getReference(BASE_URL + Constant.USER);
    }

    public DatabaseReference getNewsRef() {
        return database.getReference(BASE_URL + Constant.NEWS);
    }
    public StorageReference getImageRoot(){
        return storageRef;
    }
//
//    public DatabaseReference getTVProgramRef(){
//        return database.getReference(Constant.PROGRAM);
//    }

}
