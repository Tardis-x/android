package devfest.controller.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import devfest.controller.BaseActivity;
import devfest.controller.R;
import devfest.controller.adapters.SpeakersAdapter;
import devfest.controller.model.Speaker;
import devfest.controller.utils.FB;

/**
 * Created by Brusd on 7/17/2016.
 */


public class SpeakersFragment extends Fragment {

    private static final String TAG = NewsFragment.class.getName();
    private SpeakerFragmentInteractionListener mCallBack;

    public interface SpeakerFragmentInteractionListener {
        void onSpeakerSelected(ImageView speakerImage, Speaker speaker, int position);
    }

    private View v;
    private Context mContext;
    private ArrayList<Speaker> speakers;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private FB fb;

    private SpeakersAdapter.OnSpeakerClickListener mSpeakersItemClickListener = new SpeakersAdapter.OnSpeakerClickListener() {
        @Override
        public void onSpeakerClick(ImageView speakerImage, Speaker speaker, int position) {
            Log.d(TAG, "onClick: pos " + position + " speaker : " + speaker);
            mCallBack.onSpeakerSelected(speakerImage, speaker, position);
        }
    };


    public static SpeakersFragment newInstance() {
        SpeakersFragment fragment = new SpeakersFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_speakers, container, false);
        mContext = getActivity();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.speakers_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        fb = FB.getInstance();
        getSpeakers();


        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallBack = ((SpeakerFragmentInteractionListener) getActivity());
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SpeakerFragmentInteractionListener");
        }
    }

    public void getSpeakers() {
        DatabaseReference myRef = fb.getSpeakersRef();
        myRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        speakers = new ArrayList<Speaker>();
                        dataSnapshot.getChildrenCount();
                        for (DataSnapshot o : dataSnapshot.getChildren()) {
                            Speaker speaker = o.getValue(Speaker.class);
                            speakers.add(speaker);

                        }
                        initList(speakers);
                        Log.e(TAG, "Current Childe" + speakers.size());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                    }
                });
    }

    private void initList(ArrayList<Speaker> dataSnapshots) {
        // specify an adapter (see also next example)
        SpeakersAdapter mAdapter = new SpeakersAdapter(dataSnapshots, (BaseActivity) getActivity(), mSpeakersItemClickListener);
        mRecyclerView.setItemViewCacheSize(dataSnapshots.size());
        mRecyclerView.setAdapter(mAdapter);
    }
}
