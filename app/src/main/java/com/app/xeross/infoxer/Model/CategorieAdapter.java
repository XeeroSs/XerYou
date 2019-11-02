package com.app.xeross.infoxer.Model;

/**
 * Created by XeroSs on 08/09/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.xeross.infoxer.Controller.ClassActivity.ClassAnime;
import com.app.xeross.infoxer.Controller.ClassActivity.ClassFilm;
import com.app.xeross.infoxer.Controller.ClassActivity.ClassFood;
import com.app.xeross.infoxer.Controller.ClassActivity.ClassGames;
import com.app.xeross.infoxer.Controller.ClassActivity.ClassMusic;
import com.app.xeross.infoxer.Controller.ClassActivity.ClassSerie;
import com.app.xeross.infoxer.R;
import com.app.xeross.infoxer.View.Categorie;
import com.bumptech.glide.Glide;

import java.util.List;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.MyViewHolder> {

    private Context mContext;
    private List<Categorie> albumList;
    private OnItemClickListener mListener;

    public CategorieAdapter(Context mContext, List<Categorie> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categorie_card, parent, false);

        return new MyViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Categorie album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + " éléments");

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.title.getText() == "Musique") {
                    Intent music = new Intent(mContext, ClassMusic.class);
                    mContext.startActivity(music);
                } else if (holder.title.getText() == "Série") {
                    Intent music = new Intent(mContext, ClassSerie.class);
                    mContext.startActivity(music);
                } else if (holder.title.getText() == "Film") {
                    Intent music = new Intent(mContext, ClassFilm.class);
                    mContext.startActivity(music);
                } else if (holder.title.getText() == "Anime/Manga") {
                    Intent music = new Intent(mContext, ClassAnime.class);
                    mContext.startActivity(music);
                } else if (holder.title.getText() == "Jeux vidéo") {
                    Intent music = new Intent(mContext, ClassGames.class);
                    mContext.startActivity(music);
                } else if (holder.title.getText() == "Nourriture") {
                    Intent music = new Intent(mContext, ClassFood.class);
                    mContext.startActivity(music);
                }

            }
        });
    }

    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_categorie, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView overflow;
        public Drawable drawable;
        public ImageButton thumbnail;

        public MyViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            title = view.findViewById(R.id.title);
            count = view.findViewById(R.id.count);
            thumbnail = view.findViewById(R.id.thumbnail);
            overflow = view.findViewById(R.id.overflow);
        }
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Non disponible", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Non disponible", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
}