package edu.lesson.appmaster;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonSignup;
    EditText editTextEmail;
    EditText editTextPassword;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    TextView textviewMessage;
    TextView textviewSingin;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C2484R.layout.activity_sign_up);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.firebaseAuth = instance;
        if (instance.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        this.editTextEmail = (EditText) findViewById(C2484R.C2487id.editTextEmail);
        this.editTextPassword = (EditText) findViewById(C2484R.C2487id.editTextPassword);
        this.textviewSingin = (TextView) findViewById(C2484R.C2487id.textViewSignin);
        this.textviewMessage = (TextView) findViewById(C2484R.C2487id.textviewMessage);
        this.buttonSignup = (Button) findViewById(C2484R.C2487id.buttonSignup);
        this.progressDialog = new ProgressDialog(this);
        this.buttonSignup.setOnClickListener(this);
        this.textviewSingin.setOnClickListener(this);
    }

    private void registerUser() {
        String email = this.editTextEmail.getText().toString().trim();
        String password = this.editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email??? ????????? ?????????.", 0).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password??? ????????? ?????????.", 0).show();
        }
        this.progressDialog.setMessage("??????????????????. ????????? ?????????...");
        this.progressDialog.show();
        this.firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity) this, new OnCompleteListener<AuthResult>() {
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    JoinActivity.this.finish();
                    JoinActivity.this.startActivity(new Intent(JoinActivity.this.getApplicationContext(), ProfileActivity.class));
                } else {
                    JoinActivity.this.textviewMessage.setText("????????????\n - ?????? ????????? ?????????  \n -?????? ?????? 6?????? ?????? \n - ????????????");
                    Toast.makeText(JoinActivity.this, "?????? ??????!", 0).show();
                }
                JoinActivity.this.progressDialog.dismiss();
            }
        });
    }

    public void onClick(View view) {
        if (view == this.buttonSignup) {
            registerUser();
        }
        if (view == this.textviewSingin) {
            startActivity(new Intent(this, LogInActivity.class));
        }
    }
}
