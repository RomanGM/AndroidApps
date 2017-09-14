package by.bstu.fit.grm.calculator;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{

    Calculator calc = new Calculator();
    TextView display;
    TextView result;
    String currentClick;
    String x,y;
    String code;
    boolean isX = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x= new String("");
        y = new String("");
        code = new String();
        display = (TextView)findViewById(R.id.textView1);
        result = (TextView)findViewById(R.id.textView2);
        calc.AddFunc("+",(Float a, Float b)->a+b);
        calc.AddFunc("-",(Float a, Float b)->a-b);
        calc.AddFunc("/",(Float a, Float b)->a/b);
        calc.AddFunc("*",(Float a, Float b)->a*b);

        calc.AddFunc("±"  ,(Float a)->a*(-1));
        calc.AddFunc("√",(Float a)->  new Float(Math.sqrt(a)));
        calc.AddFunc("mod",(Float a)->new Float(Math.abs(a)));
        calc.AddFunc("sin",(Float a)->new Float(Math.sin(a)));
        calc.AddFunc("cos",(Float a)->new Float(Math.cos(a)));

        calc.AddFunc("pi",()->"3.14");
        calc.AddFunc(".",()->".");
    }

    public void Click(View v){
        if(!x.equals(""))
        {
            String temp = new String(code);
            code = ((Button) findViewById(v.getId())).getText().toString();
            if(!y.equals(""))
            {
                result.setText(calc.GetFunc(temp,Float.parseFloat(x),Float.parseFloat(y)).toString());
                if(code.equals("="))
                {
                    display.setText("");
                    x = new String("");
                    code = new String("");
                    isX = false;
                }
                else
                {
                    x = result.getText().toString();
                    display.setText("" +x + code);
                    isX= true;
                }
                y = new String("");
            }
            else if(!temp.equals(""))
            {
                display.setText(display.getText().toString().substring(0,display.getText().length()-1) + code);
            }
            else
            {
                display.append(code);
                isX=true;
            }
        }
    }

    public void Click2(View v){
        currentClick = ((Button) findViewById(v.getId())).getText().toString();

        if(!isX)
        {
            if(x.equals("") && currentClick.equals(".")) {  currentClick = "0" + currentClick;    }
            x += currentClick;
        }
        else if(!code.equals(""))
        {
            if(y.equals("") && currentClick.equals(".")) {   currentClick = "0" + currentClick;     }
            y += currentClick;
        }
        display.append(currentClick);
    }

    public void Click3(View v)
    {
        currentClick = ((Button) findViewById(v.getId())).getText().toString();
        if(!x.equals("") && y.equals(""))
        {
            x=calc.GetFunc(currentClick,Float.parseFloat(x)).toString();
            result.setText(currentClick + display.getText() + "=" + x);
            display.setText(x);
            isX=true;
        }
    }
}
