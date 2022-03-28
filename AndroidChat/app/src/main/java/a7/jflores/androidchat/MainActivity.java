package a7.jflores.androidchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText txtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUserName = findViewById(R.id.txtUserName);
    }

    public void onLoginClicked(View v) {
        String userName = txtUserName.getText().toString();
        //Log.i("LOGIN", userName);
        boolean nameIsValid = userName.matches("^\\w{2,9}[a-zA-Z0-9]$");

        if(nameIsValid) {
            //move to the next activity and chat
            Log.i("LOGIN", "Name was valid");
            Intent intent = ChatActivity.createIntent(this, userName);
            startActivity(intent);
        } else {
            Log.i("LOGIN, ", "Name was Not valid!!!!");
            Snackbar snackbar = Snackbar.make(txtUserName,
                    "User Name must be 3-10 characters long and alphanumeric only!",
                    Snackbar.LENGTH_LONG);
            snackbar.setDuration(5000);
            snackbar.setAnchorView(txtUserName);
            snackbar.show();
        }
    }
}