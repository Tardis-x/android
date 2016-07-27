package devfest.controller.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import devfest.controller.R;
import devfest.controller.model.Social;
import devfest.controller.utils.FB;

/**
 * Created by Brusd on 7/24/2016.
 */

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.ViewHolder> {
    private ArrayList<Social> mDataset;
    private Context mContext;
    private FB fb;

    public SocialAdapter(ArrayList<Social> myDataset, Context mContext) {
        this.mDataset = myDataset;
        this.mContext = mContext;
        fb = FB.getInstance();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SocialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_social, parent, false);
        // set the view's size, margins, paddings and layout parameters

        SocialAdapter.ViewHolder vh = new SocialAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final SocialAdapter.ViewHolder holder, int position) {
        final Social social = mDataset.get(position);
        holder.mSocialImage.setText(social.getName());
        holder.mSocialImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(social.getLink()));
                mContext.startActivity(i);
            }
        });


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

        private TextView mSocialImage;


        public ViewHolder(View v) {
            super(v);

            mSocialImage = (TextView) v.findViewById(R.id.social_image_view);
            setIsRecyclable(false);
        }
    }
}
