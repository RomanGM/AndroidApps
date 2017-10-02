package by.bstu.fit.grm.a5_6.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import by.bstu.fit.grm.a5_6.Base.BaseAbility;
import by.bstu.fit.grm.a5_6.R;

/**
 * Created by Roman on 30.09.2017.
 */

public class Bd extends AppCompatActivity implements BaseAbility{

    TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bd);
        tw = (TextView)findViewById(R.id.textView);
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        Cursor query = db.rawQuery("SELECT * FROM products;", null);
        Toast.makeText(this,query.getCount()+"",Toast.LENGTH_LONG).show();
        if(query.moveToFirst()){
            do{
                int count = query.getInt(0);
                int color = query.getInt(1);
                String email = query.getString(2);
                String phone = query.getString(3);

                tw.append("\nEmail: " + email + " phone: " + phone + "Color: " + color +"\n");
            }
            while(query.moveToNext());
        }
        query.close();
        db.close();
    }

    @Override
    public void Next(View v) {
        try{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        catch (Exception ex)
        {
            Log.e("12312312",ex.getMessage());
        }
    }

    @Override
    public void Prev(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
