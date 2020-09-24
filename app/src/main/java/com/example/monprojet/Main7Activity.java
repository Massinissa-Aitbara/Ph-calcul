package com.example.monprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main7Activity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed0;
    Button btn1 , btn2;
    public String s1=null;
    basededonner bd=new basededonner(Main7Activity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        ed1=findViewById(R.id.ed1);
        ed2=findViewById(R.id.ed2);
        ed3=findViewById(R.id.ed3);
        ed4=findViewById(R.id.ed4);
        ed0=findViewById(R.id.ed0);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String S=bd.returncolumn(1),d=ed0.getText().toString();
                if (!ed1.getText().toString().isEmpty() && !ed2.getText().toString().isEmpty() && !ed3.getText().toString().isEmpty() && !ed4.getText().toString().isEmpty() && !ed0.getText().toString().isEmpty()) {
                    if (ed1.getText().toString().equals(ed2.getText().toString())){
                        if (S.equals(d)) {
                            bd.modifier(ed1.getText().toString(), ed3.getText().toString(), ed4.getText().toString());
                            onBackPressed();
                            Toast.makeText(Main7Activity.this, "Le mot de pass a été bien enregistré", Toast.LENGTH_LONG).show();
                        }else {

                            Toast.makeText(Main7Activity.this, "Ancien mot de pass incorrect", Toast.LENGTH_LONG).show();

                        }

                    }else {
                        Toast.makeText(Main7Activity.this, "incorrect", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Main7Activity.this, " Il y a un champ vide", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
