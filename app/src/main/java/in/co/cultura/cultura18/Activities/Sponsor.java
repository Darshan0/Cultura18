package in.co.cultura.cultura18.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import in.co.cultura.cultura18.Interface.ItemClickListener;
import in.co.cultura.cultura18.Model.Category;
import in.co.cultura.cultura18.R;
import in.co.cultura.cultura18.ViewHolder.MenuViewHolder;

public class Sponsor extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recyler_menu;
    FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Category");


        recyler_menu = findViewById(R.id.recyclerView);
        recyler_menu.setHasFixedSize(true);
        recyler_menu.setLayoutManager(new GridLayoutManager(this,1));
        loadMenu();


    }

    private void loadMenu() {

        adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class, R.layout.menu_item, MenuViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {


                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(Sponsor.this,"SPONSOR",Toast.LENGTH_LONG).show();

                    }
                });
            }
        };
        recyler_menu.setAdapter(adapter);
    }


}