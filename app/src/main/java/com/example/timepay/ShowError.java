package com.example.timepay;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ShowError {
    public static void displayError(Context context, String message) {
        final Dialog dialog = new Dialog(context, R.style.PauseDialog);
        dialog.setContentView(R.layout.error_popup);
        final TextView error = (TextView) dialog.findViewById(R.id.tvErrorMessage);
        final Button ok = (Button) dialog.findViewById(R.id.btnOK);
        error.setText(message);
        dialog.setTitle("Error");

        dialog.show();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static Dialog displayProgressBar(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_bar);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final ProgressBar pbar = (ProgressBar) dialog.findViewById(R.id.pbar);
        //final Button ok= (Button) dialog.findViewById(R.id.btnOK);
        //error.setText(message);
        dialog.setTitle("Error");

        dialog.show();
        return dialog;
    }
/*
    public static Dialog displayProgressBar(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_bar);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final ProgressBar pbar = (ProgressBar) dialog.findViewById(R.id.pbar);
        //final Button ok= (Button) dialog.findViewById(R.id.btnOK);
        //error.setText(message);
        dialog.setTitle("Error");

        dialog.show();
        return dialog;
    }
    public static void removeProgressBar(Context context,String message){
        final Dialog dialog = new Dialog(context,R.style.PauseDialog);
        dialog.setContentView(R.layout.progress_bar);
        final ProgressBar pbar = (ProgressBar) dialog.findViewById(R.id.pbar);

        dialog.dismiss();
    }*/

}