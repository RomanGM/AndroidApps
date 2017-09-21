package by.bstu.fit.grm.lab4;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    CalendarView cal;
    TextView tw ;
    static Map<Date,String> dictionary = new Hashtable<>();
    Calendar currentDate = Calendar.getInstance();
    int year = currentDate.get(Calendar.YEAR);
    int month = currentDate.get(Calendar.MONTH);
    int day = currentDate.get(Calendar.DAY_OF_MONTH);
    Date dt = new Date(year,month,day);
    String jsonGlobal = new String();
    String path = "1.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonGlobal = InternalRead(path);
        dictionary = Deserialize(jsonGlobal);
        if(dictionary==null)dictionary = new Hashtable<>();
        cal = (CalendarView)findViewById(R.id.calendar1);
        tw = (TextView)findViewById(R.id.textView1);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                dt = new Date(i,i1,i2);
                if(dictionary.containsKey(dt)) {
                    tw.setText(dictionary.get(dt));
                    Toast.makeText(getApplicationContext(), "Есть заметка", Toast.LENGTH_SHORT).show();
                }
                else  {
                    tw.setText("");
                    Toast.makeText(getApplicationContext(), "Нет заметки", Toast.LENGTH_SHORT).show();
                }
            }
        });}

    protected void Add(View v1) {
        if(!tw.getText().equals("")){
            dictionary.put(dt,tw.getText().toString());
            InternalWrite(path,Serialize(dictionary));
            Toast.makeText(getApplicationContext(), "Добавлен", Toast.LENGTH_SHORT).show();
        }
    }

    protected void Delete(View v1) {
        if(!tw.getText().equals("")){
            dictionary.remove(dt);
            tw.setText("");
            InternalWrite(path,Serialize(dictionary));
            Toast.makeText(getApplicationContext(), "Удален", Toast.LENGTH_SHORT).show();
        }}

    protected String Serialize(Map<Date,String> dict)
    {
        return new Gson().toJson(dict);
    }

    protected Map<Date,String> Deserialize(String json)
    {
        Type type = new TypeToken<Map<String,String>>(){}.getType();
        Map<String,String> tp1= new Gson().fromJson(json, type);
        Map<Date,String> mp = new Hashtable<>();
        if(tp1!=null)
        {
            for(String str:tp1.keySet()){

            mp.put(new Date(str),tp1.get(str));
        }}
        return mp;
    }

    protected String InternalRead(String path){

        File file = getBaseContext().getFileStreamPath(path);
        if(!file.exists()) {
            File newFile = new File(getBaseContext().getFilesDir(), path);
            return new String();
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getPath()));
            return in.readLine();
        }
        catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Ошибка чтения", Toast.LENGTH_SHORT).show();
            return new String();
        }
    }

    protected void InternalWrite(String path, String json){

        File file = getBaseContext().getFileStreamPath(path);
        if(!file.exists()){
            file = new File(getBaseContext().getFilesDir(), path);
        }
        try {
            PrintWriter out = new PrintWriter(file.getPath());
            out.println(json);
            out.close();
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Ошибка записи", Toast.LENGTH_SHORT).show();
        }
    }
}
