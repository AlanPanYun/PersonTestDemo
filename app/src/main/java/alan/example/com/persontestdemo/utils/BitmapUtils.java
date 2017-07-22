package alan.example.com.persontestdemo.utils;

import android.graphics.Bitmap;

/**
 * Created by qk14 on 2017/6/21.
 */

public class BitmapUtils {


    /**
     * 按照一定的宽高比例裁剪图片
     *
     * @param bitmap
     * @param num1   长边的比例
     * @param num2   短边的比例
     * @return
     */
    public static Bitmap ImageCrop(Bitmap bitmap, int num1, int num2, int addHeght,
                                   boolean isRecycled) {
        if (bitmap == null) {
            return null;
        }
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();
        int retX, retY;
        int nw, nh;
        if (w > h) {
            if (h > w * num2 / num1) {
                nw = w;
                nh = w * num2 / num1 + addHeght;
                retX = 0;
                retY = (h - nh) / 2;
            } else {
                nw = h * num1 / num2;
                nh = h;
                retX = (w - nw) / 2;
                retY = 0;
            }
        } else {
            if (w > h * num2 / num1) {
                nh = h;
                nw = h * num2 / num1;
                retY = 0;
                retX = (w - nw) / 2;
            } else {
                nh = w * num1 / num2 + addHeght;
                nw = w;
                retY = (h - nh) / 2;
                retX = 0;
            }
        }
        Bitmap bmp = Bitmap.createBitmap(bitmap, retX, retY, nw, nh, null,
                false);
        if (isRecycled && bitmap != null && !bitmap.equals(bmp)
                && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
        return bmp;// Bitmap.createBitmap(bitmap, retX, retY, nw, nh, null,
        // false);
    }
}

