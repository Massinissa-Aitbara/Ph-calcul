package com.example.monprojet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed8, ed5 ,ed6,ed7;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        ed1=(EditText) findViewById(R.id.ed1);
        ed2=(EditText) findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        ed4=(EditText)findViewById(R.id.ed4);
        ed5=(EditText)findViewById(R.id.ed5);
        ed6=(EditText)findViewById(R.id.ed6);
        ed7=(EditText) findViewById(R.id.ed7);
        ed8=(EditText) findViewById(R.id.ed8);

        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ed1.getText().toString().isEmpty() && !ed2.getText().toString().isEmpty() && !ed3.getText().toString().isEmpty() && !ed4.getText().toString().isEmpty() && !ed5.getText().toString().isEmpty() && !ed7.getText().toString().isEmpty() && !ed8.getText().toString().isEmpty()) {
                    if (Double.parseDouble(ed5.getText().toString())<Double.parseDouble(ed6.getText().toString())){
                        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Main5Activity.this);
                        View mView = getLayoutInflater().inflate(R.layout.message_dialog, null);
                        TextView t10 = (TextView) mView.findViewById(R.id.t10);
                        Button btn33 = (Button) mView.findViewById(R.id.btn3);
                        Button btn44 = (Button) mView.findViewById(R.id.btn4);
                        t10.setText("Nom de Médicament= "+ed1.getText().toString()+"\n"+"Nom de laboratoire= "+ed2.getText().toString()+"\n"+"Stabilité= "+ed3.getText().toString()+"\n"+"Concentration initiale= "+ed4.getText().toString()+"\n"+"Concentration minimale "+ed5.getText().toString()+"\n"+"Concentration maximale= "+ed6.getText().toString()+"\n"+"Prix= "+ed8.getText().toString()+"\n"+"Volume de flacon= "+ed7.getText().toString());
                        final AlertDialog dialog ;
                        mBuilder.setView(mView);
                        dialog = mBuilder.create();

                        btn33.setOnClickListener(new View.OnClickListener() {
                          @Override
                            public void onClick(View v) {

                               medicament_model medicament_model;
                               try {
                               medicament_model = new medicament_model(-1, ed1.getText().toString(), ed2.getText().toString(), Double.parseDouble(ed4.getText().toString()), Double.parseDouble(ed5.getText().toString()), Double.parseDouble(ed6.getText().toString()), Integer.parseInt(ed3.getText().toString()),Double.parseDouble(ed7.getText().toString()),Double.parseDouble(ed8.getText().toString()));
                               } catch (Exception e) {
                              Toast.makeText(Main5Activity.this, "Er ", Toast.LENGTH_LONG).show();
                              medicament_model = new medicament_model(-1, "erreur", "erreur", 0, 0, 0, 0,0,0);

                               }
                               basededonner basededonner=new basededonner(Main5Activity.this);
                                final boolean success = basededonner.Ajouter3(medicament_model);
                                Toast.makeText(Main5Activity.this,"success ="+success,Toast.LENGTH_LONG).show();
                                dialog.cancel();
                                ed1.setText("");
                                ed2.setText("");
                                ed3.setText("");
                                ed4.setText("");
                                ed5.setText("");
                                ed6.setText("");
                                ed7.setText("");
                                ed8.setText("");
                              Toast.makeText(Main5Activity.this, "Les données ont été bien enregistré", Toast.LENGTH_LONG).show();


                          }
                        });
                        btn44.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              dialog.cancel();
                          }
                      });
                        dialog.show();
                    }else {
                        Toast.makeText(Main5Activity.this, "La concentration minimale est supérieure à la concentration maximale", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Main5Activity.this, "Il y a un champ vide  ", Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}
