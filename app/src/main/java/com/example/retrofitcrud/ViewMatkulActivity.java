package com.example.retrofitcrud;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewMatkulActivity extends AppCompatActivity {

    public static final String URL = "http://192.168.0.105/apiRetrofit/";
    private List<Hasil> results = new ArrayList<>();
    private RecyclerViewMatkulAdapter viewAdapter;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_matkul);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daftar Matkul");

        viewAdapter = new RecyclerViewMatkulAdapter(this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
        loadDataMatkul();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadDataMatkul() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value_Matkul> call = api.hasil();
        call.enqueue(new Callback<Value_Matkul>() {
            @Override
            public void onResponse(Call<Value_Matkul> call, Response<Value_Matkul> response) {
                String value = response.body().getValueMatkul();
                progressBar.setVisibility(View.GONE);
                    results = response.body().getHasil();
                    viewAdapter = new RecyclerViewMatkulAdapter(ViewMatkulActivity.this,
                            results);
                    recyclerView.setAdapter(viewAdapter);
            }
            @Override
            public void onFailure(Call<Value_Matkul> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataMatkul();
    }
}