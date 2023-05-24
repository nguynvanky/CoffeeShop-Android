package com.example.coffeeshop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.coffeeshop.DTO.Category;
import com.example.coffeeshop.DTO.Product;
import com.example.coffeeshop.R;
import com.example.coffeeshop.Services.API;
import com.example.coffeeshop.Utils.DatabaseHandler;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class AddProductManageActivity extends AppCompatActivity {

    Spinner dropdownCate;
    TextInputEditText edt_name;
    TextInputEditText edt_price;
    TextInputEditText edt_desc;
    TextInputEditText edt_img;

    Button btn_Add;
    ImageButton btnBack;
    Product product = new Product();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_manage);
        dropdownCate = findViewById(R.id.dropdown_cate);
        edt_name= findViewById(R.id.edt_NameProduct);
        edt_price = findViewById(R.id.edt_PriceProduct);
        edt_desc = findViewById(R.id.edt_DescProduct);
        edt_img = findViewById(R.id.edt_ImgProduct);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        try {
            DropdownCate();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        btn_Add = findViewById(R.id.btn_addProduct);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name.getText().toString();
                String price = edt_price.getText().toString();
                String desc = edt_desc.getText().toString();
                String img = edt_img.getText().toString();
                if(name.isEmpty() || price.isEmpty() || desc.isEmpty() || img.isEmpty())
                {
                    Toast.makeText(AddProductManageActivity.this, "Please enter all field", Toast.LENGTH_SHORT).show();
                    return;
                }
                product.setName(name);
                product.setPrice(Double.parseDouble(price));
                product.setDescription(desc);
                product.setFavourite(false);
                product.setImage(img);
                try {
                    API.AddProduct(product);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //DatabaseHandler db = new DatabaseHandler(AddProductManageActivity.this);
                //db.addProduct(product);
                finish();
            }
        });
    }
    void DropdownCate() throws JSONException, IOException {
        List<Category> categoryList = API.GetCategories();
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_custom,categoryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownCate.setAdapter(adapter);
        dropdownCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent,view,position,id);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Category category = (Category) adapter.getItem(position);
        product.set_id_cate(category.get_id());
    }
}