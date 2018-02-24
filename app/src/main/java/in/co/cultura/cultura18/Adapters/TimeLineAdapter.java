package in.co.cultura.cultura18.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.cultura.cultura18.Activities.Schedule;
import in.co.cultura.cultura18.Model.ScheduleObject;
import in.co.cultura.cultura18.R;


public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {
    private ArrayList<ScheduleObject> contents;
    public TimeLineAdapter(ArrayList<ScheduleObject> contents) {
        this.contents = contents;
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.list_item_card_small, parent, false);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        ScheduleObject scheduleObject = contents.get(position);
        holder.event.setText(scheduleObject.getEventName());
        holder.location.setText(scheduleObject.getLocation());
        holder.time.setText(scheduleObject.getTime());
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    class TimeLineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rs_time_marker)
        TimelineView mTimelineView;
        TextView time, event, location;

        TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mTimelineView.initLine(viewType);
            time = itemView.findViewById(R.id.rs_text_timeline_date);
            event = itemView.findViewById(R.id.rs_event_name);
            location = itemView.findViewById(R.id.rs_location);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());

    }
}
