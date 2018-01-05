package by.bstu.fit.grm.translator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import by.bstu.fit.grm.translator.Write.Writer;

public class HistoryActivity extends Activity {

    EditText historyField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        historyField = findViewById(R.id.historyField);
        Writer writer = new Writer(this);
        for (String str:writer.readFile()) {
            historyField.append(str + "\n");
        }
    }
}
