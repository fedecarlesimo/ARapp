package it.unifi.micc.artguide;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnPreparedListener mOpl;
    private VideoView mVideoView;
    private MediaPlayer mMediaPlayer;
    private RelativeLayout mVideoRelativeLayout;
    private boolean mResizing = false;
    String myCheck = "0";
    TextView name, autore, anno_creazione, descrizione, storia,storiatext,autoretext,descrizionetext,anno_creazionetext;
    ImageView imageView;
    Button button_audio;

    @Override
    protected void onPause() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
        super.onPause();
    }

    private Pair<Integer, Integer> getVideoSize(String url) {
        MediaMetadataRetriever retriever = null;
        Pair<Integer, Integer> videoSize = null;
        try {
            retriever = new MediaMetadataRetriever();
            Bitmap bmp = null;
            retriever.setDataSource(url, new HashMap<String, String>());
            bmp = retriever.getFrameAtTime();
            int videoHeight = bmp.getHeight();
            int videoWidth = bmp.getWidth();
            bmp.recycle();
            videoSize = new Pair<>(videoWidth, videoHeight);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (retriever != null) {
                retriever.release();
            }
        }

        return videoSize;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            myCheck = bundle.getString("checkval");
        }

        name = findViewById(R.id.nome);
        autore = findViewById(R.id.autore);
        anno_creazione = findViewById(R.id.anno_creazione);
        descrizione = findViewById(R.id.descrizione);
        storia = findViewById(R.id.storia);
        imageView=findViewById(R.id.imageView);
        button_audio = findViewById(R.id.audio);
        descrizionetext=findViewById(R.id.descrizionetext);
        anno_creazionetext=findViewById(R.id.anno_creazionetext);
        storiatext=findViewById(R.id.storiatext);
        autoretext=findViewById(R.id.autoretext);

        if (myCheck.equals("2")){

            anno_creazione.setVisibility(View.GONE);
            descrizione.setVisibility(View.GONE);
            storia.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            autore.setVisibility(View.GONE);

            descrizionetext.setVisibility(View.GONE);
            anno_creazionetext.setVisibility(View.GONE);
            storiatext.setVisibility(View.GONE);
            autoretext.setVisibility(View.GONE);

        } else {

            anno_creazione.setVisibility(View.VISIBLE);
            descrizione.setVisibility(View.VISIBLE);
            storia.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            autore.setVisibility(View.VISIBLE);

            descrizionetext.setVisibility(View.VISIBLE);
            anno_creazionetext.setVisibility(View.VISIBLE);
            storiatext.setVisibility(View.VISIBLE);
            autoretext.setVisibility(View.VISIBLE);

        }

        ((ScrollView) findViewById(R.id.scrollView)).fullScroll(ScrollView.FOCUS_UP); //Fa scroll Up altirmenti parte dal basso
        ((ScrollView) findViewById(R.id.scrollView)).smoothScrollTo(0, 0); //Fa scroll Up altirmenti parte dal basso

        final ArtObj artObj = Api.artObj; //FIXME check that artObj is not null
        mediaPlayer = new MediaPlayer();
        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mediaPlayer.setDataSource(artObj.getUrl_audio());
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        })).start();

//        final Pair<Integer, Integer> size = getVideoSize(artObj.getUrl_video());

        mVideoRelativeLayout = findViewById(R.id.video_rel_layout);
//        mVideoRelativeLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                if (mResizing) {
//                    mResizing = false;
//                    return;
//                }
//
//                mResizing = true;
//
//                int oldWidth = Math.abs(oldRight - oldLeft);
//                int oldHeight = Math.abs(oldBottom - oldTop);
//
//                int width = Math.abs(right - left);
//                int height = Math.abs(bottom - top);
//
//                Log.d("onLayoutChange", String.format(Locale.getDefault(),
//                        "%dx%d -> %dx%d", oldWidth, oldHeight, width, height));
//
//                if (mVideoView != null && mMediaPlayer != null && mOpl != null && mVideoRelativeLayout != null) {
//                    int videoWidth = mMediaPlayer.getVideoWidth();
//                    int videoHeight = mMediaPlayer.getVideoHeight();
//
//                    int newWidth = width;
//                    int newHeight =
//                            (int) (((float) width) * ((float) videoHeight) / ((float) videoWidth));
//
//                    Log.d("OnPreparedListener",
//                            String.format("[%dx%d] %dx%d -> %dx%d",
//                                    width, height, videoWidth, videoHeight, newWidth, newHeight));
//
//                    mVideoView.getLayoutParams().width = newWidth;
//                    mVideoView.getLayoutParams().height = newHeight;
//                    mVideoView.requestLayout();
//
////                    mVideoRelativeLayout.getLayoutParams().width = width;
//                    mVideoRelativeLayout.getLayoutParams().height = newHeight;
//                    mVideoRelativeLayout.requestLayout();
//                }
//            }
//        });

        mVideoView = findViewById(R.id.video);
//        mOpl = new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                Log.d("@@@", "onPrepared");
//                mMediaPlayer = mp;
//                ((AutofitVideoView)mVideoView).setVideoSize(mp.getVideoWidth(), mp.getVideoHeight());
//            }
//        };
//        mVideoView.setOnPreparedListener(mOpl);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediaController);
        (new Thread(new Runnable() {
            @Override
            public void run() {
                mVideoView.setVideoURI(Uri.parse(artObj.getUrl_video()));
//                if (size != null) {
//                    videoView.getLayoutParams().height = size.second;
//                    videoView.requestLayout();
//                    ((AutofitVideoView)videoView).setVideoSize(size.first, size.second);
//                }
            }
        })).start();

        name.setText(artObj.getNome());
        autore.setText(artObj.getAutore());
        anno_creazione.setText(artObj.getAnno_creazione());
        descrizione.setText(artObj.getDescrizione());
        storia.setText(artObj.getStoria());

        Picasso.get().load(artObj.getUrl_image()).into(imageView);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                button_audio.setText("RIPRODUCI AUDIO");
            }
        });

        button_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    button_audio.setText("RIPRODUCI AUDIO");
                    mediaPlayer.pause();
                } else {
                    button_audio.setText("PAUSA AUDIO");
                    mediaPlayer.start();
                }
            }
        });

    }
}
