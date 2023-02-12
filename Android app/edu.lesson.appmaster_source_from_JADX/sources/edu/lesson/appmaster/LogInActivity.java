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

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonSignin;
    EditText editTextEmail;
    EditText editTextPassword;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    TextView textviewFindPassword;
    TextView textviewMessage;
    TextView textviewSingin;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C2484R.layout.activity_sign_in);
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
        this.textviewFindPassword = (TextView) findViewById(C2484R.C2487id.textViewFindpassword);
        this.buttonSignin = (Button) findViewById(C2484R.C2487id.buttonSignup);
        this.progressDialog = new ProgressDialog(this);
        this.buttonSignin.setOnClickListener(this);
        this.textviewSingin.setOnClickListener(this);
        this.textviewFindPassword.setOnClickListener(this);
    }

    private void userLogin() {
        String email = this.editTextEmail.getText().toString().trim();
        String password = this.editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "email을 입력해 주세요.", 0).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "password를 입력해 주세요.", 0).show();
        } else {
            this.progressDialog.setMessage("로그인중입니다. 잠시 기다려 주세요...");
            this.progressDialog.show();
            this.firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) this, new OnCompleteListener<AuthResult>() {
                public void onComplete(Task<AuthResult> task) {
                    LogInActivity.this.progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        LogInActivity.this.finish();
                        LogInActivity.this.startActivity(new Intent(LogInActivity.this.getApplicationContext(), ProfileActivity.class));
                        return;
                    }
                    Toast.makeText(LogInActivity.this.getApplicationContext(), "로그인 실패!", 1).show();
                    LogInActivity.this.textviewMessage.setText("로그인 실패 유형\n - password가 맞지 않습니다.\n -서버에러");
                }
            });
        }
    }

    public void onClick(View view) {
        if (view == this.buttonSignin) {
            userLogin();
        }
        if (view == this.textviewSingin) {
            finish();
            startActivity(new Intent(this, JoinActivity.class));
        }
        if (view == this.textviewFindPassword) {
            finish();
            startActivity(new Intent(this, FindActivity.class));
        }
    }
}
