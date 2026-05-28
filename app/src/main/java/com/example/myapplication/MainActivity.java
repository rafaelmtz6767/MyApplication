package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import com.google.firebase.auth.FirebaseAuth;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText usuario, pass;
    private FirebaseAuth mAuth;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth=FirebaseAuth.getInstance();
        usuario = findViewById(R.id.et_user);
        pass = findViewById(R.id.et_pass);
        boton = findViewById(R.id.button);
        TextView tvRegistro= findViewById(R.id.registro);

        tvRegistro.setOnClickListener(v->{
           Intent intent= new Intent(MainActivity.this, RegistroActivity.class);
           startActivity(intent);
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioIngresado = usuario.getText().toString().trim();
                String passIngresado = pass.getText().toString().trim();

                if(usuarioIngresado.isEmpty()){

                    Toast.makeText(MainActivity.this, "tu usuarion esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(passIngresado.isEmpty()){

                    Toast.makeText(MainActivity.this, "tu contraseña está vacio", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(usuarioIngresado,passIngresado)
                        .addOnCompleteListener(MainActivity.this, task ->{
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(MainActivity.this, InicioActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
finish();
                                Intent intent= new Intent(MainActivity.this, InicioActivity.class);
                                startActivity(intent);
                                finish();
                            }




                        });



            }
        });

    }
}


