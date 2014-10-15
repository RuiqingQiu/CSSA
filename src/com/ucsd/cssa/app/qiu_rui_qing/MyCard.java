package com.ucsd.cssa.app.qiu_rui_qing;

import android.view.View;
import android.widget.TextView;
import com.ucsd.cssa.app.R;
import com.fima.cardsui.objects.RecyclableCard;

public class MyCard extends RecyclableCard {

	public MyCard(String title){
		super(title);
	}

	@Override
	protected int getCardLayoutId() {
		return R.layout.card_ex;
	}

	@Override
	protected void applyTo(View convertView) {
		((TextView) convertView.findViewById(R.id.title)).setText(title);
	}
}
