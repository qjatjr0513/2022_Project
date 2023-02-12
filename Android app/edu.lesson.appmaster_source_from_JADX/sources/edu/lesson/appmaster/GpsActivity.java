package edu.lesson.appmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class GpsActivity extends AppCompatActivity {
    /* access modifiers changed from: private */

    /* renamed from: bt */
    public BluetoothSPP f505bt;

    /* renamed from: p1 */
    TextView f506p1;

    /* renamed from: p2 */
    TextView f507p2;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C2484R.layout.activity_gps);
        BluetoothSPP bluetoothSPP = new BluetoothSPP(this);
        this.f505bt = bluetoothSPP;
        if (!bluetoothSPP.isBluetoothAvailable()) {
            Toast.makeText(getApplicationContext(), "Bluetooth is not available", 0).show();
            finish();
        }
        this.f505bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {

            /* renamed from: p1 */
            TextView f508p1;

            /* renamed from: p2 */
            TextView f509p2;

            {
                this.f508p1 = (TextView) GpsActivity.this.findViewById(C2484R.C2487id.Lat);
                this.f509p2 = (TextView) GpsActivity.this.findViewById(C2484R.C2487id.Long);
            }

            public void onDataReceived(byte[] data, String message) {
                String[] array = message.split(",");
                this.f508p1.setText(array[0]);
                this.f509p2.setText(array[1]);
            }
        });
        final TextView p1 = (TextView) findViewById(C2484R.C2487id.Lat);
        final TextView p2 = (TextView) findViewById(C2484R.C2487id.Long);
        ((Button) findViewById(C2484R.C2487id.btnSend)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str1 = p1.getText().toString();
                String str2 = p2.getText().toString();
                Intent intent = new Intent(GpsActivity.this.getApplicationContext(), LocationActivity.class);
                intent.putExtra("위도", str1);
                intent.putExtra("경도", str2);
                GpsActivity.this.startActivity(intent);
            }
        });
        this.f505bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(GpsActivity.this.getApplicationContext(), "Connected to " + name + "\n" + address, 0).show();
            }

            public void onDeviceDisconnected() {
                Toast.makeText(GpsActivity.this.getApplicationContext(), "Connection lost", 0).show();
            }

            public void onDeviceConnectionFailed() {
                Toast.makeText(GpsActivity.this.getApplicationContext(), "Unable to connect", 0).show();
            }
        });
        ((Button) findViewById(C2484R.C2487id.btnConnect)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (GpsActivity.this.f505bt.getServiceState() == 3) {
                    GpsActivity.this.f505bt.disconnect();
                    return;
                }
                GpsActivity.this.startActivityForResult(new Intent(GpsActivity.this.getApplicationContext(), DeviceList.class), BluetoothState.REQUEST_CONNECT_DEVICE);
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        this.f505bt.stopService();
    }

    public void onStart() {
        super.onStart();
        if (!this.f505bt.isBluetoothEnabled()) {
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), BluetoothState.REQUEST_ENABLE_BT);
        } else if (!this.f505bt.isServiceAvailable()) {
            this.f505bt.setupService();
            this.f505bt.startService(false);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 384) {
            if (resultCode == -1) {
                this.f505bt.connect(data);
            }
        } else if (requestCode != 385) {
        } else {
            if (resultCode == -1) {
                this.f505bt.setupService();
                this.f505bt.startService(false);
                return;
            }
            Toast.makeText(getApplicationContext(), "Bluetooth was not enabled.", 0).show();
            finish();
        }
    }
}
