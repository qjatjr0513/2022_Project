package edu.lesson.appmaster;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    final String TAG = getClass().getSimpleName();
    private Button button;
    private EditText editText;
    /* access modifiers changed from: private */
    public Geocoder geocoder;
    /* access modifiers changed from: private */
    public GoogleMap mMap;
    TextView text;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C2484R.layout.activity_main);
        this.text = (TextView) findViewById(C2484R.C2487id.text);
        this.text.setText(getIntent().getStringExtra("message"));
        this.button = (Button) findViewById(C2484R.C2487id.button);
        ((Button) findViewById(C2484R.C2487id.btn3)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), LocationActivity.class));
            }
        });
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(C2484R.C2487id.map)).getMapAsync(this);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new MainActivity$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$onCreate$0$edu-lesson-appmaster-MainActivity  reason: not valid java name */
    public /* synthetic */ void m473lambda$onCreate$0$edulessonappmasterMainActivity(Task task) {
        if (!task.isSuccessful()) {
            Log.w(this.TAG, "Fetching FCM registration token failed", task.getException());
            return;
        }
        String msg = getString(C2484R.string.msg_token_fmt, new Object[]{(String) task.getResult()});
        Log.d(this.TAG, msg);
        Toast.makeText(this, msg, 0).show();
    }

    public void onMapReady(final GoogleMap googleMap) {
        this.mMap = googleMap;
        this.geocoder = new Geocoder(this);
        this.mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng point) {
                MarkerOptions mOptions = new MarkerOptions();
                mOptions.title("마커 좌표");
                Double latitude = Double.valueOf(point.latitude);
                Double longitude = Double.valueOf(point.longitude);
                mOptions.snippet(latitude.toString() + ", " + longitude.toString());
                mOptions.position(new LatLng(latitude.doubleValue(), longitude.doubleValue()));
                googleMap.addMarker(mOptions);
            }
        });
        this.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Address> addressList = null;
                try {
                    addressList = MainActivity.this.geocoder.getFromLocationName(MainActivity.this.text.getText().toString(), 10);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(addressList.get(0).toString());
                String[] splitStr = addressList.get(0).toString().split(",");
                String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2);
                System.out.println(address);
                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1);
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1);
                System.out.println(latitude);
                System.out.println(longitude);
                LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                MarkerOptions mOptions2 = new MarkerOptions();
                mOptions2.title("search result");
                mOptions2.snippet(address);
                mOptions2.position(point);
                MainActivity.this.mMap.addMarker(mOptions2);
                MainActivity.this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15.0f));
            }
        });
        LatLng station = new LatLng(36.91686d, 127.12698d);
        this.mMap.addMarker(new MarkerOptions().position(station).title("성환역"));
        this.mMap.moveCamera(CameraUpdateFactory.newLatLng(station));
    }
}
