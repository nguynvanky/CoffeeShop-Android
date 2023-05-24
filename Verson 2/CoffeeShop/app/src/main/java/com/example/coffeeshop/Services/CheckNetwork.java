package com.example.coffeeshop.Services;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.widget.Toast;

import com.example.coffeeshop.Contains.IsConnectedNetwork;

public class CheckNetwork extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        //if(isConnectedNetwork(context.getApplicationContext())){
         if(isNetworkAvailable(context.getApplicationContext()) == true){
            if(IsConnectedNetwork.displayConnected == false)
            {
                Toast.makeText(context, "Welcome back ♥", Toast.LENGTH_SHORT).show();
                IsConnectedNetwork.displayConnected = true;
            }
            IsConnectedNetwork.isConnected = true;
            return;
        }
        else
        {
            IsConnectedNetwork.displayConnected = false;
            IsConnectedNetwork.isConnected = false;
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Notification");
            builder.setMessage("Please connect to network");
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    return;
                }
            });
            builder.setPositiveButton("Go to settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    context.startActivity(intent);
                    // Do nothing, but close the dialog
                    dialog.dismiss();
                    return;
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private static boolean isConnectedNetwork(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.Global.WIFI_ON, 0) != 0;
    }
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
