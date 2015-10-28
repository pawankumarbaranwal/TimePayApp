package com.example.timepay;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Validator {

    String emailRegex;
    Boolean email;


    public String validateAccountDetatis(String emailAddress, String phoneNumber) {

        String message = "Completed";
        email = emailValidate(emailAddress);

        if (emailAddress.isEmpty() || email==false) {
            message = "Email Address is not valid";
        } else {
            if (phoneNumber.isEmpty()) {
                message = "Phone Number cannot be empty";

            }
            else if(phoneNumber.length() != 10)
            {
                message="Phone number should be 10 digit";
            }

        }
        return message;
    }

    public void validateGPR(String fullName,
                              String address, String panNumber) throws Exception {

        String message = "Completed";
        if (fullName.isEmpty()) {
            throw new Exception("Full Name cannot be empty");
        } else if (address.isEmpty()) {
            throw new Exception("Address cannot be empty");
        } else if (panNumber.isEmpty()) {
            throw new Exception("Pan Number cannot be empty");
        }
    }

    public String validateVendorRegistration(String companyName,
                                             String shopName,
                                             String accountNumber,
                                             String ifscCode,
                                             String panNo) {
        String message="Completed";
        if(companyName.isEmpty()) {
            message = "Company Name cannot be empty";
        } else if (shopName.isEmpty()) {
            message = "Shop name cannot be empty";
        } else if (accountNumber.isEmpty()) {
            message = "Account Number cannot be empty";
        } else if (ifscCode.isEmpty()) {
            message = "IFSC Codecannot be empty";
        } else if (panNo.isEmpty()) {
            message = "Pan No cannot be empty";
        }
        return  message;
    }

    public String validatePrivilageVendorRegistration(String companyName,
                                                      String shopName,
                                                      String accountNumber,
                                                      String ifscCode,
                                                      String panNo) {
        String message="Completed";
        if(companyName.isEmpty()) {
            message = "Company Name cannot be empty";
        } else if (shopName.isEmpty()) {
            message = "Shop name cannot be empty";
        } else if (accountNumber.isEmpty()) {
            message = "Account Number cannot be empty";
        } else if (ifscCode.isEmpty()) {
            message = "IFSC Codecannot be empty";
        } else if (panNo.isEmpty()) {
            message = "Pan No cannot be empty";
        }
        return  message;
    }

    public Boolean emailValidate(String EmailAddress)
    {
        emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return EmailAddress.matches(emailRegex);
    }


    public void validateExpiryDate(String expiryMonth, String expiryYear) throws Exception{
        Calendar calendar=Calendar.getInstance();
        int currentMonthInInteger=Integer.parseInt(new SimpleDateFormat("MM").format(calendar.getTime()));
        int currentYearInInteger=Integer.parseInt(new SimpleDateFormat("yyyy").format(calendar.getTime()));
        int expiryMonthInInteger=Integer.parseInt(expiryMonth);
        int expiryYearInInteger=Integer.parseInt(expiryYear);


        Log.i("ExpiryDateRelatedData","ExpiryMonth :"+expiryMonthInInteger+" ExpiryYear :"+expiryYearInInteger);
        Log.i("CurrentDateRelatedData","CurrentMonth :"+currentMonthInInteger+" CurrentYear :"+currentYearInInteger);
        Log.i("Data",(expiryMonthInInteger<currentMonthInInteger)+"");
        Log.i("Data",(expiryYearInInteger==currentYearInInteger)+"");
        Log.i("Data",((expiryMonthInInteger<currentMonthInInteger)&&(expiryYearInInteger==currentYearInInteger))+"");

        if (((expiryMonthInInteger<currentMonthInInteger)&&(expiryYearInInteger==currentYearInInteger))||
                (expiryYearInInteger<currentYearInInteger)||
                (expiryYearInInteger-currentYearInInteger>20)||
                (expiryMonthInInteger>12)){
            throw new Exception("Please Enter correct Expiry date");
        }
    }

    public void validateCardEmptyDetails(String cardName, String cardNumber, String expiryMonth, String expiryYear) throws Exception {

        if(cardName.isEmpty()) {
            throw new Exception("Please Enter Card Name");
        } else if (cardNumber.isEmpty()) {
            throw new Exception("Please Enter Card Number");
        } else if ((expiryMonth.isEmpty())||(expiryMonth.equals("Month"))) {
            throw new Exception("Please Enter Expiry Month");
        } else if ((expiryYear.isEmpty())||(expiryYear.equals("Year"))) {
            throw new Exception("Please Enter Expiry Year");
        }
    }
    public void validateCardNumber(String cardNumber) throws Exception {
        if (cardNumber.length()<16){
            throw new Exception("Invalid card no.");
        }
    }
}
