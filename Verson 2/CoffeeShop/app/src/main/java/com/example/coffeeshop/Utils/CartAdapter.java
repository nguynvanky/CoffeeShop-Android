package com.example.coffeeshop.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeeshop.Activities.DetailProduct;
import com.example.coffeeshop.Contains.UserIsLoggedIn;
import com.example.coffeeshop.DTO.Cart;
import com.example.coffeeshop.DTO.Category;
import com.example.coffeeshop.DTO.DetailCart;
import com.example.coffeeshop.DTO.Product;
import com.example.coffeeshop.R;
import com.example.coffeeshop.Services.API;
import com.example.coffeeshop.Services.DrawableHandler;

import org.json.JSONException;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    public interface ListenerRefesh {
        public void refreshActivity() throws JSONException, IOException;
    }
    private List<DetailCart> cartList;
    Context context;
    ListenerRefesh listenerRefesh;
    public CartAdapter(Context context, ListenerRefesh listenerRefesh) {
        this.listenerRefesh = listenerRefesh;
        this.context = context;
    }
    public CartAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<DetailCart> list)
    {
        this.cartList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_cart,parent,false);


        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        DetailCart detailCart = cartList.get(position);
        if(context == null)
            return;
        Cart cart = null;
        try {
            cart = API.GetCartByIdCart(detailCart.get_id_cart());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        if(detailCart == null)
        {
            return;
        }
        holder.edt_Quantity.setText(Integer.toString(detailCart.getQuantity()) );
        Product pro = new Product();
        try {
            pro = API.GetProductById(detailCart.get_id_product());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Glide
                .with(context)
                .load(pro.getImage()).centerCrop().placeholder(R.drawable.icon_launcher)
                .into(holder.img);
        holder.name.setText(pro.getName());
        Locale locale = new Locale("en", "US"); // or you can use Locale.getDefault() for the default locale
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formattedValue = currencyFormatter.format(detailCart.getPrice());
        holder.price.setText(formattedValue );
        Product finalPro = pro;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailProduct.class);


                i.putExtra("id",Integer.toString(finalPro.get_id()) );
                context.startActivity(i);
            }
        });
        List<Category> categories = null;
        try {
            categories = API.GetCategories();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Category cate = null;
        for( Category i : categories)
        {
            if (i.get_id() == pro.get_id_cate())
            {
                cate = i;
                break;
            }
        }
        holder.subtitle.setText(cate.getNameCategory());
        //btn increase
        if(cart.getStatus() == 0)
        {

            holder.btn_incr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlphaAnimation btnClick = new AlphaAnimation(1F,0.9F);
                    view.startAnimation(btnClick);
                    int quantity = Integer.parseInt(holder.edt_Quantity.getText().toString());
                    quantity++;
                    detailCart.setQuantity(quantity);
                    holder.edt_Quantity.setText(Integer.toString(quantity));
                    try {
                        API.UpdateDetailCart(detailCart.get_id(),quantity,UserIsLoggedIn.UserIdLogged);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    notifyDataSetChanged();

                }
            });
            // btn decrease
            holder.btn_decr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlphaAnimation btnClick = new AlphaAnimation(1F,0.9F);
                    view.startAnimation(btnClick);
                    int quantity = Integer.parseInt(holder.edt_Quantity.getText().toString());
                    if(quantity <= 1)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Confirm ?");
                        builder.setMessage("Are you sure remove?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    API.DeleteDetailCart(detailCart.get_id(),UserIsLoggedIn.UserIdLogged);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                cartList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, cartList.size());
                                //notifyDataSetChanged();
                                try {
                                    listenerRefesh.refreshActivity();
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                // Do nothing, but close the dialog
                                dialog.dismiss();
                                return;

                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing
                                notifyDataSetChanged();

                                dialog.dismiss();
                                return;
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else
                    {
                        quantity--;
                        detailCart.setQuantity(quantity);
                        holder.edt_Quantity.setText(Integer.toString(quantity) );

                        try {
                            API.UpdateDetailCart(detailCart.get_id(),quantity,UserIsLoggedIn.UserIdLogged);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    notifyDataSetChanged();
                }
            });
            try {
                listenerRefesh.refreshActivity();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public int getItemCount() {
        if(cartList != null)
            return cartList.size();
        return 0;
    }


    public class CartViewHolder extends RecyclerView.ViewHolder{
        EditText edt_Quantity;
        ImageView img;
        TextView name;
        TextView price;
        TextView subtitle;

        Button btn_incr;
        Button btn_decr;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_of_product_cart);
            name = itemView.findViewById(R.id.name_of_product_cart);
            price = itemView.findViewById(R.id.price_of_product_cart);
            subtitle = itemView.findViewById(R.id.subtitle);
            edt_Quantity = itemView.findViewById(R.id.input_quantity);
            btn_incr = itemView.findViewById(R.id.btn_plus);
            btn_decr = itemView.findViewById(R.id.btn_minus);
        }
    }
}
