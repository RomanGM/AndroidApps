package by.bstu.fit.grm.a5_6.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import by.bstu.fit.grm.a5_6.Base.BaseAbility;
import by.bstu.fit.grm.a5_6.Product.Products;
import by.bstu.fit.grm.a5_6.R;

/**
 * Created by Roman on 28.09.2017.
 */

public class ActivityFirst extends AppCompatActivity implements BaseAbility {

    Spinner spnr;
    Products prd = new Products();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_first);
            Intent intent = getIntent();
            prd.setColor(intent.getIntExtra("color", -1));
            prd.setCount(intent.getIntExtra("count", -1));
            String email = intent.getStringExtra("email");
            String phone = intent.getStringExtra("phone");
            prd.setInfo(email,phone);

            spnr = ((Spinner) findViewById(R.id.spinner));

            if (prd.getCount() != -1) {
                spnr.setSelection(prd.getColor());
            }
        }
        catch (Exception ex){
            Toast toast = Toast.makeText(this, ex.getMessage() + "", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void Next(View v) {
            Intent intent = new Intent(this,ActivitySecond.class);
            intent.putExtra("color", spnr.getSelectedItemPosition());
            intent.putExtra("count", prd.getCount());
            intent.putExtra("email", prd.getEmail());
            intent.putExtra("phone", prd.getPhone());

            startActivity(intent);
    }

    @Override
    public void Prev(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("color", spnr.getSelectedItemPosition());
        intent.putExtra("count", prd.getCount());
        intent.putExtra("email", prd.getEmail());
        intent.putExtra("phone", prd.getPhone());
        startActivity(intent);
    }
}
