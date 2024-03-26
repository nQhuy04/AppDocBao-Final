package com.example.appdoctintuc.Adapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctintuc.R;
import com.example.appdoctintuc.model.BaiBao;

import java.util.ArrayList;

public class BaiBaoAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<BaiBao> arrayListBaiBao;


    public BaiBaoAdapter(Context context, int layout, ArrayList<BaiBao> arrayListBaiBao) {
        this.context = context;
        this.layout = layout;
        this.arrayListBaiBao = arrayListBaiBao;
    }

    @Override
    public int getCount() {
        return arrayListBaiBao.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListBaiBao.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView txtTieuDeBB;
        ImageView imgAnhBBItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtTieuDeBB = convertView.findViewById(R.id.txtTieudeBB);
            holder.imgAnhBBItem = convertView.findViewById(R.id.imgAnhBB);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BaiBao baiBao = arrayListBaiBao.get(position);
        holder.txtTieuDeBB.setText(baiBao.getTenBaiBao());
        byte[] anhBaiBao = baiBao.getAnhBaiBao();
        Bitmap bitmap = BitmapFactory.decodeByteArray(anhBaiBao,0,anhBaiBao.length);
        holder.imgAnhBBItem.setImageBitmap(bitmap);
        return convertView;

    }
}