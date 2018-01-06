package by.bstu.fit.grm.translator.Translate;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

import by.bstu.fit.grm.translator.MainActivity;

public class Translator extends AsyncTask<String, Void, String> {

    private static final String YANDEX_KEY = "trnsl.1.1.20171226T163610Z.7a5982a7860226f3.142137a440919dc9f1a4e221744a3593f7b0bb0e";
    private static final String YANDEX_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + YANDEX_KEY;
    private static final String SPACE_KEY_FOR_WEB = "%20";
    private static final String REGULAR_EXPRESSION_FOR_SPACES = "[\\s|\\u00A0]+";
    private MainActivity.AsyncResponse delegate;
    private String request;
    private String result;
    private String response;
    private HttpClient httpclient = new DefaultHttpClient();

    public Translator(MainActivity.AsyncResponse response){
        this.delegate = response;
        this.result = "";
        this.response = "";
    }

    @Override
    protected String doInBackground(String... params) {
        String textToBeTranslated = params[0];
        String languagePair = params[1];
        if(!textToBeTranslated.equals("") && !textToBeTranslated.equals(null)) {
            Log.e("123123","PreCreate");
            request = createRequestString(textToBeTranslated,languagePair);
            Log.e("123123","PreSend");
            result = sendRequestString(request);
        }
        Log.e("123123","PreParse");
        return parseTranslateResult(result);
    }

    private String createRequestString(String textToBeTranslated, String languagePair){
        String url = YANDEX_URL + "&text=" + textToBeTranslated
                + "&lang=" + languagePair;
        return url.replaceAll(REGULAR_EXPRESSION_FOR_SPACES, SPACE_KEY_FOR_WEB);
    }

    private String sendRequestString(String requestString){
        try {
            HttpGet httpget = new HttpGet(requestString);
            HttpResponse serverResponse = httpclient.execute(httpget);
            if (serverResponse.getStatusLine().getStatusCode() == 200) {
                response = EntityUtils.toString(serverResponse.getEntity());
            }
            return response.trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String parseTranslateResult(String result){
        if(!result.equals(null)){
            try {
                result = result.substring(result.indexOf('[') + 1, result.indexOf("]"));
                result = result.substring(1, result.length()-1);
            }
            catch (Exception ex){
                result = "";
            }
        }
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}