package com.pbp.ugd11_e_9590.ui.pdf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pbp.ugd11_e_9590.R;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    private Context context;
    private final Mahasiswa[] mhs;

    public MahasiswaAdapter(Mahasiswa[] mhs){
        this.mhs=mhs;
    }

    @NonNull
    @Override
    public MahasiswaAdapter.MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_mahasiswa, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.MahasiswaViewHolder holder, int position) {
            final Mahasiswa listMahasiswa = mhs[position];
            if(position==0){
                holder.txtNo.setText("No.");
            }else{
                holder.txtNo.setText(String.valueOf(mhs[position].getNomor()));
            }
            holder.txtNama.setText(mhs[position].getNama());
            holder.txtNIM.setText(mhs[position].getNim());
    }

    @Override
    public int getItemCount() {
        return mhs.length;
    }

    public static class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private final TextView txtNo;
        private final TextView txtNama;
        private final TextView txtNIM;
        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNo=itemView.findViewById(R.id.tvNomorMahasiswa);
            txtNama=itemView.findViewById(R.id.tvNamaMahasiswa);
            txtNIM=itemView.findViewById(R.id.tvNIMMAhasiswa);
        }
    }
}
