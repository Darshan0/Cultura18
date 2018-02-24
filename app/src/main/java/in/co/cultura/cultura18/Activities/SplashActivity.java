package in.co.cultura.cultura18.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import in.co.cultura.cultura18.R;
import in.co.cultura.cultura18.Utils.Helper;


public class SplashActivity extends AppCompatActivity {
    public static final int STARTUP_DELAY = 500;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int ITEM_DELAY = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);
        setContentView(R.layout.activity_splash);
        new FetchProductData().execute();
        if (Build.VERSION.SDK_INT >= 21) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        animator();
    }

    private void animator() {
        ImageView imageView = findViewById(R.id.splash_img_logo);
        ViewGroup container = findViewById(R.id.splash_container);
        final TextView textView = findViewById(R.id.splash_date_text);

//        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.animate);
//        animation.reset();
//        textView.clearAnimation();
//        textView.setAnimation(animation);
        ViewCompat.animate(imageView).translationY(+0)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new DecelerateInterpolator(1.2f)).start();
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();

        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator;

            viewAnimator = ViewCompat.animate(v)
                    .translationY(-height / (2.8f)).alpha(1)
                    .setStartDelay((ITEM_DELAY * i) + 500)
                    .setDuration(500);
            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
        }
        ViewCompat.animate(imageView)
                .scaleXBy(-0.2f)
                .scaleYBy(-0.2f)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(new DecelerateInterpolator(1.2f))
                .start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.animate);
                animation.reset();
                textView.setVisibility(View.VISIBLE);
                textView.clearAnimation();
                textView.setAnimation(animation);
            }
        }, 1500);

    }

    private int download() {
        try {
            String response = Jsoup.connect("https://raw.githubusercontent.com/vinit-kanani/Data/master/detail.json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute().body();
            Helper helper = new Helper();
            helper.writeToFile(response, getApplicationContext());
            return response.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @SuppressLint("StaticFieldLeak")
    private class FetchProductData extends AsyncTask<Object, Integer, Integer> {

        @Override
        protected Integer doInBackground(Object[] objects) {

            return download();
        }

        @Override
        protected void onPostExecute(Integer v) {
            Helper helper = new Helper();
            if (v == 0 && helper.readFromFile(getApplicationContext()).length() == 0) {
                Toast.makeText(getApplicationContext(), "Could not contact the server", Toast.LENGTH_LONG).show();
            } else {
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));

                    }
                }, 4500);
            }
        }
    }
}
