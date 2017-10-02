package by.bstu.fit.grm.a5_6.Product;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Roman on 29.09.2017.
 */

public class Products {

         int count=-1;
         int color=-1;
         String phone;
         String email;

    public  void setCount(int Count) {
        count = Count;
    }

    public  void setColor(int Color) {
        color = Color;
    }

    public  void setInfo(String Email, String Phone) {
        email = Email;
        phone = Phone;
    }

    public  int getCount() {
        return count;
    }

    public  int getColor() {
        return color;
    }

    public  String getPhone() {  return phone;  }

    public  String getEmail() {
        return email;
    }
}
