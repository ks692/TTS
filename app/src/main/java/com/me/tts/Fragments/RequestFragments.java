package com.me.tts.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.me.tts.Adapters.RequestListAdapter;
import com.me.tts.POJO.CustomerInformationPoJo;
import com.me.tts.R;

import java.util.ArrayList;

public class RequestFragments extends Fragment {

    ListView listView;
    ProgressBar loader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_requests, container, false);

        listView=rootView.findViewById(R.id.requestListView);
        loader = rootView.findViewById(R.id.loader);

        listView.setEmptyView(loader);

        if(isNetworkAvailable())
        {
            Toast.makeText(getActivity(), "Yes Internet Connection", Toast.LENGTH_LONG).show();

            ArrayList<CustomerInformationPoJo> data=new ArrayList<>();
            for(int i=0;i<20;i++)
            {
                data.add(new CustomerInformationPoJo("What is dell?",522,"20-08-2018","10:40 PM","Warranty"));
            }

            RequestListAdapter adapter = new RequestListAdapter(getActivity(), data);
            listView.setAdapter(adapter);

        }
        else{
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

        return rootView;

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }




}
