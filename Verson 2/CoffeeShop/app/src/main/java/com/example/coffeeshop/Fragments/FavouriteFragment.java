package com.example.coffeeshop.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeeshop.Utils.DatabaseHandler;
import com.example.coffeeshop.DTO.Product;
import com.example.coffeeshop.Utils.ProductAdapter;
import com.example.coffeeshop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavourtieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
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
        refreshScreen();
    }
    void refreshScreen()
    {
        DatabaseHandler db = new DatabaseHandler(getContext());
        List<Product> products = new ArrayList<>();
        products =db.getAlProductsIsFavourite();
        adapter.setData(products);
    }
    RecyclerView rcvCategories;
    ProductAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.fragment_favourtie, container, false);
        DatabaseHandler db = new DatabaseHandler(getContext());
        List<Product> products = new ArrayList<>();
        products =db.getAlProductsIsFavourite();

        LinearLayoutManager verticalLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvCategories = view.findViewById(R.id.rcvFavourite);
        rcvCategories.setLayoutManager(verticalLayoutManager);
        adapter = new ProductAdapter(getContext());
        adapter.setData(products);
        rcvCategories.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }
}