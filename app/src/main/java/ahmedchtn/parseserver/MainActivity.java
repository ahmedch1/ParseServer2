package ahmedchtn.parseserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initalize(new Parse.Configuration.Builder(this)
                .applicationId("")
                .clientKey(null)
                .server("https://parseapi.back4app.com/")//the trailing slash is important
                .build()
        );
        ParseUser.enableRevocableSessionInBackground();

        Parse.enableLocalDatastore(this);

    }
}
