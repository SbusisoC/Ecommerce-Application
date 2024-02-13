package com.example.myapplication.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.Domain.GiftDomain;
import com.example.myapplication.Interface.ChangeNumberItemsListener;

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
        tinyDB.putListObject("CartList",listGift);
        Toast.makeText(context,"Added To Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<GiftDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }
    public void plusNumberGift(ArrayList<GiftDomain>listGift, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listGift.get(position).setNumberInCart(listGift.get(position).getNumberInCart());
        tinyDB.putListObject("CartList", listGift);
        changeNumberItemsListener.change();
    }
    public void minusNumberGift(ArrayList<GiftDomain>listGift, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(listGift.get(position).getNumberInCart()==1 ){
            listGift.remove(position);
        }else {
            listGift.get(position).setNumberInCart(listGift.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList",listGift);
        changeNumberItemsListener.change();
    }

    public Double getTotalFee(){
        ArrayList<GiftDomain> listGift = getListCart();
        double fee = 0;
        for(int i = 0; i < listGift.size(); i++){
            fee = fee + (listGift.get(i).getFee() * listGift.get(i).getNumberInCart());
        }
        return fee;
    }
}
