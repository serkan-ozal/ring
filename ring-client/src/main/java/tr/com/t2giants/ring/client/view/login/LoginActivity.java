package tr.com.t2giants.ring.client.view.login;

import tr.com.t2giants.ring.client.model.LoginRequest;
import tr.com.t2giants.ring.client.model.LoginResponse;
import tr.com.t2giants.ring.client.view.BaseRingActivity;
import tr.com.t2giants.ring.client.view.R;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseRingActivity {

    private static String TAG = "ring-login";

    private TextView txtUsername;
    private TextView txtPassword;
    private Button btnClear;
    private Button btnSend;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
        setContentView(R.layout.login);
        
        txtUsername = (TextView)findViewById(R.id.txtUsername);
        txtPassword = (TextView)findViewById(R.id.txtPassword);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnSend = (Button)findViewById(R.id.btnSend);
        
        txtUsername.setText("abc");
        txtPassword.setText("admin");
        
        btnClear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				clear();
			}
		});
        
        btnSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				login();
			}
		});
    }
    
    private void clear() {
    	txtUsername.setText("");
    	txtPassword.setText("");
    }

    private void login() {
    	CharSequence usernameValue = txtUsername.getText();
    	CharSequence passwordValue = txtPassword.getText();
    	
    	if (usernameValue == null || passwordValue == null) {
    		Toast.makeText(getApplicationContext(), "Username and Password cannot be empty !", 
    				Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	String username = txtUsername.getText().toString();
    	String password = txtPassword.getText().toString();
    	
    	if (username == null || password == null || username.length() == 0 || password.length() == 0) {
    		Toast.makeText(getApplicationContext(), "Username and Password cannot be empty !", 
    				Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	LoginResponse response = ringService.login(new LoginRequest(username, password));
    	if (response.isSuccessful()) {
    		Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
    	}
    	else {
    		Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
    	}
    }
    
}

