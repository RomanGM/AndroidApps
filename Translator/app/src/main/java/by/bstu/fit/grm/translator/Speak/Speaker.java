package by.bstu.fit.grm.translator.Speak;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by Roman on 03.01.2018.
 */

public class Speaker implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private Locale speakLocale;

    public Speaker(Context context){
        this.textToSpeech = new TextToSpeech(context, this);
        this.speakLocale = new Locale("ru");
    }

    public void SetInputLanguage(Locale mSpeakLocale){
        this.speakLocale = mSpeakLocale;
        textToSpeech.setLanguage(mSpeakLocale);
    }

    public Locale GetInputLanguage(){
        return speakLocale;
    }

    public void SpeakText(String text){
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

    public void Destroy(){
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    @Override
    public void onInit(int status) {

    }
}
