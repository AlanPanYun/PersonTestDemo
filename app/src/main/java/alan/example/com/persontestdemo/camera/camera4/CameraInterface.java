package alan.example.com.persontestdemo.camera.camera4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.util.List;

public class CameraInterface {
    private static final String TAG = "YanZi";
    private Camera mCamera;
    private Camera.Parameters mParams;
    private boolean isPreviewing = false;
    private static CameraInterface mCameraInterface;

    private PictureCallBack mPictureCallBack;

    public interface CamOpenOverCallback {
        public void cameraHasOpened();
    }

    private CameraInterface() {

    }

    public static synchronized CameraInterface getInstance() {
        if (mCameraInterface == null) {
            mCameraInterface = new CameraInterface();
        }
        return mCameraInterface;
    }

    /**
     * ��Camera
     *
     * @param callback
     */
    public void doOpenCamera(CamOpenOverCallback callback, int cameraId) {
        Log.i(TAG, "Camera open....");
        mCamera = Camera.open(cameraId);
        if (callback != null) {
            callback.cameraHasOpened();
        }
    }

    public void doStartPreview(SurfaceHolder holder, float previewRate) {
        Log.i(TAG, "doStartPreview...");
        if (isPreviewing) {
            mCamera.stopPreview();
            return;
        }
        if (mCamera != null) {

            mParams = mCamera.getParameters();
            mParams.setPictureFormat(PixelFormat.JPEG);
            CamParaUtil.getInstance().printSupportPictureSize(mParams);
            CamParaUtil.getInstance().printSupportPreviewSize(mParams);
            Size pictureSize = CamParaUtil.getInstance().getPropPictureSize(
                    mParams.getSupportedPictureSizes(), previewRate, 800);
            mParams.setPictureSize(pictureSize.width, pictureSize.height);
            Size previewSize = CamParaUtil.getInstance().getPropPreviewSize(
                    mParams.getSupportedPreviewSizes(), previewRate, 800);
            mParams.setPreviewSize(previewSize.width, previewSize.height);

            mCamera.setDisplayOrientation(90);

            CamParaUtil.getInstance().printSupportFocusMode(mParams);
            List<String> focusModes = mParams.getSupportedFocusModes();
            if (focusModes.contains("continuous-video")) {
                mParams.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }
            mCamera.setParameters(mParams);

            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();//����Ԥ��
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            isPreviewing = true;

            mParams = mCamera.getParameters();
        }
    }

    public void doStopCamera() {
        if (null != mCamera) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            isPreviewing = false;
            mCamera.release();
            mCamera = null;
        }
    }

    public void doTakePicture(PictureCallBack pictureCallBack) {
        if (isPreviewing && (mCamera != null)) {
            mCamera.takePicture(null, null, mJpegPictureCallback);
            mPictureCallBack = pictureCallBack;
        }
    }

    public Camera.Parameters getCameraParams() {
        if (mCamera != null) {
            mParams = mCamera.getParameters();
            return mParams;
        }
        return null;
    }


    PictureCallback mJpegPictureCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub
            Log.i(TAG, "myJpegCallback:onPictureTaken...");
            Bitmap b = null;
            if (null != data) {
                b = BitmapFactory.decodeByteArray(data, 0, data.length);
                mCamera.stopPreview();
                isPreviewing = false;
            }
            if (null != b) {
                Bitmap rotaBitmap = ImageUtil.getRotateBitmap(b, 90.0f);
                mPictureCallBack.setBitmap(rotaBitmap);
                FileUtil.saveBitmap(rotaBitmap, mPictureCallBack);
            }
            mCamera.startPreview();
            isPreviewing = true;
        }
    };

}
