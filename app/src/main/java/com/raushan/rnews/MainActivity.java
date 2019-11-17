package com.raushan.rnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private ArrayList<NewsItem> newsList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON(){

        String url = "https://newsapi.org/v2/top-headlines?country=in&category=technology&pageSize=100&apiKey=4a4e7ad44b9a4def8e22c40a1f30d01a";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("articles");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject article = jsonArray.getJSONObject(i);
                                String title = article.getString("title");
                                String imageUrl = article.getString("urlToImage");
                                String author = article.getString("author");
                                String publishat = article.getString("publishedAt");
                                String description = article.getString("description");

                                newsList.add(new NewsItem(imageUrl, author, title, description, publishat));
                            }

                            newsAdapter = new NewsAdapter(MainActivity.this, newsList);
                            recyclerView.setAdapter(newsAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);

    }
}
