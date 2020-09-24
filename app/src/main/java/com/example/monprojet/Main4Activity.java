package com.example.monprojet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Main4Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn1,btn2,btn4,btn5;
    EditText ed1,ed2,ed3,ed4,ed6;
    Spinner spinner1;
    public String datetime,item;
    public String preciddent="0",message;
    DecimalFormat num=new DecimalFormat("0.00");
    public SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.FRANCE);

    basededonner basededonner=new basededonner(Main4Activity.this);
    public double  resultat=0,ci=0,cma=0,cmi=0,volume_flacon=0, surface_corporelle=0,r1=0,r2=0,r3=0,dose=0, posologee=0,volume_final=0,prix=0,Relliquat=0;
    public int stabilite=0;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);

        ed1=findViewById(R.id.ed1);
        ed2=findViewById(R.id.ed2);
        ed3=findViewById(R.id.ed3);
        ed4=findViewById(R.id.ed4);
        ed6=findViewById(R.id.ed6);

        spinner1 = findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(this);
        remplirspinnerdata();
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main4Activity.this,Main6Activity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String poche="";
                int a=0,b=0;

                Calendar calendar=Calendar.getInstance();
                datetime=simpleDateFormat.format(calendar.getTime());

                try {
                    basededonner.deletedatawithdate(datetime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (!ed1.getText().toString().isEmpty() && !ed2.getText().toString().isEmpty() && !ed3.getText().toString().isEmpty() && !ed4.getText().toString().isEmpty() && !ed6.getText().toString().isEmpty() && item!=null){

                    preciddent="0";
                    r2=Double.parseDouble(ed3.getText().toString());
                    r3=Double.parseDouble(ed4.getText().toString());

                    r1=(r2*r3)/3600;
                    surface_corporelle=(double) Math.sqrt(r1);


                   ci=Double.parseDouble(basededonner.recherche(item,1));
                   cma=Double.parseDouble(basededonner.recherche(item,3));
                   cmi=Double.parseDouble(basededonner.recherche(item,2));
                   stabilite=Integer.parseInt(basededonner.recherche(item,4));
                   volume_flacon=Double.parseDouble(basededonner.recherche(item,5));
                   prix=Double.parseDouble(basededonner.recherche(item,6));


                    posologee=Double.parseDouble(ed6.getText().toString());

                    dose= surface_corporelle*posologee;

                    if (dose>=(cmi*250) && dose<=(cma*250)){
                        a=1;
                    }
                    if (dose>=(cmi*500) && dose<=cma*500){
                        b=1;
                    }
                    if (a==1 && b==1){
                        poche="250 ml et 500 ml";
                    }else{
                       if (a==1 && b==0){
                        poche="250 ml";
                       }else {
                          if (a==0 && b==1){
                          poche="500 ml";
                         }else {
                              poche=(String.valueOf((int)(dose/cma)+" <= poche <= "+(int) (dose/cmi)));
                               }
                         }
                      }


                    double volume_flacon_1=0;
                    int y=0,j=0;

                    volume_final=dose/ci;
                    double volume_final2=volume_final;

                    String d=basededonner.recherchedata(item,1);

                    if (d==null){

                        for (int i=1;volume_flacon_1<=volume_final;i++) {
                            volume_flacon_1 = volume_flacon_1 + volume_flacon;
                            y=i;
                        }
                        Relliquat=(y*volume_flacon)-volume_final;
                        j=y-1;
                        resultat=volume_flacon-Relliquat;
                        message=(String.valueOf(j)+" flacon de volume "+volume_flacon+" ml\n\n   et "+num.format(resultat)+" ml dans "+String.valueOf(y)+" ème flacon");


                    }else {

                        double F=Double.parseDouble(d);
                        preciddent=String.valueOf(num.format(F));
                        resultat=volume_flacon-Relliquat;

                        if(volume_final>F){
                            volume_final=volume_final-F;
                            for (int i=1;volume_flacon_1<=volume_final;i++) {
                                volume_flacon_1 = volume_flacon_1 + volume_flacon;
                                y=i;
                            }
                            Relliquat=(y*volume_flacon)-volume_final;
                            j=y-1;
                            message=(String.valueOf(j)+" flacon de volume "+volume_flacon+" ml\n\n   et "+num.format(resultat)+" ml dans "+String.valueOf(y)+" ème flacon");

                        }else {
                            if (volume_final==F){
                                volume_final=0;
                                Relliquat=0;
                                y=0;
                                basededonner.deletedata(item);
                            }else {
                                Relliquat=F-volume_final;
                                y=0;
                                resultat=0;
                                j=0;
                                message=(num.format(volume_final)+" dans le reliquat precident");
                            }
                        }

                    }


                    final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Main4Activity.this);

                    View mView = getLayoutInflater().inflate(R.layout.message_dialog, null);

                    TextView t10 = (TextView) mView.findViewById(R.id.t10);

                    Button btn3 = (Button) mView.findViewById(R.id.btn3);
                    Button btn4 = (Button) mView.findViewById(R.id.btn4);

                    if (y==1){
                        message=(num.format(volume_final)+" ml dans un flacon de "+volume_flacon+" ml");
                    }


                    t10.setText("1) Le nom médicament : "+item+"\n\n2) La dose : "+num.format(dose)+" mg\n\n"+"3) Le volume finale : "+num.format(volume_final2)+" ml\n\n"+"4) Le reliquat précident : "+preciddent+" ml\n\n"+"5) On utilise : "+message+"\n\n"+"4) Nouveau reliquat : "+num.format(Relliquat) +" ml\n\n"+"6) Poche : "+poche+"\n");


                    final AlertDialog dialog ;
                    mBuilder.setView(mView);
                    dialog = mBuilder.create();


                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (basededonner.recherchedata(item,1) == null) {
                                Calculation_model Calculation_model;
                                try {
                                    Calculation_model = new Calculation_model(-1, item, String.valueOf(Relliquat), datetime, String.valueOf(stabilite), String.valueOf(prix));
                                } catch (Exception e) {
                                    Calculation_model = new Calculation_model(-1, null, null, null, null, null);
                                }

                                basededonner.Ajouter2(Calculation_model);
                            }else {
                                basededonner.updatereliquat(item,String.valueOf(Relliquat),datetime);
                            }
                            dialog.cancel();
                            ed1.setText("");
                            ed2.setText("");
                            ed3.setText("");
                            ed4.setText("");
                            ed6.setText("");
                            Toast.makeText(Main4Activity.this, "Les données ont été bien enregistré", Toast.LENGTH_LONG).show();

                        }
                    });
                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });


                    dialog.show();

                }else {
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remplirspinnerdata();
            }
        });

    }

    public void remplirspinnerdata() {
        basededonner db = new basededonner(Main4Activity.this);
        List<String> labels = db.getllLabels();
        ArrayAdapter<String> data = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,labels);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(data);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item= parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
