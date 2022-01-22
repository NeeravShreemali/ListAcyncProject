package com.aerium.listacyncproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class CustomAdapter extends BaseAdapter {
    Context context;
    JSONArray jsonArray;
    public CustomAdapter(Context context, JSONArray jsonArray){
        this.context = context;
        this.jsonArray = jsonArray;
    }
    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        try{
            return jsonArray.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.single_item, null);

            viewHolder = new ViewHolder();
            viewHolder.txtview = view.findViewById(R.id.txtview);
            viewHolder.txtUrl = view.findViewById(R.id.urlView);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }

        try {
            String View = jsonArray.getJSONObject(i).getString("formula");
            String Url = jsonArray.getJSONObject(i).getString("url");
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
    static class ViewHolder{
        TextView txtview,txtUrl;
    }
}
