package alan.example.com.persontestdemo.camera.camera1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import alan.example.com.persontestdemo.R;

public class CustomCamera
        extends AppCompatActivity
        implements SurfaceHolder.Callback {

    /**
     * Camera回调，通过data[]保持图片数据信息
     */
    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = getOutputMediaFile();
            if (pictureFile == null) {
                return;
            }
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(pictureFile);
//                fos.write(data);
//                fos.close();
//                Intent intent = new Intent(CustomCamera.this, CameraResult.class);
//                intent.putExtra("picPath", pictureFile.getAbsolutePath());

                Bitmap bitmap = BitmapFactory.decodeStream(fis);
                Matrix matrix = new Matrix();
                matrix.setRotate(90);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(),
                        matrix, true);

//            final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                ivPreview.setImageBitmap(bitmap);
//            Log.i("alan", bitmap.toString());
//                startActivity(intent);
                CustomCamera.this.finish();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };
    private SurfaceView mCameraPreview;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;
    private boolean isBackCameraOn = true;
    private ImageView ivPreview;
    private int surfaceWidth,surfaceHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_preview);
        initViews();
    }

    private void initViews() {

        mCameraPreview = (SurfaceView) findViewById(R.id.sv_camera);
        ivPreview = (ImageView) findViewById(R.id.iv_preview);

        mSurfaceHolder = mCameraPreview.getHolder();
        mSurfaceHolder.addCallback(this);
        mCameraPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.autoFocus(null);
            }
        });
    }

    /**
     * 切换前后摄像头
     *
     * @param view view
     */
    public void switchCamera(View view) {
        int cameraCount;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        // 遍历可用摄像头
        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (isBackCameraOn) {
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    releaseCamera();
                    mCamera = Camera.open(i);
                    setStartPreview(mCamera, mSurfaceHolder);
                    isBackCameraOn = false;
                    break;
                }
            } else {
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    releaseCamera();
                    mCamera = Camera.open(i);
                    setStartPreview(mCamera, mSurfaceHolder);
                    isBackCameraOn = true;
                    break;
                }
            }
        }

    }

    /**
     * 拍照
     *
     * @param view view
     */
    public void capture(View view) {
        Camera.Parameters params = mCamera.getParameters();
        params.setPictureFormat(ImageFormat.JPEG);

        params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        mCamera.setParameters(params);

        setSize();
        // 使用自动对焦功能
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    mCamera.takePicture(null, null, mPictureCallback);
                }
            }
        });
    }

    /**
     * 获取图片保持路径
     *
     * @return pic Path
     */
    private File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyCameraApp");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "temp.png");
        return mediaFile;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        DisplayMetrics dis = getResources().getDisplayMetrics();
        mSurfaceHolder.setFixedSize(dis.widthPixels,dis.heightPixels);

        setStartPreview(mCamera, mSurfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mSurfaceHolder.getSurface() == null) {
            return;
        }
        surfaceWidth = width;
        surfaceHeight = height;
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setSize();

        setStartPreview(mCamera, mSurfaceHolder);
    }

    private void setSize() {
        List<Camera.Size> mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

         Camera.Size mPreviewSize = CameraSize.getCloselyPreSize(
                 surfaceWidth,
                 surfaceHeight,
                 mSupportedPreviewSizes,
                 false
        );

        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
        parameters.setPictureSize(mPreviewSize.width,mPreviewSize.height);
        mSurfaceHolder.setFixedSize(mPreviewSize.width, mPreviewSize.height);

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }

    /**
     * 释放相机资源
     */
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.checkCameraHardware(this) && (mCamera == null)) {
            mCamera = getCamera();
            if (mSurfaceHolder != null) {
                setStartPreview(mCamera, mSurfaceHolder);
            }
        }
    }

    /**
     * 初始化相机
     *
     * @return camera
     */
    private Camera getCamera() {
        Camera camera;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            camera = null;
        }
        return camera;
    }

    /**
     * 检查是否具有相机功能
     *
     * @param context context
     * @return 是否具有相机功能
     */
    private boolean checkCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA);
    }

    /**
     * 在SurfaceView中预览相机内容
     *
     * @param camera camera
     * @param holder SurfaceHolder
     */
    private void setStartPreview(Camera camera, SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }
}
