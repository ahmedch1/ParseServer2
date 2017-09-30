package ahmedchtn.parseserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("fdU8IDm1v2DSfTJY1Ocfr4LkNORnjH42AfNyA6wP")
                .clientKey("7UIFtj4m6CbRkEEcqKzyzQ565luzoaXuVANECQPr")
                .server("https://parseapi.back4app.com/")//the trailing slash is important
                .build()
        );
        ParseUser.enableRevocableSessionInBackground();

        Parse.enableLocalDatastore(this);

    }
}
