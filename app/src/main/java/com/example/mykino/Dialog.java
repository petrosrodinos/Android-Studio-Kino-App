package com.example.mykino;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment {

    private Dialoglistener listener;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog,null);

        builder.setView(view)

                .setNegativeButton("ΣΥΝΕΧΕΙΑ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.apply("cont");
                    }
                }).setPositiveButton("ΑΠΟΤΕΛΕΣΜΑΤΑ", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.apply("result");
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (Dialoglistener) context;
        } catch (ClassCastException e) {
           throw new ClassCastException(context.toString()+"implements example dialog listener");
        }
    }

    public interface  Dialoglistener{
        void apply(String name);
    }



}
