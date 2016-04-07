package com.bitm.simplecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends Activity {

	TextView showRsult;
	int num;
	int tempNum;
	Character operator;
	String input = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		showRsult = (TextView) findViewById(R.id.result_id);

	}

	public void btn7Clicked(View view) {

		insertNum(7);
	}

	public void btn8Clicked(View view) {

		insertNum(8);
	}

	public void btn9Clicked(View view) {

		insertNum(9);
	}

	public void btn4Clicked(View view) {

		insertNum(4);
	}

	public void btn5Clicked(View view) {

		insertNum(5);
	}

	public void btn6Clicked(View view) {

		insertNum(6);
	}

	public void btn3Clicked(View view) {

		insertNum(3);
	}

	public void btn2Clicked(View view) {

		insertNum(2);
	}

	public void btn1Clicked(View view) {

		insertNum(1);
	}
//	 public void btnPointClicked(View view){
//	
//		 insertNum(1);
//	}
	
	public void btnZeroClicked(View view){
		
		insertNum(0);
	}
	
	public void btnClearClicked(View view){
        input="";
        num=0;
        tempNum=0;
        showRsult.setText("");

    }

	
	 private void insertNum(int i){
	        input=input+Integer.toString(i);
	        num=Integer.parseInt(input);
	        showRsult.setText(input);

	    }
	 
	 
	 public void btnPlusClicked(View view){

	        operation();
	        operator='+';
	    }

	    public void btnMinusClicked(View view){
	        operation();
	        operator='-';
	      
	    }

	 
	 public void btnDivideClicked(View view){
	        operation();
	        operator='/';
	    }
	    public void btnMultiClicked(View view){
	        operation();
	        operator='X';
	    }

	    
	    private  void operation(){
	        tempNum=num;
	        input="";
	    }


	    public void btnEqualClicked(View view){
	        if (operator=='+'){
	            num=tempNum+num;
	        }
	         else  if (operator=='-'){
	            num=tempNum-num;
	        }
	        else  if (operator=='X'){
	            num=num*tempNum;
	        }
	        else  if (operator=='/'){
	            num=tempNum/num;
	        }
	         showRsult.setText(""+num);

	     }


}
