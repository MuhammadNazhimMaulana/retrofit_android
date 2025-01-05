package com.example.retrofitcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

public class UpdateMatkulActivity extends AppCompatActivity {

    public static final String URL = "http://192.168.0.105/apiRetrofit/";
    private ProgressDialog progress;


    @BindView(R.id.editTextNamaMatkul) EditText editTextNamaMatkul;
    @BindView(R.id.editTextSks) EditText editTextSks;
    @OnClick(R.id.buttonUbah) void ubah_matkul() {

        //membuat progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();
        //mengambil data dari edittext
        String nama_matkul = editTextNamaMatkul.getText().toString();
        String sks = editTextSks.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value_Matkul> call = api.ubah_matkul(nama_matkul, sks);
        call.enqueue(new Callback<Value_Matkul>() {
            @Override
            public void onResponse(Call<Value_Matkul> call, Response<Value_Matkul> response) {
                String value_matkul = response.body().getValueMatkul();
                String message_matkul = response.body().getMessageMatkul();
                progress.dismiss();
                if (value_matkul.equals("1")) {
                    Toast.makeText(UpdateMatkulActivity.this, message_matkul,
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateMatkulActivity.this, message_matkul,
                            Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Value_Matkul> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(UpdateMatkulActivity.this, "Jaringan Error!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_matkul);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ubah Data Matkul");

        Intent intent = getIntent();
        String nama_matkul = intent.getStringExtra("nama_matkul");
        String sks = intent.getStringExtra("sks");

        editTextNamaMatkul.setText(nama_matkul);
        editTextSks.setText(sks);
    }

}