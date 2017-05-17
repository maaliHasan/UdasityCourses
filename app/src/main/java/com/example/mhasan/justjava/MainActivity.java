package com.example.mhasan.justjava;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    public TextView quantityTextView, priceTextView;
    public int numberOfCoffees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        priceTextView = (TextView) findViewById(R.id.price_text_view);
        numberOfCoffees = Integer.valueOf(quantityTextView.getText().toString());
    }

    /**
     * This method is called when the order button is clicked.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void submitOrder(View view) {
        int price= numberOfCoffees*5;
        display(numberOfCoffees);
        String priceMessage= "That would be $ " +price+ " please ! ";
        priceMessage=priceMessage+"\nThank you ";
        displayPriceMessage(priceMessage );
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        numberOfCoffees=number;
        quantityTextView.setText(String.valueOf(numberOfCoffees));

    }

    /**
     * This method display the given price value on the screen
     */

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayPrice(int number) {
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayPriceMessage(String priceMsg) {
     priceTextView.setText(priceMsg);
    }

    public void increment(View view) {
        int value = Integer.valueOf(quantityTextView.getText().toString());
        display(value + 1);
    }

    public void decrement(View view) {
        int value = Integer.valueOf(quantityTextView.getText().toString());
        display(value - 1);
    }
}