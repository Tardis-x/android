package devfest.controller.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import devfest.controller.R;
import devfest.controller.model.News;
import devfest.controller.utils.FB;

/**
 * Created by Brusd on 7/17/2016.
 */

public class ScheduleFragment extends Fragment {

    private static final String TAG = ScheduleFragment.class.getName();
    private View v;
    private Context mContext;




    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_schedule, container, false);
        mContext = getActivity();



        return v;
    }
}
