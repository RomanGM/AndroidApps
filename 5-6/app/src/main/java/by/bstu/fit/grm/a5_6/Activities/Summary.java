package by.bstu.fit.grm.a5_6.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import by.bstu.fit.grm.a5_6.Base.BaseAbility;
import by.bstu.fit.grm.a5_6.Product.Products;
import by.bstu.fit.grm.a5_6.R;

/**
 * Created by Roman on 29.09.2017.
 */

public class Summary extends AppCompatActivity implements BaseAbility {

    TextView tw4,tw1,tw2,tw3;
    Products prd = new Products();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        Intent intent = getIntent();
        prd.setColor(intent.getIntExtra("color", -1));
        prd.setCount(intent.getIntExtra("count", -1));
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        prd.setInfo(email,phone);
        tw1 = (TextView) findViewById(R.id.textView);
        tw2 = (TextView) findViewById(R.id.textView2);
        tw3 = (TextView) findViewById(R.id.textView3);
        tw4 = (TextView) findViewById(R.id.textView4);
        tw2.append(prd.getCount()+"");
        tw1.append(prd.getColor()+"");
        tw3.append(prd.getEmail()+"");
        tw4.append(prd.getPhone()+"");
    }
    @Override
    public void Next(View v) {
        try{
            SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS products (count INTEGER, color INTEGER, email TEXT,  phone TEXT)");
            db.execSQL("INSERT INTO products VALUES ("+prd.getCount()+","+prd.getColor()+",'" +prd.getEmail() + "','" +prd.getPhone()+"');");
            Toast.makeText(this,"Insert",Toast.LENGTH_LONG).show();
            db.close();
            prd = new Products();
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
        Intent intent = new Intent(this, ActivityThird.class);
        intent.putExtra("count", prd.getCount());
        intent.putExtra("color", prd.getColor());
        intent.putExtra("email", prd.getEmail());
        intent.putExtra("phone", prd.getPhone());
        startActivity(intent);
    }
}
