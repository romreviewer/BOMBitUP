package bombitup.romreviwer.com.bombitup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        super.onCreate(savedInstanceState);

        View aboutPage = new AboutPage(this)
                .isRTL(false)

                .addGroup("Also Checkout My Youtube channel and other App")
                .setDescription("Make Your Friends Pull Their Hairs By Bombing Them with Multiple SMS. Call Bomber will soon be Added" )
                .addEmail("sanchitgera01@gmail.com")
                .addFacebook("romreviewer")
                .addYoutube("romreviewer20")
                .addPlayStore("com.romreviewer.sanchit.dataschedulerroot")
                .addGitHub("sanchit43")
                .addInstagram("sanchit.gera")

                .create();

        setContentView(aboutPage);
        Element versionElement = new Element();
        versionElement.setTitle("Version 6.2");

    }


}



