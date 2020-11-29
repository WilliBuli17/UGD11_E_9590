package com.pbp.ugd11_e_9590.ui.pdf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pbp.ugd11_e_9590.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterBuku extends RecyclerView.Adapter<AdapterBuku.adapterBukuViewHolder> {

    private List<Buku> bukuList;
    private Context context;
    private View view;

    public AdapterBuku(Context context, List<Buku> bukuList) {
        this.context            = context;
        this.bukuList           = bukuList;
    }

    @NonNull
    @Override
    public AdapterBuku.adapterBukuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_buku, parent, false);
        return new AdapterBuku.adapterBukuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBuku.adapterBukuViewHolder holder, int position) {
        final Buku buku = bukuList.get(position);

        NumberFormat formatter = new DecimalFormat("#,###");
        holder.txtNama.setText(buku.getNamaBuku());
        holder.txtPengarang.setText(buku.getPengarang());
        holder.txtHarga.setText("Rp "+ formatter.format(buku.getHarga()));
        Glide.with(context)
                .load(BukuAPI.URL_IMAGE+buku.getGambar())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.ivGambar);
    }

    @Override
    public int getItemCount() {
        return bukuList.size();
    }

    public class adapterBukuViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtPengarang, txtHarga;
        private ImageView ivGambar;
        private CardView cardBuku;

        public adapterBukuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama         = itemView.findViewById(R.id.tvNamaBuku);
            txtPengarang    = itemView.findViewById(R.id.tvPengarang);
            txtHarga        = itemView.findViewById(R.id.tvHarga);
            ivGambar        = itemView.findViewById(R.id.ivGambar);
            cardBuku        = itemView.findViewById(R.id.cardBuku);
        }
    }
}