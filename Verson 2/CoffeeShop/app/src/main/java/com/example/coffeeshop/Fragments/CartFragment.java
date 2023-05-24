package com.example.coffeeshop.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.Activities.TransactionActivity;
import com.example.coffeeshop.Contains.UserIsLoggedIn;
import com.example.coffeeshop.DTO.Cart;
import com.example.coffeeshop.Services.API;
import com.example.coffeeshop.Utils.CartAdapter;
import com.example.coffeeshop.DTO.DetailCart;
import com.example.coffeeshop.Utils.DatabaseHandler;
import com.example.coffeeshop.R;

import org.json.JSONException;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements CartAdapter.ListenerRefesh {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final Double Delivery = 35d;
    private static final Double Taxes = 15d;
    private static final Double Total_Price = Delivery+Taxes;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
    TextView tvDelivery;
    TextView tvTaxes ;
    TextView tvTotal;
    Button btnPay;
    RelativeLayout apply_coupon;

    @Override
    public void onResume() {
        super.onResume();
        try {
            reload_Pay();
            loadCartDetail();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    RecyclerView rcvCart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_cart, container, false);
        //
        tvDelivery = view.findViewById(R.id.deliveryCharges);
        tvTaxes = view.findViewById(R.id.taxes);
        tvTotal = view.findViewById(R.id.grandTotal);
        btnPay = view.findViewById(R.id.btn_pay);
        apply_coupon = view.findViewById(R.id.apply_coupon);
        apply_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Is in developing process.", Toast.LENGTH_SHORT).show();
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart match = null;
                try {
                    match = API.GetCartByIdUser(UserIsLoggedIn.UserIdLogged);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                if(match == null)
                {
                    Toast.makeText(getContext(), "Empty cart, please add something", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<DetailCart> detailCartList = null;
                try {
                    detailCartList = API.GetDetailsCartByUser(UserIsLoggedIn.UserIdLogged);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                if(detailCartList.size() ==  0)
                {
                    Toast.makeText(getContext(), "Empty cart, please add something", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(getContext(), TransactionActivity.class);
                i.putExtra("total",tvTotal.getText().toString());
                getContext().startActivity(i);
            }
        });
        try {
            reload_Pay();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCart = view.findViewById(R.id.rcvCart);
        rcvCart.setLayoutManager(verticalLayoutManager);

        try {
            loadCartDetail();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Inflate the layout for this fragment
        return view; 
    }
    void loadCartDetail() throws JSONException, IOException {
        CartAdapter adapter = new CartAdapter(getContext(), CartFragment.this);
        Cart match = API.GetCartByIdUser(UserIsLoggedIn.UserIdLogged);
        if(match == null)
        {
            List<DetailCart> detailCartList = new ArrayList<>(0);
            adapter.setData(detailCartList);
            rcvCart.setAdapter(adapter);
            return;
        }
        List<DetailCart> detailCartList = API.GetDetailsCartByUser(UserIsLoggedIn.UserIdLogged);
        if(detailCartList == null)
        {
            return;
        }
        btnPay.setText("CHECK OUT ("+detailCartList.size()+")");
        adapter.setData(detailCartList);
        rcvCart.setAdapter(adapter);

    }
    private void reload_Pay() throws JSONException, IOException {
        Cart match = API.GetCartByIdUser(UserIsLoggedIn.UserIdLogged);
        reload_CheckOut();
        tvDelivery.setText(Delivery.toString() + " $");
        tvTaxes.setText(Taxes.toString() + " $");
        double total =  Delivery + Taxes;
        tvTotal.setText(Double.toString(total));
        if(match == null)
            return;
        total +=  match.getTotal_price();
        // set tv of grand total
        String tol = Double.toString(total) + " $";
        Locale locale = new Locale("en", "US"); // or you can use Locale.getDefault() for the default locale
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formattedValue = currencyFormatter.format(total);
        tvTotal.setText(formattedValue);

    }

    public void reload_CheckOut() throws JSONException, IOException {
        Cart match = API.GetCartByIdUser(UserIsLoggedIn.UserIdLogged);

        btnPay.setText("CHECK OUT (0)");
        if(match == null)
        {
            return ;
        }
        List<DetailCart> detailCartList = API.GetDetailsCartByUser(UserIsLoggedIn.UserIdLogged);
        if(detailCartList != null)
            btnPay.setText("CHECK OUT ("+detailCartList.size()+")");
    }
    @Override
    public void refreshActivity() throws JSONException, IOException {
        reload_Pay();
    }
}