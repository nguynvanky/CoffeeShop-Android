package com.example.coffeeshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.coffeeshop.DTO.Product;
import com.example.coffeeshop.R;
import com.example.coffeeshop.Services.API;
import com.example.coffeeshop.Utils.DatabaseHandler;
import com.example.coffeeshop.Utils.ManageProductAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class ManageProductActivity extends AppCompatActivity {
    RecyclerView rcv ;
    ManageProductAdapter adapter ;
    ImageButton btnBack;
    ImageButton btnAdd;


    @Override
    protected void onResume() {
        super.onResume();
        try {
            update();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
     void update() throws JSONException, IOException {
         List<Product> productList=  API.GetProducts();
         adapter.setData(productList);
         rcv.setAdapter(adapter);
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);


        rcv = findViewById(R.id.rcvManage);
        btnBack = findViewById(R.id.btn_back);
        btnAdd = findViewById(R.id.btn_add);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        List<Product> productList= null;
        try {
            productList = API.GetProducts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        adapter = new ManageProductAdapter(this);
        adapter.setData(productList);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rcv.setLayoutManager(verticalLayoutManager);
        rcv.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageProductActivity.this,AddProductManageActivity.class);
                startActivity(i);
            }
        });

    }
}