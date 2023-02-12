package app.akexorcist.bluetotohspp.library;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class BluetoothService {
    private static final String NAME_SECURE = "Bluetooth Secure";
    private static final String TAG = "Bluetooth Service";
    /* access modifiers changed from: private */
    public static final UUID UUID_ANDROID_DEVICE = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
    /* access modifiers changed from: private */
    public static final UUID UUID_OTHER_DEVICE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    /* access modifiers changed from: private */
    public boolean isAndroid = true;
    /* access modifiers changed from: private */
    public final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
    /* access modifiers changed from: private */
    public ConnectThread mConnectThread;
    private ConnectedThread mConnectedThread;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    private AcceptThread mSecureAcceptThread;
    /* access modifiers changed from: private */
    public int mState = 0;

    public BluetoothService(Context context, Handler handler) {
        this.mHandler = handler;
    }

    private synchronized void setState(int state) {
        Log.d(TAG, "setState() " + this.mState + " -> " + state);
        this.mState = state;
        this.mHandler.obtainMessage(1, state, -1).sendToTarget();
    }

    public synchronized int getState() {
        return this.mState;
    }

    public synchronized void start(boolean isAndroid2) {
        ConnectThread connectThread = this.mConnectThread;
        if (connectThread != null) {
            connectThread.cancel();
            this.mConnectThread = null;
        }
        ConnectedThread connectedThread = this.mConnectedThread;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.mConnectedThread = null;
        }
        setState(1);
        if (this.mSecureAcceptThread == null) {
            AcceptThread acceptThread = new AcceptThread(isAndroid2);
            this.mSecureAcceptThread = acceptThread;
            acceptThread.start();
            this.isAndroid = isAndroid2;
        }
    }

    public synchronized void connect(BluetoothDevice device) {
        ConnectThread connectThread;
        if (this.mState == 2 && (connectThread = this.mConnectThread) != null) {
            connectThread.cancel();
            this.mConnectThread = null;
        }
        ConnectedThread connectedThread = this.mConnectedThread;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.mConnectedThread = null;
        }
        ConnectThread connectThread2 = new ConnectThread(device);
        this.mConnectThread = connectThread2;
        connectThread2.start();
        setState(2);
    }

    public synchronized void connected(BluetoothSocket socket, BluetoothDevice device, String socketType) {
        ConnectThread connectThread = this.mConnectThread;
        if (connectThread != null) {
            connectThread.cancel();
            this.mConnectThread = null;
        }
        ConnectedThread connectedThread = this.mConnectedThread;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.mConnectedThread = null;
        }
        AcceptThread acceptThread = this.mSecureAcceptThread;
        if (acceptThread != null) {
            acceptThread.cancel();
            this.mSecureAcceptThread = null;
        }
        ConnectedThread connectedThread2 = new ConnectedThread(socket, socketType);
        this.mConnectedThread = connectedThread2;
        connectedThread2.start();
        Message msg = this.mHandler.obtainMessage(4);
        Bundle bundle = new Bundle();
        bundle.putString(BluetoothState.DEVICE_NAME, device.getName());
        bundle.putString(BluetoothState.DEVICE_ADDRESS, device.getAddress());
        msg.setData(bundle);
        this.mHandler.sendMessage(msg);
        setState(3);
    }

    public synchronized void stop() {
        ConnectThread connectThread = this.mConnectThread;
        if (connectThread != null) {
            connectThread.cancel();
            this.mConnectThread = null;
        }
        ConnectedThread connectedThread = this.mConnectedThread;
        if (connectedThread != null) {
            connectedThread.cancel();
            this.mConnectedThread = null;
        }
        AcceptThread acceptThread = this.mSecureAcceptThread;
        if (acceptThread != null) {
            acceptThread.cancel();
            this.mSecureAcceptThread.kill();
            this.mSecureAcceptThread = null;
        }
        setState(0);
    }

    public void write(byte[] out) {
        synchronized (this) {
            try {
                if (this.mState == 3) {
                    ConnectedThread r = this.mConnectedThread;
                    try {
                        r.write(out);
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public void connectionFailed() {
        start(this.isAndroid);
    }

    /* access modifiers changed from: private */
    public void connectionLost() {
        start(this.isAndroid);
    }

    private class AcceptThread extends Thread {
        boolean isRunning = true;
        private String mSocketType;
        private BluetoothServerSocket mmServerSocket;

        public AcceptThread(boolean isAndroid) {
            BluetoothServerSocket tmp = null;
            if (isAndroid) {
                try {
                    tmp = BluetoothService.this.mAdapter.listenUsingRfcommWithServiceRecord(BluetoothService.NAME_SECURE, BluetoothService.UUID_ANDROID_DEVICE);
                } catch (IOException e) {
                }
            } else {
                tmp = BluetoothService.this.mAdapter.listenUsingRfcommWithServiceRecord(BluetoothService.NAME_SECURE, BluetoothService.UUID_OTHER_DEVICE);
            }
            this.mmServerSocket = tmp;
        }

        public void run() {
            setName("AcceptThread" + this.mSocketType);
            while (BluetoothService.this.mState != 3 && this.isRunning) {
                try {
                    BluetoothSocket socket = this.mmServerSocket.accept();
                    if (socket != null) {
                        synchronized (BluetoothService.this) {
                            switch (BluetoothService.this.mState) {
                                case 0:
                                case 3:
                                    try {
                                        socket.close();
                                        break;
                                    } catch (IOException e) {
                                        break;
                                    }
                                case 1:
                                case 2:
                                    BluetoothService.this.connected(socket, socket.getRemoteDevice(), this.mSocketType);
                                    break;
                            }
                        }
                    }
                } catch (IOException e2) {
                    return;
                }
            }
        }

        public void cancel() {
            try {
                this.mmServerSocket.close();
                this.mmServerSocket = null;
            } catch (IOException e) {
            }
        }

        public void kill() {
            this.isRunning = false;
        }
    }

    private class ConnectThread extends Thread {
        private String mSocketType;
        private final BluetoothDevice mmDevice;
        private final BluetoothSocket mmSocket;

        public ConnectThread(BluetoothDevice device) {
            this.mmDevice = device;
            BluetoothSocket tmp = null;
            try {
                if (BluetoothService.this.isAndroid) {
                    tmp = device.createRfcommSocketToServiceRecord(BluetoothService.UUID_ANDROID_DEVICE);
                } else {
                    tmp = device.createRfcommSocketToServiceRecord(BluetoothService.UUID_OTHER_DEVICE);
                }
            } catch (IOException e) {
            }
            this.mmSocket = tmp;
        }

        public void run() {
            BluetoothService.this.mAdapter.cancelDiscovery();
            try {
                this.mmSocket.connect();
                synchronized (BluetoothService.this) {
                    ConnectThread unused = BluetoothService.this.mConnectThread = null;
                }
                BluetoothService.this.connected(this.mmSocket, this.mmDevice, this.mSocketType);
            } catch (IOException e) {
                try {
                    this.mmSocket.close();
                } catch (IOException e2) {
                }
                BluetoothService.this.connectionFailed();
            }
        }

        public void cancel() {
            try {
                this.mmSocket.close();
            } catch (IOException e) {
            }
        }
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private final BluetoothSocket mmSocket;

        public ConnectedThread(BluetoothSocket socket, String socketType) {
            this.mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }
            this.mmInStream = tmpIn;
            this.mmOutStream = tmpOut;
        }

        public void run() {
            ArrayList<Integer> arr_byte = new ArrayList<>();
            while (true) {
                try {
                    int data = this.mmInStream.read();
                    if (data != 10) {
                        if (data == 13) {
                            byte[] buffer = new byte[arr_byte.size()];
                            for (int i = 0; i < arr_byte.size(); i++) {
                                buffer[i] = arr_byte.get(i).byteValue();
                            }
                            BluetoothService.this.mHandler.obtainMessage(2, buffer.length, -1, buffer).sendToTarget();
                            arr_byte = new ArrayList<>();
                        } else {
                            arr_byte.add(Integer.valueOf(data));
                        }
                    }
                } catch (IOException e) {
                    BluetoothService.this.connectionLost();
                    BluetoothService bluetoothService = BluetoothService.this;
                    bluetoothService.start(bluetoothService.isAndroid);
                    return;
                }
            }
        }

        public void write(byte[] buffer) {
            try {
                this.mmOutStream.write(buffer);
                BluetoothService.this.mHandler.obtainMessage(3, -1, -1, buffer).sendToTarget();
            } catch (IOException e) {
            }
        }

        public void cancel() {
            try {
                this.mmSocket.close();
            } catch (IOException e) {
            }
        }
    }
}
