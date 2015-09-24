package com.stonevalleypartners.doghouse.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stonevalleypartners.doghouse.model.Dog;
import com.stonevalleypartners.peoplelist.R;
import com.stonevalleypartners.doghouse.ui.views.SquaredImageView;
import java.util.List;

import roboguice.util.temp.Ln;

/**
 * Created by lindenle on 9/24/15.
 */
public class DogImageGridAdapter extends ArrayAdapter<Dog> {
    private Context mContext;
    private List<Dog> mDogs;
    private String mLastBreed = "";

    public DogImageGridAdapter(Context context, List<Dog> dogs) {
        super(context, R.layout.grid_square, dogs);
        mContext = context;
        mDogs = dogs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        Dog dog = (Dog) getItem(position);
        if (convertView == null) {
            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.grid_square, null);
        } else {
            gridView = (View) convertView;
        }

        if ( !dog.breed.equals(mLastBreed)) {
            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.grid_header, null);
            TextView headerText = (TextView) gridView.findViewById(R.id.sectionTitle);
            headerText.setText(dog.breed);
            Ln.d("new breed " + mLastBreed + " " + dog.breed);
            mLastBreed = dog.breed;
        }

        SquaredImageView imageView = (SquaredImageView) gridView.findViewById(R.id.imageView);
        TextView votesText = (TextView) gridView.findViewById(R.id.voteText);
        votesText.setText(dog.votes.toString());
        Picasso.with(mContext)
                .load(dog.url)
                .placeholder(R.drawable.dog_placeholder)
                .error(R.drawable.dog_err)
                .centerCrop()
                .fit()
                .tag(mContext)
                .into(imageView);

        return gridView;
    }
}
