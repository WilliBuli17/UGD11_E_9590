package com.pbp.ugd11_e_9590.ui.download;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Status;
import com.pbp.ugd11_e_9590.R;
import com.shashank.sony.fancytoastlib.FancyToast;

public class FileDownloadAdapter extends RecyclerView.Adapter<FileDownloadAdapter.FileDownloadViewHolder> {
    private final Context context;
    private final FileDownload[] fileDownloads;

    public FileDownloadAdapter(Context context, FileDownload[] fileDownloads) {
        this.context = context;
        this.fileDownloads = fileDownloads;
    }

    @NonNull
    @Override
    public FileDownloadAdapter.FileDownloadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_download, parent, false);
        return new FileDownloadAdapter.FileDownloadViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FileDownloadAdapter.FileDownloadViewHolder holder, int position) {
        final FileDownload listFileDownload = fileDownloads[position];
        final String dirPath;


        PRDownloader.initialize(context);
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(context, config);
        dirPath = UtilityPR.getRootDirPath(context);

        holder.tvName.setText(listFileDownload.getFileName());
        holder.btnCancel.setOnClickListener(v -> PRDownloader.cancel(holder.downloadId));
        holder.btnStart.setOnClickListener(v -> {
            if (Status.RUNNING == PRDownloader.getStatus(holder.downloadId)) {
                PRDownloader.pause(holder.downloadId);
                return;
            }
            holder.pb.setIndeterminate(true);
            holder.pb.getIndeterminateDrawable().setColorFilter(Color.BLUE,
                    PorterDuff.Mode.SRC_IN);
            if (Status.PAUSED == PRDownloader.getStatus(holder.downloadId)) {
                PRDownloader.resume(holder.downloadId);
                return;
            }
            holder.downloadId =
                    PRDownloader.download(listFileDownload.getFileUrl(),
                            dirPath, listFileDownload.getFileName())
                            .build()
                            .setOnStartOrResumeListener(() -> {
                                holder.pb.setIndeterminate(false);
                                holder.btnStart.setEnabled(true);
                                holder.btnCancel.setEnabled(true);
                                holder.btnStart.setText("Hentikan");
                                FancyToast.makeText(context,"Download dimulai!",
                                        FancyToast.LENGTH_SHORT,
                                        FancyToast.INFO,
                                        false)
                                        .show();
                            })
                            .setOnPauseListener(() -> {
                                holder.btnStart.setText("Teruskan");
                                FancyToast.makeText(context,"Download dihentikan sementara!",
                                        FancyToast.LENGTH_SHORT,
                                        FancyToast.INFO,
                                        false)
                                        .show();
                            })
                            .setOnCancelListener(() -> {
                                holder.btnStart.setEnabled(true);
                                holder.btnCancel.setEnabled(false);
                                holder.btnStart.setText("Download");
                                holder.tvProgress.setText("");
                                holder.downloadId = 0;
                                holder.pb.setProgress(0);
                                holder.pb.setIndeterminate(false);
                                FancyToast.makeText(context,"File batal didownload !",
                                        FancyToast.LENGTH_LONG,
                                        FancyToast.WARNING,
                                        false)
                                        .show();
                            })
                            .setOnProgressListener(progress -> {
                                holder.tvProgress.setText(UtilityPR.getProgressDisplayLine(progress.currentBytes,
                                        progress.totalBytes));
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                holder.pb.setProgress((int) progressPercent);
                                holder.pb.setIndeterminate(false);
                            })
                            .start(new OnDownloadListener() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void onDownloadComplete() {
                                    holder.btnStart.setEnabled(false);
                                    holder.btnCancel.setEnabled(false);
                                    holder.btnStart.setBackgroundColor(Color.GRAY);
                                    holder.btnCancel.setText("Berhasil");
                                    holder.btnStart.setText("Downloaded");
                                    FancyToast.makeText(context,"File berhasil didownload!",
                                            FancyToast.LENGTH_SHORT,
                                            FancyToast.SUCCESS,
                                            false)
                                            .show();
                                }
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void onError(Error error) {
                                    holder.btnStart.setEnabled(true);
                                    holder.btnCancel.setEnabled(false);
                                    holder.btnStart.setText("Download");
                                    holder.tvProgress.setText("");
                                    holder.downloadId =0;
                                    holder.pb.setIndeterminate(false);
                                    holder.pb.setProgress(0);
                                    FancyToast.makeText(context,"Kesalahan Jaringan!",
                                            FancyToast.LENGTH_LONG,
                                            FancyToast.ERROR,
                                            false)
                                            .show();
                                }
                            });
        });
    }

    @Override
    public int getItemCount() {
        return fileDownloads.length;
    }

    public static class FileDownloadViewHolder extends RecyclerView.ViewHolder {

        private final Button btnStart;
        private final Button btnCancel;
        private final TextView tvProgress;
        private final ProgressBar pb;
        private final TextView tvName;

        private int downloadId = 0;

        public FileDownloadViewHolder(@NonNull View itemView) {
            super(itemView);
            btnStart = itemView.findViewById(R.id.btnStart);
            btnCancel = itemView.findViewById(R.id.btnCancel);
            tvProgress = itemView.findViewById(R.id.tvProgress);
            pb = itemView.findViewById(R.id.pb);
            tvName = itemView.findViewById(R.id.tvNama);
            

        }
    }
}
