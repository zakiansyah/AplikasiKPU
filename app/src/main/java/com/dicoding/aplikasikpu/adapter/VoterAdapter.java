package com.dicoding.aplikasikpu.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.aplikasikpu.DetailVoterActivity;
import com.dicoding.aplikasikpu.ListDataActivity;
import com.dicoding.aplikasikpu.R;
import com.dicoding.aplikasikpu.db.DbHelper;
import com.dicoding.aplikasikpu.model.Voter;

import java.io.Serializable;
import java.util.ArrayList;

public class VoterAdapter extends RecyclerView.Adapter<VoterAdapter.VoterViewHolder> {
    private ArrayList<Voter> listVoter = new ArrayList<>();
    private Activity activity;
    DbHelper dbHelper;

    public VoterAdapter(Activity activity){
        this.activity = activity;
        dbHelper = new DbHelper(activity);
    }

    public ArrayList<Voter> getListVoter(){
        return listVoter;
    }

    public void setListVoter(ArrayList<Voter> listVoter){
        if (listVoter.size() > 0){
            this.listVoter.clear();
        }
        this.listVoter.addAll(listVoter);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VoterAdapter.VoterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voter, parent, false);
        return new VoterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoterAdapter.VoterViewHolder holder, int position) {
        holder.tvNik.setText(listVoter.get(position).getNik());
        holder.tvName.setText(listVoter.get(position).getName());
        holder.cvItem.setOnClickListener((View v) -> {
            Intent detailIntent = new Intent(activity, DetailVoterActivity.class);
            detailIntent.putExtra("voter", (Serializable) listVoter.get(position));
            activity.startActivity(detailIntent);
        });
        holder.btnDelete.setOnClickListener((View v) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            builder.setTitle("Konfirmasi Hapus");
            builder.setMessage("Apakah anda yakin menghapus ?");

            builder.setPositiveButton("YA", (dialog, which) -> {
                dbHelper.deleteUser(listVoter.get(position).getId());
                Toast.makeText(activity, "Hapus Berhasil", Toast.LENGTH_SHORT).show();
                Intent delIntent = new Intent(activity, ListDataActivity.class);
                activity.startActivity(delIntent);
                activity.finish();
            });

            builder.setNegativeButton("TIDAK", (dialog, which) -> dialog.dismiss());

            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    @Override
    public int getItemCount() {
        return listVoter.size();
    }

    public class VoterViewHolder extends RecyclerView.ViewHolder {

        TextView tvNik, tvName;
        CardView cvItem;
        Button btnDelete;
        public VoterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNik = itemView.findViewById(R.id.tv_item_nik);
            tvName = itemView.findViewById(R.id.tv_item_nama);
            cvItem = itemView.findViewById(R.id.cv_item_voter);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
