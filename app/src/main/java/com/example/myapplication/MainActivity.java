package com.example.myapplication;


import android.content.Context;

import android.os.PowerManager;
import android.net.wifi.WifiManager;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        // Set a click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();

                // Check if Wi-Fi is turned on
                if (!isWifiOn()) {
                    Toast.makeText(MainActivity.this, "Wi-Fi is turned off, please enable Wi-Fi and try again.", Toast.LENGTH_SHORT).show();
                    return; // If Wi-Fi is off, don't proceed to check the password
                }

                // Check if Bluetooth is turned on
                if (!isBluetoothOn()) {
                    Toast.makeText(MainActivity.this, "Bluetooth is turned off, please enable Bluetooth and try again.", Toast.LENGTH_SHORT).show();
                    return; // If Bluetooth is off, don't proceed to check the password
                }
                if (!isPowerSaveModeOn()) {
                    Toast.makeText(MainActivity.this, "Power Save Mode is turned off, please enable Power Save Mode and try again.", Toast.LENGTH_SHORT).show();
                    return; // If Power Save Mode is off, don't proceed to check the password
                }


                if (isWifiOn() && isBluetoothOn() && isPowerSaveModeOn() && password.equals("Hadar")) {
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    // Additional actions after successful login
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect password or one of the conditions is not met", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to check if Wi-Fi is turned on
    private boolean isWifiOn() {
        Context context = getApplicationContext();
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }


    // Method to check if Bluetooth is turned on
    private boolean isBluetoothOn() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    private boolean isPowerSaveModeOn() {
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            return powerManager.isPowerSaveMode();
        }
        return false;
    }


}

