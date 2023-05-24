package com.example.coffeeshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.Contains.UserIsLoggedIn;
import com.example.coffeeshop.DTO.Cart;
import com.example.coffeeshop.DTO.User;
import com.example.coffeeshop.R;
import com.example.coffeeshop.Services.API;
import com.example.coffeeshop.Utils.DatabaseHandler;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class TransactionActivity extends AppCompatActivity {
    ImageButton btnBack;
    Button btnOrder;
    TextView tvTotal;
    TextInputEditText phone;
    TextInputEditText email;
    TextInputEditText address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        btnBack = findViewById(R.id.btn_back);
        tvTotal= findViewById(R.id.total);
        btnOrder = findViewById(R.id.btn_placeOrder);
        phone = findViewById(R.id.edt_phone);
        email = findViewById(R.id.edt_email);
        address = findViewById(R.id.edt_address);

        Intent i = getIntent();
        if(i != null)
        {
            String total =  i.getStringExtra("total");
            tvTotal.setText(total);
        }

        email.setText(UserIsLoggedIn.user.getEmail());
        phone.setText(UserIsLoggedIn.user.getPhone_number());
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = email.getText().toString();
                String sPhone = phone.getText().toString();
                String sAddress = address.getText().toString();


                if(sAddress.isEmpty() || sEmail.isEmpty() || sPhone.isEmpty())
                {
                    Toast.makeText(TransactionActivity.this, "Please enter all field", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(TransactionActivity.this);
                builder.setTitle("Payment confirmation");
                builder.setMessage("Is the address and phone number confirmed to be correct?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            API.PlaceOrder(UserIsLoggedIn.UserIdLogged,sAddress);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        addNotification("Thank you for using our service.");
                        onBackPressed();
                        // Do nothing, but close the dialog
                        dialog.dismiss();
                        return;

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                        return;
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();



            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView name = findViewById(R.id.name_user);
        User user = UserIsLoggedIn.user;
        name.setText(user.getFull_name());
        phone.setText(user.getPhone_number());
    }
    private void addNotification(String s) {
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CHANNEL_ID ="channel_notification";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            //mChannel.enableVibration(true);
            //mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(true);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_48)
                .setContentTitle("Message from coffee shop")
                .setContentText(s);

        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.putExtra("notification",1);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify(1, builder.build());
    }
}