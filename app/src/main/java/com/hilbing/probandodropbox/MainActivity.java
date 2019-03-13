package com.hilbing.probandodropbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.android.Auth;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

public class MainActivity extends AppCompatActivity {

    //private static final String ACCESS_TOKEN = "KIvEwbgLGjMAAAAAAAADOGqJXmEJexDNNzhzWiRFA3ZkqE9rSjR2Un1uG_xK2E7W";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Auth.startOAuth2Authentication(getApplicationContext(), getString(R.string.app_key));
        String ACCESS_TOKEN = Auth.getOAuth2Token();

        //Create Dropbox Client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("ExampleMarch").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        //Get current account info
        FullAccount account = null;
        try {
            account = client.users().getCurrentAccount();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        Log.d("Account", account.getName().getDisplayName());

    }
}
