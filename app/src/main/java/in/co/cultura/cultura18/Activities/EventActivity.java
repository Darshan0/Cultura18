package in.co.cultura.cultura18.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import in.co.cultura.cultura18.Model.Category;
import in.co.cultura.cultura18.R;
import in.co.cultura.cultura18.Utils.Helper;

public class EventActivity extends AppCompatActivity {
    String Event, CategoryName;
    TextView desc, part, rules, venue, prize, amount, co;
    ImageView imageView;
    FloatingActionButton floatingActionButton;
    String registerurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_DayNight_DarkActionBar);
        setContentView(R.layout.activity_event);
        Event = getIntent().getStringExtra("event");
        setTitle(Event);
        CategoryName = getIntent().getStringExtra("category");
        System.out.println(Event);
        System.out.println(CategoryName);
        desc = findViewById(R.id.ae_event_desc);
        part = findViewById(R.id.ae_event_part);
        rules = findViewById(R.id.ae_event_rules);
        venue = findViewById(R.id.ae_event_venue);
        prize = findViewById(R.id.ae_event_prize);
        imageView = findViewById(R.id.ae_image);
        amount = findViewById(R.id.ae_event_reg_amt);
        co = findViewById(R.id.ae_event_co);
        floatingActionButton = findViewById(R.id.ae_event_reg);
        obtainData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(registerurl));
                startActivity(i);
            }
        });
    }

    private void obtainData() {
        try {
            Helper helper = new Helper();
            JSONObject jsonObject = new JSONObject(helper.readFromFile(getApplicationContext()));
            JSONArray jsonArray = jsonObject.getJSONArray(CategoryName);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject eventBlock = jsonArray.getJSONObject(i);
                if (eventBlock.getString("name").equalsIgnoreCase(Event)) {
                    desc.setText(eventBlock.getString("1desc"));
                    part.setText(eventBlock.getString("participation"));
                    rules.setText(eventBlock.getString("rules"));
                    venue.setText(eventBlock.getString("venue"));
                    prize.setText(eventBlock.getString("prize"));
                    amount.setText(eventBlock.getString("registration"));
                    registerurl = eventBlock.getString("link");
                    co.setText(eventBlock.getString("eventco"));
                    Picasso.with(EventActivity.this).load(eventBlock.getString("imageurl")).into(imageView);
                }
            }
            System.out.println();
            System.out.println(jsonObject);
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
