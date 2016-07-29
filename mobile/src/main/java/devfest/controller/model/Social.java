package devfest.controller.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Brusd on 7/24/2016.
 */

public class Social implements Parcelable {
    private String icon;
    private String link;
    private String name;


    public Social() {
    }

    protected Social(Parcel in) {
        this.icon = in.readString();
        this.link = in.readString();
        this.name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.icon);
        dest.writeString(this.link);
        dest.writeString(this.name);
    }


    public static final Parcelable.Creator<Social> CREATOR = new Parcelable.Creator<Social>() {
        @Override
        public Social createFromParcel(Parcel source) {
            return new Social(source);
        }

        @Override
        public Social[] newArray(int size) {
            return new Social[size];
        }
    };
}
