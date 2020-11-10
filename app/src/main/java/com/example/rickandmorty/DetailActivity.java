package com.example.rickandmorty;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
    }

    private void initView() {
        TextView name = findViewById(R.id.name1);
        TextView gender = findViewById(R.id.gender1);
        TextView status = findViewById(R.id.status1);
        TextView species = findViewById(R.id.species1);
        ImageView image = findViewById(R.id.image1);

        if (getIntent().getExtras() != null) {
            name.setText(getIntent().getStringExtra("name"));
            gender.setText(getIntent().getStringExtra("gender"));
            status.setText(getIntent().getStringExtra("status"));
            species.setText(getIntent().getStringExtra("species"));
            Picasso.get().load(getIntent().getIntExtra("image",1)).into(image);
        }
    }
}
