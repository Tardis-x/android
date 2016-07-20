package devfest.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import devfest.controller.model.Speaker;

public class SpeakerActivity extends BaseActivity {

    public static final String EXTRA_IMAGE = "DetailActivity:image";
    public static Speaker mSpeaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView image = (ImageView) findViewById(R.id.image_view);
        ViewCompat.setTransitionName(image, EXTRA_IMAGE);
        Glide.with(this)
                .load(getIntent().getStringExtra(EXTRA_IMAGE)).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade()
                .into(image);

    }


    public static void launch(BaseActivity activity, View transitionView, String url, Speaker speaker) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, transitionView, EXTRA_IMAGE);
        Intent intent = new Intent(activity, SpeakerActivity.class);
        intent.putExtra(EXTRA_IMAGE, url);
        mSpeaker = speaker;
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
