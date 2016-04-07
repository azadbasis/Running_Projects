package com.azharul.android.sids;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity {
    DataSource dataSource;

    TextView textViewID;
    TextView textViewName;
    TextView textViewDepartment;
    TextView textViewWeaver;
    TextView textViewBalance;
    TextView textViewCellPhone;
    TextView textViewEmail;
    TextView textViewCredit;
    ImageView imageView;


    int showKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        textViewID = (TextView) findViewById(R.id.textViewID);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewDepartment = (TextView) findViewById(R.id.textViewDepartment);
        textViewWeaver = (TextView) findViewById(R.id.textViewWeaver);
        textViewBalance = (TextView) findViewById(R.id.textViewBalance);
        textViewCellPhone = (TextView) findViewById(R.id.textViewCellPhone);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewCredit = (TextView) findViewById(R.id.textViewCredits);
        LinearLayout li = (LinearLayout) findViewById(R.id.linearLayout);
        imageView = (ImageView) findViewById(R.id.imageStudent);


        dataSource = new DataSource(this);
        showKey = getIntent().getIntExtra("key", 0);

        if (showKey % 2 == 1) {
            li.setBackgroundColor(Color.parseColor("#627583"));
            imageView.setImageResource(R.drawable.three);

        } else {
            li.setBackgroundColor(Color.parseColor("#8AB8E0"));
            imageView.setImageResource(R.drawable.image);
        }

        textViewName.setText(":  " + dataSource.getStudent(showKey).getStudentName());
        textViewID.setText(":  " + dataSource.getStudent(showKey).getId());

        textViewDepartment.setText(":  " + dataSource.getStudent(showKey).getDepartment());
        textViewWeaver.setText(":  " + dataSource.getStudent(showKey).getWeaver() + " %");
        textViewBalance.setText(":  " + dataSource.getStudent(showKey).getBalance() + " TK");
        textViewCellPhone.setText(dataSource.getStudent(showKey).getCellPhone());
        textViewEmail.setText(dataSource.getStudent(showKey).getEmail());
        textViewCredit.setText(":  " + dataSource.getStudent(showKey).getCredit() + " Hours");
    }

    public void buttonDelete(View view) {
        dataSource.deleteStudent(showKey);
        Intent intent = new Intent(this, StudentInformationActivity.class);
        intent.putExtra("deleteKey", 1);
        startActivity(intent);
    }

    public void buttonUpdate(View view) {
        Intent intent = new Intent(this, StudentFormActivity.class);
        intent.putExtra("updateKey", 1);
        intent.putExtra("showKey", showKey);
        startActivity(intent);
    }

    public void buttonCall(View view) {
        String number = dataSource.getStudent(showKey).getCellPhone();
        Intent callIntent = new Intent(Intent.ACTION_CALL);

        callIntent.setData(Uri.parse("tel:" + number));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }

    public void buttonMail(View view)
    {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",dataSource.getStudent(showKey).getEmail(), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Notice");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Hi Student");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}
