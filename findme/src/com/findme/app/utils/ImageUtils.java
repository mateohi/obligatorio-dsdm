package com.findme.app.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;

public class ImageUtils {

	private static final int WIDTH = 120;
	private static final int HEIGHT = 120;

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

	public static void saveImageOnDevice(Bitmap image, String filename,
			Context ctx) throws IOException {
		Bitmap resizedBitmap = Bitmap.createScaledBitmap(image, WIDTH, HEIGHT,
				false);
		FileOutputStream fos = ctx.openFileOutput(filename,
				Context.MODE_PRIVATE);
		resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
		fos.close();
	}

	public static Bitmap getImageFromDevice(String fileName, Context ctx)
			throws FileNotFoundException {
		FileInputStream fis = ctx.openFileInput(fileName);
		Bitmap bitmap = BitmapFactory.decodeStream(fis);

		return bitmap;
	}

	public static Bitmap getCircleBitmapFromDevice(String fileName, Context ctx)
			throws FileNotFoundException {
		Bitmap original = getImageFromDevice(fileName, ctx);

		return getCircleBitmap(original);
	}

	public static Bitmap getImageFromUri(ContentResolver cr, Uri selectedImage) {
		String[] filePathColumn = { MediaStore.Images.Media.DATA };
		Cursor cursor = cr.query(selectedImage, filePathColumn, null, null, null);
		cursor.moveToFirst();
		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		String picturePath = cursor.getString(columnIndex);
		cursor.close();

		Bitmap image = BitmapFactory.decodeFile(picturePath);
		return image;
	}
	
	public static byte[] bitmapToBytes(Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] bytes = stream.toByteArray();
		
		return bytes;
	}
	
	public static Bitmap bytesToBitmap(byte[] imageBytes) {
		Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes , 0, imageBytes.length);
		return bitmap;
	}
}
