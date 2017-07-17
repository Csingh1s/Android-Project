package com.csing1s.stormy.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Csing1s on 6/4/2016.
 */
public class AlertUserAboutError extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)//this method will configure the dialoge  of the activity

    {
        Context context = getActivity();
        AlertDialog.Builder  builder = new AlertDialog.Builder(context).//this method will be called when we create dialoge
        setTitle("Opps! Sorry").
                setMessage("There was an error message,Please Try again").
                setPositiveButton("Ok",null);//pop up on the screen

                    AlertDialog dialog   = builder.create();
        return dialog;

    }



}

