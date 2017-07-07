package com.ws.ijk.ws_ijk;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by WangShuo on 2017/6/30.
 */

public class CameraUtil {
    private static final String tag = "wangshuo";
    private CameraSizeComparator sizeComparator = new CameraSizeComparator();
    private static CameraUtil cameraUtil = null;
    private CameraUtil(){

    }
    public static CameraUtil getInstance(){
        if(cameraUtil == null){
            cameraUtil = new CameraUtil();
            return cameraUtil;
        }
        else{
            return cameraUtil;
        }
    }

    public Size getBestSize(List<Size> list, int th){
        if(list == null || list.size() < 1){
            return null;
        }
        boolean bool= false;

        Collections.sort(list, sizeComparator);
        int i = 0;
        for(Size s:list){
            if((s.width >= th) && equalRate(s, 1.3333f)){
                Log.i(tag, "最终设置Video尺寸:w = " + s.width + "h = " + s.height);
                bool = true;
                break;
            }
            i++;
        }
        if(bool){
            return list.get(i);
        }
        return null;
    }

    public Size getPictureSize(List<Size> list, int th){
        Collections.sort(list, sizeComparator);

        int i = 0;
        for(Size s:list){
            if((s.width >= th) && equalRate(s, 1.333f)){
                Log.i(tag, "最终设置图片尺寸:w = " + s.width + "h = " + s.height);
                break;
            }
            i++;
        }

        return list.get(i);
    }

    public boolean equalRate(Size s, float rate){
        float r = (float)(s.width)/(float)(s.height);
        if(Math.abs(r - rate) <= 0.2)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public  class CameraSizeComparator implements Comparator<Camera.Size> {
        //按升序排列
        public int compare(Size lhs, Size rhs) {
            // TODO Auto-generated method stub
            if(lhs.width == rhs.width){
                return 0;
            }
            else if(lhs.width > rhs.width){
                return 1;
            }
            else{
                return -1;
            }
        }

    }

     public static List<Camera.Size> getBackCameraPreviewSize(){
         Camera back = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
         List<Camera.Size> backSizeList = back.getParameters().getSupportedPreviewSizes();
         back.release();
         return backSizeList;
     }

    public static List<Camera.Size> getFrontCameraPreviewSize(){
        Camera front = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        List<Camera.Size> frontSizeList = front.getParameters().getSupportedPreviewSizes();
       //added by log
        StringBuilder str = new StringBuilder();
        str.append("如使用前置摄像头您可以输入的宽度有Preview：");
        for (Camera.Size fSize : frontSizeList) {
            str.append(fSize.width+"*"+fSize.height + "、;");
        }
        Log.e(tag,str.toString());
        //added by log
        front.release();
        return frontSizeList;
    }

    public static List<Camera.Size> getBackCameraVideoSize(){
        Camera back = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
        List<Camera.Size> backSizeList = back.getParameters().getSupportedVideoSizes();
        if(backSizeList == null){
            return null;
        }
        back.release();
        return backSizeList;
    }

    public static List<Camera.Size> getFrontCameraVideoSize(){
        Camera front = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        List<Camera.Size> frontSizeList = front.getParameters().getSupportedVideoSizes();
        if(frontSizeList == null){
            return null;
        }
        //added by log
        StringBuilder str = new StringBuilder();
        str.append("如使用前置摄像头您可以输入的宽度有Video：");
        for (Camera.Size fSize : frontSizeList) {
            str.append(fSize.width+"*"+fSize.height + "、;");
        }
        Log.e(tag,str.toString());
        //added by log
        front.release();
        return frontSizeList;
    }

    public static List<Camera.Size> getFrontCameraSize(){
        Camera front = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        List<Camera.Size> frontSizeList = front.getParameters().getSupportedVideoSizes();
        if(null == frontSizeList || frontSizeList.size()<=0){
            frontSizeList = front.getParameters().getSupportedPreviewSizes();
            Log.e(tag,"getSupportedVideoSizes==null");
        }
        //added by log
        StringBuilder str = new StringBuilder();
        str.append("如使用前置摄像头您可以输入的宽度有Preview：");
        for (Camera.Size fSize : frontSizeList) {
            str.append(fSize.width+"*"+fSize.height + "、;");
        }
        Log.e(tag,str.toString());
        //added by log
        front.release();
        return frontSizeList;
    }


    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
