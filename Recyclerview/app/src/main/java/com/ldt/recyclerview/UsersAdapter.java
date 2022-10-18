package com.ldt.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersAdapterVh> {
    private List<UserModel> userModelList;
    private Context context;

    public UsersAdapter(List<UserModel> userModelList) {
        this.userModelList = userModelList;
    }

    @NonNull
    @Override
    public UsersAdapter.UsersAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UsersAdapterVh(LayoutInflater.from(context).inflate(R.layout.row_users, null));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UsersAdapterVh holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class UsersAdapterVh extends RecyclerView.ViewHolder {
        TextView tvPrefix;
        TextView tvUsername;
        ImageView imgIcon;
        public UsersAdapterVh(@NonNull View itemView) {
            super(itemView);
            tvPrefix = itemView.findViewById(R.id.prefix);
            tvUsername = itemView.findViewById(R.id.username);
            imgIcon = itemView.findViewById(R.id.imageview);
        }
    }
}
