package com.example.timepay;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.GroupedInputFormatWatcher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class GeneralPublicRegistration extends ActionBarActivity implements View.OnClickListener {

    private static final int CAPTURE_IMAGE_FROM_CAMERA = 0;
    private static final int LOAD_IMAGE_FROM_GALLERY = 1;
    EditText fullName, cardName, panNumber, address, cardNumber;
    Button continueBtn;
    TextView expiryMonth, expiryYear, addAnotherCard;
    Intent builderIntent;
    ListView cardList;
    ArrayAdapter<String> adapter;
    List<String> cardNumberList = new ArrayList<String>();
    String[] cArray = {};
    List<CardDetails> cardDetailsList=new ArrayList<CardDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_public_registration);

        initialize();

        cardNumberList = new ArrayList<String>(Arrays.asList(cArray));
        adapter = new ArrayAdapter<String>(this, R.layout.list_cardnumbers, cardNumberList);
        cardList.setAdapter(adapter);
        expiryMonth.setOnClickListener(this);
        expiryYear.setOnClickListener(this);
        continueBtn.setOnClickListener(this);
        addAnotherCard.setOnClickListener(this);
        /*DatePickerDialog datePickerDialog=new DatePickerDialog(this, listener, year, month, day);
        DatePicker datepicker=datePickerDialog.getDatePicker();
        //datep.removeViewAt(0);
        LinearLayout v1=(LinearLayout)datepicker.getChildAt(0);
        LinearLayout v2=(LinearLayout)v1.getChildAt(0);
        View v3=v2.getChildAt(1);
        v3.setVisibility(View.GONE);
        Toast.makeText(this, "" + v3.getClass().getName() + "\ncount " + v2.getChildCount(), 1).show();
        datePickerDialog.show();*/

        //To populate the Card Name from Full Name
        fullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    //Toast.makeText(getApplicationContext(), "got unfocus", Toast.LENGTH_LONG).show();
                    cardName.setEnabled(true);
                    cardName.setText("");
                    cardName.setText(fullName.getText());
                    cardName.setEnabled(false);
                } else {
                    //Toast.makeText(getApplicationContext(), "got the focus", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initialize() {
        expiryYear = (TextView) findViewById(R.id.tvExpiryYear);
        expiryMonth = (TextView) findViewById(R.id.tvExpiryMonth);
        addAnotherCard = (TextView) findViewById(R.id.tvAddAnotherCard);
        fullName = (EditText) findViewById(R.id.etFullName);
        cardName = (EditText) findViewById(R.id.etCardFullName);
        panNumber = (EditText) findViewById(R.id.etPANNumber);
        address = (EditText) findViewById(R.id.etAddress);
        cardNumber = (EditText) findViewById(R.id.etCardNumber);
        cardName = (EditText) findViewById(R.id.etCardFullName);
        continueBtn = (Button) findViewById(R.id.bContinue);
        //cardList = (ListView) findViewById(R.id.lvCards);
        cardNumber.addTextChangedListener(new GroupedInputFormatWatcher(cardNumber));

        cardList = (ListView)findViewById(R.id.lvCards);  // your listview inside scrollview
        cardList.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        //ApplyInputFilters applyFilters = new ApplyInputFilters(getString(R.string.AddressCharacterFilter));
        //address.setFilters(new InputFilter[]{applyFilters});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_general_public_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if (view == expiryMonth) {

            final CharSequence[] mnth = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
            AlertDialog.Builder builder = new AlertDialog.Builder(GeneralPublicRegistration.this);
            builder.setTitle("Select Month");
            builder.setItems(mnth, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(getApplicationContext(), "You have selected  " + mnth[which], Toast.LENGTH_LONG).show();
                    expiryMonth.setText(mnth[which]);
                }
            });
            builder.setInverseBackgroundForced(true);
            builder.create();
            builder.show();
        }
       else if (view == expiryYear) {

            Integer currentYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
            String[] yr = new String[20];
            for (int x = 0; x < 20; x++) {
                yr[x] = currentYear + x + "";
            }
            final CharSequence[] yearToDisplay = yr;
            AlertDialog.Builder builder = new AlertDialog.Builder(GeneralPublicRegistration.this);
            builder.setTitle("Select Year");
            builder.setItems(yearToDisplay, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(getApplicationContext(), "You have selected  " + yr[which], Toast.LENGTH_LONG).show();
                    expiryYear.setText(yearToDisplay[which]);
                }
            });
            builder.setInverseBackgroundForced(true);
            builder.create();
            builder.show();
        }
       else if (view == addAnotherCard) {

            try {
                Validator validator = new Validator();
                validator.validateGPR(fullName.getText() + "", address.getText() + "", panNumber.getText() + "");
                validator.validateCardEmptyDetails(cardName.getText() + "", cardNumber.getText() + "", expiryMonth.getText() + "", expiryYear.getText() + "");
                validator.validateExpiryDate(expiryMonth.getText() + "", expiryYear.getText() + "");
                final Dialog dialog = new Dialog(GeneralPublicRegistration.this);
                dialog.setContentView(R.layout.dialogbox_add_anoather_card);
                final TextView cardNameDialog = (TextView) dialog.findViewById(R.id.tvCardNameDialogBox);
                final EditText cardNoDialog = (EditText) dialog.findViewById(R.id.edCardNoDialogBox);
                final EditText expiryMonthDialog = (EditText) dialog.findViewById(R.id.edExpiryMonthDialogBox);
                final EditText expiryYearDialog = (EditText) dialog.findViewById(R.id.edExpiryYearDialogBox);
                final Button cancel = (Button) dialog.findViewById(R.id.btnCancelDialogForAddCard);
                final Button ok = (Button) dialog.findViewById(R.id.btnOKDialogForAddCard);
                cardNoDialog.addTextChangedListener(new GroupedInputFormatWatcher(cardNoDialog));
                cardNameDialog.setText(cardName.getText());
                dialog.setTitle("Card Details");
                dialog.show();
                // if decline button is clicked, close the custom dialog
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Validator validator = new Validator();
                        CardDetails cardDetails=new CardDetails();
                        try {
                            Log.i("GPR", "hello" + expiryMonthDialog.getText() + "       " + expiryYearDialog.getText() + "");
                            validator.validateCardEmptyDetails(cardNameDialog.getText() + "", cardNoDialog.getText() + "", expiryMonthDialog.getText() + "", expiryYearDialog.getText() + "");
                            validator.validateCardNumber(cardNoDialog.getText() + "");
                            validator.validateExpiryDate(expiryMonthDialog.getText() + "", expiryYearDialog.getText() + "");
                            cardNumberList.add(cardNoDialog.getText() + "");
                            adapter.notifyDataSetChanged();
                            adapter.notifyDataSetInvalidated();
                            dialog.dismiss();
                            cardDetails.setCardName(cardNameDialog.getText() + "");
                            cardDetails.setCardNumber(cardNoDialog.getText() + "");
                            cardDetails.setCardExpiryMonth(expiryMonthDialog.getText() + "");
                            cardDetails.setCardExpiryYear(expiryYearDialog.getText() + "");
                            cardDetailsList.add(cardDetails);
                            for (int i = 0; i < cardDetailsList.size(); i++) {
                                Log.i("test",cardDetailsList.get(i).getCardName()+"   "+cardDetailsList.get(i).getCardNumber());
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        //removeItemFromList(position);
                        deleteItemFromList(position);
                    }
                });
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
            }
        }
        else if (view == continueBtn) {
            Log.i("GPR", expiryYear.getText() + "" + expiryMonth.getText());
            Validator validator = new Validator();
            try {
                validator.validateGPR(fullName.getText() + "", address.getText() + "", panNumber.getText() + "");
                validator.validateCardEmptyDetails(cardName.getText() + "", cardNumber.getText() + "", expiryMonth.getText() + "", expiryYear.getText() + "");
                validator.validateExpiryDate(expiryMonth.getText() + "", expiryYear.getText() + "");
                CardDetails cardDetails=new CardDetails();
                cardDetails.setCardName(cardName.getText()+"");
                cardDetails.setCardNumber(cardNumber.getText() + "");
                cardDetails.setCardExpiryMonth(expiryMonth.getText() + "");
                cardDetails.setCardExpiryYear(expiryYear.getText() + "");
                cardDetailsList.add(cardDetails);
                for (int i = 0; i < cardDetailsList.size(); i++) {
                    Log.i("test",cardDetailsList.get(i).getCardName()+"   "+cardDetailsList.get(i).getCardNumber());
                }
                Toast.makeText(getApplicationContext(), "Completed Successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void deleteItemFromList(final int position) {
        final int deletePosition = position;
        AlertDialog.Builder alert = new AlertDialog.Builder(GeneralPublicRegistration.this);
        alert.setTitle("Delete");
        alert.setMessage("Do you want delete this card ?");
        alert.setPositiveButton("YES", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TOD O Auto-generated method stub

                // main code on after clicking yes
                cardNumberList.remove(deletePosition);
                cardDetailsList.remove(cardDetailsList.get(position));
                adapter.notifyDataSetChanged();
                adapter.notifyDataSetInvalidated();

                for (int i = 0; i < cardDetailsList.size(); i++) {
                    Log.i("test",cardDetailsList.get(i).getCardName()+"   "+cardDetailsList.get(i).getCardNumber());
                }


            }
        });
        alert.setNegativeButton("CANCEL", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
