package com.example.coffeeshop.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.coffeeshop.Activities.Login;
import com.example.coffeeshop.Activities.ManageProductActivity;
import com.example.coffeeshop.Contains.UserIsLoggedIn;
import com.example.coffeeshop.Services.API;
import com.example.coffeeshop.Services.ApiService;
import com.example.coffeeshop.Utils.ListViewCategoryAdapter;
import com.example.coffeeshop.DTO.Category;
import com.example.coffeeshop.Utils.DatabaseHandler;
import com.example.coffeeshop.DTO.Product;
import com.example.coffeeshop.Utils.ProductAdapter;
import com.example.coffeeshop.R;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements ListViewCategoryAdapter.Listener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ApiService.apiService.GetProducts("GetProducts").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                adapter.setData(products);
                rcvHome.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("fail","fial");
            }
        });

    }

    List<Product> products = new ArrayList<>();
    List<Category> categories = new ArrayList<>();
    ProductAdapter adapter = new ProductAdapter(getContext());
    ListViewCategoryAdapter adapter_cate = null;
    RecyclerView rcvHome ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        View view  =inflater.inflate(R.layout.fragment_home, container, false);
        rcvHome = view.findViewById(R.id.rcvHome);
        products = new ArrayList<>();
        LinearLayoutManager verticalLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvHome.setLayoutManager(verticalLayoutManager);
        ApiService.apiService.GetProducts("GetProducts").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                adapter = new ProductAdapter(getContext());
                adapter.setData(products);
                rcvHome.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("fail","fial");
            }
        });



        Category All = new Category("ALL",0);
        ListView list = (ListView) view.findViewById(R.id.list_category);
        ApiService.apiService.GetCategories("GetCategories").enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categories = response.body();
                categories.add(0,All);
                try {
                    adapter_cate = new ListViewCategoryAdapter(getActivity(), categories, HomeFragment.this);
                    list.setAdapter(adapter_cate);
                }catch (Exception e)
                {
                    Log.d("error",e.getMessage());
                }
                // List view
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("fail","fial");
            }
        });


        //search
        EditText search = view.findViewById(R.id.edt_search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ImageButton btnProfile = view.findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext(), btnProfile);
                // Inflating popup menu from popup_menu.xml file

                popupMenu.getMenuInflater().inflate(R.menu.control_account_menu, popupMenu.getMenu());
                int  id_mange_product = 0403;
                if(UserIsLoggedIn.Role.equals("admin"))
                {
                    popupMenu.getMenu().add(Menu.NONE,id_mange_product,0,"Manage Product");
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == id_mange_product)
                        {
                            Intent i = new Intent(getContext(), ManageProductActivity.class);
                            startActivity(i);
                        }
                        else if(menuItem.getItemId() == R.id.signout)
                        {
                            UserIsLoggedIn.signOut();
                            UserIsLoggedIn.isLogin = false ;
                            Intent i = new Intent(getContext(),Login.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }
                        else if(menuItem.getItemId() == R.id.profile)
                        {

                        }
                        else if(menuItem.getItemId() == R.id.changepassword)
                        {

                        }
                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });
        return view;
    }



    @Override
    public void OnItemListener(Category category) {
        adapter.getFilterByIDCate().filter(Integer.toString(category.get_id()));
    }


}