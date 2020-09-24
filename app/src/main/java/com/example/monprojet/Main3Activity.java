package com.example.monprojet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main3Activity extends AppCompatActivity {


    Button btn2,btn3,btn4,btn5,btn6;
    SessionManager sessionManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

       btn2=findViewById(R.id.btn2);
       btn3=findViewById(R.id.btn3);
       btn4=findViewById(R.id.btn4);
       btn5=findViewById(R.id.btn5);
       btn6=findViewById(R.id.btn6);

       sessionManager=new SessionManager(getApplicationContext());


        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main3Activity.this,Main8Activity.class);
                startActivity(intent);



            }
        });

       btn5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Main3Activity.this);

               View mView = getLayoutInflater().inflate(R.layout.deconnexion, null);

               Button btn8 = (Button) mView.findViewById(R.id.btn9);
               Button btn7 = (Button) mView.findViewById(R.id.btn8);

               final AlertDialog dialog ;
               mBuilder.setView(mView);
               dialog = mBuilder.create();

               btn8.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialog.cancel();
                   }
               });

               btn7.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       sessionManager.setlogin(false);
                       Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                       startActivity(intent);

                       finish();

                   }
               });

               dialog.show();



           }
       });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main3Activity.this,Main5Activity.class);
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main3Activity.this,Main4Activity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main3Activity.this,Main7Activity.class);
                 startActivity(intent);

        }
        });
    }

}
