package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegistroActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email,pass;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth=FirebaseAuth.getInstance();

        email=findViewById(R.id.email_registro);
        pass=findViewById(R.id.password_reistro);
        btnRegistrar=findViewById(R.id.registrar_completo);

        btnRegistrar.setOnClickListener(v->{
            String correo=email.getText().toString();
            String contraseña=pass.getText().toString();

            mAuth.createUserWithEmailAndPassword(correo, contraseña)
                    .addOnCompleteListener(this, task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                            finish();
                        } else {
                            Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
