package com.me.tts.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.me.tts.POJO.CustomerInformationPoJo;
import com.me.tts.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<CustomerInformationPoJo>  data;

    public RequestListAdapter (Activity a, ArrayList<CustomerInformationPoJo> d) {
        activity = a;
        data=d;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView=LayoutInflater.from(activity).inflate(R.layout.list_element, parent, false);

        TextView userTxt = convertView.findViewById(R.id.username);
        userTxt.setText(data.get(position).getId()+"");

        TextView questionTxt = convertView.findViewById(R.id.question);
        questionTxt.setText("["+data.get(position).getQuestion()+"]     "+data.get(position).getQuestion());

        TextView time = convertView.findViewById(R.id.date);
        time.setText(data.get(position).getDate());

        TextView date = convertView.findViewById(R.id.time);
        date.setText(data.get(position).getTime());

        return convertView;
    }
}
