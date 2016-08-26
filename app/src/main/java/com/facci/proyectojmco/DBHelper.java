package com.facci.proyectojmco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by juanm on 24/8/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static  final String DB_NOMBRE="CNE_JMCO.db";
    public static final String TABLA_NOMBRE="VOTANTES_JMCO";
    public  static  final  String COL_1="ID_JMCO";
    public  static  final  String COL_2="NOMBRE_JMCO";
    public  static  final  String COL_3="APELLIDO_JMCO";
    public  static  final  String COL_4="RECINTO_ELECTORAL_JMCO";
    public static final String COL_5="NACIMIENTO_JMCO";

    public DBHelper(Context context) {
        super(context, DB_NOMBRE, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create table %s (%s INTEGER PRIMARY KEY, %s TEXT,%s TEXT,%s TEXT, %s INTEGER)",TABLA_NOMBRE,COL_1,COL_2,COL_3,COL_4,COL_5));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s",TABLA_NOMBRE));
        onCreate(db);
    }
    public boolean insertar(int id, String nombre, String apellido, String recintoElectoral, int nacimiento){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recintoElectoral);
        contentValues.put(COL_5,nacimiento);
        long resultado= db.insert(TABLA_NOMBRE, null, contentValues);
        if (resultado==-1){
            return false;
        }
        else{
            return  true;
        }
    }
    public Cursor selectVerTodos(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res=db.rawQuery(String.format("select * from %s",TABLA_NOMBRE),null);
        return  res;
    }
    public boolean modificarRegistro(int id,String nombre,String apellido,String recintoElectoral,int  anoNacimiento){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recintoElectoral);
        contentValues.put(COL_5,anoNacimiento);

        db.update(TABLA_NOMBRE,contentValues,"id = ?",new String []{Integer.toString(id)});
        return true;
    }
    public Integer eliminarRegistro(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLA_NOMBRE,"id = ?",new String[]{Integer.toString(id)});
    }
}
