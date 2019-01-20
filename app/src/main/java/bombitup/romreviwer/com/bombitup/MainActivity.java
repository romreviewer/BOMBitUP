package bombitup.romreviwer.com.bombitup;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import de.cketti.library.changelog.ChangeLog;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd interstitial;
    TextView textView;
    TextView count;
    TextView output;
    TextView smssent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        AppUpdater appUpdater = new AppUpdater(this);
        appUpdater.start();
        if(this.isInternetAvailable())
        {
            Toast t = Toast.makeText(MainActivity.this, "Internet Is Connected", Toast.LENGTH_SHORT);
            t.show();
        }
        else
        {
            Toast t = Toast.makeText(MainActivity.this, "Please Connect to internet First", Toast.LENGTH_SHORT);
            t.show();

        }

        ChangeLog cl = new ChangeLog(this);
        if (cl.isFirstRun()) {
            cl.getLogDialog().show();
        }





    }
    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
/*    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }*/
/*
    public boolean isInternetAvailable() {
        try {
            final InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            // Log error
        }
        return false;
    }
*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(this,ProfileActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void button(View v) {
        AdRequest adRequest = new AdRequest.Builder().build();

        interstitial = new InterstitialAd(MainActivity.this);
// Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial));

        interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
// Call displayInterstitial() function
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        displayInterstitial();
                    }
                }, 4000);

            }
        });
        textView=(TextView) findViewById(R.id.editText);
        count = (TextView) findViewById(R.id.editText2);
        output=(TextView) findViewById(R.id.textView);
        String countstr = count.getText().toString();
        int countint = Integer.parseInt(countstr);
        if(!this.isInternetAvailable())
        {
            output.setText("Internet Not Connected");
        }
        else {


        if(textView.getText().toString().equals("")&&count.getText().toString().equals(""))
    {
        output.setText("Please enter the number");
    }
    else {
            for (int i = 1; i <= countint; i++) {
                if (i % 2 == 0) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            sendrequest();

                        }
                    }, 2000 * i);
                } else {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            hindustan();

                        }
                    }, 2000 * i);

                }

                Toast toast = Toast.makeText(MainActivity.this, "TIP: Try To Send less that 20 SMS at a time", Toast.LENGTH_SHORT);
                toast.show();


            }
        }
        }
    }
public void protect(View view)
{
    Toast toast1=Toast.makeText(MainActivity.this,"This Feature will be added In the Next 
                                ",Toast.LENGTH_SHORT);
    toast1.show();
}


        public void sendrequest()
    {
        TextView textView=(TextView) findViewById(R.id.editText);
        TextView output=(TextView) findViewById(R.id.textView);
        String url="ADD YOUR API HERE"+textView.getText();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i("TAG", e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {

                Log.i("TAG", response.body().string());
/*
                output.setText(response.body().string());
*/
            }
        });
        output.setText("SMS SENT VIA NAPTOL API");
    }
    public void hindustan()
    {
        TextView textView=(TextView) findViewById(R.id.editText);
        TextView output=(TextView) findViewById(R.id.textView);


        String url="ADD YOUR API HERE"+textView.getText();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i("TAG", e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {

                Log.i("TAG", response.body().string());
/*
                output.setText(response.body().string());
*/
            }
        });
        output.setText("SMS SENT VIA Homeshop18 API");

    }
            public void displayInterstitial() {

                if (interstitial.isLoaded()) {
                    interstitial.show();
                }
            }
}

