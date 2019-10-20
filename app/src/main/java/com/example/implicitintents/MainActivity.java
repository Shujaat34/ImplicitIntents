package com.example.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText edtWeb , edtLocation , edtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWeb = findViewById(R.id.edtweb);
        edtLocation = findViewById(R.id.edtloc);
        edtMsg = findViewById(R.id.edtmsg);

    }

    public void openWeb(View view) {

        String url = edtWeb.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d(TAG,"Intent not handled");
        }
    }

    public void openLocation(View view) {
        //Getting Data From EditText
        String loc = edtLocation.getText().toString();
        //Setting the Action and Data
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=" + loc));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d(TAG,"Intent not handled");
        }
    }

    public void shareMsg(View view) {
        String txt = edtMsg.getText().toString();
        // Seeting MimeType (Multipurpose Internet Mail Extension) for the text and other stuff
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("My Message")
                .setText(txt)
                .startChooser();
    }
}
