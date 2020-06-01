package it.unifi.micc.artguide.tracking.object;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.wikitude.NativeStartupConfiguration;
import com.wikitude.WikitudeSDK;
import com.wikitude.common.WikitudeError;
import com.wikitude.common.camera.CameraSettings;
import com.wikitude.common.rendering.RenderExtension;
import com.wikitude.rendering.ExternalRendering;
import com.wikitude.tracker.ObjectTarget;
import com.wikitude.tracker.ObjectTracker;
import com.wikitude.tracker.ObjectTrackerListener;
import com.wikitude.tracker.TargetCollectionResource;

import it.unifi.micc.artguide.Api;
import it.unifi.micc.artguide.WikitudeSDKConstants;
import it.unifi.micc.artguide.rendering.external.CustomSurfaceView;
import it.unifi.micc.artguide.rendering.external.Driver;
import it.unifi.micc.artguide.rendering.external.GLRenderer;
import it.unifi.micc.artguide.rendering.external.OccluderCube;
import it.unifi.micc.artguide.rendering.external.StrokedCube;

public class ObjectTrackingActivity extends Activity implements ObjectTrackerListener, ExternalRendering {

    private static final String TAG = "SimpleObjectTracking";

    private WikitudeSDK wikitudeSDK;
    private CustomSurfaceView customSurfaceView;
    private Driver driver;
    private GLRenderer glRenderer;

    private Api api;

    private boolean isImageRecognized=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = new Api(this,"0");

        wikitudeSDK = new WikitudeSDK(this);
        NativeStartupConfiguration startupConfiguration = new NativeStartupConfiguration();
        startupConfiguration.setLicenseKey(WikitudeSDKConstants.WIKITUDE_SDK_KEY);
        startupConfiguration.setCameraPosition(CameraSettings.CameraPosition.BACK);
        startupConfiguration.setCameraResolution(CameraSettings.CameraResolution.AUTO);

        wikitudeSDK.onCreate(getApplicationContext(), this, startupConfiguration);

        final TargetCollectionResource targetCollectionResource = wikitudeSDK.getTrackerManager().createTargetCollectionResource("file:///storage/emulated/0/Android/data/it.unifi.micc.artguide/files/tracker.wto");
        wikitudeSDK.getTrackerManager().createObjectTracker(targetCollectionResource, ObjectTrackingActivity.this, null);

    }

    private void makeToast(final String s){
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(ObjectTrackingActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        wikitudeSDK.onResume();
        customSurfaceView.onResume();
        driver.start();
        this.isImageRecognized=false;
        this.api.stopProgressDialog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        customSurfaceView.onPause();
        driver.stop();
        wikitudeSDK.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wikitudeSDK.onDestroy();
    }

    @Override
    public void onRenderExtensionCreated(final RenderExtension renderExtension) {
        glRenderer = new GLRenderer(renderExtension);
        wikitudeSDK.getCameraManager().setRenderingCorrectedFovChangedListener(glRenderer);
        customSurfaceView = new CustomSurfaceView(getApplicationContext(), glRenderer);
        driver = new Driver(customSurfaceView, 30);
        setContentView(customSurfaceView);
    }

    @Override
    public void onTargetsLoaded(ObjectTracker tracker) {
        Log.v(TAG, "Object tracker loaded");
        System.out.println("Image tracker loaded");
    }

    @Override
    public void onErrorLoadingTargets(ObjectTracker tracker, WikitudeError error) {
        Log.v(TAG, "Unable to load image tracker. Reason: " + error.getMessage());
    }

    @Override
    public void onObjectRecognized(ObjectTracker tracker, final ObjectTarget target) {
        Log.v(TAG, "Recognized target " + target.getName());
        System.out.println("2>>>>>"+target.getName());

        if(!this.isImageRecognized){
            this.isImageRecognized=true;
            System.out.println("3>>>>>" + target.getName());
            //  makeToast(target.getName());
            api.request(target.getName());
        }


        StrokedCube strokedCube = new StrokedCube();
        OccluderCube occluderCube = new OccluderCube();

        glRenderer.setRenderablesForKey(target.getName(), strokedCube, occluderCube);
    }

    @Override
    public void onObjectTracked(ObjectTracker tracker, final ObjectTarget target) {
        StrokedCube strokedCube = (StrokedCube) glRenderer.getRenderableForKey(target.getName());
        System.out.println(">>tracked>>>"+target.getName());
        if (strokedCube != null) {
            strokedCube.viewMatrix = target.getViewMatrix();

            strokedCube.setXScale(target.getTargetScale().x);
            strokedCube.setYScale(target.getTargetScale().y);
            strokedCube.setZScale(target.getTargetScale().z);
        }

        OccluderCube occluderCube = (OccluderCube) glRenderer.getOccluderForKey(target.getName());
        if (occluderCube != null) {
            occluderCube.viewMatrix = target.getViewMatrix();

            occluderCube.setXScale(target.getTargetScale().x);
            occluderCube.setYScale(target.getTargetScale().y);
            occluderCube.setZScale(target.getTargetScale().z);
        }
    }

    @Override
    public void onObjectLost(ObjectTracker tracker, final ObjectTarget target) {
        Log.v(TAG, "Lost target " + target.getName());
        glRenderer.removeRenderablesForKey(target.getName());
    }

    @Override
    public void onExtendedTrackingQualityChanged(final ObjectTracker tracker, final ObjectTarget target, final int oldTrackingQuality, final int newTrackingQuality) {

    }
}
