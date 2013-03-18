package com.example.pantallatactil;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class PantallaTactilActivity extends Activity implements OnTouchListener {

	private TextView tvAction;
	private ProgressFragment frPressure;
	private ProgressFragment frSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView entrada = (TextView) findViewById(R.id.TextViewEntrada);
		entrada.setOnTouchListener(this);

		tvAction = (TextView) findViewById(R.id.tvAction);
		frPressure = (ProgressFragment) getFragmentManager().findFragmentById(
				R.id.frPressure);
		frPressure.setValueName("Pressure");
		frSize = (ProgressFragment) getFragmentManager().findFragmentById(
				R.id.frSize);
		frSize.setValueName("Size");
	}

	public void doReset(View v) {
		doShow(-1,0,0);
	}

	private void doShow(int aAction, float aPressure, float aSize) {
		boolean bReset = false;
		switch (aAction) {
		case -1:
			tvAction.setText("Not pressed yet");
			bReset = true;
			break;
		case 0:
			tvAction.setText("ACTION_DOWN");
			break;
		case 1:
			tvAction.setText("ACTION_UP");
			break;
		case 2:
			tvAction.setText("ACTION_MOVE");
			break;
		}
		frPressure.UpdateValue(bReset,aPressure);
		frSize.UpdateValue(bReset,aSize);
		
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			frPressure.UpdateProgress(0);
			frSize.UpdateProgress(0);
		} else {
			doShow(event.getAction(),event.getPressure(),event.getSize());
		}
		return true;
	}

}
