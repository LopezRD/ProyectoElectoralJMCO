package com.facci.proyectojmco;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityJMCO extends AppCompatActivity {
    DBHelper dbSQLITE;

    EditText txtID, txtNombre, txtApellido, txtRecintoElectoral, txtAnoNacimiento;

    Button btnInsertar, btnModificar, btnEliminar, btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jmco);
        dbSQLITE= new DBHelper(this);
    }
    public void ingresarClick(View v){
        txtID=(EditText)findViewById(R.id.txtID);
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtApellido=(EditText)findViewById(R.id.txtApellido);
        txtRecintoElectoral=(EditText)findViewById(R.id.txtRecintoElectoral);
        txtAnoNacimiento=(EditText)findViewById(R.id.txtAnoNacimiento);

          boolean estaInsertado = dbSQLITE.insertar(Integer.parseInt(txtID.getText().toString()),txtNombre.getText().toString(),txtApellido.getText().toString(),txtRecintoElectoral.getText().toString(),Integer.parseInt(txtAnoNacimiento.getText().toString()));
        if  (estaInsertado){
            Toast.makeText(MainActivityJMCO.this,"Datos ingresados exitosamente",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivityJMCO.this,"Ocurrió un error inesperado",Toast.LENGTH_SHORT).show();
        }
    }
    public void verTodosClick(View v){

        Cursor res = dbSQLITE.selectVerTodos();
        if(res.getCount() == 0){
            mostrarMensaje("Error","No hay registros encontrados");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()){
            buffer.append("Id : "+res.getInt(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellido : "+res.getString(2)+"\n");
            buffer.append("Recinto : "+res.getString(3)+"\n\n");
            buffer.append("Nacimiento:"+res.getInt(4)+"\n\n");
        }

        mostrarMensaje("Registros",buffer.toString());
    }
    public void mostrarMensaje(String titulo, String Mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }
    public void Modificar (View v){
        txtID=(EditText)findViewById(R.id.txtID);
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtApellido=(EditText)findViewById(R.id.txtApellido);
        txtRecintoElectoral=(EditText)findViewById(R.id.txtRecintoElectoral);
        txtAnoNacimiento=(EditText)findViewById(R.id.txtAnoNacimiento);
        boolean actualizando=
                dbSQLITE.modificarRegistro
                        (Integer.parseInt(txtID.getText().toString()),
                                txtNombre.getText().toString(),
                                txtApellido.getText().toString(),
                                txtRecintoElectoral.getText().toString(),
                                Integer.parseInt(txtAnoNacimiento.getText().toString())
                        );
        if (actualizando){
            Toast.makeText(MainActivityJMCO.this,"Datos modificados exitosamente",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityJMCO.this,"Ha ocurrido un error inesperado",Toast.LENGTH_SHORT).show();;
        }
    }
    public void Eliminar (View v){
        txtID=(EditText)findViewById(R.id.txtID);
        Integer registrosEliminados=dbSQLITE.eliminarRegistro(Integer.parseInt(txtID.getText().toString()));
        if(registrosEliminados>0){
            Toast.makeText(MainActivityJMCO.this,"Registros eliminados con éxito",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityJMCO.this,"Ha ocurrido un error, no se ha eliminado",Toast.LENGTH_SHORT).show();
        }
    }
}
