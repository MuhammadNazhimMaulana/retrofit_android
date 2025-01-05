package com.example.retrofitcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MatkulActivity extends AppCompatActivity {

    public static final String URL = "http://192.168.0.105/apiRetrofit/";
    private ProgressDialog progress;

    @BindView(R.id.editTextMatkul) EditText editTextMatkul;
    @BindView(R.id.editTextSks) EditText editTextSks;
    @BindView(R.id.buttonLihatMatkul) Button buttonLihatMatkul;
    @BindView(R.id.buttonLihat) Button buttonLihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matkul);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.buttonMatkul) void matkul() {
        //membuat progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        //mengambil data dari edittext
        String nama_matkul = editTextMatkul.getText().toString();
        String sks = editTextSks.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value_Matkul> call = api.matkul(nama_matkul, sks);
        call.enqueue(new Callback<Value_Matkul>() {

            @Override
            public void onResponse(Call<Value_Matkul> call, Response<Value_Matkul> response) {
                String value_matkul = response.body().getValueMatkul();
                String message_matkul = response.body().getMessageMatkul();
                progress.dismiss();

            }

            @Override
            public void onFailure(Call<Value_Matkul> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(MatkulActivity.this, "Jaringan Error!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        buttonLihatMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MatkulActivity.this, ViewMatkulActivity.class));
            }
        });

        buttonLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MatkulActivity.this, MainActivity.class));
            }
        });
    }
}