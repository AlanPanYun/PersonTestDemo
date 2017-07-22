package alan.example.com.persontestdemo.camera.camera4;

import android.graphics.Bitmap;

/**
 * Created by qk14 on 2017/6/23.
 */

public interface PictureCallBack {

    void setPictureUrl(String path);

    void setBitmap(Bitmap bitmap);

}
