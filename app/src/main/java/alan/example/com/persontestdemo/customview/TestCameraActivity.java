package alan.example.com.persontestdemo.customview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.OrientationEventListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import alan.example.com.persontestdemo.R;

public class TestCameraActivity extends Activity {

    Camera camera;
    Button snap;
    Button switchCamera;
    
    SurfaceView surfaceView;
    int camera_id = 0;
    IOrientationEventListener iOriListener;
    
    final int SUCCESS = 233;
    SnapHandler handler = new SnapHandler();
    
    int camera_direction = Camera.CameraInfo.CAMERA_FACING_BACK; //����ͷ����
    
    public void switchCamera()
    {
        if(camera_direction == CameraInfo.CAMERA_FACING_BACK)
        {
            camera_direction = CameraInfo.CAMERA_FACING_FRONT;
        }
        else
        {
            camera_direction = CameraInfo.CAMERA_FACING_BACK;
        }
        int mNumberOfCameras = Camera.getNumberOfCameras();
        CameraInfo cameraInfo = new CameraInfo();
        for (int i = 0; i < mNumberOfCameras; i++)
        {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == camera_direction)
            {
                camera_id = i;
            }
        }
        if(null != camera)
        {
            camera.stopPreview();
            camera.release();
        }
        camera = Camera.open(camera_id);
        try {
            camera.setPreviewDisplay(surfaceView.getHolder());
            camera.startPreview();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        setCameraAndDisplay(surfaceView.getWidth(), surfaceView.getHeight());
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // ��ʾ����  
        setContentView(R.layout.activity_incell_camera);  
        surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);
        switchCamera = (Button) this.findViewById(R.id.switch_btn);
        switchCamera.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                switchCamera();
            }
        });
        snap = (Button) this.findViewById(R.id.snap);
        snap.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                camera.takePicture(null, null, new PictureCallback(){

                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        // TODO Auto-generated method stub
                        final byte[] tempdata = data;
                        Thread thread = new Thread(new Runnable(){

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                File dir = new File("mnt/sdcard/testcamera"); 
                                if(!dir.exists())
                                {
                                    dir.mkdirs();// �����ļ���  
                                }
                                String name = DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance()) + ".jpg";
                                File f = new File("mnt/sdcard/testcamera/" +name);
                                if(!f.exists())
                                {
                                    try {
                                        f.createNewFile();
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                                FileOutputStream outputStream;
                                try {
                                    outputStream = new FileOutputStream(f);
                                    outputStream.write(tempdata); // д��sd����  
                                    outputStream.close(); // �ر������  
                                } catch (FileNotFoundException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } // �ļ������  
                                catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                Log.v("TestCameraActivityTag", "store success");
                                handler.sendEmptyMessage(SUCCESS);
                            }
                            
                        });
                        //�����洢��Ƭ���߳�
                        thread.start();
                    }
                    
                });
            }
            
        });
  
        
        surfaceView.getHolder().setKeepScreenOn(true);// ��Ļ����  
        surfaceView.getHolder().addCallback(new Callback(){

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // TODO Auto-generated method stub
                int mNumberOfCameras = Camera.getNumberOfCameras();

                // Find the ID of the default camera
                CameraInfo cameraInfo = new CameraInfo();
                for (int i = 0; i < mNumberOfCameras; i++)
                {
                    Camera.getCameraInfo(i, cameraInfo);
                    if (cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK)
                    {
                        camera_id = i;
                    }
                }
                camera = Camera.open(camera_id);
                try {
                    camera.setPreviewDisplay(holder);
                    camera.startPreview(); // ��ʼԤ��  

                    iOriListener.enable();
                    
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
                
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                    int width, int height) {
                // TODO Auto-generated method stub
                setCameraAndDisplay(width, height);
                
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // TODO Auto-generated method stub
                if(null != camera)
                {
                    camera.release();
                    camera = null;
                }
                
            }
            
        });//ΪSurfaceView�ľ�����һ���ص�����  
        
        iOriListener = new IOrientationEventListener(this);
    }
    
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        this.iOriListener.disable();
    }

    public class IOrientationEventListener extends OrientationEventListener{

        public IOrientationEventListener(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }


        @Override
        public void onOrientationChanged(int orientation) {
            // TODO Auto-generated method stub
            if(ORIENTATION_UNKNOWN == orientation)
            {
                return;
            }
            CameraInfo info = new CameraInfo();
            Camera.getCameraInfo(camera_id, info);
            orientation = (orientation + 45) / 90 * 90;
            int rotation = 0;
            if(info.facing == CameraInfo.CAMERA_FACING_FRONT)
            {
                rotation = (info.orientation - orientation + 360) % 360;
            }
            else
            {
                rotation = (info.orientation + orientation) % 360;
            }
            if(null != camera)
            {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setRotation(rotation);
                camera.setParameters(parameters);
            }
            
        }
        
    }
    
    public void setCameraAndDisplay(int width, int height)
    {
        Camera.Parameters parameters = camera.getParameters();
        /*��ȡ����ͷ֧�ֵ�PictureSize�б�*/
        List<Size> pictureSizeList = parameters.getSupportedPictureSizes();
        /*���б���ѡȡ���ʵķֱ���*/
        Size picSize = CameraUtils.getProperSize(pictureSizeList, ((float)width)/height);
        if(null != picSize)
        {
            parameters.setPictureSize(picSize.width, picSize.height);
        }
        else
        {
            picSize = parameters.getPictureSize();
        }
        /*��ȡ����ͷ֧�ֵ�PreviewSize�б�*/
        List<Size> previewSizeList = parameters.getSupportedPreviewSizes();
        Size preSize = CameraUtils.getProperSize(previewSizeList, ((float)width)/height);
        if(null != preSize)
        {Log.v("TestCameraActivityTag", preSize.width + "," + preSize.height);
            parameters.setPreviewSize(preSize.width, preSize.height);
        }
        
        /*����ѡ����PictureSize��������SurfaceView��С*/
        float w = picSize.width;
        float h = picSize.height;
        surfaceView.setLayoutParams(new RelativeLayout.LayoutParams( (int)(height*(w/h)), height)); 
        
        parameters.setJpegQuality(100); // ������Ƭ����
        
        //���ж��Ƿ�֧�֣�����ᱨ��
        if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE))
        {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }
        camera.cancelAutoFocus();//ֻ�м�������һ�䣬�Ż��Զ��Խ���  
        camera.setDisplayOrientation(0);
        camera.setParameters(parameters);
    }
    
    class SnapHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if(msg.what == SUCCESS)
            {
                Toast.makeText(TestCameraActivity.this, "��Ƭ�洢��testcamera�ļ���", Toast.LENGTH_SHORT).show();
            }
            try {
                camera.setPreviewDisplay(surfaceView.getHolder());
                camera.startPreview();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
}
