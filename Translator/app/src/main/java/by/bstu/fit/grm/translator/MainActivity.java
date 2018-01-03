package by.bstu.fit.grm.translator;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import by.bstu.fit.grm.translator.Listen.Listener;
import by.bstu.fit.grm.translator.Speak.Speaker;
import by.bstu.fit.grm.translator.Translate.Translator;

public class MainActivity extends Activity {

    private Speaker speaker;
    private Translator translator;
    private EditText inputField;
    private EditText outputField;
    private Spinner inputLanguage;
    private Spinner outputLanguage;
    private Listener listener = new Listener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        speaker = new Speaker(this);
        inputField = findViewById(R.id.text);
        outputField = findViewById(R.id.text2);
        inputLanguage = findViewById(R.id.spinner);
        outputLanguage = findViewById(R.id.spinner2);
        translator = new Translator(new AsyncResponse(){
            @Override
            public void processFinish(String output) {
                outputField.setText(output);
            }
        });
    }

    public void InputSpeak(View v) {
        speaker.SpeakText(inputField.getText().toString());
    }

    public void OutputSpeak(View v) {
        speaker.SpeakText(outputField.getText().toString());
    }

    public void Translate(View v) {
        String textToBeTranslated = inputField.getText().toString();
        String languagePair = CreatePair();
        translator.execute(textToBeTranslated,languagePair);
    }

    public String CreatePair()
    {
        String firstPart = inputLanguage.getSelectedItem().toString();
        String secondPart = outputLanguage.getSelectedItem().toString();
        return firstPart + "-" + secondPart;
    }

    public void Listen(View v){
        startActivityForResult(listener.CreateVoiceIntent(), 999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        String voiceInput = listener.GetVoiceInput(requestCode,resultCode,intent);
        inputField.append(voiceInput);
        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onDestroy() {
        speaker.Destroy();
        super.onDestroy();
    }

    public interface AsyncResponse {
        void processFinish(String output);
    }
}