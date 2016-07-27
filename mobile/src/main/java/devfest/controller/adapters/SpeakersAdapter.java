package devfest.controller.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import devfest.controller.BaseActivity;
import devfest.controller.R;
import devfest.controller.model.Speaker;
import devfest.controller.utils.FB;

/**
 * Created by Brusd on 7/17/2016.
 */

public class SpeakersAdapter extends RecyclerView.Adapter<SpeakersAdapter.ViewHolder> {
    private final OnSpeakerClickListener mOnSpeakerClickListener;
    private ArrayList<Speaker> mSpeakerList;
    private BaseActivity mContext;
    private FB fb;
    private static String url;

    public interface OnSpeakerClickListener {
        void onSpeakerClick(ImageView speakerImage, Speaker speaker, int position);
    }

    public SpeakersAdapter(ArrayList<Speaker> data, BaseActivity context, OnSpeakerClickListener listener) {
        mSpeakerList = data;
        mContext = context;
        mOnSpeakerClickListener = listener;
        fb = FB.getInstance();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SpeakersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_speaker, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new SpeakersAdapter.ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final SpeakersAdapter.ViewHolder holder, final int position) {
        final Speaker speaker = mSpeakerList.get(position);
        holder.speakerImage.setImageDrawable(null);

        holder.titleTextView.setText(speaker.title);
        holder.nameTextView.setText(speaker.name);
        holder.bioTextView.setText(speaker.bio);
        holder.countryCompanyTextView.setText(speaker.company+", "+ speaker.country);


        Log.e("URL", speaker.photoUrl);
        if (speaker.photoUrl.startsWith("http")) {
            url = speaker.photoUrl;
            Log.e("URL", url);
            Glide.with(mContext)
                    .load(url).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade()
                    .into(holder.speakerImage);
        } else {
            Log.e("URL", fb.getImageRoot().toString());
            fb.getImageRoot().child(speaker.photoUrl).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    speaker.photoUrl = uri.toString();
                    url = uri.toString();
                    Log.e("URL", url);
                    Glide.with(mContext)
                            .load(speaker.photoUrl).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade()
                            .into(holder.speakerImage);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
        }

        holder.speakerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnSpeakerClickListener != null) {
                    mOnSpeakerClickListener.onSpeakerClick(holder.speakerImage, speaker, position);
                }
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mSpeakerList != null ? mSpeakerList.size() : 0;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        ImageView speakerImage;
        TextView titleTextView;
        TextView nameTextView;
        TextView countryCompanyTextView;
        TextView bioTextView;
        public ViewHolder(View v) {
            super(v);
            speakerImage = (ImageView) v.findViewById(R.id.main_speakers_image);
            titleTextView = (TextView) v.findViewById(R.id.speakers_title_text_view);
            nameTextView = (TextView) v.findViewById(R.id.speakers_name_text_view);
            countryCompanyTextView = (TextView) v.findViewById(R.id.speakers_company_country_text_view);
            bioTextView =(TextView)v.findViewById(R.id.speakers_bio_text_view);

            setIsRecyclable(false);
        }
    }
}
