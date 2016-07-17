package devfest.controller.model;

/**
 * Created by Brusd on 7/17/2016.
 */

public class Speaker {


    public long id;
    public String name;
    public String title;
    public String photoUrl;
    public String bio;
    public String company;
    public String country;
    public boolean featured;


//  socials
//  tags

    public Speaker() {

    }

    public Speaker(long id,
                   String name,
                   String title,
                   String photoUrl,
                   String bio,
                   String company,
                   String country,
                   boolean featured) {

        this.id = id;
        this.name = name;
        this.title = title;
        this.photoUrl = photoUrl;
        this.bio = bio;
        this.company = company;
        this.country = country;
        this.featured = featured;

    }

}
