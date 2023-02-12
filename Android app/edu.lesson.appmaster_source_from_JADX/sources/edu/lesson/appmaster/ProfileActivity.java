package edu.lesson.appmaster;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ProfileActivity";
    private Button buttonLogout;
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private TextView textivewDelete;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C2484R.layout.activity_profile);
        ((Button) findViewById(C2484R.C2487id.btn2)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProfileActivity.this.startActivity(new Intent(ProfileActivity.this.getApplicationContext(), GpsActivity.class));
            }
        });
        ((Button) findViewById(C2484R.C2487id.btn1)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProfileActivity.this.startActivity(new Intent(ProfileActivity.this.getApplicationContext(), MainActivity.class));
            }
        });
        this.textViewUserEmail = (TextView) findViewById(C2484R.C2487id.textviewUserEmail);
        this.buttonLogout = (Button) findViewById(C2484R.C2487id.buttonLogout);
        this.textivewDelete = (TextView) findViewById(C2484R.C2487id.textviewDelete);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.firebaseAuth = instance;
        if (instance.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LogInActivity.class));
        }
        this.textViewUserEmail.setText("반갑습니다.\n" + this.firebaseAuth.getCurrentUser().getEmail() + "으로 로그인 하였습니다.");
        this.buttonLogout.setOnClickListener(this);
        this.textivewDelete.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == this.buttonLogout) {
            this.firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LogInActivity.class));
        }
        if (view == this.textivewDelete) {
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
            alert_confirm.setMessage((CharSequence) "정말 계정을 삭제 할까요?").setCancelable(false).setPositiveButton((CharSequence) "확인", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(Task<Void> task) {
                            Toast.makeText(ProfileActivity.this, "계정이 삭제 되었습니다.", 1).show();
                            ProfileActivity.this.finish();
                            ProfileActivity.this.startActivity(new Intent(ProfileActivity.this.getApplicationContext(), JoinActivity.class));
                        }
                    });
                }
            });
            alert_confirm.setNegativeButton((CharSequence) "취소", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(ProfileActivity.this, "취소", 1).show();
                }
            });
            alert_confirm.show();
        }
    }
}
