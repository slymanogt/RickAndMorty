package com.example.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;
import android.os.AsyncTask;
import android.os.Bundle;
import com.example.rickandmorty.adapters.CharacterAdapter;
import com.example.rickandmorty.models.Character;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String JSON_URL = "https://rickandmortyapi.com/api/character/";
    private final OkHttpClient client = new OkHttpClient();
    private List<Character> characterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DownloadJSON().execute();
    }

    private class DownloadJSON extends AsyncTask<String, Void, List<Character>> {
        @Override
        protected void onPreExecute() {
            Timber.i("onPreExecute");
        }

        @Override
        protected List<Character> doInBackground(String... params) {
            String jsonData = null;
            try {
                jsonData = run(JSON_URL);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (null != jsonData) {
                Gson gson = new Gson();
                try {
                    JSONObject mainObj = new JSONObject(jsonData);
                    if(mainObj != null){
                        JSONArray result = mainObj.getJSONArray("results");
                        if(result != null){
                            characterList = Arrays.asList(gson.fromJson(String.valueOf(result), Character[].class));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return characterList;
        }

        @Override
        protected void onPostExecute(List<Character> result) {
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            CharacterAdapter characterAdapter = new CharacterAdapter(characterList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(characterAdapter);
        }
    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}