package in.co.cultura.cultura18.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.cultura.cultura18.Activities.AJDJ;
import in.co.cultura.cultura18.Activities.EventActivity;
import in.co.cultura.cultura18.Activities.SubEvent;
import in.co.cultura.cultura18.Model.EventObject;
import in.co.cultura.cultura18.R;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private ArrayList<EventObject> eventObjectArrayList;
    private Context context;
    private int act;

    public EventAdapter(Context context, ArrayList<EventObject> eventObjectArrayList, int act) {
        this.eventObjectArrayList = eventObjectArrayList;
        this.context = context;
        this.act = act;
    }

    @Override
    public EventAdapter.EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event, parent, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.EventHolder holder, int position) {
        final EventObject eventObject = eventObjectArrayList.get(position);
//        holder.imageView.setImageURI(Uri.parse(eventObject.getImage()));
        Picasso.with(context).load(eventObject.getImage()).into(holder.imageView);
        holder.textView.setText(eventObject.getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (act == 1) {
                    if (eventObject.getName().equals("ARJUN JANYA \nRAVE&CRAVE")) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://www.instamojo.com/CULTURA/arjun-janya-concert-dj-rave-crave/?ref=store"));
                        context.startActivity(i);
//                        context.startActivity(new Intent(context, AJDJ.class));
                    } else {
                        Intent intent = new Intent(context, SubEvent.class);
                        intent.putExtra("category", eventObject.getName());
                        context.startActivity(intent);
                    }
                } else if (act == 2) {
                    Intent intent = new Intent(context, EventActivity.class);
                    intent.putExtra("category", eventObject.getCategory());
                    intent.putExtra("event", eventObject.getName());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventObjectArrayList.size();
    }

    class EventHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        EventHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.re_event_name);
            imageView = itemView.findViewById(R.id.re_event_image);
        }
    }

}
