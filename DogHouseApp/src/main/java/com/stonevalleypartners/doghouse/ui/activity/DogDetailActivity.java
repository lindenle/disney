package com.stonevalleypartners.doghouse.ui.activity;

import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.squareup.picasso.Picasso;
import com.stonevalleypartners.doghouse.model.Dog;
import com.stonevalleypartners.doghouse.network.VoteRetrofitRequest;
import com.stonevalleypartners.doghouse.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import roboguice.util.temp.Ln;

public class DogDetailActivity extends DogHouseBaseActivity {
    @Bind(R.id.detailImage)
    ImageView detailView;

    private Dog mDog;
    private MenuItem mLikeItem;

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
        mLikeItem = menu.findItem(R.id.action_vote);
        updateState();
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
        Toast.makeText(this, "voting for dog " + mDog.id, Toast.LENGTH_SHORT).show();
        String clientID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        String voteAction = mDog.haveVoted? "down" : "up";
        VoteRetrofitRequest voteRestRequest = new VoteRetrofitRequest(mDog.id, voteAction, clientID);
        getSpiceManager().execute(voteRestRequest, new DogDetailActivity.VoteRequestListener());
    }

    public void updateDog(Dog dog) {
        Ln.d("updateDog" + dog.toString());
        mDog = dog;
        updateState();
    }

    public void updateState() {
        setTitle("Votes: " + mDog.votes);
        if (mDog.haveVoted) {
            mLikeItem.setTitle("unlike");
        } else {
            mLikeItem.setTitle("like");

        }
    }

    public final class VoteRequestListener implements RequestListener<Dog> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(DogDetailActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final Dog result) {
            //Toast.makeText(DogHouseMainActivity.this, "success", Toast.LENGTH_SHORT).show();
            updateDog(result);
        }
    }

}
