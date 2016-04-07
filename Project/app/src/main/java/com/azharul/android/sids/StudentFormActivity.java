package com.azharul.android.sids;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class StudentFormActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;

///////////////////////////////// Resource Deceleration Start ////////////////////////////////////////////////////////////////
    ImageView imageView;
    EditText editTextStudentName;
    EditText editTextStudentID;
    EditText editTextDept;
    EditText editTextCredits;
    EditText editTextEmail;
    EditText editTextCellPhone;
    EditText editTextWeaver;
    EditText editTextBalance;
    ArrayList<String> idCheck;
    LinearLayout li;


///////////////////////////////// Resource Deceleration End ////////////////////////////////////////////////////////////////


///////////////////////////////// Object Deceleration Start ////////////////////////////////////////////////////////////////

    Student student;
    DataSource dataSource;

///////////////////////////////// Object Deceleration End ////////////////////////////////////////////////////////////////

    int showKey; // Means Database primary-Key
    int updateKey; //Work for update function
    String studentIdKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentform);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        showKey= getIntent().getIntExtra("showKey",0);
        updateKey = getIntent().getIntExtra("updateKey",0);


///////////////////////////////// Resource Initialization Start ////////////////////////////////////////////////////////////////


        imageView = (ImageView) findViewById(R.id.imageView);
        dataSource = new DataSource(this);
        editTextStudentName = (EditText) findViewById(R.id.editTextName);
        editTextStudentID =(EditText) findViewById(R.id.editTextID);
        editTextDept = (EditText) findViewById(R.id.editTextDeptName);
        editTextCredits =(EditText) findViewById(R.id.editTextCredits);
        editTextEmail =(EditText) findViewById(R.id.editTextEmail);
        editTextCellPhone =(EditText) findViewById(R.id.editTextCellPhone);
        editTextWeaver =(EditText) findViewById(R.id.editTextWeaver);
        editTextBalance =(EditText) findViewById(R.id.editTextBalance);
        li = (LinearLayout) findViewById(R.id.linearLayoutForm);


///////////////////////////////// Resource Initialization End ////////////////////////////////////////////////////////////////

        if(updateKey ==1) // if updateKey value is 1 then the value from student-activity will be set in Student-form-activity
        {

            if (showKey % 2 == 1) {
                li.setBackgroundColor(Color.parseColor("#627583"));
                imageView.setImageResource(R.drawable.three);

            } else {
                li.setBackgroundColor(Color.parseColor("#8AB8E0"));
                imageView.setImageResource(R.drawable.image);
            }

            editTextStudentName.setText(dataSource.getStudent(showKey).getStudentName());
            editTextStudentID.setText(dataSource.getStudent(showKey).getId());
            studentIdKey =dataSource.getStudent(showKey).getId();
            editTextDept.setText(dataSource.getStudent(showKey).getDepartment());
            editTextCredits.setText(""+dataSource.getStudent(showKey).getCredit());
            editTextEmail.setText(dataSource.getStudent(showKey).getEmail());
            editTextCellPhone.setText(dataSource.getStudent(showKey).getCellPhone());
            editTextWeaver.setText(""+dataSource.getStudent(showKey).getWeaver());
            editTextBalance.setText(""+dataSource.getStudent(showKey).getBalance());

        }
