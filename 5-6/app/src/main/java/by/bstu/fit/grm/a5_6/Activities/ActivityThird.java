package by.bstu.fit.grm.a5_6.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import by.bstu.fit.grm.a5_6.Base.BaseAbility;
import by.bstu.fit.grm.a5_6.Product.Products;
import by.bstu.fit.grm.a5_6.R;

/**
 * Created by Roman on 28.09.2017.
 */

public class ActivityThird extends AppCompatActivity implements BaseAbility {
    Products prd = new Products();
    EditText tw;
    EditText tw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        tw = (EditText)findViewById(R.id.editText);
        tw2 = (EditText)findViewById(R.id.editText2);
        Intent intent = getIntent();
        prd.setColor(intent.getIntExtra("color", -1));
        prd.setCount(intent.getIntExtra("count", -1));
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        prd.setInfo(email,phone);
        if(prd.getEmail()!="")tw.setText(prd.getEmail());
        if(prd.getPhone()!="")tw2.setText(prd.getPhone());
    }

    @Override
    public void Next(View v) {
        Intent intent = new Intent(this, Summary.class);
        intent.putExtra("count", prd.getCount());
        intent.putExtra("color", prd.getColor());
        intent.putExtra("email",tw.getText().toString());
        intent.putExtra("phone", tw2.getText().toString());
        startActivity(intent);
    }

    @Override
    public void Prev(View v) {
        Intent intent = new Intent(this, ActivitySecond.class);
        intent.putExtra("count", prd.getCount());
        intent.putExtra("color", prd.getColor());
        intent.putExtra("email",tw.getText().toString());
        intent.putExtra("phone", tw2.getText().toString());
        startActivity(intent);
    }
}
