package in.co.cultura.cultura18.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.cultura.cultura18.Adapters.TimeLineAdapter;
import in.co.cultura.cultura18.Model.ScheduleObject;
import in.co.cultura.cultura18.R;


public class RecyclerViewFragment extends Fragment {

    private static final boolean GRID_LAYOUT = false;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static RecyclerViewFragment newInstance(int day) {
        Bundle bundle = new Bundle();
        bundle.putInt("day", day);
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        recyclerViewFragment.setArguments(bundle);
        return recyclerViewFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("SavedInstanceState");
        System.out.println(getArguments().getInt("day"));

        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);


        final ArrayList<ScheduleObject> day1 = new ArrayList<>();
        final ArrayList<ScheduleObject> day2 = new ArrayList<>();
        day1.add(new ScheduleObject("Inauguration", "Main Stage", "09:00 am - 10:00 am"));
        day1.add(new ScheduleObject("Desi Beats", "Main Stage", "10:00 am - 12:00 pm"));
        day1.add(new ScheduleObject("Kinkini", "Dwani", "10:00 am - 12:00 pm"));
        day1.add(new ScheduleObject("Street Play", "Side Stage", "10:00 am - 1:00 pm"));
        day1.add(new ScheduleObject("EYESHOT", "Mech AV Hall Ground Floor", "10:00 am - 4:00 pm"));
        day1.add(new ScheduleObject("Turn Coat", "Mech AV Hall Second Floor", "10:00 am - 12:00 pm"));
        day1.add(new ScheduleObject("Rasaprashne", "Civil AV Hall Fourth Floor", "10:00 am - 12:00 pm"));
        day1.add(new ScheduleObject("Mini Militia", "Mech Auditorium Fifth Floor", "10:00 am - 04:00 pm"));
        day1.add(new ScheduleObject("Circuit Breaker", "EC Lab", "10:00 am - 01:00 pm"));
        day1.add(new ScheduleObject("COD + FIFA + NFS-MW", "CS Lab", "10:00 am - 04:00 pm"));
        day1.add(new ScheduleObject("Cryptography", "IS Lab", "10:00 am - 12:00 pm"));
        day1.add(new ScheduleObject("DOTA 2", "PCD Lab", "10:00 am - 04:00 pm"));
        day1.add(new ScheduleObject("CS:GO", "CAED Lab 3", "10:00 am - 04:00 pm"));
        day1.add(new ScheduleObject("Blind Art", "ME 001", "10:00 am - 01:00 pm"));
        day1.add(new ScheduleObject("Blitz Chess", "BS 102", "10:00 am - 01:00 pm"));
        day1.add(new ScheduleObject("Paper Wings", "Basketball Court", "10:00 am - 12:00 pm"));
        day1.add(new ScheduleObject("Treasure Hunt", "Basketball Court", "10:00 am - 04:00 pm"));
        day1.add(new ScheduleObject("Mad Ads", "Main Stage", "12:00 pm - 02:00 pm"));
        day1.add(new ScheduleObject("Two for Tango", "Dwani", "12:00 pm - 02:00 pm"));
        day1.add(new ScheduleObject("Pictionary", "Mech AV Hall Second Floor", "12:00 pm - 02:00 pm"));
        day1.add(new ScheduleObject("Kalabhinaya", "Civil AV Hall Fourth Floor", "12:00 pm - 02:00 pm"));
        day1.add(new ScheduleObject("Technocrat", "IS Lab", "12:00 pm - 02:00 pm"));
        day1.add(new ScheduleObject("Hogathon", "Side Stage", "1:00 pm - 04:00 pm"));
        day1.add(new ScheduleObject("Switch Foot", "Main Stage", "02:00 pm - 04:00 pm"));
        day1.add(new ScheduleObject("Western Solo", "Dwani", "02:00 pm - 04:00 pm"));
        day1.add(new ScheduleObject("Web Designing", "IS Lab", "02:00 pm - 04:00 pm"));
        day1.add(new ScheduleObject("Fashion Show", "Main Stage", "04:00 pm - 06:00 pm"));
        day1.add(new ScheduleObject("Rave & Crave", "Main Stage", "07:00 pm - 08:00 pm"));


        day2.add(new ScheduleObject("Beatboxing", "Main Stage", "09:00 am - 11:00 am"));
        day2.add(new ScheduleObject("Indian Solo", "Dwani", "09:00 am - 10:00 am"));
        day2.add(new ScheduleObject("Anthyakshari", "Mech AV Hall Ground Floor", "09:00 am - 11:00 am"));
        day2.add(new ScheduleObject("Blindline", "Mech AV Hall Second Floor", "09:00 am - 11:00 am"));
        day2.add(new ScheduleObject("Tech Quiz", "Civil AV Hall Fourth Floor", "09:00 am - 11:00 am"));
        day2.add(new ScheduleObject("COD + FIFA + NFS-MW(Second Round/Finals)", "CS Lab", "10:00 am - 04:00 pm"));
        day2.add(new ScheduleObject("Robotics", "Mech Auditorium Fifth Floor ", "09:00 am - 03:00 pm"));
        day2.add(new ScheduleObject("Code Completion", "IS Lab", "09:00 am - 12:00 pm"));
        day2.add(new ScheduleObject("Ankura", "ME 001", "09:00 am - 11:00 am"));
        day2.add(new ScheduleObject("Face Painting", "BS 102", "09:00 am - 11:00 am"));
        day2.add(new ScheduleObject("Art Hunt", "BS 103", "09:00 am - 03:00 pm"));
        day2.add(new ScheduleObject("Solve a Mystery", "Basketball Court", "09:00 am - 03:00 pm"));
        day2.add(new ScheduleObject("DOTA 2(Second Round/Finals)", "PCD Lab", "10:00 am - 04:00 pm"));
        day2.add(new ScheduleObject("CS:GO(Second Round/Finals)", "CAED Lab 3", "10:00 am - 04:00 pm"));
        day2.add(new ScheduleObject("Kick In Crew", "Main Stage", "11:00 am - 02:00 pm"));
        day2.add(new ScheduleObject("Pakka Kannadiga", "Mech AV Hall Ground Floor", "11:00 am - 01:00 pm"));
        day2.add(new ScheduleObject("JAM", "Mech AV Hall Second Floor", "11:00 am - 01:00 pm"));
        day2.add(new ScheduleObject("Spell Bee", "BS102", "11:00 am - 01:00 pm"));
        day2.add(new ScheduleObject("Air Crash", "Civil AV Hall Fourth Floor", "11:00 am - 01:00 pm"));
        day2.add(new ScheduleObject("Tech Hunt", "IS Lab", "12:00 pm - 03:00 pm"));
        day2.add(new ScheduleObject("Mime", "Dwani", "01:00 pm - 03:00 pm"));
        day2.add(new ScheduleObject("1 vs 1", "Side Stage", "01:00 pm - 03:00 pm"));
        day2.add(new ScheduleObject("Valedictory", "Dwani", "03:00 pm - 05:00 pm"));
        day2.add(new ScheduleObject("AJ", "Main Stage", "05:00 pm - 08:00 pm"));

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        if (getArguments().getInt("day") == 0) {
            mRecyclerView.setAdapter(new TimeLineAdapter(day1));
        } else {
            mRecyclerView.setAdapter(new TimeLineAdapter(day2));

        }
    }

}
