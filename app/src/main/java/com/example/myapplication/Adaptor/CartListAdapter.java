package com.example.myapplication.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Domain.GiftDomain;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.Interface.ChangeNumberItemsListener;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<GiftDomain> giftDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<GiftDomain> giftDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener){
        this.giftDomains = giftDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull  CartListAdapter.ViewHolder holder, int position){
        holder.title.setText(giftDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(giftDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((giftDomains.get(position).getNumberInCart() * giftDomains.get(position).getFee())*100)/100));
        holder.num.setText(String.valueOf(giftDomains.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(giftDomains.get(position).getPic()
        , "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberGift(giftDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberGift(giftDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        changeNumberItemsListener.change();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount(){
        return giftDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCart);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
        }

    }
}
