package com.ldt.sqlitecrude_sv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ldt.sqlitecrude_sv.R;
import com.ldt.sqlitecrude_sv.model.SinhVien;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    private Context context;
    private List<SinhVien> list;

    public SinhVienAdapter(Context context, List<SinhVien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_sinhvien_item, null);
        }

        TextView tvIdSinhVien = view.findViewById(R.id.tvIdSinhVien);
        TextView tvTenSinhVien = view.findViewById(R.id.tvTenSinhVien);
        TextView tvLHP = view.findViewById(R.id.tvLHP);
        TextView tvDTB = view.findViewById(R.id.tvDTB);

        SinhVien sv = list.get(i);
        tvIdSinhVien.setText("ID: " + sv.getId());
        tvTenSinhVien.setText("Ten: " + sv.getTen());
        tvLHP.setText("LHP: "+sv.getLop());
        tvDTB.setText("DTB: " +sv.getDtb().toString());
        return view;
    }
}
