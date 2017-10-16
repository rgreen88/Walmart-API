package com.example.rynel.walmartapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rynel.walmartapp.model.Item;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by rynel on 10/15/2017.
 */

public class WalmartListAdapter extends RecyclerView.Adapter<WalmartListAdapter.ViewHolder>{

    //create an array list walmartItemList
    List<Item> walmartItemList = new ArrayList<>();
    Context context;

    public WalmartListAdapter(List<Item> walmartItemList){

        this.walmartItemList = walmartItemList;

    }


    @Override
    public WalmartListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.walmart_item_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item walmart = walmartItemList.get(position);

        Glide.with( context )
                .load( walmart.getThumbnailImage() )
                .into( holder.itemImage );

        Glide.with( context )
                .load( walmart.getCustomerRatingImage() )
                .into( holder.itemRating );

        holder.item.setText( walmart.getName() );

        holder.itemPrice.setText( String.format( "$%,.2f", walmart.getSalePrice()));
        holder.itemStock.setText( walmart.getStock() );
    }

    @Override
    public int getItemCount() {

        return walmartItemList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage, itemRating;
        TextView item, itemPrice, itemStock;

        public ViewHolder(View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.ivThumbnail);
            itemPrice = itemView.findViewById(R.id.tvItemPrice);
            item = itemView.findViewById(R.id.tvItem);
            itemRating = itemView.findViewById(R.id.ivRating);
            itemStock = itemView.findViewById(R.id.tvItemStock);

        }
    }
}
