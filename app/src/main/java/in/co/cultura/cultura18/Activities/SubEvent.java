package in.co.cultura.cultura18.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import in.co.cultura.cultura18.Adapters.EventAdapter;
import in.co.cultura.cultura18.Model.EventObject;
import in.co.cultura.cultura18.R;
import in.co.cultura.cultura18.Utils.Helper;

public class SubEvent extends AppCompatActivity {
    private ArrayList<EventObject> eventObjectArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_DayNight_DarkActionBar);
        setContentView(R.layout.activity_sub_event);
        RecyclerView recyclerView = findViewById(R.id.ase_recycler_view);
        String category = getIntent().getStringExtra("category");
        setTitle(category);
        addData(category);
        EventAdapter adapter = new EventAdapter(SubEvent.this, eventObjectArrayList, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    private void addData(String category) {
        eventObjectArrayList = new ArrayList<>();
        try {
            Helper helper = new Helper();
            JSONObject jsonObject = new JSONObject(helper.readFromFile(getApplicationContext()));
            JSONArray jsonArray = jsonObject.getJSONArray(category);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject eventBlock = jsonArray.getJSONObject(i);
                eventObjectArrayList.add(new EventObject(eventBlock.getString("name"), eventBlock.getString("imageurl"), category));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("detail.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int read = is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
