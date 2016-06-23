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
    private boolean pv = false;

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

                voz.speak("Hola, bienvenido soldado, sor Rollito un robot hecho por os profesores de inteli i estoy aqui para ayudarte. Este es un proyecto de realidad virtual hecho por los alumnos de programacion avanzada de Intelirobot; en este juego llamado Botsapocalypse robolution debes acabar con todos los robots a tu alredeor, a lo largo del juego estarán apareciendo en diferentes posiciones robots malvados i de dispararan bloke de LEGO, para eso tienes un arma, puedes esquivar i moverte en todo el esspacio pero cuidado a tu alrededor. Puedes pedir apoyo dicendo, necesito apoyo, luego se te dira una lista de comandos.", TextToSpeech.QUEUE_FLUSH, null);
                try {
                    Thread.sleep(42000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                voz.speak("¿quieres saber la historia?, por favor diga, quiero conocer la historia, sino´ diga empezar", TextToSpeech.QUEUE_FLUSH, null);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pv = true;

            }

            if(text.get(0).equals("Quiero conocer la historia") && pv == true)
            {

                voz.speak("Ok, por favor espere", TextToSpeech.QUEUE_FLUSH, null);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                voz.speak("Hace dos años los robots se rebelaron en Inteli,debido a que fueren sobreexplotados, pero pensarann que no debio pasar porque no piensan, pero nooooooo, si piensan y mucho, pero no igualan nuestra inteligencia, excepto DarkDroid el robot mas poderoso e inteligente jamas creado, pero ya tu mision, solo diga empezar", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("quiero conocer la historia") && pv == false)
            {

                voz.speak("Espera, todovia no empezamos", TextToSpeech.QUEUE_FLUSH, null);

            }

            if(text.get(0).equals("empezar") && pv == true)
            {

                voz.speak("Preparate", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("empezar") && pv == false)
            {

                voz.speak("Espera, todovia no empezamos", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("necesito apoyo") && pv == true)
            {

                voz.speak("Puedes pedir: ,Apoyo aereo, Inmunidad por kince segundos, Disparo mortal", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("necesito apoyo") && pv == false)
            {

                voz.speak("Espera, todovia no empezamos", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("apoyo aéreo") && pv == true)
            {

                voz.speak("En camino soldado",TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("apoyo aéreo") && pv == false)
            {
                
                voz.speak("Espera, todovia no empezamos", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("inmunidad") && pv == true)
            {

                voz.speak("Ahora eres invensible por kince segundos", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("inmunidad") && pv == false)
            {

                voz.speak("Espera, todovia no empezamos", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("disparo mortal") && pv == true)
            {

                voz.speak("Tienes un solo disparo poderoso, usalo bien", TextToSpeech.QUEUE_FLUSH, null);

            }

            if (text.get(0).equals("disparo mortal") && pv == false)
            {

                voz.speak("Espera, todovia no empezamos", TextToSpeech.QUEUE_FLUSH, null);

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
