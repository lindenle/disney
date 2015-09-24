package com.stonevalleypartners.doghouse.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.stonevalleypartners.doghouse.network.DogListRetrofitRequest;
import com.stonevalleypartners.doghouse.ui.adapter.DogImageGridAdapter;
import com.stonevalleypartners.peoplelist.R;
import com.stonevalleypartners.doghouse.model.Dog;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import roboguice.util.temp.Ln;

public class DogHouseMainActivity extends DogHouseBaseActivity {

    DogListRetrofitRequest dogsRestRequest;
    @Bind(R.id.gridview) GridView mMainGrid;
    List<Dog> mDogs = new ArrayList<Dog>();
    DogImageGridAdapter mAdapter;

    @OnItemClick(R.id.gridview) public void onItemClick( AdapterView<?> parent, View v, int position, long id) {
        Ln.d("clicked grid"+position);
        //Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        Intent detailIntent = new Intent(DogHouseMainActivity.this, DogDetailActivity.class);
        detailIntent.putExtra("dog",mAdapter.getItem(position));
        startActivity(detailIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAdapter = new DogImageGridAdapter(this, mDogs);
        mMainGrid.setAdapter(mAdapter);
        dogsRestRequest = new DogListRetrofitRequest();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchDogs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fetchDogs() {
        getSpiceManager().execute(dogsRestRequest, new DogsRequestListener());
    }

    public void updateDogs(final Dog.AllDogList dogs) {
    //here we need to update the adapter
        DogImageGridAdapter adapter = (DogImageGridAdapter) mMainGrid.getAdapter();
        for ( Dog.DogList dogList: dogs ) {
            Ln.d("%s",dogList.breed);
            adapter.addAll(dogList.dogs);
            for ( Dog dog: dogList.dogs) {
                Ln.d("%s",dog.toString());
            }
        }
        adapter.notifyDataSetChanged();
    }

    public final class DogsRequestListener implements RequestListener<Dog.AllDogList> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(DogHouseMainActivity.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final Dog.AllDogList result) {
            //Toast.makeText(DogHouseMainActivity.this, "success", Toast.LENGTH_SHORT).show();
            updateDogs(result);
        }
    }

}
