package es.iesnervion.atellez.pruebasharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et1;
    private TextView tv2, tv3;
    private int numero;
    private Button btnVerficiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);

        tv2 = (TextView) findViewById(R.id.tv2);

        tv3 = (TextView) findViewById(R.id.tv3);

        btnVerficiar = (Button) findViewById(R.id.btnVerificar);
        btnVerficiar.setOnClickListener(this);

        //CREACION DE LA SHAREDPREFERENCE
        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String v = String.valueOf(prefe.getInt("puntos", 0));
        tv2.setText(v);
        //CREACION DEL NUMERO ALEATORIO
        numero = 1 + (int) (Math.random() * 10);
    }
    
    @Override
    public void onClick(View v) {
        int valor = Integer.parseInt(et1.getText().toString());
        if (numero == valor) {
            int puntosactual = Integer.parseInt(tv2.getText().toString());
            puntosactual++;
            tv2.setText(String.valueOf(puntosactual));
            tv3.setText("Has acertado!!! Prueba uno nuevo");
            et1.setText("");
            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putInt("puntos", puntosactual);
            editor.commit();

        } else {
            if (valor > numero) {
                tv3.setText("El número introducido es menor que el correcto");
            } else {
                tv3.setText("El número introducido es mayor que el correcto.");
            }
        }
    }
}
