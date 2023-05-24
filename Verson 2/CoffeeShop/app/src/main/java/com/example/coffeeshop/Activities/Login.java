package com.example.coffeeshop.Activities;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.coffeeshop.Contains.IpconfigLocalhost;
import com.example.coffeeshop.Contains.IsConnectedNetwork;
import com.example.coffeeshop.Contains.UserIsLoggedIn;
import com.example.coffeeshop.DTO.LoginRequest;
import com.example.coffeeshop.DTO.Product;
import com.example.coffeeshop.DTO.User;
import com.example.coffeeshop.R;
import com.example.coffeeshop.Services.API;
import com.example.coffeeshop.Services.ApiService;
import com.example.coffeeshop.Services.CheckNetwork;
import com.example.coffeeshop.Utils.DatabaseHandler;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Login extends AppCompatActivity {
    Button signIn;
    Button signUp;
    TextInputEditText username;
    TextInputEditText password;

    CheckNetwork checkNetwork = new CheckNetwork();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        signIn = findViewById(R.id.btn_signIn) ;
        signUp = findViewById(R.id.btn_signUp);
        username = findViewById(R.id.edt_username);
        password = findViewById(R.id.edt_password);
        if(UserIsLoggedIn.isLogin == true) // still login
        {
            Intent i = new Intent(Login.this,MainActivity.class);
            startActivity(i);
            finish();
        }
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(IsConnectedNetwork.isConnected == false)
                {
                    Toast.makeText(Login.this, "Please connect to network", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                {
                    Toast.makeText(Login.this, "Please enter username & password", Toast.LENGTH_SHORT).show();
                    username.requestFocus();
                    return;
                }
                if(IsConnectedNetwork.isConnected == false)
                {
                    Toast.makeText(Login.this, "Please connect to network", Toast.LENGTH_SHORT).show();
                    return;
                }
                String username_ = username.getText().toString().trim();
                String password_ = password.getText().toString().trim();
                LoginRequest body = new LoginRequest("Login",username_,password_);
                ApiService.apiService.Login("Login",username_,password_).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if(user != null)
                        {
                            UserIsLoggedIn.user = user;
                            UserIsLoggedIn.UserIdLogged = user.get_id();
                            UserIsLoggedIn.Role = user.getRole();
                            UserIsLoggedIn.UsernameLogged = user.get_username();
                            UserIsLoggedIn.isLogin = true;
                            // login
                            Intent i = new Intent(Login.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Login failed, please try again.", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(Login.this, "Login failed, please try again.", Toast.LENGTH_SHORT).show();
                        }
                });
//                try {
//                    if(isAuthentication(username,password.getText().toString()))
//                    {
//                        Intent i = new Intent(Login.this,MainActivity.class);
//                        startActivity(i);
//                        finish();
//
//                    }
//                    else
//                    {
//                        Toast.makeText(Login.this, "Login failed, please try again.", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter checkConnectedNetwork = new IntentFilter(CONNECTIVITY_ACTION);
        registerReceiver(checkNetwork, checkConnectedNetwork);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(checkNetwork);
    }

//    private boolean isAuthentication(String username, String password) throws JSONException, IOException {
//
//        User user = API.Login(username, password);
//        if(user != null)
//        {
//            UserIsLoggedIn.user = user;
//            UserIsLoggedIn.UserIdLogged = user.get_id();
//            UserIsLoggedIn.Role = user.getRole();
//            UserIsLoggedIn.UsernameLogged = user.get_username();
//            UserIsLoggedIn.isLogin = true;
//            return true;
//        }
//        UserIsLoggedIn.isLogin = false;
//        UserIsLoggedIn.UsernameLogged = "";
//        UserIsLoggedIn.UserIdLogged = -1;
//        return false;
//    }
}