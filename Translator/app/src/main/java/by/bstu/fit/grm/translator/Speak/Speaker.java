package by.bstu.fit.grm.translator.Speak;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import java.util.Locale;

import by.bstu.fit.grm.translator.R;

public class Speaker implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private Locale speakLocale;
    Context context;

    public Speaker(Context context){
        this.textToSpeech = new TextToSpeech(context, this);
        this.speakLocale = new Locale("ru");
        this.context = context;
    }

    public String[] GetSupportLanguage(){
        return context.getResources().getStringArray(R.array.supportLanguages);
    }


    public void SetLanguage(Locale mSpeakLocale){
        this.speakLocale = mSpeakLocale;
        textToSpeech.setLanguage(mSpeakLocale);
    }

    public Locale GetLanguage(){
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
