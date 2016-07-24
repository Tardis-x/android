package devfest.controller.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import devfest.controller.R;
import devfest.controller.adaptors.NewsAdaptor;
import devfest.controller.model.News;
import devfest.controller.utils.FB;

/**
 * Created by Brusd on 7/16/2016.
 */

public class NewsFragment extends Fragment {

    private static final String TAG = NewsFragment.class.getName();
    private View v;
    private Context mContext;
    private ArrayList<News> newses;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private FB fb;



    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_news, container, false);
        mContext = getActivity();
        mRecyclerView = (RecyclerView)v.findViewById(R.id.news_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        fb = FB.getInstance();
        getNews();


        return v;
    }

    private void getNews() {

        DatabaseReference myRef = fb.getNewsRef();
        myRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        newses = new ArrayList<News>();
                        dataSnapshot.getChildrenCount();
                        for (DataSnapshot o : dataSnapshot.getChildren()) {
                            News news = o.getValue(News.class);
                            newses.add(news);

                        }
                        initList(newses);
                        Log.e(TAG, "Current Childe" + dataSnapshot.getChildrenCount());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }

    private void initList(ArrayList<News> dataSnapshots) {
        // specify an adapter (see also next example)
        NewsAdaptor mAdapter = new NewsAdaptor(dataSnapshots, mContext);
        mRecyclerView.setItemViewCacheSize(dataSnapshots.size());
        mRecyclerView.setAdapter(mAdapter);
    }

}
