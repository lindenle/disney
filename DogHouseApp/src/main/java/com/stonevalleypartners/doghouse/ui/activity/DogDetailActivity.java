package com.stonevalleypartners.doghouse.ui.activity;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.stonevalleypartners.doghouse.model.Dog;
import com.stonevalleypartners.peoplelist.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import roboguice.util.temp.Ln;

public class DogDetailActivity extends DogHouseBaseActivity {
    @Bind(R.id.detailImage)
    ImageView detailView;

    private Dog mDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_detail);
        mDog = (Dog) getIntent().getSerializableExtra("dog");
        Ln.d("%s", mDog.toString());
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // need to change the like button if haveVoted
        setTitle("Votes: "+mDog.votes);
        Picasso.with(this)
                .load(mDog.url)
                .placeholder(R.drawable.dog_placeholder)
                .error(R.drawable.dog_err)
                .tag(this)
                .into(detailView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_vote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_vote:
                doVote(mDog);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void doVote(Dog mDog) {
        Toast.makeText(this, "voting for dog "+mDog.id, Toast.LENGTH_SHORT).show();
    }

}
