package br.com.ilines.futeonline.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DialogMessage {

    public static void show(Context useThis, String message) {
        // final Dialog dialog = new Dialog(this); // Context, this, etc.
        //dialog.setContentView(R.layout.activity_config);
        // dialog.setTitle(message);
        // dialog.show();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(useThis);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

        alertDialogBuilder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
