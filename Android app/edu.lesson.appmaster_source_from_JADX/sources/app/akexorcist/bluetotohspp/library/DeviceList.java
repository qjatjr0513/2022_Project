package app.akexorcist.bluetotohspp.library;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Set;

public class DeviceList extends Activity {

    /* renamed from: D */
    private static final boolean f417D = true;
    private static final String TAG = "BluetoothSPP";
    /* access modifiers changed from: private */
    public BluetoothAdapter mBtAdapter;
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View v, int arg2, long arg3) {
            if (DeviceList.this.mBtAdapter.isDiscovering()) {
                DeviceList.this.mBtAdapter.cancelDiscovery();
            }
            String strNoFound = DeviceList.this.getIntent().getStringExtra("no_devices_found");
            if (strNoFound == null) {
                strNoFound = "No devices found";
            }
            if (!((TextView) v).getText().toString().equals(strNoFound)) {
                String info = ((TextView) v).getText().toString();
                String address = info.substring(info.length() - 17);
                Intent intent = new Intent();
                intent.putExtra(BluetoothState.EXTRA_DEVICE_ADDRESS, address);
                DeviceList.this.setResult(-1, intent);
                DeviceList.this.finish();
            }
        }
    };
    /* access modifiers changed from: private */
    public ArrayAdapter<String> mPairedDevicesArrayAdapter;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.device.action.FOUND".equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (device.getBondState() != 12) {
                    String strNoFound = DeviceList.this.getIntent().getStringExtra("no_devices_found");
                    if (strNoFound == null) {
                        strNoFound = "No devices found";
                    }
                    if (((String) DeviceList.this.mPairedDevicesArrayAdapter.getItem(0)).equals(strNoFound)) {
                        DeviceList.this.mPairedDevicesArrayAdapter.remove(strNoFound);
                    }
                    DeviceList.this.mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
            } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                DeviceList.this.setProgressBarIndeterminateVisibility(false);
                String strSelectDevice = DeviceList.this.getIntent().getStringExtra("select_device");
                if (strSelectDevice == null) {
                    strSelectDevice = "Select a device to connect";
                }
                DeviceList.this.setTitle(strSelectDevice);
            }
        }
    };
    private Set<BluetoothDevice> pairedDevices;
    private Button scanButton;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(5);
        setContentView(getIntent().getIntExtra("layout_list", C2403R.layout.device_list));
        String strBluetoothDevices = getIntent().getStringExtra("bluetooth_devices");
        if (strBluetoothDevices == null) {
            strBluetoothDevices = "Bluetooth Devices";
        }
        setTitle(strBluetoothDevices);
        setResult(0);
        this.scanButton = (Button) findViewById(C2403R.C2404id.button_scan);
        String strScanDevice = getIntent().getStringExtra("scan_for_devices");
        if (strScanDevice == null) {
            strScanDevice = "SCAN FOR DEVICES";
        }
        this.scanButton.setText(strScanDevice);
        this.scanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DeviceList.this.doDiscovery();
            }
        });
        this.mPairedDevicesArrayAdapter = new ArrayAdapter<>(this, getIntent().getIntExtra("layout_text", C2403R.layout.device_name));
        ListView pairedListView = (ListView) findViewById(C2403R.C2404id.list_devices);
        pairedListView.setAdapter(this.mPairedDevicesArrayAdapter);
        pairedListView.setOnItemClickListener(this.mDeviceClickListener);
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.device.action.FOUND"));
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED"));
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBtAdapter = defaultAdapter;
        Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
        this.pairedDevices = bondedDevices;
        if (bondedDevices.size() > 0) {
            for (BluetoothDevice device : this.pairedDevices) {
                this.mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
            return;
        }
        this.mPairedDevicesArrayAdapter.add("No devices found");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        BluetoothAdapter bluetoothAdapter = this.mBtAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.cancelDiscovery();
        }
        unregisterReceiver(this.mReceiver);
        finish();
    }

    /* access modifiers changed from: private */
    public void doDiscovery() {
        Log.d(TAG, "doDiscovery()");
        this.mPairedDevicesArrayAdapter.clear();
        if (this.pairedDevices.size() > 0) {
            for (BluetoothDevice device : this.pairedDevices) {
                this.mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        } else {
            String strNoFound = getIntent().getStringExtra("no_devices_found");
            if (strNoFound == null) {
                strNoFound = "No devices found";
            }
            this.mPairedDevicesArrayAdapter.add(strNoFound);
        }
        String strScanning = getIntent().getStringExtra("scanning");
        if (strScanning == null) {
            strScanning = "Scanning for devices...";
        }
        setProgressBarIndeterminateVisibility(true);
        setTitle(strScanning);
        if (this.mBtAdapter.isDiscovering()) {
            this.mBtAdapter.cancelDiscovery();
        }
        this.mBtAdapter.startDiscovery();
    }
}
