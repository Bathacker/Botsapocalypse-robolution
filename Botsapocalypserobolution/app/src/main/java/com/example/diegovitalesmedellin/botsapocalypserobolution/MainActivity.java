package com.example.diegovitalesmedellin.botsapocalypserobolution;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{

    private TextToSpeech voz;
    private String nombre;
    Intent datos;
    ArrayList<String> text = datos.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voz = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener()
        {

            @Override
            public void onInit(int status)
            {

                if (status != TextToSpeech.ERROR)
                {

                    voz.setLanguage(Locale.US);

                }

            }

        });

        ImageButton reconoce=(ImageButton) findViewById(R.id.imageButton);
        reconoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Este es el intent del reconocimiento de voz
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//              Especificamos el idioma, en esta ocasión probé con el de Estados Unidos
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //Iniciamos la actividad dentro de un Try en caso surja un error.
                try
                {

                    startActivityForResult(intent, 1);

                }

                catch (ActivityNotFoundException a)
                {

                    Toast.makeText(getApplicationContext(), "Tu dispositivo no soporta el reconocimiento de voz", Toast.LENGTH_LONG).show();

                }

            }

        });

    }

    public void onActivityResult(int requestcode, int resultcode, Intent datos)
    {

// Si el reconocimiento de voz es correcto almacenamos dentro de un array los datos obtenidos.
//Mostramos en pantalla el texto obtenido de la posición 0.
        if (resultcode== Activity.RESULT_OK && datos!=null)
        {

            Toast.makeText(this, text.get(0), Toast.LENGTH_LONG).show();
            if (text.get(0).equals("Hola")) {

                voz.speak("Hola soldado, como te yamas?", TextToSpeech.QUEUE_FLUSH, null);

                text.set(0," ");

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//              Especificamos el idioma, en esta ocasión probé con el de Estados Unidos
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //Iniciamos la actividad dentro de un Try en caso surja un error.
                try
                {

                    startActivityForResult(intent, 1);

                }

                catch (ActivityNotFoundException a)
                {

                    Toast.makeText(getApplicationContext(), "Tu dispositivo no soporta el reconocimiento de voz", Toast.LENGTH_LONG).show();

                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nombre = text.get(0);
                voz.speak("Bienvenido, novato " + nombre, TextToSpeech.QUEUE_FLUSH, null);

            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {

            return true;

        }

        return super.onOptionsItemSelected(item);

    }

}
