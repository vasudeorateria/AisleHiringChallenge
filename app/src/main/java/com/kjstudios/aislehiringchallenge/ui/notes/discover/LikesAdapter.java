package com.kjstudios.aislehiringchallenge.ui.notes.discover;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.kjstudios.aislehiringchallenge.R;
import com.kjstudios.aislehiringchallenge.data.model.Profile;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class LikesAdapter extends RecyclerView.Adapter<LikesAdapter.LikesViewHolder> {

    private List<Profile> likes_profile_list = new ArrayList<Profile>();
    private boolean canViewLikes = false;

    public LikesAdapter(List<Profile> likes_profile_list, boolean canViewLikes) {
        this.likes_profile_list = likes_profile_list;
        this.canViewLikes = canViewLikes;
    }

    @NonNull
    @Override
    public LikesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.likes_item_layout, parent, false);
        return new LikesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikesViewHolder holder, int position) {
        final Profile profile = likes_profile_list.get(position);
        holder.name.setText(profile.getFirst_name());
        if (canViewLikes) {
            Glide.with(holder.image.getContext())
                    .load(profile.getAvatar())
                    .into(holder.image);
        } else {
            Glide.with(holder.image.getContext())
                    .load(profile.getAvatar())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3)))
                    .into(holder.image);
        }

    }

    @Override
    public int getItemCount() {
        return likes_profile_list.size();
    }

    public static class LikesViewHolder extends RecyclerView.ViewHolder {

        public ShapeableImageView image;
        public TextView name;

        public LikesViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.like_image);
            name = itemView.findViewById(R.id.like_name);
        }

    }

}
