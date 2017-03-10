package com.example.rulop_000.deliverym.Vista;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rulop_000.deliverym.Controlador.AgregarClienteEmpresaWebService;
import com.example.rulop_000.deliverym.Entidad.Cliente;
import com.example.rulop_000.deliverym.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddClienteActivity extends AppCompatActivity implements  View.OnClickListener{

    private RadioButton rbNegocio,rbPersona;
    private RadioGroup  rbMain;
    private TextView tvFecha;
    private EditText etIdEmpleado,etNombreN,etNombreC,etAPaternoC,etAMaternoC,etCP,etDireccion,etColonia,etTelefono,etCorreo,etRFC;
    private Button btnAñadir;
    private Spinner sCiudad;
    private String[] ciudades;
    private Cliente cliente;
    //private String cp="\d{5}";
    //private String email=/^[\w,.,-]+@[\w]+(\.\w+){1,3}$/;
    //private String rfcP="^[A-Z]{4}[0-9]{6}";
    //Private String rfcN="^[A-Z]{3}[0-9]{6}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initCliente();
        cliente = new Cliente("","","","","","",0,"",0,"","","",0) ;
        ciudades= new String[]{"Guadalajara","Tlaquepaque","Tonala","Zapopan"};

        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        etIdEmpleado.setText("ID: "+String.valueOf(preferences.getInt("id",0)));
        sCiudad=(Spinner)findViewById(R.id.sCiudad);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(AddClienteActivity.this,R.layout.support_simple_spinner_dropdown_item,ciudades);

        sCiudad.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbNegocio:
                ANegocio();
                break;
            case R.id.rbPersona:
                APersona();
                break;
            case R.id.btnAñadir:
                if (rbNegocio.isChecked()) {
                    if(     etNombreN.getText().toString().isEmpty()||
                            etDireccion.getText().toString().isEmpty()||
                            etCP.getText().toString().isEmpty()||
                            etColonia.getText().toString().isEmpty()||
                            sCiudad.getSelectedItem().equals(null)||
                            etTelefono.getText().toString().isEmpty()||
                            etCorreo.getText().toString().isEmpty()||
                            etRFC.getText().toString().isEmpty()
                            ){
                        Toast.makeText(AddClienteActivity.this, "Completa todos los  campos", Toast.LENGTH_LONG).show();
                    }
                    else {
                       String s=etRFC.getText().toString();
                       Pattern p=Pattern.compile("^[A-Z]{3}[0-9]{6}");
                        Matcher m = p.matcher(s);

                             if(Integer.valueOf(etCP.getText().toString())<=44000 || Integer.valueOf(etCP.getText().toString())>=45999 ) {
                                 Toast.makeText(AddClienteActivity.this, "El Código Postal es incorrecto", Toast.LENGTH_LONG).show();
                             }
                                 if(etTelefono.length()!=8) {
                                     Toast.makeText(AddClienteActivity.this, "El Telefono es incorrecto", Toast.LENGTH_LONG).show();
                                 }
                              if(Integer.valueOf(etCP.getText().toString())>=44000 && Integer.valueOf(etCP.getText().toString())<=45999&&etTelefono.length()==8) {
                                  if (sCiudad.getSelectedItem().equals("Guadalajara"))
                                      cliente.setCiudad("Guadalajara");
                                  else if (sCiudad.getSelectedItem().equals("Tlaquepaque"))
                                      cliente.setCiudad("Tlaquepaque");
                                  else if (sCiudad.getSelectedItem().equals("Tonala"))
                                      cliente.setCiudad("Tonala");
                                  else if (sCiudad.getSelectedItem().equals("Zapopan"))
                                      cliente.setCiudad("Zapopan");

                                  SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);

                                  cliente = new Cliente(etNombreN.getText().toString(), "", "", "", etDireccion.getText().toString(), etColonia.getText().toString(), Integer.valueOf(etCP.getText().toString()),
                                          sCiudad.getSelectedItem().toString(), Integer.valueOf(etTelefono.getText().toString()), etRFC.getText().toString(),
                                          etCorreo.getText().toString(), tvFecha.getText().toString(), Integer.valueOf(preferences.getInt("id", 0)));
                                  Anadir anadir = new Anadir();
                                  anadir.execute();
                              }
                        }
                }

                else if(rbPersona.isChecked()){
                    if( etIdEmpleado.getText().toString().isEmpty()||
                            etNombreC.getText().toString().isEmpty()||
                            etAPaternoC.getText().toString().isEmpty()||
                            etAMaternoC.getText().toString().isEmpty()||
                            etDireccion.getText().toString().isEmpty()||
                            etCP.getText().toString().isEmpty()||
                            sCiudad.getSelectedItem().equals(null)||
                            etColonia.getText().toString().isEmpty()||
                            etTelefono.getText().toString().isEmpty()||
                            etCorreo.getText().toString().isEmpty()||
                            etRFC.getText().toString().isEmpty()||
                            tvFecha.getText().toString().equals("fecha")
                            ){
                        Toast.makeText(AddClienteActivity.this, "Completa todos los  campos y selecciona la fecha", Toast.LENGTH_LONG).show();
                    }
                    else {

                        if(sCiudad.getSelectedItem().equals("Guadalajara"))
                            cliente.setCiudad("Guadalajara");
                        else if(sCiudad.getSelectedItem().equals("Tlaquepaque"))
                            cliente.setCiudad("Tlaquepaque");
                        else if(sCiudad.getSelectedItem().equals("Tonala"))
                            cliente.setCiudad("Tonala");
                        else if(sCiudad.getSelectedItem().equals("Zapopan"))
                            cliente.setCiudad("Zapopan");

                        cliente = new Cliente("",etNombreC.getText().toString(),etAPaternoC.getText().toString(),etAMaternoC.getText().toString(),
                                              etDireccion.getText().toString(),etColonia.getText().toString(),Integer.valueOf(etCP.getText().toString()),
                                              sCiudad.getSelectedItem().toString(), Integer.valueOf(etTelefono.getText().toString()),etRFC.getText().toString(),
                                              etCorreo.getText().toString(),tvFecha.getText().toString(),Integer.valueOf(etIdEmpleado.getText().toString()));
                        Anadir anadir = new Anadir();
                        anadir.execute();
                    }
                }
                break;
        }
    }
    public void initCliente(){
        rbNegocio=(RadioButton)findViewById(R.id.rbNegocio);
        rbPersona=(RadioButton)findViewById(R.id.rbPersona);
        etIdEmpleado=(EditText)findViewById(R.id.etIdEmpleado);
        etNombreN=(EditText)findViewById(R.id.etNombreN);
        etNombreC=(EditText)findViewById(R.id.etNombreC);
        etAPaternoC=(EditText)findViewById(R.id.etAPaternoC);
        etAMaternoC=(EditText)findViewById(R.id.etAMaternoC);
        etDireccion=(EditText)findViewById(R.id.etDireccion);
        etCP=(EditText)findViewById(R.id.etCP);
        etColonia=(EditText)findViewById(R.id.etColonia);
        sCiudad=(Spinner)findViewById(R.id.sCiudad);
        etTelefono=(EditText)findViewById(R.id.etTelefono);
        etCorreo=(EditText)findViewById(R.id.etCorreo);
        etRFC=(EditText)findViewById(R.id.etRFC);
        tvFecha=(TextView)findViewById(R.id.tvFecha);
        btnAñadir=(Button)findViewById(R.id.btnAñadir);
        rbMain = (RadioGroup)findViewById(R.id.rbMain);
        btnAñadir.setOnClickListener(this);
        rbMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                if (id == R.id.rbNegocio){
                    ANegocio();
                }else if (id == R.id.rbPersona){
                    APersona();
                }

            }
        });
        rbNegocio.setChecked(true);
        ANegocio();
    }
    public void ANegocio(){
        etNombreC.setVisibility(View.GONE);
        etAPaternoC.setVisibility(View.GONE);
        etAMaternoC.setVisibility(View.GONE);
        etNombreN.setVisibility(View.VISIBLE);
    }
    public void APersona(){
        etNombreC.setVisibility(View.VISIBLE);
        etAPaternoC.setVisibility(View.VISIBLE);
        etAMaternoC.setVisibility(View.VISIBLE);
        etNombreN.setVisibility(View.GONE);
    }

    //--------------------------------------------------------------
    //-------------------Clase Anadir Cliente-----------------------
    //--------------------------------------------------------------
    public class Anadir extends AsyncTask<Void, Void, Cliente> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(AddClienteActivity.this);
            progressDialog.setMessage("Espere un momento...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }


        @Override
        protected Cliente doInBackground(Void... params) {
            AgregarClienteEmpresaWebService agregarClienteEmpresaWebService = new AgregarClienteEmpresaWebService();
            Cliente client1e = agregarClienteEmpresaWebService.setClienteEmpresa(cliente);
            return client1e;
        }
        @Override
        protected void onPostExecute(Cliente cliente) {
            super.onPostExecute(cliente);
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            Toast.makeText(getBaseContext(), "Cliente Agregado", Toast.LENGTH_LONG).show();
            finish();

        }
    }

}
