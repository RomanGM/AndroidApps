package by.bstu.fit.grm.a5_6.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import by.bstu.fit.grm.a5_6.Base.BaseAbility;
import by.bstu.fit.grm.a5_6.Product.Products;
import by.bstu.fit.grm.a5_6.R;

/**
 * Created by Roman on 28.09.2017.
 */

public class ActivitySecond extends AppCompatActivity implements BaseAbility {
    SeekBar bar;
    TextView tw;
    Products prd = new Products();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bar = (SeekBar) findViewById(R.id.bar);
        tw = ((TextView) findViewById(R.id.textView));
        Intent intent = getIntent();
        prd.setColor(intent.getIntExtra("color", -1));
        prd.setCount(intent.getIntExtra("count", -1));
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        prd.setInfo(email,phone);

        if(prd.getCount()!=-1) bar.setProgress(prd.getCount());

        tw.setText("Count: " + bar.getProgress());
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progresValue, boolean fromUser) {
                tw.setText("Count: " + progresValue);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void Next(View v) {
        Intent intent = new Intent(this, ActivityThird.class);
        intent.putExtra("count",bar.getProgress());
        intent.putExtra("color", prd.getColor());
        intent.putExtra("email", prd.getEmail());
        intent.putExtra("phone", prd.getPhone());
        startActivity(intent);
    }

    @Override
    public void Prev(View v) {
        Intent intent = new Intent(this, ActivityFirst.class);
        intent.putExtra("count",bar.getProgress());
        intent.putExtra("color", prd.getColor());
        intent.putExtra("email", prd.getEmail());
        intent.putExtra("phone", prd.getPhone());
        startActivity(intent);
    }
}
