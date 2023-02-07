package com.vrgsoft.TopRedditApp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.vgrsoft.topRedditApp.R;
import com.vrgsoft.TopRedditApp.model.TopPublication;
import com.vrgsoft.TopRedditApp.util.ImageLoaderConfigurer;

import static androidx.core.content.ContextCompat.startActivity;

public class TopPublicationAdapter extends
        RecyclerView.Adapter<TopPublicationAdapter.TopViewHolder> {
    Context context;
    List<TopPublication> topPublications;

    public TopPublicationAdapter(Context context, List<TopPublication> topPublications) {
        this.context = context;
        this.topPublications = topPublications;
    }

    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.top_card, parent, false);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int position) {
        setupImageLoader();

        holder.textViewAuthor.setText(topPublications.get(position).getAuthor());
        holder.textViewDateTime.setText(topPublications.get(position).getDateTime());
        holder.textViewTitle.setText(topPublications.get(position).getTitle());
        holder.textViewCommentsNumber.setText(topPublications.get(position)
                .getCommentsNumber());

        ImageLoader.getInstance().displayImage(topPublications.get(position).getThumbnail(),
                holder.thumbnailView);
        setupClickListener(holder, position);
    }

    @Override
    public int getItemCount() {
        return topPublications.size();
    }

    private void setupImageLoader() {
        new ImageLoaderConfigurer(context).setupImageLoader();
    }

    private void setupClickListener(TopViewHolder holder, int position) {
        holder.thumbnailView.setOnClickListener(view -> {
            Uri imageUri = Uri.parse(topPublications.get(position).getImageUrl());
            Intent intent= new Intent(Intent.ACTION_VIEW, imageUri);
            startActivity(context, intent, null);
        });
    }

    public static final class TopViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailView;
        TextView textViewAuthor;
        TextView textViewDateTime;
        TextView textViewTitle;
        TextView textViewCommentsNumber;

        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailView = itemView.findViewById(R.id.cardThumbnail);
            textViewAuthor = itemView.findViewById(R.id.cardAuthor);
            textViewDateTime = itemView.findViewById(R.id.cardDateTime);
            textViewTitle = itemView.findViewById(R.id.cardTitle);
            textViewCommentsNumber = itemView.findViewById(R.id.cardComments);
        }
    }
}
