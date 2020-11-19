package com.pbp.gd11_x_yyyy.ui.home;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.pbp.gd11_x_yyyy.R;

import java.util.HashSet;
import java.util.Set;

import io.github.bleoo.windowImageView.WindowImageView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView rv;

    //TODO 1 - Ubah XXXX dengan Nama dan YYYYYYYYY dengan NIM pada textview di window0.xml
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        init();

        rv=root.findViewById(R.id.rvHome);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new MyAdapter());

        return root;
    }

    //inisialisasi Fresco
    private void init(){

    }

    //letakkan inner class MyAdapter di sini
}