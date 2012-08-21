package com.liqingyi.mapbo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

/**
 * An assortment of UI helpers.
 */
public class UIUtils {

	// Thu Aug 16 20:48:33 +0800 2012
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"EEE MMM d HH:mm:ss Z yyyy");
	private static final SimpleDateFormat format = new SimpleDateFormat(
			"yyyy年MM月dd日 HH时mm分");

	public static String formatCreated_at(String string) throws ParseException {
		Date date = dateFormat.parse(string);
		return format.format(date);
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
								: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	public static Bitmap getCircleBitmap(Bitmap bitmap) {
		int x = bitmap.getWidth();
//		int y=bitmap.getHeight();
		Bitmap output = Bitmap.createBitmap(x, x, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		// 根据原来图片大小画一个矩形
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		paint.setAntiAlias(true);
		paint.setColor(color);
		// 画出一个圆
		canvas.drawCircle(x / 2, x / 2, x / 2, paint);
//		canvas.translate(-25, -6);
		// 取两层绘制交集,显示上层
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		// 将图片画上去
		canvas.drawBitmap(bitmap, rect, rect, paint);
		// 返回Bitmap对象
		return output;
	}
}
