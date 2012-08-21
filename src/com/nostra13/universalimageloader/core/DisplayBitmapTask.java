package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.ImageView;

/**
 * Displays bitmap in {@link ImageView}. Must be called on UI thread.
 * 
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 * @see ImageLoadingListener
 */
final class DisplayBitmapTask implements Runnable {

	private final Bitmap bitmap;
	private final ImageView imageView;
	private final ImageLoadingListener listener;

	public DisplayBitmapTask(Bitmap bitmap, ImageView imageView,
			ImageLoadingListener listener) {
		this.bitmap = bitmap;
		this.imageView = imageView;
		this.listener = listener;
	}

	public void run() {
		imageView.setImageBitmap(bitmap);
		listener.onLoadingComplete(bitmap);
	}

}
