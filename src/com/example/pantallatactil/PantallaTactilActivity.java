package com.example.pantallatactil;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class PantallaTactilActivity extends Activity implements OnTouchListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView entrada = (TextView) findViewById(R.id.TextViewEntrada);
		entrada.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		TextView salida = (TextView) findViewById(R.id.TextViewSalida);
		salida.append(event.toString() + "\n");
		return true;
	}
}
