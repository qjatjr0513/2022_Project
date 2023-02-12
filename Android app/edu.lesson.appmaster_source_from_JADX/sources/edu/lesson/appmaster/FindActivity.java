package edu.lesson.appmaster;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FindActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "FindActivity";
    private Button buttonFind;
    private EditText editTextUserEmail;
    private FirebaseAuth firebaseAuth;
    /* access modifiers changed from: private */
    public ProgressDialog progressDialog;
    private TextView textviewMessage;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C2484R.layout.activity_find);
        this.editTextUserEmail = (EditText) findViewById(C2484R.C2487id.editTextUserEmail);
        this.buttonFind = (Button) findViewById(C2484R.C2487id.buttonFind);
        this.progressDialog = new ProgressDialog(this);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.buttonFind.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == this.buttonFind) {
            this.progressDialog.setMessage("처리중입니다. 잠시 기다려 주세요...");
            this.progressDialog.show();
            this.firebaseAuth.sendPasswordResetEmail(this.editTextUserEmail.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(FindActivity.this, "이메일을 보냈습니다.", 1).show();
                        FindActivity.this.finish();
                        FindActivity.this.startActivity(new Intent(FindActivity.this.getApplicationContext(), LogInActivity.class));
                    } else {
                        Toast.makeText(FindActivity.this, "메일 보내기 실패!", 1).show();
                    }
                    FindActivity.this.progressDialog.dismiss();
                }
            });
        }
    }
}
