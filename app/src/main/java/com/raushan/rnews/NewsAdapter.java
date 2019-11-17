package com.raushan.rnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


    private Context context;
    private ArrayList<NewsItem> newsList;

    public NewsAdapter(Context context, ArrayList<NewsItem> newsList) {
        this.context = context;
        this.newsList = newsList;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.new_list, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        NewsItem currentItem = newsList.get(position);
        String imageUrl = currentItem.getImageUrl();
        String author = currentItem.getAuthor();
        if(author == "null"){
            author = "Unknown";
        }
        String description = currentItem.getDescription();
        String publishedAt = currentItem.getPublishedat();
        String title = currentItem.getTitle();

        Picasso.with(context).load(imageUrl).fit().centerInside().into(holder.imageView);
        holder.authorTextView.setText("Author: "+author);
        holder.titleTextView.setText(title);
        holder.publishedAtTextView.setText("Date: "+publishedAt);
        holder.descriptionTextView.setText(description);



    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView authorTextView, titleTextView, descriptionTextView, publishedAtTextView;

        public NewsViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            authorTextView = itemView.findViewById(R.id.author_textView);
            titleTextView = itemView.findViewById(R.id.title_textView);
            descriptionTextView = itemView.findViewById(R.id.description_textView);
            publishedAtTextView = itemView.findViewById(R.id.publishedAt_textView);

        }
    }
}
