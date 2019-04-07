package com.app.jjesusmp.googleplacesapi.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.jjesusmp.googleplacesapi.R;
import com.app.jjesusmp.googleplacesapi.domain.model.Place;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    public interface OnItemClickListener {
        void savePlace(Place place);
        void removePlace(Place place);
    }

    private List<Place> mDataset;
    private static OnItemClickListener listener;

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        public TextView tv_name;
        @BindView(R.id.tv_address)
        public TextView tv_address;
        @BindView(R.id.iv_icon)
        public ImageView iv_icon;
        @BindView(R.id.iv_save)
        public ImageView iv_save;
        @BindView(R.id.iv_delete)
        public ImageView iv_delete;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public PlaceAdapter(List<Place> myDataset, OnItemClickListener onItemClickListener) {
        mDataset = myDataset;
        listener = onItemClickListener;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_item, parent, false);

        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        holder.tv_name.setText(mDataset.get(position).getName());
        holder.tv_address.setText(mDataset.get(position).getFormattedAddress());
        holder.iv_save.setOnClickListener(v -> listener.savePlace(mDataset.get(position)));
        holder.iv_delete.setOnClickListener(v -> listener.removePlace(mDataset.get(position)));
        Glide.with(holder.itemView.getContext())
                .load(mDataset.get(position).getIcon())
                .centerCrop()
                .into(holder.iv_icon);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}