package devfest.controller.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Brusd on 7/17/2016.
 */

public class Speaker implements Parcelable {


    public long id;
    public String name;
    public String title;
    public String photoUrl;
    public String bio;
    public String company;
    public String country;
    public String featured;
    public ArrayList<Social> socials;
    public ArrayList<String> tags;

    public Speaker(long id, String name, String title, String photoUrl, String bio, String company, String country, String featured, ArrayList<Social> socials, ArrayList<String> tags) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.photoUrl = photoUrl;
        this.bio = bio;
        this.company = company;
        this.country = country;
        this.featured = featured;
        this.socials = socials;
        this.tags = tags;
    }

    public Speaker() {

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.title);
        dest.writeString(this.photoUrl);
        dest.writeString(this.bio);
        dest.writeString(this.company);
        dest.writeString(this.country);
        dest.writeString(this.featured);
        dest.writeList(this.socials);
        dest.writeStringList(this.tags);
    }

    protected Speaker(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.title = in.readString();
        this.photoUrl = in.readString();
        this.bio = in.readString();
        this.company = in.readString();
        this.country = in.readString();
        this.featured = in.readString();
        this.socials = new ArrayList<Social>();
        in.readList(this.socials, Social.class.getClassLoader());
        this.tags = in.createStringArrayList();
    }

    public static final Creator<Speaker> CREATOR = new Creator<Speaker>() {
        @Override
        public Speaker createFromParcel(Parcel source) {
            return new Speaker(source);
        }

        @Override
        public Speaker[] newArray(int size) {
            return new Speaker[size];
        }
    };
}
