package com.app.xeross.infoxer.Model;

/**
 * Created by XeroSs on 08/09/2018.
 */

import android.content.Context;
import android.graphics.Color;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.xeross.infoxer.R;
import com.app.xeross.infoxer.View.ItemClassement;
import com.bumptech.glide.Glide;

import java.util.List;

public class ClassementAdapter extends RecyclerView.Adapter<ClassementAdapter.MyViewHolder> {

    private Context mContext;
    private List<ItemClassement> mItemClassements;
    private OnItemClickListener mListener;

    public ClassementAdapter(Context mContext, List<ItemClassement> mItemClassements) {
        this.mContext = mContext;
        this.mItemClassements = mItemClassements;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_classement, parent, false);

        return new MyViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ItemClassement classements = mItemClassements.get(position);
        String c1 = "1", c2 = "2", c3 = "3";

        holder.title.setText(classements.getTitle());
        holder.statut.setText(classements.getStatut());
        holder.classement.setText(classements.getClassement());
        Glide.with(mContext).load(classements.getImage()).into(holder.image);

        if (c1.equalsIgnoreCase(holder.classement.getText().toString())) {
            holder.classement.setTextColor(Color.parseColor("#FFD700"));
            holder.title.setTextColor(Color.parseColor("#FFD700"));
        } else if (c2.equalsIgnoreCase(holder.classement.getText().toString())) {
            holder.classement.setTextColor(Color.parseColor("#909090"));
            holder.title.setTextColor(Color.parseColor("#909090"));
        } else if (c3.equalsIgnoreCase(holder.classement.getText().toString())) {
            holder.classement.setTextColor(Color.parseColor("#ce895b"));
            holder.title.setTextColor(Color.parseColor("#ce895b"));
        }

    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_categorie, popup.getMenu());
        popup.show();
    }

    @Override
    public int getItemCount() {
        return mItemClassements.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, classement, statut;
        public ImageView image;

        public MyViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.titles);
            classement = view.findViewById(R.id.classement);
            statut = view.findViewById(R.id.statut);
        }
    }
}