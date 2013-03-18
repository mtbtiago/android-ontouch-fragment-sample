package com.example.pantallatactil;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PantallaTactilActivity extends Activity implements OnTouchListener {

	private TextView tvAction;
	private TextView tvPressure;
	private TextView tvPressureMax;
	private ProgressBar pbPressure;
	private ImageView imPressureAlert;
	private float mPressureMax = 0;
	private TextView tvSize;
	private TextView tvSizeMax;
	private ProgressBar pbSize;
	private ImageView imSizeAlert;
	private float mSizeMax = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView entrada = (TextView) findViewById(R.id.TextViewEntrada);
		entrada.setOnTouchListener(this);

		tvAction = (TextView) findViewById(R.id.tvAction);
		tvPressure = (TextView) findViewById(R.id.tvPressure);
		tvPressureMax = (TextView) findViewById(R.id.tvPressureMax);
		imPressureAlert = (ImageView) findViewById(R.id.imPressureAlert);
		pbPressure = (ProgressBar) findViewById(R.id.pbPressure);
		tvSize = (TextView) findViewById(R.id.tvSize);
		tvSizeMax = (TextView) findViewById(R.id.tvSizeMax);
		pbSize = (ProgressBar) findViewById(R.id.pbSize);
		imSizeAlert = (ImageView) findViewById(R.id.imSizeAlert);

		doReset(null);
	}

	private void doShow(int aAction, float aPressure, float aSize) {
		switch (aAction) {
		case -1:
			tvAction.setText("Not pressed yet");
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

		if (aPressure > mPressureMax) {
			mPressureMax = aPressure;
		}
		if (aAction < 0) {
			tvPressureMax.setText("");
		} else {
			String s = Float.toString(mPressureMax);
			if (s.length() > 4) {
				s = s.substring(0, 4);
			}
			tvPressureMax.setText(s);
		}
		tvPressure.setText(Float.toString(aPressure));

		if (aSize > mSizeMax) {
			mSizeMax = aSize;
		}
		if (aAction < 0) {
			tvSizeMax.setText("");
		} else {
			String s = Float.toString(mSizeMax);
			if (s.length() > 4) {
				s = s.substring(0, 4);
			}
			tvSizeMax.setText(s);
		}
		tvSize.setText(Float.toString(aSize));

		doProgress(aPressure, aSize);

	}

	public void doReset(View v) {
		doShow(-1, 0, 0);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		doShow(event.getAction(), event.getPressure(), event.getSize());
		if (event.getAction() == MotionEvent.ACTION_UP) {
			doProgress(0, 0);
		}
		return true;
	}

	private void doProgress(float aPressure, float aSize) {
		int iPressure = Math.round(aPressure * 100);
		pbPressure.setProgress(iPressure);
		if (iPressure > 100) {
			imPressureAlert.setVisibility(View.VISIBLE);
		}else{
			imPressureAlert.setVisibility(View.GONE);
		}

		int iSize = Math.round(aSize * 100);
		pbSize.setProgress(iSize);
		if (iSize > 100) {
			imSizeAlert.setVisibility(View.VISIBLE);
		}else{
			imSizeAlert.setVisibility(View.GONE);
		}
	}
}
