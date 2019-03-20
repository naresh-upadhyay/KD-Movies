package com.example.naresh.kdmovies.adpters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.naresh.kdmovies.R;
import com.example.naresh.kdmovies.network.videos.Video;
import com.example.naresh.kdmovies.utils.Constants;

import java.util.List;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context mContext;
    private List<Video> mVideos;

    public VideoAdapter(Context mContext, List<Video> videos) {
        this.mContext = mContext;
        this.mVideos = videos;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

        Glide.with(mContext.getApplicationContext()).load(Constants.YOUTUBE_THUMBNAIL_BASE_URL + mVideos.get(position).getKey() + Constants.YOUTUBE_THUMBNAIL_IMAGE_QUALITY)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.videoImageView);

        if (mVideos.get(position).getName() != null)
            holder.videoTextView.setText(mVideos.get(position).getName());
        else
            holder.videoTextView.setText("");
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        public CardView videoCard;
        public ImageView videoImageView;
        public TextView videoTextView;

        public VideoViewHolder(View itemView) {
            super(itemView);
            videoCard = (CardView) itemView.findViewById(R.id.card_view_video);
            videoImageView = (ImageView) itemView.findViewById(R.id.image_view_video);
            videoTextView = (TextView) itemView.findViewById(R.id.text_view_video_name);

            videoCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.YOUTUBE_WATCH_BASE_URL + mVideos.get(getAdapterPosition()).getKey()));
                    mContext.startActivity(youtubeIntent);
                }
            });
        }
    }

}
