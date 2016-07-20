package devfest.controller.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Brusd on 7/17/2016.
 */

public class News implements Parcelable {

    public String id;
    public String title;
    public String image;
    public String posted;
    public String brief;
    public String fullText;
    public String primaryColor;
    public String secondaryColor;


    public News() {

    }

    public News(String id, String title, String image, String posted, String brief, String fullText, String primaryColor, String secondaryColor) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.posted = posted;
        this.brief = brief;
        this.fullText = fullText;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.image);
        dest.writeString(this.posted);
        dest.writeString(this.brief);
        dest.writeString(this.fullText);
        dest.writeString(this.primaryColor);
        dest.writeString(this.secondaryColor);
    }

    protected News(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.image = in.readString();
        this.posted = in.readString();
        this.brief = in.readString();
        this.fullText = in.readString();
        this.primaryColor = in.readString();
        this.secondaryColor = in.readString();
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
