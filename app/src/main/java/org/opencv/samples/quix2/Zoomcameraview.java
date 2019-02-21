package org.opencv.samples.quix2;

/**
 * Created by ygyg331 on 2019-01-25.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

import org.opencv.android.JavaCameraView;


public class Zoomcameraview extends JavaCameraView {
    public Zoomcameraview(Context context, int cameraId) {
        super(context, cameraId);
    }

    public Zoomcameraview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected SeekBar seekBar;

    public void setZoomControl(SeekBar _seekBar)
    {
        seekBar=_seekBar;
    }

    protected void enableZoomControls(final Camera.Parameters params)
    {

        final int maxZoom = params.getMaxZoom();
        seekBar.setMax(maxZoom);
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                                               int progressvalue=20;
                                               @Override
                                               public void onProgressChanged(SeekBar seekBar, int progress,
                                                                             boolean fromUser) {
                                                   // TODO Auto-generated method stub
                                                   progressvalue=15;
                                                   Camera.Parameters params = mCamera.getParameters();
                                                   params.setZoom(progress);
                                                   mCamera.setParameters(params);



                                               }
                                               @Override
                                               public void onStartTrackingTouch(SeekBar seekBar) {
                                                   // TODO Auto-generated method stub


                                               }
                                               @Override
                                               public void onStopTrackingTouch(SeekBar seekBar) {
                                                   // TODO Auto-generated method stub
                                                   params.setFocusMode("FOCUS_MODE_FIXED");
                                                   mCamera.setParameters(params);

                                               }



                                           }

        );

    }


    protected boolean initializeCamera(int width, int height)
    {

        boolean ret = super.initializeCamera(width, height);


        Camera.Parameters params = mCamera.getParameters();

        if(params.isZoomSupported())
            enableZoomControls(params);

        mCamera.setParameters(params);

        return ret;
    }

}