package alan.example.com.persontestdemo.camera.camera4;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import alan.example.com.persontestdemo.R;

public class Camera4Activity extends Activity implements PictureCallBack {
    private static final String TAG = "yanzi";
    CameraSurfaceView surfaceView = null;

    float previewRate = -1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera_4);
        initUI();
        initViewParams();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action b
        return true;
    }

    private void initUI() {
        surfaceView = (CameraSurfaceView) findViewById(R.id.camera_surfaceview);
    }

    private void initViewParams() {
        LayoutParams params = surfaceView.getLayoutParams();
        Point p = DisplayUtil.getScreenMetrics(this);
        params.width = p.x;
        params.height = p.y;
        previewRate = DisplayUtil.getScreenRate(this);
        surfaceView.setLayoutParams(params);


    }


    private void takePicture() {
        CameraInterface.getInstance().doTakePicture(this);
     }


    @Override
    public void setPictureUrl(String path) {
    }

    @Override
    public void setBitmap(Bitmap bitmap) {

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(bitmap);
    }
}
