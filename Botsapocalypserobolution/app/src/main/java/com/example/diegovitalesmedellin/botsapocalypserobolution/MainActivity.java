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
    private boolean pv = false, aa = false, in = false, dm = false;

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

            ArrayList<String> text = datos.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Toast.makeText(this, text.get(0), Toast.LENGTH_LONG).show();
            if (text.get(0).equals("Hola") && pv == false) {

                voz.speak("Hola, bienvenido soldado", TextToSpeech.QUEUE_FLUSH, null);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pv = true;

            }

            if (text.get(0).equals("necesito apoyo") && pv == true)
            {

                if (aa == true)
                {

                    voz.speak("Puedes pedir: ,Inmunidad por 15 segundos, Disparo mortal", TextToSpeech.QUEUE_FLUSH, null);

                }

                if (in == true)
                {

                    voz.speak("Puedes pedir: ,Apoyo aereo, Disparo mortal", TextToSpeech.QUEUE_FLUSH, null);

                }

                if (dm == true)
                {

                    voz.speak("Puedes pedir: ,Apoyo aereo, Inmunidad por 15 segundos", TextToSpeech.QUEUE_FLUSH, null);

                }

                else
                {

                    voz.speak("Puedes pedir: ,Apoyo aereo, Inmunidad por 15 segundos, Disparo mortal", TextToSpeech.QUEUE_FLUSH, null);

                }

            }

            if (text.get(0).equals("necesito apoyo") && pv == false)
            {

                voz.speak("Espera, todovia no empezamos", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("apoyo aéreo") && pv == true)
            {

                if (!aa)
                {

                    voz.speak("En camino soldado",TextToSpeech.QUEUE_FLUSH, null);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    aa = true;

                }

                else
                {

                    voz.speak("No puedes, ya lo usaste",TextToSpeech.QUEUE_FLUSH, null);

                }

            }

            if (text.get(0).equals("apoyo aéreo") && pv == false)
            {
                
                voz.speak("Espera, todovia no empezamos", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("inmunidad") && pv == true && in == false)
            {

                voz.speak("Ahora eres invensible por 15 segundos", TextToSpeech.QUEUE_FLUSH, null);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                in = true;

            }

            if (text.get(0).equals("inmunidad") && pv == false && in == false)
            {

                voz.speak("Espera, todovia no empezamos", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("inmunidad") && pv == true && in == true)
            {

                voz.speak("No puedes ser invencible dos veces", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("disparo mortal") && pv == true && dm == false)
            {

                voz.speak("Tienes un solo disparo poderoso, usalo bien", TextToSpeech.QUEUE_FLUSH, null);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dm = true;

            }

            if (text.get(0).equals("disparo mortal") && pv == false && in == false)
            {

                voz.speak("Espera, todovia no empezamos", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("disparo mortal") && pv == true && dm == true)
            {

                voz.speak("Lo siento, ya lo usaste y no hay mas", TextToSpeech.QUEUE_FLUSH, null);

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
