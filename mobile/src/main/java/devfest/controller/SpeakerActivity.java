package devfest.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import devfest.controller.adaptors.SocialAdaptor;
import devfest.controller.adaptors.TagAdaptor;
import devfest.controller.model.Speaker;
import us.feras.mdv.MarkdownView;

public class SpeakerActivity extends BaseActivity {

    public static final String EXTRA_IMAGE = "DetailActivity:image";
    public static Speaker mSpeaker;
    private TextView mNameTV;
    private TextView mCountry;
    private TextView mTitleTV;
    private MarkdownView mBioTV;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView tagsRV;
    private RecyclerView socialRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);
        initToolbar();

        ImageView image = (ImageView) findViewById(R.id.image_view);
        ViewCompat.setTransitionName(image, EXTRA_IMAGE);
        Glide.with(this)
                .load(getIntent().getStringExtra(EXTRA_IMAGE)).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade()
                .into(image);
        initView();
        Log.e("TAGS  ____", mSpeaker.tags.size()+"");

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        mCountry = (TextView)findViewById(R.id.company_country_tv);
        mTitleTV =  (TextView)findViewById(R.id.title_speaker_textView);
        mBioTV = (MarkdownView )findViewById(R.id.bio_speaker_textView);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_tool_bar);
        tagsRV = (RecyclerView)findViewById(R.id.tags_recycler_view);
        socialRV = (RecyclerView)findViewById(R.id.social_recycler_view);


        collapsingToolbarLayout.setTitle(mSpeaker.name);
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));

//        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
//        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));


        SocialAdaptor socialAdaptor =  new SocialAdaptor( mSpeaker.socials,this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        socialRV.setLayoutManager(layoutManager);
        socialRV.setAdapter(socialAdaptor);

        TagAdaptor tagAdaptor =  new TagAdaptor( mSpeaker.tags ,this);
        LinearLayoutManager layoutTagManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        tagsRV.setLayoutManager(layoutTagManager);
        tagsRV.setAdapter(tagAdaptor);

        mCountry.setText(mSpeaker.company+", "+ mSpeaker.country);
        mTitleTV.setText(mSpeaker.title);
        mBioTV.loadMarkdown(mSpeaker.bio);
        Log.e("Tag", mSpeaker.toString());
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
