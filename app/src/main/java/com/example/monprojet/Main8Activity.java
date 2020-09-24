package com.example.monprojet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Main8Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Button btn1,btn2,btn3,btn4;
    basededonner bd =new basededonner(Main8Activity.this);;
    List<String> medicament,reliquat,reliquatsup;
    ListView listView;
    DecimalFormat num=new DecimalFormat("0.00");
    String item,datetime;
    public double resultat, prix,volume,dd,bb;
    public int position1;
    public SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.FRANCE);
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);

        Calendar calendar=Calendar.getInstance();
        datetime=simpleDateFormat.format(calendar.getTime());
        final Drawable background=btn1.getBackground();

        listView=findViewById(R.id.listview);
        Medicament();
        btn1.setBackgroundResource(R.color.border);
        listView.setOnItemClickListener(this);



        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setBackgroundResource(R.color.border);
                btn2.setBackground(background);
                btn3.setBackground(background);
                Medicament();
                try {
                    bd.deletedatawithdate(datetime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn2.setBackgroundResource(R.color.border);
                btn1.setBackground(background);
                btn3.setBackground(background);
                Relquat();
                try {
                    bd.deletedatawithdate(datetime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn3.setBackgroundResource(R.color.border);
                btn2.setBackground(background);
                btn1.setBackground(background);
                Reliquatsup();
                try {
                    bd.deletedatawithdate(datetime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        item= (String) parent.getItemAtPosition(position);

        prix=bd.prixdereliquat(item,1);
        volume=bd.prixdereliquat(item,2);


        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Main8Activity.this);

        View mView = getLayoutInflater().inflate(R.layout.supprime, null);

        Button btn8 = (Button) mView.findViewById(R.id.btn9);
        Button btn7 = (Button) mView.findViewById(R.id.btn8);
        TextView txt1=(TextView) mView.findViewById(R.id.t10xvx);

        final AlertDialog dialog ;
        mBuilder.setView(mView);
        dialog = mBuilder.create();

        if (position1==2){
            txt1.setText("Voulez-vous supprimé cette reliquat ?         ");
        }
        if (position1==3){
            prix=bd.prixdereliquat(item,1);
            volume=bd.prixdereliquat(item,2);
            resultat=prix*volume;

            txt1.setText("Voulez-vous supprimé cette reliquat périmé ? \n\nPrix = "+num.format(resultat)+" Da");
        }
        if (position1==1){
            txt1.setText("Voulez-vous supprimé cette médicament ?     ");
        }
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(item);
                dialog.cancel();
            }
        });

        dialog.show();
    }
    public void Reliquatsup(){
        reliquatsup=bd.getreliquatSup();
        arrayAdapter=new ArrayAdapter<String>(Main8Activity.this,android.R.layout.simple_list_item_1,reliquatsup);
        listView.setAdapter(arrayAdapter);
        position1=3;
    }
    public void Relquat(){
        reliquat=bd.getreliquat();
        ArrayAdapter arrayAdapter=new ArrayAdapter<String>(Main8Activity.this,android.R.layout.simple_list_item_1,reliquat);
        listView.setAdapter(arrayAdapter);
        position1=2;
    }
    public void Medicament(){
        medicament=bd.getmedicament();
        ArrayAdapter arrayAdapter=new ArrayAdapter<String>(Main8Activity.this,android.R.layout.simple_list_item_1,medicament);
        listView.setAdapter(arrayAdapter);
        position1=1;
    }
    public void deleteData(String item){
        if (position1==2){
            bd.deletdatareliquat(item);
            Relquat();
        }
        if (position1==3){
            bd.deletdatareliquatsup(item);
            Reliquatsup();
        }
        if (position1==1){
            bd.deletedatamedicament(item);
            Medicament();
        }



    }
}