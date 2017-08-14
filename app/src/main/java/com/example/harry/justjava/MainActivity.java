/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

package com.example.harry.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;
    boolean has_wippedCream=false;
    boolean has_choco=false;
    int prc_forSingleCup=10;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.harry.justjava.R.layout.firstpage);

    }

    /**
     * THis method check for cream
     */
    public void addCream(View view){
        has_wippedCream=((CheckBox) view).isChecked();
    }

    public void addChoco(View view){
        has_choco=((CheckBox) view).isChecked();
    }



    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        if(quantity<0)
            quantity=0;
        if(has_wippedCream && has_choco)
            prc_forSingleCup=20;
        else if(has_wippedCream || has_choco)
            prc_forSingleCup=15;
        EditText mEdit=(EditText)findViewById(R.id.name_feild);
        name=mEdit.getText().toString();
        int price=calculatePrice(quantity,prc_forSingleCup);
        String priceMessage=createOrderSummary(price,name);
        //displayMessage(priceMessage);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order");
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * this method return total payable amount
     * @param quantity number of cups ordered
     * @param percup price of 1 cup
     * @return
     */
    public int  calculatePrice(int quantity,int percup){
        return quantity*percup;
    }

    /**
     *THis method creates summary
     */
    public String createOrderSummary(int price,String name){
        String message=getString(R.string.order_summary_name,name);
        message+="\n"+getString(R.string.has_wippered_cream,has_wippedCream);
        message+="\n"+getString(R.string.has_chocolate,has_choco);
        message+="\n"+getString(R.string.order_quantity,quantity);
        message+="\n"+getString(R.string.order_price,price);
        message+="\n"+getString(R.string.thank_you);

        return message;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(com.example.harry.justjava.R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method counts quantity
     */

    public void increment(View view){
        quantity++;
        display(quantity);
    }
    public void decrement(View view){
        quantity--;
        display(quantity);
    }



    /**
    *
     */

}

