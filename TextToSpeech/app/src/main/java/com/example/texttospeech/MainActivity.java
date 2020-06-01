package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity{


    TextToSpeech mtts;
    Button talk;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        talk = findViewById(R.id.talk);
        editText = findViewById(R.id.editText);

        mtts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if(status == TextToSpeech.SUCCESS){

                    int result = mtts.setLanguage(Locale.ENGLISH);

                    if(result == TextToSpeech.LANG_NOT_SUPPORTED){

                        talk.setEnabled(false);
                        Toast.makeText(getApplicationContext(), "Language not supported", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        talk.setEnabled(true);
                    }

                }

                else{

                    Toast.makeText(getApplicationContext(), "Initalization failed", Toast.LENGTH_SHORT).show();
                }


            }
        });

        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();

                mtts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        });



    }

    @Override
    protected void onDestroy() {

        if(mtts != null){
            mtts.stop();
            mtts.shutdown();
        }
        super.onDestroy();
    }

}
