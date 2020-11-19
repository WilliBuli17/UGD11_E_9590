package com.pbp.gd11_x_yyyy.ui.download;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.downloader.Status;
import com.pbp.gd11_x_yyyy.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.File;
import java.util.List;

public class DownloadFragment extends Fragment {
    Button btnStartPertama;
    Button btnCancelPertama;
    Button btnStartKedua;
    Button btnCancelKedua;
    TextView tvProgressPertama;
    TextView tvProgressKedua;
    ProgressBar pb1;
    ProgressBar pb2;
    private static String dirPath;
    int downloadIdPertama, downloadIdKedua;
    private DownloadViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(DownloadViewModel.class);
        View root = inflater.inflate(R.layout.fragment_download, container, false);

        return root;
    }


}