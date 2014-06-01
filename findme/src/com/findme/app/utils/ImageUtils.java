package com.findme.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;

public class ImageUtils {

	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;

	public static Bitmap getCircleBitmap(Bitmap sourceBitmap) {
		Rect sourceRect = new Rect(0, 0, sourceBitmap.getWidth(),
				sourceBitmap.getHeight());
		Rect newRect = new Rect(0, 0, WIDTH, HEIGHT);

		Bitmap targetBitmap = Bitmap.createBitmap(WIDTH, HEIGHT,
				Bitmap.Config.ARGB_8888);

		Path path = new Path();
		path.addCircle(((float) WIDTH - 1) / 2, ((float) HEIGHT - 1) / 2,
				(Math.min(((float) WIDTH), ((float) HEIGHT)) / 2),
				Path.Direction.CCW);

		Canvas canvas = new Canvas(targetBitmap);
		canvas.clipPath(path);
		canvas.drawBitmap(sourceBitmap, sourceRect, newRect, null);

		return targetBitmap;
	}
	
	public static void saveImageOnDevice(Bitmap image, String filename, Context ctx) throws IOException {
		FileOutputStream fos = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
		image.compress(Bitmap.CompressFormat.PNG, 100, fos);
		fos.close();
	}
	
	public static Bitmap getImageFromDevice(String fileName, Context ctx) {

        File file = new File(path, "profile.jpg");
        Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
        return bitmap;
	}
}
