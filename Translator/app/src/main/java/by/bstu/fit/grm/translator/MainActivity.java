package by.bstu.fit.grm.translator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import by.bstu.fit.grm.translator.Listen.Listener;
import by.bstu.fit.grm.translator.Speak.Speaker;
import by.bstu.fit.grm.translator.Translate.Translator;
import by.bstu.fit.grm.translator.Write.Writer;

public class MainActivity extends Activity {

    private Speaker speaker;
    private EditText inputField;
    private EditText outputField;
    private Spinner inputLanguage;
    private Translator translator;
    private Spinner outputLanguage;
    private Listener listener = new Listener();
    private Writer writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        speaker = new Speaker(this);
        writer = new Writer(this);
        inputField = findViewById(R.id.text);
        outputField = findViewById(R.id.text2);
        inputLanguage = findViewById(R.id.spinner);
        outputLanguage = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, speaker.GetSupportLanguage());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputLanguage.setAdapter(adapter);
        outputLanguage.setAdapter(adapter);
    }

    public void inputSpeak(View v) {
        speaker.SetLanguage(new Locale(inputLanguage.getSelectedItem().toString()));
        speaker.SpeakText(inputField.getText().toString());
    }

    public void outputSpeak(View v) {
        speaker.SetLanguage(new Locale(outputLanguage.getSelectedItem().toString()));
        speaker.SpeakText(outputField.getText().toString());
    }

    public void showHistory(View v){
        startActivity(new Intent(this,HistoryActivity.class));
    }

    public void translate(View v) {
        String textToBeTranslated = inputField.getText().toString();
        String languagePair = createPair();
        translator = new Translator(new AsyncResponse(){
            @Override
            public void processFinish(String output) {
                outputField.setText(output);
                writer.writeFile(" output " + output);
            }
        });
        translator.execute(textToBeTranslated,languagePair);
        writer.writeFile(" input " + textToBeTranslated);
    }

    public String createPair() {
        String firstPart = inputLanguage.getSelectedItem().toString();
        String secondPart = outputLanguage.getSelectedItem().toString();
        return firstPart + "-" + secondPart;
    }

    public void listen(View v){
        startActivityForResult(listener.createVoiceIntent(), 999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        String voiceInput = listener.getVoiceInput(requestCode,resultCode,intent);
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