package com.example.monprojet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.MediaSessionManager;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    public String password;
    Button btn1,btn2;
    EditText passwords;
    TextView t1;
    SessionManager sessionManager;
    basededonner bd=new basededonner(Main2Activity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn1=(Button) findViewById(R.id.btn1);
        passwords =(EditText) findViewById(R.id.password0);
        btn2=findViewById(R.id.btn2);
        t1=(TextView)findViewById(R.id.t1);

        sessionManager =new SessionManager(getApplicationContext());
        if (bd.returncolumn(1)==null){
            bd.Ajouter5();
        }
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Main2Activity.this);

                View mView = getLayoutInflater().inflate(R.layout.motddepassoublier, null);


                Button btn3 = (Button) mView.findViewById(R.id.btn1);
                Button btn4 = (Button) mView.findViewById(R.id.btn2);
                TextView textView=(TextView) mView.findViewById(R.id.t1);
                final TextView t3=(TextView) mView.findViewById(R.id.t3);
                final EditText editText=(EditText) mView.findViewById(R.id.t2);
                textView.setText(bd.returncolumn(2));


                final AlertDialog dialog ;
                mBuilder.setView(mView);
                dialog = mBuilder.create();


                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!editText.getText().toString().isEmpty()){
                            String D=bd.returncolumn(3);
                            String D1=editText.getText().toString();
                            if (D.equals(D1)){
                                sessionManager.setlogin(true);
                                openActivity();
                            }else {
                                t3.setText("La réponse est incorrect");
                            }
                        }else{
                            t3.setText("saisier la réponse");


                        }


                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });


                dialog.show();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass =passwords.getText().toString();
                password=bd.returncolumn(1);
                if (passwords.getText().toString().isEmpty())
                    t1.setText("saisir le mot de pass ");
                if (pass.equals(password)){
                    sessionManager.setlogin(true);
                    openActivity();
                }else {
                    t1.setText("le mot de pass incorrect ");
                }

            }
        });
        if (sessionManager.getlogin()){
            openActivity();
        }


    }
    public void openActivity(){
        Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
        startActivity(intent);
        finish();
    }
}