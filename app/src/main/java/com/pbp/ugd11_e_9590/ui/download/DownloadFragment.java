package com.pbp.ugd11_e_9590.ui.download;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pbp.ugd11_e_9590.R;

public class DownloadFragment extends Fragment {
    private DownloadViewModel slideshowViewModel;

    FileDownload[] fileDownloads = new FileDownload[]{
            new FileDownload("Modul.pdf", "https://darmanto.akakom.ac.id/praktikandroid/Modul%201%20Praktikum%20Pemrograman%20Mobile.pdf"),
            new FileDownload("PuntenPecel.jpg", "https://pelangidb.com/pbp/download/punten_pecel.jpg"),
            new FileDownload("PAR.mp3", "https://pelangidb.com/pbp/download/Passionate_Anthem_Roselia.mp3")
    };

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = new ViewModelProvider(this).get(DownloadViewModel.class);
        View root = inflater.inflate(R.layout.fragment_download, container, false);

        RecyclerView rv = root.findViewById(R.id.rvDwnload);
        FileDownloadAdapter adapter = new FileDownloadAdapter(getContext(), fileDownloads);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);

        return root;
    }
}