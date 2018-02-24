package in.co.cultura.cultura18.Activities;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.cultura.cultura18.Fragments.RecyclerViewFragment;
import in.co.cultura.cultura18.R;

public class Schedule extends AppCompatActivity {
    /**
     * Floating Images are larger thn required
     */
    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppBaseTheme);
        setContentView(R.layout.activity_schedule);
        setTitle("");
        ButterKnife.bind(this);
        final Toolbar toolbar = mViewPager.getToolbar();
        if (Build.VERSION.SDK_INT >= 21) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        if (toolbar != null) {
            toolbar.setMinimumHeight(200);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);

        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {

                Bundle bundle = new Bundle();
                bundle.putInt("day", position);
                switch (position % 4) {
                    case 0:
                        return RecyclerViewFragment.newInstance(position);
                    case 1:
                        return RecyclerViewFragment.newInstance(position);
                    default:
                        return RecyclerViewFragment.newInstance(3);
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "Day 1";
                    case 1:
                        return "Day 2";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.purple,
                                "https://firebasestorage.googleapis.com/v0/b/culturaapp18.appspot.com/o/music%20club.jpg?alt=media&token=033b53b4-4ae7-4c50-b0bc-6d814a0c3529");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "https://firebasestorage.googleapis.com/v0/b/culturaapp18.appspot.com/o/literary%20club.jpg?alt=media&token=c4f0823c-c438-4c3e-b1a5-ae5a7b1763cc");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        final View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                }
            });
        }
    }
}
