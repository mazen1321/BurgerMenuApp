package com.example.navigationbarapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, usernameEditText, passwordEditText;
    private Button signupButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbHelper = new DatabaseHelper(this);

        firstNameEditText = findViewById(R.id.editTextFirstName);
        lastNameEditText = findViewById(R.id.editTextLastName);
        usernameEditText = findViewById(R.id.editTextNewUsername);
        passwordEditText = findViewById(R.id.editTextNewPassword);
        signupButton = findViewById(R.id.buttonSignupNew);

        // Set onClickListener for the signup button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignupNewButtonClick(v);
            }
        });
    }

    public void onSignupNewButtonClick(View view) {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String newUsername = usernameEditText.getText().toString();
        String newPassword = passwordEditText.getText().toString();

        long userId = dbHelper.addUser(firstName, lastName, newUsername, newPassword);

        if (userId != -1) {
            Toast.makeText(SignupActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(SignupActivity.this, "Signup failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}


