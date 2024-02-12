package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.Adaptor.CategoryAdaptor;
import com.example.myapplication.Adaptor.PopularAdaptor;
import com.example.myapplication.Domain.CategoryDomain;
import com.example.myapplication.Domain.GiftDomain;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
    }

    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add(new CategoryDomain("Flowers","cat_1"));
        category.add(new CategoryDomain("Chocolate","cat_2"));
        category.add(new CategoryDomain("Cards","cat_3"));
        category.add(new CategoryDomain("Wine","cat_4"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<GiftDomain> giftList=new ArrayList<>();
        giftList.add(new GiftDomain("Red Roses", "red_roses", "Medium romantic red roses(24 Stem)", 1765.00));
        giftList.add(new GiftDomain("Mixed Roses", "mixed_roses", "A colour mix(50 Stems) ", 2150.00));
        giftList.add(new GiftDomain("Ferrero Rocher", "chocolate1", "Hazelnut ab=nd milk chocolate T24 300g ", 290.00));

        adapter2 = new PopularAdaptor(giftList);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}