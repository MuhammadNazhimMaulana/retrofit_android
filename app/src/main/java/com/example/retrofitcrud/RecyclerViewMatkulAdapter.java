package com.example.retrofitcrud;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewMatkulAdapter extends
RecyclerView.Adapter<RecyclerViewMatkulAdapter.ViewHolder> {

private Context context;
private List<Hasil> results;

public RecyclerViewMatkulAdapter(Context context, List<Hasil> results) {
        this.context = context;
        this.results = results;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_matkul_view, parent,
        false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
        }

@Override
public void onBindViewHolder(ViewHolder holder, int position) {
        Hasil result = results.get(position);
        holder.textViewNamaMatkul.setText(result.getNamaMatkul());
        holder.textViewSks.setText(result.getSks());
        }

@Override
public int getItemCount() {
        return 0;
        }

public class ViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener{
    @BindView(R.id.textNamaMatkul) TextView textViewNamaMatkul;
    @BindView(R.id.textSks) TextView textViewSks;
    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String nama_matkul = textViewNamaMatkul.getText().toString();
        String sks = textViewSks.getText().toString();
        Intent i = new Intent(context, UpdateActivity.class);
        i.putExtra("nama_matkul", nama_matkul);
        i.putExtra("sks", sks);
        context.startActivity(i);
    }
}

}
