package com.example.myapplication.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.Domain.GiftDomain;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertGift(GiftDomain item){
        ArrayList<GiftDomain>listGift = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listGift.size(); i++){
            if(listGift.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }

        if(existAlready) {
            listGift.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listGift.add(item);
        }
        tinyDB.putListObject("CardList",listGift);
        Toast.makeText(context,"Added To Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<GiftDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }
}
