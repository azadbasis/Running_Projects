package android.azadbasis.com.testcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {


    TextView showRsult;
    int num;
    int tempNum;
    Character operator;
    String input="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        showRsult=(TextView)findViewById(R.id.text_id);
    }



    public void btn7c(View view){
        insertNum(7);
    }

    public void btn6c(View view){
        insertNum(6);
    }

    public void btn5c(View view){
        insertNum(5);
    }

    public void btn3c(View view){
        insertNum(3);
    }


    public void btn2c(View view){
        insertNum(2);
    }

    public void btn1c(View view){
        insertNum(1);
    }

    public void btn0c(View view){
        insertNum(0);
    }

    public void btn8c(View view){
        insertNum(8);
    }

    public void btn9c(View view){
        insertNum(9);
    }
    private void insertNum(int i){
        input=input+Integer.toString(i);
        num=Integer.parseInt(input);
        showRsult.setText(input);

    }

}
