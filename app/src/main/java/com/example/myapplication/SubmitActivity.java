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

import com.example.myapplication.services.ServiceBuilder;
import com.example.myapplication.services.SubmitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {
    public static final String URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
    private EditText mTvName;
    private EditText mTvEmail;
    private EditText mTvLink;
    private EditText mLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        mTvName = findViewById(R.id.tv_fname);
        mTvEmail = findViewById(R.id.tv_lastname);
        mTvLink = findViewById(R.id.tv_link);
        mLastName = findViewById(R.id.tv_lastname);

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
                if(mTvName.getText().toString().isEmpty() && mTvEmail.getText().toString().isEmpty() &&
                        mTvLink.getText().toString().isEmpty() && mLastName.getText().toString().isEmpty()){
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
                submitForm(v);
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
    }

    private void submitForm(final View v) {
        SubmitService ideaService = ServiceBuilder.builderService(SubmitService.class);
        Call<Void> createRequest = ideaService.postForm(URL, mTvEmail.getText().toString(),
                mTvName.getText().toString(), mLastName.getText().toString(),
                mTvLink.getText().toString());

        createRequest.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if ( response.code() == 200){
                    showSuccessDialog(v);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showFailDialog(v);
            }
        });
    }
    private void showSuccessDialog(View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this,R.style.CustomAlertDialog);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.success_dialog, viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void showFailDialog(View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this,R.style.CustomAlertDialog);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.fail_dialog, viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}