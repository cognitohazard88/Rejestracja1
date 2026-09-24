package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText editTextImie, editTextNazwisko, editTextEmail, editTextHaslo;
    Button buttonZarejestruj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextImie = findViewById(R.id.editTextImie);
        editTextNazwisko = findViewById(R.id.editTextNazwisko);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextHaslo = findViewById(R.id.editTextHaslo);
        buttonZarejestruj = findViewById(R.id.buttonZarejestruj);

        buttonZarejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringImie = editTextImie.getText().toString().trim();
                String stringNazwisko = editTextNazwisko.getText().toString().trim();
                String stringEmail = editTextEmail.getText().toString().trim();
                String stringHaslo = editTextHaslo.getText().toString().trim();

                if(stringImie.isEmpty() || stringNazwisko.isEmpty() || stringEmail.isEmpty() || stringHaslo.isEmpty()){
                    Toast.makeText(MainActivity.this, "Uzupełnij wszystkie pola", Toast.LENGTH_SHORT).show();
                    return;
                };

                if(!stringEmail.contains("@") || !stringEmail.contains(".")){
                    Toast.makeText(MainActivity.this, "Podaj poprawny adres email", Toast.LENGTH_SHORT).show();
                    return;
                };

                Pattern pattern1 = Pattern.compile("[a-z]");
                Pattern pattern2 = Pattern.compile("[A-Z]");
                Pattern pattern3 = Pattern.compile("[!@#$%&*]");
                Matcher matcher1 = pattern1.matcher(stringHaslo);
                Matcher matcher2 = pattern2.matcher(stringHaslo);
                Matcher matcher3 = pattern3.matcher(stringHaslo);
                String problem = "Hasło musi mieć ";

                if(stringHaslo.length()<8){
                    problem += "co najmniej 8 znaków ";
                }if(!matcher1.find()){
                    problem += "małą literę ";
                }if(!matcher2.find()){
                    problem += "dużą literę ";
                }if(!matcher3.find()){
                    problem += "znak specjalny";
                };


                if(stringHaslo.length()>=8 && matcher1.find() && matcher2.find() && matcher3.find()){
                    Toast.makeText(MainActivity.this, "Dane są poprawne", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(MainActivity.this, problem, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}