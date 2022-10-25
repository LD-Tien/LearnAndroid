package com.ldt.contentprovider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Contact> contactList;

    public ContactAdapter(Context context, int layout, List<Contact> contactList) {
        this.context = context;
        this.layout = layout;
        this.contactList = contactList;
    }

    public ContactAdapter() {}



    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder holder;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new Viewholder();

            //anh xa
            holder.tvName = view.findViewById(R.id.tvName);
            holder.tvSDT = view.findViewById(R.id.tvSDT);
            view.setTag(holder);
        } else {
            holder = (Viewholder) view.getTag();
        }

        //gán giá trị
        Contact contact = contactList.get(i);
        holder.tvName.setText(contact.getName());
        holder.tvSDT.setText(contact.getNumber());

        return view;
    }

    private class Viewholder {
        TextView tvName, tvSDT;
    }
}
