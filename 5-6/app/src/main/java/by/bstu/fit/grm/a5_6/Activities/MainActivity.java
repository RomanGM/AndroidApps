package by.bstu.fit.grm.a5_6.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import by.bstu.fit.grm.a5_6.Base.BaseAbility;
import by.bstu.fit.grm.a5_6.R;

public class MainActivity extends AppCompatActivity implements BaseAbility {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void Next(View v) {
        Intent intent = new Intent(this, ActivityFirst.class);
        startActivity(intent);
    }

    @Override
    public void Prev(View v) {
        Intent intent = new Intent(this, Bd.class);
        startActivity(intent);
    }
}
