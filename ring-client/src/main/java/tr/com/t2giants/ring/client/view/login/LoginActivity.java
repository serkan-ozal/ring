package tr.com.t2giants.ring.client.view.login;

import tr.com.t2giants.ring.client.model.LoginRequest;
import tr.com.t2giants.ring.client.model.LoginResponse;
import tr.com.t2giants.ring.client.view.BaseRingActivity;
import tr.com.t2giants.ring.client.view.R;
import tr.com.t2giants.ring.client.view.friendship.FriendshipActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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
		
        setTitle("Login");
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
    	
    	boolean inputValid = true;
    	
    	if (usernameValue == null) {
    		txtUsername.setError("Username cannot be empty !");
    		inputValid = false;
    	}
    	
    	if (passwordValue == null) {
    		txtPassword.setError("Password cannot be empty !");
    		inputValid = false;
    	}
    	
    	if (inputValid == false) {
    		return;
    	}

    	String username = txtUsername.getText().toString();
    	String password = txtPassword.getText().toString();
    	
    	if (username == null || username.length() == 0) {
    		txtUsername.setError("Username cannot be empty !");
    		inputValid = false;
    	}
    	
    	if (password == null || password.length() == 0) {
    		txtPassword.setError("Password cannot be empty !");
    		inputValid = false;
    	}
    	
    	if (inputValid == false) {
    		return;
    	}
    	
    	LoginResponse response = ringService.login(new LoginRequest(username, password));
    	if (response.isSuccessful()) {
    		// TODO After login, user may be redirected to another activity
            startActivity(new Intent(this, FriendshipActivity.class));
    	}
    	else {
    		AlertDialog ad = 
    				new AlertDialog.Builder(LoginActivity.this).
    						setIcon(R.drawable.login_failed_32x32).
    						setCancelable(false). 
    						setMessage(response.getError().getMessage()).
    					create();   
    		ad.setCanceledOnTouchOutside(true);
    		ad.show();  
    	}
    }
    
}

