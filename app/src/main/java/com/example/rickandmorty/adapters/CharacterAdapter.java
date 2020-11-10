package com.example.rickandmorty.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rickandmorty.DetailActivity;
import com.example.rickandmorty.R;
import com.example.rickandmorty.models.Character;
import com.squareup.picasso.Picasso;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.MyViewHolder> {

    private final List<Character> characterList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView status;
        public TextView gender;
        public TextView species;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            status = view.findViewById(R.id.status);
            gender = view.findViewById(R.id.gender);
            image = view.findViewById(R.id.image);
            species = view.findViewById(R.id.species);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {

                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("gender", gender.getText().toString());
                    intent.putExtra("status", status.getText().toString());
                    intent.putExtra("species", species.getText().toString());
                    //intent.putExtra(Intent.EXTRA_TEXT, image.getDrawable().toString());

                    intent.putExtra("image", image.getImageAlpha());

                    view2.getContext().startActivity(intent);
                }
            });
        }
    }

    public CharacterAdapter(List<Character> charactersList) {
        this.characterList = charactersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.name.setText("Name: " + character.name);
        holder.status.setText("Status: " + character.status);
        holder.gender.setText("Gender: " + character.gender);
        holder.species.setText("Species: " + character.species);
        Picasso.get().load(character.image).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }
}