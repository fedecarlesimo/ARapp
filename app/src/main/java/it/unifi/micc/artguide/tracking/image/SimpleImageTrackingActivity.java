package it.unifi.micc.artguide.tracking.image;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.wikitude.NativeStartupConfiguration;
import com.wikitude.WikitudeSDK;
import com.wikitude.common.WikitudeError;
import com.wikitude.common.camera.CameraSettings;
import com.wikitude.common.rendering.RenderExtension;
import com.wikitude.rendering.ExternalRendering;
import com.wikitude.tracker.ImageTarget;
import com.wikitude.tracker.ImageTracker;
import com.wikitude.tracker.ImageTrackerListener;
import com.wikitude.tracker.TargetCollectionResource;

import it.unifi.micc.artguide.Api;
import it.unifi.micc.artguide.WikitudeSDKConstants;
import it.unifi.micc.artguide.rendering.external.CustomSurfaceView;
import it.unifi.micc.artguide.rendering.external.Driver;
import it.unifi.micc.artguide.rendering.external.GLRenderer;
import it.unifi.micc.artguide.rendering.external.StrokedRectangle;

public class SimpleImageTrackingActivity extends Activity implements ImageTrackerListener, ExternalRendering {

    private static final String TAG = "SimpleImageTracking";

    private WikitudeSDK wikitudeSDK;
    private CustomSurfaceView customSurfaceView;
    private Driver driver;
    private GLRenderer glRenderer;

    private Api api;
    String checkVal = "0";

    private boolean isImageRecognized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            checkVal = bundle.getString("checkval");
        }

        api = new Api(this, checkVal);

        wikitudeSDK = new WikitudeSDK(this);
        NativeStartupConfiguration startupConfiguration = new NativeStartupConfiguration();
        startupConfiguration.setLicenseKey(WikitudeSDKConstants.WIKITUDE_SDK_KEY);
        startupConfiguration.setCameraPosition(CameraSettings.CameraPosition.BACK);
        startupConfiguration.setCameraResolution(CameraSettings.CameraResolution.AUTO);
        wikitudeSDK.onCreate(getApplicationContext(), this, startupConfiguration);

        //   final TargetCollectionResource targetCollectionResource = wikitudeSDK.getTrackerManager().createTargetCollectionResource("file:///android_asset/monnadavi.wtc");
        final TargetCollectionResource targetCollectionResource = wikitudeSDK.getTrackerManager().createTargetCollectionResource("file:///storage/emulated/0/Android/data/it.unifi.micc.artguide/files/tracker.wtc");

        wikitudeSDK.getTrackerManager().createImageTracker(targetCollectionResource, this, null);

    }

    private void makeToast(final String s) {
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(SimpleImageTrackingActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        wikitudeSDK.onResume();
        customSurfaceView.onResume();
        driver.start();
        this.isImageRecognized = false;
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
    public void onTargetsLoaded(ImageTracker tracker) {
        Log.v(TAG, "Image tracker loaded");
        System.out.println("Image tracker loaded");

    }

    @Override
    public void onErrorLoadingTargets(ImageTracker tracker, WikitudeError error) {
        Log.v(TAG, "Unable to load image tracker. Reason: " + error.getMessage());
    }


    @Override
    public void onImageRecognized(ImageTracker tracker, final ImageTarget target) {
        Log.v(TAG, "Recognized target " + target.getName());

        if (!this.isImageRecognized) {
            this.isImageRecognized = true;
            System.out.println("1>>>>>" + target.getName());
            //  makeToast(target.getName());
            api.request(target.getName());
        }

        StrokedRectangle strokedRectangle = new StrokedRectangle(StrokedRectangle.Type.STANDARD);
        glRenderer.setRenderablesForKey(target.getName() + target.getUniqueId(), strokedRectangle, null);
    }

    @Override
    public void onImageTracked(ImageTracker tracker, final ImageTarget target) {
        StrokedRectangle strokedRectangle = (StrokedRectangle) glRenderer.getRenderableForKey(target.getName() + target.getUniqueId());

        if (strokedRectangle != null) {
            strokedRectangle.viewMatrix = target.getViewMatrix();
            strokedRectangle.setXScale(target.getTargetScale().x);
            strokedRectangle.setYScale(target.getTargetScale().y);
        }

    }

    @Override
    public void onImageLost(ImageTracker tracker, final ImageTarget target) {
        Log.v(TAG, "Lost target " + target.getName());
        glRenderer.removeRenderablesForKey(target.getName() + target.getUniqueId());
    }

    @Override
    public void onExtendedTrackingQualityChanged(ImageTracker tracker, final ImageTarget target, final int oldTrackingQuality, final int newTrackingQuality) {

    }
}
