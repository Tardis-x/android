package devfest.controller;

import android.content.Intent;
import android.graphics.Color;
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
import devfest.controller.model.News;
import devfest.controller.model.Speaker;
import us.feras.mdv.MarkdownView;

/**
 * Created by Brusd on 7/24/2016.
 */

public class NewsActivity extends BaseActivity {

    public static final String EXTRA_IMAGE = "DetailActivity:image";
    public static News mNews;

    private TextView mTitleTV;
    private MarkdownView mContentTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_launcher, null));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);

        ImageView image = (ImageView) findViewById(R.id.news_image_view);
        ViewCompat.setTransitionName(image, EXTRA_IMAGE);
        Glide.with(this)
                .load(getIntent().getStringExtra(EXTRA_IMAGE)).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade()
                .into(image);
        initView();


    }

    private void initView() {
        mTitleTV = (TextView)findViewById(R.id.title_news_textView);
        mContentTV = (MarkdownView)findViewById(R.id.news_detel_textView);


        mTitleTV.setText(mNews.getTitle());
        mTitleTV.setBackgroundColor(Color.parseColor(mNews.getPrimaryColor()));
        mContentTV.loadMarkdown(mNews.fullText);

    }


    public static void launch(BaseActivity activity, View transitionView, String url, News news) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, transitionView, EXTRA_IMAGE);
        Intent intent = new Intent(activity, NewsActivity.class);
        intent.putExtra(EXTRA_IMAGE, url);
        mNews = news;
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
