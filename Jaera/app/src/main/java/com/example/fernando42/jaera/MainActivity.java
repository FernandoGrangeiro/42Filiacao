package com.example.fernando42.jaera;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText ed1;
    public static EditText nome;
    private TelephonyManager tm;
    private AlertDialog ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        ed1 = (EditText) findViewById(R.id.em1);
        nome = (EditText) findViewById(R.id.nome);
        Button bt1 = (Button) findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder adt = new AlertDialog.Builder(MainActivity.this);
                if(ed1.getText().length() == 0 ||ed1.getText().length()<15  ){
                    adt.setMessage("O campo de Email está inválido");

                }else{
                    if(nome.getText().length() == 0){
                        adt.setMessage("O campo de Nome está vazio");

                    }else{
                        String tel = tm.getLine1Number();
                        String ema1 = ed1.getText().toString();
                        String nom = nome.getText().toString();
                        adt.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mandaEmail();
                            }
                        });
                        adt.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                        adt.setMessage("Nome: " + nom + "\nEmail: " + ema1 + "\nTelefone: " + tel);
                    }
                }
                ad=adt.create();
                ad.show();
            }
        });
    }
    private void mandaEmail () {
        String ema1 = ed1.getText().toString();
        MandaEmail me=new MandaEmail(this,ema1);
        me.execute();
    }
}
