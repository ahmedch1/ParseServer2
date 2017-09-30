package ahmedchtn.parseserver;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
    EditText et_username;
    EditText et_password;
    Button login;
    Button register;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        login = (Button) findViewById(R.id.blogin);
        register = (Button) findViewById(R.id.bregister);
        progressDialog = new ProgressDialog(MainActivity.this);
        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("fdU8IDm1v2DSfTJY1Ocfr4LkNORnjH42AfNyA6wP")
                .clientKey("7UIFtj4m6CbRkEEcqKzyzQ565luzoaXuVANECQPr")
                .server("https://parseapi.back4app.com/")//the trailing slash is important
                .build()
        );
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait");
                progressDialog.setTitle("Logging in");
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            parseLogin();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait");
                progressDialog.setTitle("Registering");
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            parseRegister();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }

    void parseLogin() {
        ParseUser.logInInBackground(et_username.getText().toString(), et_password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();
                    alertDisplayer("Login Fail", e.getMessage() + " Please re-try");
                }
            }
        });
    }

    void parseRegister() {
        ParseUser user = new ParseUser();
        user.setUsername(et_username.getText().toString());
        user.setPassword(et_password.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {

                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();
                    alertDisplayer("Register Fail", e.getMessage());
                }
            }
        });
    }


    void alertDisplayer(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }


}
