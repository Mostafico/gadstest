package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SubmitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        final EditText tvName = findViewById(R.id.tv_fname);
        final EditText tvEmail = findViewById(R.id.tv_lastname);
        final EditText tvLink = findViewById(R.id.tv_link);
        final EditText lastName = findViewById(R.id.tv_lastname);

        Button submitBtn = findViewById(R.id.submit_btn);
        ImageButton backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SubmitActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvName.getText().toString().isEmpty() && tvEmail.getText().toString().isEmpty() &&
                        tvLink.getText().toString().isEmpty() && lastName.getText().toString().isEmpty()){
                    Toast.makeText(SubmitActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }else{
                    showSureDialog(v);
                }
            }
        });
    }

    private void showSureDialog(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this,R.style.CustomAlertDialog);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.sure_dialog, viewGroup, false);
        Button buttonOk=dialogView.findViewById(R.id.btn_yes);
        ImageButton btnClose = dialogView.findViewById(R.id.btn_exit);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
        ;
    }
}