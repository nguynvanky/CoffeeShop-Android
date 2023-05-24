package com.example.coffeeshop.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeeshop.Contains.UserIsLoggedIn;
import com.example.coffeeshop.DTO.Cart;
import com.example.coffeeshop.R;
import com.example.coffeeshop.Services.API;
import com.example.coffeeshop.Utils.DatabaseHandler;
import com.example.coffeeshop.Utils.HistoryOrderAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    RecyclerView rcvHistory;
    HistoryOrderAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        rcvHistory = view.findViewById(R.id.rcvHistory);
        adapter = new HistoryOrderAdapter(getContext());
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvHistory.setLayoutManager(verticalLayoutManager);
        List<Cart> cartList = null;
        try {
            cartList = API.getAllCartsByUser_Ordered(UserIsLoggedIn.UserIdLogged);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        adapter.setData(cartList);
        rcvHistory.setAdapter(adapter);

        return view;
    }
}