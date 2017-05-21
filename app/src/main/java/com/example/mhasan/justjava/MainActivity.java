package com.example.mhasan.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    public TextView quantityTextView, priceTextView;
    public EditText name;
    public static final int CREAM_PRICE = 1;
    public static final int CHOCOLATE_PRICE = 2;
    public CheckBox checkBox_1, checkBox_2;
    public int numberOfCoffees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        numberOfCoffees = Integer.valueOf(quantityTextView.getText().toString());
        name = (EditText) findViewById(R.id.name);
        checkBox_1 = (CheckBox) findViewById(R.id.whipped_Cream_box);
        checkBox_2 = (CheckBox) findViewById(R.id.chocolate_Cream_box);
    }

    /**
     * This method is called when the order button is clicked.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)

    public void submitOrder(View view) {
        int price = numberOfCoffees * 5;
        boolean hasWhippedCream = checkBox_1.isChecked();
        boolean hasChocolate = checkBox_2.isChecked();
        if (hasWhippedCream) {
            price += CREAM_PRICE * numberOfCoffees;
        } else if (hasChocolate) {
            price += CHOCOLATE_PRICE * numberOfCoffees;
        }
        displayQuantity(numberOfCoffees);
        orderSummery(price, hasWhippedCream, hasChocolate);

    }

    public void orderSummery(int price, boolean state1, boolean state2) {
        String priceMessage = "Name :" + name.getText();
        priceMessage = priceMessage + "\n That would be $ " + price + " please ! ";
        String boxState = Boolean.toString(state1);
        priceMessage = priceMessage + "\n Add whipped cream?" + boxState;
        priceMessage = priceMessage + "\n Add chocolate ?" + Boolean.toString(state2);
        priceMessage = priceMessage + "\nThank you ";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        String Name=  name.getText().toString();
        Log.d(Name, "orderSummery: ");
        intent.putExtra(Intent.EXTRA_SUBJECT, Name) ;
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
      //  displayPriceMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        numberOfCoffees = number;
        quantityTextView.setText(String.valueOf(numberOfCoffees));
    }

    private void displayPriceMessage(String priceMsg) {
        priceTextView.setText(priceMsg);
    }

    public void increment(View view) {
        int value = Integer.valueOf(quantityTextView.getText().toString()) + 1;
        if (value > 100) {
            Toast.makeText(getBaseContext(), "U can't order more than 100  coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(value);
    }

    public void decrement(View view) {
        int value = Integer.valueOf(quantityTextView.getText().toString()) - 1;
        if (value < 1) {
            Toast.makeText(getBaseContext(), "U can't order less than one coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(value);
    }




}