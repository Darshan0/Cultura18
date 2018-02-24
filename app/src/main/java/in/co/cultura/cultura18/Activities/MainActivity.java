package in.co.cultura.cultura18.Activities;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.co.cultura.cultura18.Adapters.EventAdapter;
import in.co.cultura.cultura18.Model.Category;
import in.co.cultura.cultura18.Model.EventObject;
import in.co.cultura.cultura18.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    EventAdapter adapter;
    private ArrayList<EventObject> eventObjectArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("EVENTS");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        addData();
        adapter = new EventAdapter(MainActivity.this, eventObjectArrayList, 1);
        recyclerView = findViewById(R.id.cm_recycler_menu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "lol");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                            startActivity(intent);
                            finish();
                            onDestroy();
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("No", null)
                    .setNeutralButton("Rate", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            rateApp();
                        }
                    })
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_schedule) {
            startActivity(new Intent(MainActivity.this, Schedule.class));
        } else if (id == R.id.nav_sponser) {
            startActivity(new Intent(getApplicationContext(), Sponsor.class));
        } else if (id == R.id.nav_location) {
            startActivity(new Intent(getApplicationContext(), LocationActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(getApplicationContext(), AboutActivity.class));

        } else if (id == R.id.nav_youtube) {
            String url = "https://www.youtube.com/channel/UCln6uzSuS0MDiEQtXj_Vcnw";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

        } else if (id == R.id.nav_facebook) {
            String url = "https://www.facebook.com/Cultura.cmrit/?ref=br_rs";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

        } else if (id == R.id.nav_instagram) {
            String url = "https://www.instagram.com/cmritcultura/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addData() {
        eventObjectArrayList = new ArrayList<>();
        eventObjectArrayList.add(new EventObject("GAMING", "https://i.imgur.com/fiWG2bn.jpg", ""));
        eventObjectArrayList.add(new EventObject("DANCE", "https://i.imgur.com/XIEyBdH.jpg", ""));
        eventObjectArrayList.add(new EventObject("LITERARY", "https://i.imgur.com/J0biFRj.jpg", ""));
        eventObjectArrayList.add(new EventObject("MUSIC", "https://i.imgur.com/rWuAl27.jpg", ""));
        eventObjectArrayList.add(new EventObject("THEATRE", "https://i.imgur.com/uwRRhIz.jpg", ""));
        eventObjectArrayList.add(new EventObject("TECH", "https://i.imgur.com/mBXOKTp.jpg", ""));
        eventObjectArrayList.add(new EventObject("KANNADA", "https://i.imgur.com/HsbQUBk.png", ""));
        eventObjectArrayList.add(new EventObject("ART", "https://i.imgur.com/HSNUHGa.png", ""));
        eventObjectArrayList.add(new EventObject("INFORMAL", "https://i.imgur.com/g2rOoyf.png", ""));
        eventObjectArrayList.add(new EventObject("ARJUN JANYA \nRAVE&CRAVE", "https://i.imgur.com/T7Vd843.jpg", ""));
    }

    private void rateApp() {
        Uri uri = Uri.parse("market://details?id=" + "in.co.cultura.cultura18");
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + "in.co.cultura.cultura18")));
        }
    }
}
