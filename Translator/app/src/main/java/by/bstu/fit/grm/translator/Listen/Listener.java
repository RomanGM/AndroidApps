package by.bstu.fit.grm.translator.Listen;

import android.content.Intent;
import android.speech.RecognizerIntent;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Roman on 03.01.2018.
 */

public class Listener {
    public Intent CreateVoiceIntent() {
        Intent listenIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        listenIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                getClass().getPackage().getName());
        listenIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a word!");
        listenIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        listenIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
        return listenIntent;
    }

    public String GetVoiceInput(int requestCode, int resultCode, Intent intent) {
        if(requestCode== 999 && resultCode == RESULT_OK)
        {
            ArrayList<String> suggestedWords =
                    intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            return suggestedWords.get(0);
        }
        return "";
    }
}