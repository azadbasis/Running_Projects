package com.azhar.basiccalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	ImageButton btnzero;
	ImageButton btn1;
	ImageButton btn2;
	ImageButton btn3;
	ImageButton btn4;
	ImageButton btn5;
	ImageButton btn6;
	ImageButton btn7;
	ImageButton btn8;
	ImageButton btn9;
	ImageButton btnplus;
	ImageButton btnminus;
	ImageButton btnmultiply;
	ImageButton btndivided;
	ImageButton btndot;
	ImageButton btnequal;
	ImageButton btnplusminus;
	ImageButton btnroot;
	ImageButton btnsquare;
	ImageButton btnclear;
	ImageButton btndelete;
	ImageButton btnpercentage;
	ImageButton btnpie;
	ImageButton btncub;

	TextView textDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnzero = (ImageButton) findViewById(R.id.imageButton19);
		btn1 = (ImageButton) findViewById(R.id.imageButton15);
		btn2 = (ImageButton) findViewById(R.id.imageButton16);
		btn3 = (ImageButton) findViewById(R.id.imageButton17);
		btn4 = (ImageButton) findViewById(R.id.imageButton11);
		btn5 = (ImageButton) findViewById(R.id.imageButton12);
		btn6 = (ImageButton) findViewById(R.id.imageButton13);
		btn7 = (ImageButton) findViewById(R.id.imageButton7);
		btn8 = (ImageButton) findViewById(R.id.imageButton8);
		btn9 = (ImageButton) findViewById(R.id.imageButton9);
		btnplus = (ImageButton) findViewById(R.id.imageButton22);
		btndot = (ImageButton) findViewById(R.id.imageButton20);
		btndivided = (ImageButton) findViewById(R.id.imageButton10);
		btnmultiply = (ImageButton) findViewById(R.id.imageButton14);
		btnminus = (ImageButton) findViewById(R.id.imageButton18);
		btnclear = (ImageButton) findViewById(R.id.imageButton1);
		btndelete = (ImageButton) findViewById(R.id.imageButton2);
		btnequal = (ImageButton) findViewById(R.id.imageButton21);
		btnplusminus = (ImageButton) findViewById(R.id.imageButton3);
		btnroot = (ImageButton) findViewById(R.id.imageButton5);
		btnsquare = (ImageButton) findViewById(R.id.imageButton4);
		btnpercentage = (ImageButton) findViewById(R.id.imageButton6);
		btnpie = (ImageButton) findViewById(R.id.imageButton23);
		btncub = (ImageButton) findViewById(R.id.imageButton24);

		textDisplay = (TextView) findViewById(R.id.textView1);

		btnzero.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btndot.setOnClickListener(this);
		btnclear.setOnClickListener(this);
		btndivided.setOnClickListener(this);
		btndelete.setOnClickListener(this);
		btnpercentage.setOnClickListener(this);
		btnplus.setOnClickListener(this);
		btnplusminus.setOnClickListener(this);
		btnminus.setOnClickListener(this);
		btnmultiply.setOnClickListener(this);
		btnequal.setOnClickListener(this);
		btnsquare.setOnClickListener(this);
		btnroot.setOnClickListener(this);
		btnpie.setOnClickListener(this);
		btncub.setOnClickListener(this);

	}

	int clear_falg=0 ;
	Double total = 0.0;
	String sign_flag="" ;
	int last_button = 0;

	public void showNum(String num) {
		if (clear_falg == 1) {
			textDisplay.setText("");
			clear_falg = 0;
		} else if (textDisplay.getText() == "0") {

			textDisplay.setText("");
		}
		textDisplay.setText(textDisplay.getText() + num);
	}

	public void showSign(String sign) {
		if (last_button == R.id.imageButton22
				|| last_button == R.id.imageButton18
				|| last_button == R.id.imageButton14
				|| last_button == R.id.imageButton10) {

		} else {
			clear_falg = 1; // set flag
			Double newNumber = Double.parseDouble(textDisplay.getText()
					.toString());
			if (sign_flag == "" || sign_flag == "=") {
				total = newNumber;
				textDisplay.setText(total.toString());
			} else if (sign_flag == "+") {
				total = total + newNumber;
				textDisplay.setText(total.toString());
			} else if (sign_flag == "-") {
				total = total - newNumber;
				textDisplay.setText(total.toString());
			} else if (sign_flag == "*") {
				total = total * newNumber;
				textDisplay.setText(total.toString());
			} else if (sign_flag == "/") {
				total = total / newNumber;
				textDisplay.setText(total.toString());
			}
		}
		sign_flag = sign;
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.imageButton19) {
			showNum("0");
		} else if (v.getId() == R.id.imageButton15) {
			showNum("1");
		} else if (v.getId() == R.id.imageButton16) {
			showNum("2");
		} else if (v.getId() == R.id.imageButton17) {
			showNum("3");
		} else if (v.getId() == R.id.imageButton11) {
			showNum("4");
		} else if (v.getId() == R.id.imageButton12) {
			showNum("5");
		} else if (v.getId() == R.id.imageButton13) {
			showNum("6");
		} else if (v.getId() == R.id.imageButton7) {
			showNum("7");
		} else if (v.getId() == R.id.imageButton8) {
			showNum("8");
		} else if (v.getId() == R.id.imageButton9) {
			showNum("9");
		} else if (v.getId() == R.id.imageButton1) {
			textDisplay.setText("0");
			total = 0.0;
			sign_flag = "";

		}// dot button
		else if (v.getId() == R.id.imageButton20) {
			if (clear_falg == 1) {
				textDisplay.setText("");
				clear_falg = 0;
			}
			if (textDisplay.getText().toString().indexOf(".") < 0) {
				textDisplay.setText(textDisplay.getText() + ".");
			}
		}// back button
		else if (v.getId() == R.id.imageButton2) {
			if (textDisplay.getText().toString().length() > 0) {

				int start = 0;
				int end = textDisplay.getText().toString().length() - 1;
				String newText = textDisplay.getText().toString()
						.substring(start, end);
				textDisplay.setText(newText);

			}

		}

		else if (v.getId() == R.id.imageButton22) {
			showSign("+");
		} else if (v.getId() == R.id.imageButton18) {
			showSign("-");
		} else if (v.getId() == R.id.imageButton14) {
			showSign("*");
		} else if (v.getId() == R.id.imageButton10) {
			showSign("/");
		}

		// equal
		else if (v.getId() == R.id.imageButton21) {
			Double newNumber = Double.parseDouble(textDisplay.getText()
					.toString());
			if (sign_flag == "+") {
				total = total + newNumber;
				textDisplay.setText(total.toString());
			} else if (sign_flag == "-") {
				total = total - newNumber;
				textDisplay.setText(total.toString());
			} else if (sign_flag == "*") {
				total = total * newNumber;
				textDisplay.setText(total.toString());
			} else if (sign_flag == "/") {
				total = total / newNumber;
				textDisplay.setText(total.toString());
			} else if (sign_flag == "%") {
				total = (total * newNumber) / 100;
				textDisplay.setText(total.toString());
			} else if (sign_flag == "22/7") {
				total = Math.PI;
				textDisplay.setText(total.toString());
			}
			sign_flag = "=";
				//square root
		} else if (v.getId() == R.id.imageButton5) {
			Double newNumber = Double.parseDouble(textDisplay.getText()
					.toString());
			total = Math.sqrt(newNumber);
			textDisplay.setText(total.toString());

		} else if (v.getId() == R.id.imageButton4) {
			Double newNumber = Double.parseDouble(textDisplay.getText()
					.toString());
			total = Math.pow(newNumber, 2);
			textDisplay.setText(total.toString());

		} else if (v.getId() == R.id.imageButton24) {
			Double newNumber = Double.parseDouble(textDisplay.getText()
					.toString());
			total = Math.pow(newNumber, 3);
			textDisplay.setText(total.toString());

		} else if (v.getId() == R.id.imageButton3) {
			Double newNumber = Double.parseDouble(textDisplay.getText()
					.toString());
			total = newNumber * (-1);
			textDisplay.setText(total.toString());

		} else if (v.getId() == R.id.imageButton6) {

			sign_flag = "%";
			clear_falg = 1; // set flag
			Double newNumber = Double.parseDouble(textDisplay.getText()
					.toString());
			total = newNumber ;

		} else if (v.getId() == R.id.imageButton23) {
			Double newNumber = Double.parseDouble(textDisplay.getText()
					.toString());
			total = Math.PI;
			textDisplay.setText(total.toString());

		}

		last_button = v.getId();

	}

}
