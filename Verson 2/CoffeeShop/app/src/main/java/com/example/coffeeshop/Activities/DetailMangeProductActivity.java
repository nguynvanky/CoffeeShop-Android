package com.example.coffeeshop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class DetailMangeProductActivity extends AppCompatActivity {

    Spinner dropdownCate;
    TextInputEditText edt_name;
    TextInputEditText edt_price;
    TextInputEditText edt_desc;
    TextInputEditText edt_img;
    Button btn_Confirm;
    ImageButton btnBack;
    Product product = new Product();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mange_product);

        Intent i = getIntent();
        if(i != null)
        {
            int id = i.getIntExtra("id",0);
            try {
                product = API.GetProductById(id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }


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
            DropdownCate(product);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        edt_name.setText(product.getName());
        edt_desc.setText(product.getDescription());
        edt_price.setText( product.getPrice().toString());
        edt_img.setText(product.getImage().toString());
        btn_Confirm = findViewById(R.id.btn_confirmUpdate);
        btn_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name.getText().toString();
                String price = edt_price.getText().toString();
                String desc = edt_desc.getText().toString();
                String img = edt_img.getText().toString();
                if(name.isEmpty() || price.isEmpty() || desc.isEmpty() || img.isEmpty())
                {
                    Toast.makeText(DetailMangeProductActivity.this, "Please enter all field", Toast.LENGTH_SHORT).show();
                    return;
                }
                product.setName(name);
                product.setPrice(Double.parseDouble(price));
                product.setDescription(desc);
                product.setImage(img);
                try {
                    API.UpdateProduct(product);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                finish();
            }
        });

    }


    void DropdownCate(Product product) throws JSONException, IOException {
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

        for (int i = 0 ; i< categoryList.size(); i++)
        {
            if(categoryList.get(i).get_id() == product.get_id_cate())
            {
                dropdownCate.setSelection(i);
                break;
            }
        }

    }
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Category category = (Category) adapter.getItem(position);
        product.set_id_cate(category.get_id());
    }
}