//////////////////////////// Auto written part in Edit-Text Start //////////////////////////////////////////////////////////////


        editTextStudentID.addTextChangedListener(new TextWatcher() {//auto-written part for Student ID editText
            int len=0;
            @Override
            public void afterTextChanged(Editable s) {
                String str = editTextStudentID.getText().toString();
                if(str.length()==4&& len <str.length()||str.length()==7&& len <str.length()||str.length()==10&& len <str.length()){//len check for backspace
                    editTextStudentID.append("-");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                String str = editTextStudentID.getText().toString();
                len = str.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


        });
        editTextDept.addTextChangedListener(new TextWatcher() { //auto-written part for Department name editText
            int len=0;
            @Override
            public void afterTextChanged(Editable s) {
                String str = editTextDept.getText().toString();
                if(str.length()==1&& len <str.length()||str.length()==3&& len <str.length()){//len check for backspace
                    editTextDept.append(".");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                String str = editTextDept.getText().toString();
                len = str.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


        });
//////////////////////////// Auto written part in Edit-Text End //////////////////////////////////////////////////////////////



    }
/////////////////////////////Taking Image by Camera Start Here ///////////////////////////////////////////////////////////////


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    public void  AddImage(View view)
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

/////////////////////////////Taking Image by Camera END Here ///////////////////////////////////////////////////////////////

/////////////////////////////////////////Mother of All-button Save Button Code Start Here//////////////////////////////////////

    public void buttonSave(View view)
    {
////////////////////////////////////////// that part work for update in if condition //////////////////////////////////////////////
        if (updateKey == 1)
        {
            String name= editTextStudentName.getText().toString();

            int idExist=0;
            String checkID= editTextStudentID.getText().toString().toUpperCase();
            idCheck = dataSource.GetIDCheck();

            for(String value : idCheck) //Check-post for already taken id in for each loop here idCheck is coming from dataSource and checkID is editText StudentID
            {
                if(value.equals(checkID))
                {
                    idExist=3;
                    break;
                }
                idExist = 0;
            }


            if(studentIdKey.equals(checkID) || idExist== 0)//(if update id and resent id is same)or (that update id no one taken)
            {
                String id= editTextStudentID.getText().toString().toUpperCase(); // hare is that stupid id
                String department =editTextDept.getText().toString().toUpperCase();

                int creditCheck= Integer.parseInt(editTextCredits.getText().toString());
                if(creditCheck>200)// credit hour can,t be more that 200 hour
                {
                    Toast.makeText(getApplicationContext(), "Credit will not be greater than 200", Toast.LENGTH_LONG).show();
                }
                else if(creditCheck<1)// credit hour can,t less than 1 hour . No one take 0 credit
                {
                    Toast.makeText(getApplicationContext(), "Credit will not be greater than 1", Toast.LENGTH_LONG).show();
                }
                else //Credit hour is ok
                {
                    int credit =Integer.parseInt(editTextCredits.getText().toString());
                    int weaverCheck= Integer.parseInt(editTextWeaver.getText().toString());
                    if(weaverCheck>100) //Weaver will not be greater than 100%
                    {
                        Toast.makeText(getApplicationContext(), "Weaver will not be greater than 100%", Toast.LENGTH_LONG).show();
                    }
                    else if(weaverCheck<0)//Weaver will not be less then 0%
                    {
                        Toast.makeText(getApplicationContext(), "Weaver will not be less then 0%", Toast.LENGTH_LONG).show();
                    }
                    else// weaver is ok
                    {
                        int weaver =Integer.parseInt(editTextWeaver.getText().toString()) ;
                        long balance =Long.parseLong(editTextBalance.getText().toString());
                        String cellPhone =editTextCellPhone.getText().toString();
                        String email =editTextEmail.getText().toString();

                        student = new Student(name, id,department,credit,weaver,balance,cellPhone,email);

                        boolean inserted = dataSource.updateStudent(showKey, student);

                        if (inserted) {
                            Toast.makeText(getApplicationContext(), "Student information Updated", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(getApplicationContext(), "Student information Update Failed", Toast.LENGTH_LONG).show();
                        showKey=0;
                        updateKey =0;

                        Intent intent = new Intent(this,StudentInformationActivity.class);
                        startActivity(intent);
                    }



                }

            }
            else if(idExist == 3)
            {
                Toast.makeText(getApplicationContext(), "Student Id already Taken", Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),studentIdKey + "  "+checkID, Toast.LENGTH_LONG).show();

            }

        }
////////////////////////////////////// if update part not activated then that normal save part will work////////////////////////////

        else // else when update-key is zero
        {

            if(editTextStudentName.getText().toString().equals("")||editTextStudentID.getText().toString().equals("")||
                    editTextDept.getText().toString().equals("")||editTextCredits.getText().toString().equals("")||editTextEmail.getText().toString().equals("")
                    ||editTextBalance.getText().toString().equals("")||editTextCellPhone.getText().toString().equals("")||editTextWeaver.getText().toString().equals(""))
            {
                Toast.makeText(getApplicationContext(), "Fill All Field", Toast.LENGTH_LONG).show();
            }
            else
            {
                String name= editTextStudentName.getText().toString();

                int idExist=0;
                String checkID= editTextStudentID.getText().toString().toUpperCase();
                idCheck = dataSource.GetIDCheck();

                for(String value : idCheck)//Check-post for already taken id in for each loop here idCheck is coming from dataSource and checkID is editText StudentID
                {
                    if(value.equals(checkID))
                    {
                        idExist=3;
                        break;
                    }
                    idExist = 0;
                }

                if(idExist == 3)// if student id is already taken then that show the toast massage
                {
                    Toast.makeText(getApplicationContext(), "Student Id already Taken", Toast.LENGTH_LONG).show();
                }
                else if(idExist == 0)
                {
                    String id= editTextStudentID.getText().toString().toUpperCase();// hare is that stupid id
                    String department =editTextDept.getText().toString().toUpperCase();

                    int creditCheck= Integer.parseInt(editTextCredits.getText().toString());
                    if(creditCheck>200)
                    {
                        Toast.makeText(getApplicationContext(), "Credit will not be greater than 200", Toast.LENGTH_LONG).show();
                    }
                    else if(creditCheck<1)
                    {
                        Toast.makeText(getApplicationContext(), "Credit will not be greater than 1", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        int credit =Integer.parseInt(editTextCredits.getText().toString());
                        int weaverCheck= Integer.parseInt(editTextWeaver.getText().toString());
                        if(weaverCheck>100)
                        {
                            Toast.makeText(getApplicationContext(), "Weaver will not be greater than 100%", Toast.LENGTH_LONG).show();
                        }
                        else if(weaverCheck<0)
                        {
                            Toast.makeText(getApplicationContext(), "Weaver will not be less then 0%", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            int weaver =Integer.parseInt(editTextWeaver.getText().toString());
                            long balance =Long.parseLong(editTextBalance.getText().toString()) ;
                            String cellPhone =editTextCellPhone.getText().toString();
                            String email =editTextEmail.getText().toString();

                            student = new Student(name, id,department,credit,weaver,balance,cellPhone,email);

                            boolean inserted = dataSource.addStudent(student);
                            //dataSource.addStudent(student);

                            if (inserted) {
                                Toast.makeText(getApplicationContext(), "Student information Added", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(getApplicationContext(), "Student information Insertion Failed", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(this,StudentInformationActivity.class);
                            startActivity(intent);
                        }



                    }

                }

            }


        }



    }

/////////////////////////////////////////Mother of ALL-button Save Button Code END Here//////////////////////////////////////

}
