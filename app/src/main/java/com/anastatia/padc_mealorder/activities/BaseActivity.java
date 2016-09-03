package com.anastatia.padc_mealorder.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;

import com.anastatia.padc_mealorder.R;
import com.anastatia.padc_mealorder.utils.MealOrderConstants;

/**
 * Created by Nyein Nyein on 8/28/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 100;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 101;
    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 102;

    private static final int REQUEST_IMAGE_CAPTURE = 1001;
    private static final int REQUEST_IMAGE_CAPTURE_FULL_RESOLUTION = 1002;
    private static final int REQUEST_SELECT_IMAGE_ABOVE_KITKAT = 1003;
    private static final int REQUEST_SELECT_IMAGE = 1004;

    private String numberToCall = null;
    private String mCurrentPhotoPath;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay!
                    makeCall(numberToCall);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

        }
    }

    protected void sendViaShareIntent(String msg) {
        startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(BaseActivity.this)
                .setType("text/plain")
                .setText(msg)
                .getIntent(), getString(R.string.action_share)));
    }

    protected void openLocationInMap(String location) {
        String uriToOpen = MealOrderConstants.URI_TO_OPEN_IN_MAP + location;
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uriToOpen));
        startActivity(intent);
    }

    protected void makeCall(String numberToCall) {
        numberToCall = numberToCall.replaceAll(" ", "");
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numberToCall));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            this.numberToCall = numberToCall;

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

            return;
        }
        startActivity(intent);
    }

    protected void sendEmail(String recipient, String subject, String msgBody) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, recipient);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, msgBody);

        startActivity(Intent.createChooser(intent,
                getString(R.string.action_send_email)));
    }
}
