package com.liqingyi.mapbo.util;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import android.widget.ImageView;

public class CornerImageView extends ImageView {

	public CornerImageView(Context context) {
		super(context);
	}

	public CornerImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CornerImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	@Override
	public void setImageDrawable(Drawable drawable) {

		super.setImageDrawable(new BitmapDrawable(getResources(), UIUtils
				.getCircleBitmap(UIUtils.drawableToBitmap(drawable))));

	}

}
