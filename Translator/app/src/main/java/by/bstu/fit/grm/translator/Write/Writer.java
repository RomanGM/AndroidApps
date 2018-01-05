package by.bstu.fit.grm.translator.Write;


import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_WORLD_READABLE;

public class Writer {

    private String fileName = "history.txt";
    private Context context;

    public Writer(Context context){
        this.context = context;
    }

    public void writeFile(String str) {
        try {
            FileOutputStream os  = context.openFileOutput(fileName, MODE_APPEND | MODE_WORLD_READABLE );
            os.write((getToday() + " - " + str + "\n").getBytes());
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getToday(){
        Date presentTime_Date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(presentTime_Date);
    }

    public List<String> readFile() {
        List<String> content = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader( context.openFileInput(fileName)));
            String str = "";
            while ((str = br.readLine()) != null) {
                content.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
