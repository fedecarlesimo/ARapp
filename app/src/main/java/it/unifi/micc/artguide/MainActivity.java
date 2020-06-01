package it.unifi.micc.artguide;


//Author: Alessandro Amedei, Gian Maria Pandolfi, Corso Vignoli
//Date: 25/06/2019

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wikitude.WikitudeSDK;
import com.wikitude.common.permission.PermissionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.unifi.micc.artguide.tracking.image.SimpleImageTrackingActivity;
import it.unifi.micc.artguide.tracking.object.ObjectTrackingActivity;
import it.unifi.micc.artguide.util.SampleCategory;


public class MainActivity extends AppCompatActivity {

    private static final int EXPANDABLE_INDICATOR_START_OFFSET = 60;
    private static final int EXPANDABLE_INDICATOR_END_OFFSET = 30;

    private ExpandableListView listView;

    private final List<SampleCategory> sampleCategories = new ArrayList<>();
    private MainActivity mainActivity;
    private TextView mErrorTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mainActivity = this;

        //  WikitudeSDK.deleteRootCacheDirectory(this); FIXME this should be uncommented

        (new DownloadWtc(this)).execute("tracker.wto");
        (new DownloadWtc(this)).execute("tracker.wtc");

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(0, "1");
            }
        });

        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(1, "0");
            }
        });

        ((Button) findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(0, "2");
            }
        });

        ((Button) findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });

        mErrorTextView = findViewById(R.id.error_textview);
    }

    public void addError(String error) {
        if (mErrorTextView.length() != 0)
            mErrorTextView.setText(mErrorTextView.getText() + "\n");

        mErrorTextView.setText(mErrorTextView.getText() + error);
        mErrorTextView.setVisibility(View.VISIBLE);

    }

    public void start(final int val, final String checkVal) {

        WikitudeSDK.getPermissionManager().checkPermissions(this, new String[]{Manifest.permission.CAMERA}, PermissionManager.WIKITUDE_PERMISSION_REQUEST, new PermissionManager.PermissionManagerCallback() {
            @Override
            public void permissionsGranted(int requestCode) {

                if (val == 0) {
                    final Intent intent = new Intent(MainActivity.this, SimpleImageTrackingActivity.class);
                    intent.putExtra("checkval", checkVal);
                    startActivity(intent);
                }
                
                if (val == 1) {
                    Intent intent1 = new Intent(MainActivity.this, ObjectTrackingActivity.class);
                    startActivity(intent1);
                }

            }

            @Override
            public void permissionsDenied(String[] deniedPermissions) {
                Toast.makeText(MainActivity.this, "The Wikitude SDK needs the following permissions to enable an AR experience: " + Arrays.toString(deniedPermissions), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showPermissionRationale(final int requestCode, final String[] permissions) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Wikitude Permissions");
                alertBuilder.setMessage("The Wikitude SDK needs the following permissions to enable an AR experience: " + Arrays.toString(permissions));
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WikitudeSDK.getPermissionManager().positiveRationaleResult(requestCode, permissions);
                    }
                });

                AlertDialog alert = alertBuilder.create();
                alert.show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        WikitudeSDK.getPermissionManager().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
