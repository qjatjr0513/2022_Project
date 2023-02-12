package app.akexorcist.bluetotohspp.library;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.Set;

public class BluetoothSPP {
    /* access modifiers changed from: private */
    public BluetoothConnectionListener bcl;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public int f416c = 0;
    private boolean isAndroid = true;
    /* access modifiers changed from: private */
    public boolean isAutoConnecting = false;
    /* access modifiers changed from: private */
    public boolean isAutoConnectionEnabled = false;
    /* access modifiers changed from: private */
    public boolean isConnected = false;
    /* access modifiers changed from: private */
    public boolean isConnecting = false;
    /* access modifiers changed from: private */
    public boolean isServiceRunning = false;
    /* access modifiers changed from: private */
    public String keyword = "";
    /* access modifiers changed from: private */
    public AutoConnectionListener mAutoConnectionListener = null;
    private BluetoothAdapter mBluetoothAdapter = null;
    /* access modifiers changed from: private */
    public BluetoothConnectionListener mBluetoothConnectionListener = null;
    /* access modifiers changed from: private */
    public BluetoothStateListener mBluetoothStateListener = null;
    /* access modifiers changed from: private */
    public BluetoothService mChatService = null;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public OnDataReceivedListener mDataReceivedListener = null;
    /* access modifiers changed from: private */
    public String mDeviceAddress = null;
    /* access modifiers changed from: private */
    public String mDeviceName = null;
    private final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (BluetoothSPP.this.mBluetoothStateListener != null) {
                        BluetoothSPP.this.mBluetoothStateListener.onServiceStateChanged(msg.arg1);
                    }
                    if (BluetoothSPP.this.isConnected && msg.arg1 != 3) {
                        if (BluetoothSPP.this.mBluetoothConnectionListener != null) {
                            BluetoothSPP.this.mBluetoothConnectionListener.onDeviceDisconnected();
                        }
                        if (BluetoothSPP.this.isAutoConnectionEnabled) {
                            boolean unused = BluetoothSPP.this.isAutoConnectionEnabled = false;
                            BluetoothSPP bluetoothSPP = BluetoothSPP.this;
                            bluetoothSPP.autoConnect(bluetoothSPP.keyword);
                        }
                        boolean unused2 = BluetoothSPP.this.isConnected = false;
                        String unused3 = BluetoothSPP.this.mDeviceName = null;
                        String unused4 = BluetoothSPP.this.mDeviceAddress = null;
                    }
                    if (!BluetoothSPP.this.isConnecting && msg.arg1 == 2) {
                        boolean unused5 = BluetoothSPP.this.isConnecting = true;
                        return;
                    } else if (BluetoothSPP.this.isConnecting) {
                        if (!(msg.arg1 == 3 || BluetoothSPP.this.mBluetoothConnectionListener == null)) {
                            BluetoothSPP.this.mBluetoothConnectionListener.onDeviceConnectionFailed();
                        }
                        boolean unused6 = BluetoothSPP.this.isConnecting = false;
                        return;
                    } else {
                        return;
                    }
                case 2:
                    byte[] readBuf = (byte[]) msg.obj;
                    String readMessage = new String(readBuf);
                    if (readBuf != null && readBuf.length > 0 && BluetoothSPP.this.mDataReceivedListener != null) {
                        BluetoothSPP.this.mDataReceivedListener.onDataReceived(readBuf, readMessage);
                        return;
                    }
                    return;
                case 4:
                    String unused7 = BluetoothSPP.this.mDeviceName = msg.getData().getString(BluetoothState.DEVICE_NAME);
                    String unused8 = BluetoothSPP.this.mDeviceAddress = msg.getData().getString(BluetoothState.DEVICE_ADDRESS);
                    if (BluetoothSPP.this.mBluetoothConnectionListener != null) {
                        BluetoothSPP.this.mBluetoothConnectionListener.onDeviceConnected(BluetoothSPP.this.mDeviceName, BluetoothSPP.this.mDeviceAddress);
                    }
                    boolean unused9 = BluetoothSPP.this.isConnected = true;
                    return;
                case 5:
                    Toast.makeText(BluetoothSPP.this.mContext, msg.getData().getString(BluetoothState.TOAST), 0).show();
                    return;
                default:
                    return;
            }
        }
    };

    public interface AutoConnectionListener {
        void onAutoConnectionStarted();

        void onNewConnection(String str, String str2);
    }

    public interface BluetoothConnectionListener {
        void onDeviceConnected(String str, String str2);

        void onDeviceConnectionFailed();

        void onDeviceDisconnected();
    }

    public interface BluetoothStateListener {
        void onServiceStateChanged(int i);
    }

    public interface OnDataReceivedListener {
        void onDataReceived(byte[] bArr, String str);
    }

    static /* synthetic */ int access$1408(BluetoothSPP x0) {
        int i = x0.f416c;
        x0.f416c = i + 1;
        return i;
    }

    public BluetoothSPP(Context context) {
        this.mContext = context;
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean isBluetoothAvailable() {
        try {
            BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
            if (bluetoothAdapter == null || bluetoothAdapter.getAddress().equals((Object) null)) {
                return false;
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isBluetoothEnabled() {
        return this.mBluetoothAdapter.isEnabled();
    }

    public boolean isServiceAvailable() {
        return this.mChatService != null;
    }

    public boolean isAutoConnecting() {
        return this.isAutoConnecting;
    }

    public boolean startDiscovery() {
        return this.mBluetoothAdapter.startDiscovery();
    }

    public boolean isDiscovery() {
        return this.mBluetoothAdapter.isDiscovering();
    }

    public boolean cancelDiscovery() {
        return this.mBluetoothAdapter.cancelDiscovery();
    }

    public void setupService() {
        this.mChatService = new BluetoothService(this.mContext, this.mHandler);
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return this.mBluetoothAdapter;
    }

    public int getServiceState() {
        BluetoothService bluetoothService = this.mChatService;
        if (bluetoothService != null) {
            return bluetoothService.getState();
        }
        return -1;
    }

    public void startService(boolean isAndroid2) {
        BluetoothService bluetoothService = this.mChatService;
        if (bluetoothService != null && bluetoothService.getState() == 0) {
            this.isServiceRunning = true;
            this.mChatService.start(isAndroid2);
            this.isAndroid = isAndroid2;
        }
    }

    public void stopService() {
        BluetoothService bluetoothService = this.mChatService;
        if (bluetoothService != null) {
            this.isServiceRunning = false;
            bluetoothService.stop();
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (BluetoothSPP.this.mChatService != null) {
                    boolean unused = BluetoothSPP.this.isServiceRunning = false;
                    BluetoothSPP.this.mChatService.stop();
                }
            }
        }, 500);
    }

    public void setDeviceTarget(boolean isAndroid2) {
        stopService();
        startService(isAndroid2);
        this.isAndroid = isAndroid2;
    }

    public void stopAutoConnect() {
        this.isAutoConnectionEnabled = false;
    }

    public void connect(Intent data) {
        this.mChatService.connect(this.mBluetoothAdapter.getRemoteDevice(data.getExtras().getString(BluetoothState.EXTRA_DEVICE_ADDRESS)));
    }

    public void connect(String address) {
        this.mChatService.connect(this.mBluetoothAdapter.getRemoteDevice(address));
    }

    public void disconnect() {
        BluetoothService bluetoothService = this.mChatService;
        if (bluetoothService != null) {
            this.isServiceRunning = false;
            bluetoothService.stop();
            if (this.mChatService.getState() == 0) {
                this.isServiceRunning = true;
                this.mChatService.start(this.isAndroid);
            }
        }
    }

    public void setBluetoothStateListener(BluetoothStateListener listener) {
        this.mBluetoothStateListener = listener;
    }

    public void setOnDataReceivedListener(OnDataReceivedListener listener) {
        this.mDataReceivedListener = listener;
    }

    public void setBluetoothConnectionListener(BluetoothConnectionListener listener) {
        this.mBluetoothConnectionListener = listener;
    }

    public void setAutoConnectionListener(AutoConnectionListener listener) {
        this.mAutoConnectionListener = listener;
    }

    public void enable() {
        this.mBluetoothAdapter.enable();
    }

    public void send(byte[] data, boolean CRLF) {
        if (this.mChatService.getState() != 3) {
            return;
        }
        if (CRLF) {
            byte[] data2 = new byte[(data.length + 2)];
            for (int i = 0; i < data.length; i++) {
                data2[i] = data[i];
            }
            data2[data2.length - 2] = 10;
            data2[data2.length - 1] = Ascii.f53CR;
            this.mChatService.write(data2);
            return;
        }
        this.mChatService.write(data);
    }

    public void send(String data, boolean CRLF) {
        if (this.mChatService.getState() == 3) {
            if (CRLF) {
                data = data + "\r\n";
            }
            this.mChatService.write(data.getBytes());
        }
    }

    public String getConnectedDeviceName() {
        return this.mDeviceName;
    }

    public String getConnectedDeviceAddress() {
        return this.mDeviceAddress;
    }

    public String[] getPairedDeviceName() {
        int c = 0;
        Set<BluetoothDevice> devices = this.mBluetoothAdapter.getBondedDevices();
        String[] name_list = new String[devices.size()];
        for (BluetoothDevice device : devices) {
            name_list[c] = device.getName();
            c++;
        }
        return name_list;
    }

    public String[] getPairedDeviceAddress() {
        int c = 0;
        Set<BluetoothDevice> devices = this.mBluetoothAdapter.getBondedDevices();
        String[] address_list = new String[devices.size()];
        for (BluetoothDevice device : devices) {
            address_list[c] = device.getAddress();
            c++;
        }
        return address_list;
    }

    public void autoConnect(String keywordName) {
        if (!this.isAutoConnectionEnabled) {
            this.keyword = keywordName;
            this.isAutoConnectionEnabled = true;
            this.isAutoConnecting = true;
            AutoConnectionListener autoConnectionListener = this.mAutoConnectionListener;
            if (autoConnectionListener != null) {
                autoConnectionListener.onAutoConnectionStarted();
            }
            final ArrayList<String> arr_filter_address = new ArrayList<>();
            final ArrayList<String> arr_filter_name = new ArrayList<>();
            String[] arr_name = getPairedDeviceName();
            String[] arr_address = getPairedDeviceAddress();
            for (int i = 0; i < arr_name.length; i++) {
                if (arr_name[i].contains(keywordName)) {
                    arr_filter_address.add(arr_address[i]);
                    arr_filter_name.add(arr_name[i]);
                }
            }
            C19413 r4 = new BluetoothConnectionListener() {
                public void onDeviceConnected(String name, String address) {
                    BluetoothConnectionListener unused = BluetoothSPP.this.bcl = null;
                    boolean unused2 = BluetoothSPP.this.isAutoConnecting = false;
                }

                public void onDeviceDisconnected() {
                }

                public void onDeviceConnectionFailed() {
                    Log.e("CHeck", "Failed");
                    if (!BluetoothSPP.this.isServiceRunning) {
                        return;
                    }
                    if (BluetoothSPP.this.isAutoConnectionEnabled) {
                        BluetoothSPP.access$1408(BluetoothSPP.this);
                        if (BluetoothSPP.this.f416c >= arr_filter_address.size()) {
                            int unused = BluetoothSPP.this.f416c = 0;
                        }
                        BluetoothSPP bluetoothSPP = BluetoothSPP.this;
                        bluetoothSPP.connect((String) arr_filter_address.get(bluetoothSPP.f416c));
                        Log.e("CHeck", "Connect");
                        if (BluetoothSPP.this.mAutoConnectionListener != null) {
                            BluetoothSPP.this.mAutoConnectionListener.onNewConnection((String) arr_filter_name.get(BluetoothSPP.this.f416c), (String) arr_filter_address.get(BluetoothSPP.this.f416c));
                            return;
                        }
                        return;
                    }
                    BluetoothConnectionListener unused2 = BluetoothSPP.this.bcl = null;
                    boolean unused3 = BluetoothSPP.this.isAutoConnecting = false;
                }
            };
            this.bcl = r4;
            setBluetoothConnectionListener(r4);
            this.f416c = 0;
            AutoConnectionListener autoConnectionListener2 = this.mAutoConnectionListener;
            if (autoConnectionListener2 != null) {
                autoConnectionListener2.onNewConnection(arr_name[0], arr_address[0]);
            }
            if (arr_filter_address.size() > 0) {
                connect(arr_filter_address.get(this.f416c));
            } else {
                Toast.makeText(this.mContext, "Device name mismatch", 0).show();
            }
        }
    }
}
