package devfest.controller.adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import devfest.controller.R;

import devfest.controller.utils.FB;

/**
 * Created by Brusd on 7/24/2016.
 */

public class TagAdaptor extends RecyclerView.Adapter<TagAdaptor.ViewHolder> {
    private ArrayList<String> mDataset;
    private Context mContext;
    private FB fb;

    public TagAdaptor(ArrayList<String> myDataset, Context mContext) {
        this.mDataset = myDataset;
        this.mContext = mContext;
        fb = FB.getInstance();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TagAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_social, parent, false);
        // set the view's size, margins, paddings and layout parameters

        TagAdaptor.ViewHolder vh = new TagAdaptor.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final TagAdaptor.ViewHolder holder, int position) {
        final String tag = mDataset.get(position);
        holder.mSocialImage.setText(tag);


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