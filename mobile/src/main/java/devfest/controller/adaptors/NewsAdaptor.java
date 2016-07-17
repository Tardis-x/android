package devfest.controller.adaptors;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import devfest.controller.R;
import devfest.controller.model.News;
import devfest.controller.utils.FB;

/**
 * Created by Brusd on 7/17/2016.
 */

public class NewsAdaptor  extends RecyclerView.Adapter<NewsAdaptor.ViewHolder> {
    private ArrayList<News> mDataset;
    private Context mContext;
    private FB fb;

    public NewsAdaptor(ArrayList<News> myDataset, Context mContext) {
        this.mDataset = myDataset;
        this.mContext = mContext;
        fb = FB.getInstance();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NewsAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        News news = mDataset.get(position);
        holder.mNewsTitle.setText(news.getTitle());
        holder.descTextView.setText(news.getBrief());

        Log.e("URL", news.getImage());
        if(news.getImage().startsWith("http")){
            String url = news.getImage();
            Log.e("URL", url);
            Glide.with(mContext)
                    .load(url)
                    .into(holder.mMaineNewsImage);
        }else {
            Log.e("URL", fb.getImageRoot().toString());
         fb.getImageRoot().child(news.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    String url = uri.toString();
                    Log.e("URL", url);
                    Glide.with(mContext)
                            .load(url)
                            .into(holder.mMaineNewsImage);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
        }

        holder.mNewsTitle.setBackgroundColor(Color.parseColor(news.getPrimaryColor()));
//        holder.cardView.setBackgroundColor(Color.parseColor(news.getSecondaryColor()));


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView mNewsTitle;
        private ImageView mMaineNewsImage;
        private TextView descTextView;
        private CardView cardView;

        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            mNewsTitle = (TextView) v.findViewById(R.id.news_title_text_view);
            mMaineNewsImage = (ImageView) v.findViewById(R.id.main_new_image);
            descTextView =(TextView)v.findViewById(R.id.news_short_text_view);
        }
    }
}
