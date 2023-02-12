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
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    Button passButton;
    EditText textBox;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C2484R.layout.activity_location);
        TextView et1 = (TextView) findViewById(C2484R.C2487id.Text1);
        TextView et2 = (TextView) findViewById(C2484R.C2487id.Text2);
        Intent intent = getIntent();
        String str1 = intent.getStringExtra("위도");
        String str2 = intent.getStringExtra("경도");
        et1.setText(str1);
        et2.setText(str2);
        final Geocoder geocoder = new Geocoder(this);
        final TextView textView = et1;
        final TextView textView2 = et2;
        final TextView textView3 = (TextView) findViewById(C2484R.C2487id.textView4);
        ((Button) findViewById(C2484R.C2487id.button1)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Address> list = null;
                try {
                    list = geocoder.getFromLocation(Double.parseDouble(textView.getText().toString()), Double.parseDouble(textView2.getText().toString()), 10);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
                }
                if (list == null) {
                    return;
                }
                if (list.size() == 0) {
                    textView3.setText("해당되는 주소 정보는 없습니다");
                    return;
                }
                textView3.setText(list.get(0).toString());
                textView3.setText(list.get(0).getAddressLine(0));
                String str = textView3.getText().toString();
                Intent intent = new Intent(LocationActivity.this.getApplicationContext(), MainActivity.class);
                intent.putExtra("message", str);
                LocationActivity.this.startActivity(intent);
            }
        });
    }
}
