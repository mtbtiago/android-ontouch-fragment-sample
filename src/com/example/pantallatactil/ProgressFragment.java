package com.example.pantallatactil;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressFragment extends Fragment {

	private TextView tvValueName;
	private TextView tvValue;
	private TextView tvValueMax;
	private TextView tvValueMin;
	private ProgressBar pbValue;
	private ImageView imAlert;
	private float mValueMax = 0;
	private float mValueMin = 0xFFFF;
	public static final boolean RESET = true;
	private String valueName;

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String aValue) {
		valueName = aValue;
		tvValueName.setText(aValue);
	}

	// Called once the Fragment has been created in order for it to
	// create its user interface.
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Create, or inflate the Fragment's UI, and return it.
		// If this Fragment has no UI then return null.
		View view = inflater.inflate(R.layout.fragment_progress, container,
				false);
		tvValueName = (TextView) view.findViewById(R.id.tvValueName);
		tvValue = (TextView) view.findViewById(R.id.tvValue);
		tvValueMax = (TextView) view.findViewById(R.id.tvMax);
		tvValueMin = (TextView) view.findViewById(R.id.tvMin);
		imAlert = (ImageView) view.findViewById(R.id.imAlert);
		pbValue = (ProgressBar) view.findViewById(R.id.pbValue);
		return view;
	}

	// Called at the start of the visible lifetime.
	@Override
	public void onStart() {
		super.onStart();
		UpdateValue(RESET, 0);
	}

	private String cutTo4Chars(Float aValue) {
		String s = Float.toString(aValue);
		if (s.length() > 4) {
			s = s.substring(0, 4);
		}
		return s;
	}

	public void UpdateValue(boolean aReset, float aValue) {
		if ((aValue < mValueMin) && (aValue != 0)) {
			mValueMin = aValue;
		}
		if (aValue > mValueMax) {
			mValueMax = aValue;
		}
		if (aReset) {
			tvValueMin.setText("");
			tvValueMax.setText("");
		} else {
			tvValueMin.setText(cutTo4Chars(mValueMin));
			tvValueMax.setText(cutTo4Chars(mValueMax));
		}
		tvValue.setText(cutTo4Chars(aValue));

		UpdateProgress(aValue);
	}

	public void UpdateProgress(float aValue) {
		int iValue = Math.round(aValue * 100);
		pbValue.setProgress(iValue);
		if (iValue > 100) {
			imAlert.setVisibility(View.VISIBLE);
		} else {
			imAlert.setVisibility(View.GONE);
		}
	}
}
