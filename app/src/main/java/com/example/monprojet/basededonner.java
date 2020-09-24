package com.example.monprojet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class basededonner extends SQLiteOpenHelper {



    public SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.FRANCE);


    public basededonner(@Nullable Context context) {
        super(context, "Les_information.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String table_de_Calculation ="CREATE TABLE  table_calculation  (id  INTEGER PRIMARY KEY AUTOINCREMENT, medicament  TEXT ,  reliquat  TEXT , date  TEXT , stabilite1  TEXT , prix1  TEXT)";
        String deleted_data ="CREATE TABLE  table_suprission  (id  INTEGER PRIMARY KEY AUTOINCREMENT, medicament1  TEXT ,  reliquat1  TEXT , date1  TEXT , stabilite11  TEXT , prix11  TEXT)";
        String table_medicament = " CREATE TABLE   table_medicament  (id  INTEGER PRIMARY KEY AUTOINCREMENT, nommedicament TEXT , nomlabo TEXT, ci TEXT,cma TEXT ,cmi TEXT ,stabilite TEXT ,volume TEXT ,prix TEXT)";
        String table_motdepass = " CREATE TABLE   table_motdepass  (id  INTEGER PRIMARY KEY AUTOINCREMENT, mot_de_pass TEXT ,question TEXT, reponse TEXT)";


        db.execSQL(table_medicament);
        db.execSQL(table_de_Calculation);
        db.execSQL(deleted_data);
        db.execSQL(table_motdepass);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        }

    public boolean Ajouter2(Calculation_model  Calculation_model){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put("medicament", Calculation_model.getT1());
        cv.put("reliquat", Calculation_model.getT2());
        cv.put("date", Calculation_model.getT3());
        cv.put("stabilite1",Calculation_model.getT4());
        cv.put("prix1",Calculation_model.getT5());


        long insert = db.insert("table_calculation", null, cv);
        if (insert == -1){
            return true;
        } else {
            return true;
        }
    }
    public boolean Ajouter3(medicament_model  medicament_model){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put("nommedicament",medicament_model.getNom());
        cv.put("nomlabo",medicament_model.getNom_labo());
        cv.put("ci",medicament_model.getCi());
        cv.put("cma",medicament_model.getCMa());
        cv.put("cmi",medicament_model.getCMi());
        cv.put("stabilite",medicament_model.getStabilite());
        cv.put("volume",medicament_model.getvolume());
        cv.put("prix",medicament_model.getprix());

        long insert = db.insert("table_medicament", null, cv);
        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }
    public void Ajouter5(){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put("mot_de_pass","faculté");
        cv.put("question","Le nom de mon université");
        cv.put("reponse","Ferhat Abbas");

        db.insert("table_motdepass", null, cv);


    }
    public void modifier(String mot,String question,String reponse){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put("mot_de_pass",mot);
        cv.put("question", question);
        cv.put("reponse", reponse);
        db.update("table_motdepass",cv,"id=?",new String[]{"1"});
    }
    public String returncolumn(int position){
        String tab = null;

        String selectQuery = "SELECT * FROM "+"table_motdepass";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                String test;
                test=cursor.getString(0);
                if (test.equals("1")){
                    if (position==1){
                        tab=cursor.getString(1);}
                    if (position==2){
                        tab=cursor.getString(2);}
                    if (position==3){
                        tab=cursor.getString(3);}
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tab;
    }

    public List<String> getllLabels() {

        List<String> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+"table_medicament";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                String nommedicament,nomlabo;
                nommedicament=cursor.getString(1);
                nomlabo=cursor.getString(2);
                list.add(nommedicament+"-"+nomlabo);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;

    }
    public String recherche(String item,int position){
        String tab = null;

        String selectQuery = "SELECT * FROM "+"table_medicament";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                String nommedicament,nomlabo,test;
                nommedicament=cursor.getString(1);
                nomlabo=cursor.getString(2);
                test=String.valueOf(nommedicament+"-"+nomlabo);
                if (test.equals(item)){
                    if (position==1){
                        tab=cursor.getString(3);}
                    if (position==2){
                        tab=cursor.getString(4);}
                    if (position==3){
                        tab=cursor.getString(5);}
                    if (position==4){
                        tab=cursor.getString(6);}
                    if (position==5){
                        tab=cursor.getString(7);}
                    if (position==6){
                        tab=cursor.getString(8);}
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tab;
    }

    public boolean updatereliquat( String nom,String reliquat,String date){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put("medicament",nom);
        cv.put("reliquat", reliquat);
        cv.put("date", date);
        db.update("table_calculation",cv,"medicament=?",new String[]{ nom });
        return true;
    }

    public boolean Ajouter4(String item, String reliquat, String date,String stabilite,String prix) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if ( reliquat != null) {

            cv.put("medicament1",item);
            cv.put("reliquat1", reliquat);
            cv.put("date1", date);
            cv.put("stabilite11",stabilite );
            cv.put("prix11", prix);
            db.insert("table_suprission", null, cv);
            return true;

        }else {
            return false;
        }
    }
    public void deletedatawithdate(String date) throws ParseException {
        String date0;
        Date date1,date2;
        long nbrh=0,stabilite;
        String selectQuery = "SELECT * FROM "+"table_calculation";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                date0=cursor.getString(3);
                stabilite=Long.parseLong(cursor.getString(4));
                date1=simpleDateFormat.parse(date0);
                date2=simpleDateFormat.parse(date);

                nbrh=date2.getTime()-date1.getTime();

                if (!(nbrh<(stabilite*3600000))){
                    Ajouter4(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                    deletedata(cursor.getString(1));
                }

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

    }

    public String recherchedata(String item,int position) {

        String tab = null, test;

        String selectQuery = "SELECT * FROM " + "table_calculation";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                test = String.valueOf(cursor.getString(1));

                if (test.equals(item)) {
                    if (position == 1) {
                        tab = cursor.getString(2);
                    }
                    if (position == 2) {
                        tab = cursor.getString(3);
                    }
                    if (position == 3) {
                        tab = cursor.getString(4);
                    }
                    if (position == 4) {
                        tab = cursor.getString(5);
                    }

                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return tab;
    }
    public double prixdereliquat(String item,int position) {

        double tab = 0;
        String test;

        String selectQuery = "SELECT * FROM " + "table_suprission";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                test=("Id="+cursor.getInt(0)+"\n"+"Nom-nomlabo="+cursor.getString(1)+"\n"+"Reliquat="+cursor.getString(2)+"\n"+"Date="+cursor.getString(3)+"\n"+"Stabilité="+cursor.getString(4)+"\n"+"Prix="+cursor.getString(5));
                if (test.equals(item)) {
                    if (position == 1) {
                        tab = Double.parseDouble(cursor.getString(2));
                    }
                    if (position == 2) {
                        tab = Double.parseDouble(cursor.getString(5));
                    }
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return tab;
    }


    public List<String> getmedicament(){
        List<String> returnlist=new ArrayList<>();

        String selectQuery = "SELECT * FROM "+"table_medicament";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                returnlist.add("Id="+cursor.getInt(0)+"\n"+"Nom="+cursor.getString(1)+"\n"+"Nomlabo="+cursor.getString(2)+"\n"+"Ci="+cursor.getString(3)+"\n"+"Cmi="+cursor.getString(4)+"\n"+"Cma="+cursor.getString(5)+"\n"+"Stabilité="+cursor.getString(6)+"\n"+"Volume de flacon="+cursor.getString(7)+"\n"+"Prix="+cursor.getString(8));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnlist;

    }
    public List<String> getreliquat(){
        List<String> returnlist=new ArrayList<>();
        String selectQuery = "SELECT * FROM "+"table_calculation";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                returnlist.add("Id="+cursor.getInt(0)+"\n"+"Nom-nomlabo="+cursor.getString(1)+"\n"+"Reliquat="+cursor.getString(2)+"\n"+"Date="+cursor.getString(3)+"\n"+"Stabilité="+cursor.getString(4)+"\n"+"Prix="+cursor.getString(5));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnlist;

    }
    public List<String> getreliquatSup(){
        List<String> returnlist=new ArrayList<>();
        String selectQuery = "SELECT * FROM "+"table_suprission";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                returnlist.add("Id="+cursor.getInt(0)+"\n"+"Nom-nomlabo="+cursor.getString(1)+"\n"+"Reliquat="+cursor.getString(2)+"\n"+"Date="+cursor.getString(3)+"\n"+"Stabilité="+cursor.getString(4)+"\n"+"Prix="+cursor.getString(5));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnlist;
    }
    public void deletedata(String item){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("table_calculation", "medicament=?", new String[]{item});

    }
    public void deletedatasup(String item){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("table_suprission", "medicament1=?", new String[]{item});

    }
    public void datamedicament(String item){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("table_medicament", "id=?", new String[]{item});

    }
    public void deletdatareliquat(String item){
        String test=null;
        String selectQuery = "SELECT * FROM "+"table_calculation";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                test=("Id="+cursor.getInt(0)+"\n"+"Nom-nomlabo="+cursor.getString(1)+"\n"+"Reliquat="+cursor.getString(2)+"\n"+"Date="+cursor.getString(3)+"\n"+"Stabilité="+cursor.getString(4)+"\n"+"Prix="+cursor.getString(5));
                if (test.equals(item)){
                    deletedata(cursor.getString(1));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
    public void deletdatareliquatsup(String item){
        String test=null;
        String selectQuery = "SELECT * FROM "+"table_suprission";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                test=("Id="+cursor.getInt(0)+"\n"+"Nom-nomlabo="+cursor.getString(1)+"\n"+"Reliquat="+cursor.getString(2)+"\n"+"Date="+cursor.getString(3)+"\n"+"Stabilité="+cursor.getString(4)+"\n"+"Prix="+cursor.getString(5));
                if (test.equals(item)){
                    deletedatasup(cursor.getString(1));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
    public void deletedatamedicament(String item){
        String test=null;
        String selectQuery = "SELECT * FROM "+"table_medicament";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                test=("Id="+cursor.getInt(0)+"\n"+"Nom="+cursor.getString(1)+"\n"+"Nomlabo="+cursor.getString(2)+"\n"+"Ci="+cursor.getString(3)+"\n"+"Cmi="+cursor.getString(4)+"\n"+"Cma="+cursor.getString(5)+"\n"+"Stabilité="+cursor.getString(6)+"\n"+"Volume de flacon="+cursor.getString(7)+"\n"+"Prix="+cursor.getString(8));
                if (test.equals(item)){
                    datamedicament(cursor.getString(0));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }





}

