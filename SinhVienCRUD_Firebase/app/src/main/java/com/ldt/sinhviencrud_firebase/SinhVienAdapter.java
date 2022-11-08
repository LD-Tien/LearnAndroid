package com.ldt.sinhviencrud_firebase;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.SinhVienViewHolder>{
    private Context context;
    private List<SinhVien> sinhVienList;
    private List<SinhVien> sinhVienList2;
    private OnSinhVienListener onMovieListener;
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    public SinhVienAdapter(FragmentActivity context, OnSinhVienListener onMovieListener) {
        this.context = context;
        this.onMovieListener = onMovieListener;
    }

    public void setData(List<SinhVien> sinhVienList) {
        this.sinhVienList = sinhVienList;
        this.sinhVienList2 = sinhVienList;
        notifyDataSetChanged(); // bin/ load dữ liệu vào MovieAdapter
    }

    @NonNull
    @Override
    public SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sinhvien,parent,false);
        return new SinhVienViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
        SinhVien sv = sinhVienList.get(position);
        if(sv == null) {
            return;
        }
        holder.tvMSV.setText("MSV: " + sv.getMsv());
        holder.tvHoTen.setText("Họ tên: " +sv.getHoTen());
        holder.tvLHP.setText("LHP: " + sv.getLhp());
        holder.tvDTB.setText("DTB: " + sv.getDtb().toString());

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateSinhVienActivity.class);
                intent.putExtra("SinhVien", sinhVienList.get(position));
                context.startActivity(intent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa sinh viên");
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mData.child("DSSV").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                String msv = snapshot.getValue(SinhVien.class).getMsv();
                                if(sinhVienList.get(position).getMsv().equals(msv)) {
                                    mData.child("DSSV").child(snapshot.getKey()).removeValue()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                                                    return;
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                                    return;
                                                }
                                            });
                                }
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(sinhVienList != null)
            return sinhVienList.size();
        return 0;
    }

    public class SinhVienViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvMSV, tvHoTen, tvLHP, tvDTB;
        private ImageButton btnDelete, btnUpdate;
        OnSinhVienListener onSinhVienListener;
        public SinhVienViewHolder(@NonNull View itemView, OnSinhVienListener onMovieListener) {
            super(itemView);
            this.onSinhVienListener = onMovieListener;
            tvMSV = itemView.findViewById(R.id.tvMSV);
            tvHoTen = itemView.findViewById(R.id.tvHoTen);
            tvLHP = itemView.findViewById(R.id.tvLHP);
            tvDTB = itemView.findViewById(R.id.tvDTB);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onSinhVienListener.onSinhVienClick(getAdapterPosition());
        }
    }

    public interface OnSinhVienListener {
        void onSinhVienClick(int position);
    }

    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length() == 0) {
                    filterResults.count = sinhVienList2.size();
                    filterResults.values = sinhVienList2;
                } else {
                    String searchChr = charSequence.toString().toLowerCase();
                    List<SinhVien> resultData = new ArrayList<>();
                    for(SinhVien sv: sinhVienList2) {
                        if(sv.getHoTen().toLowerCase().contains(searchChr)){
                            resultData.add(sv);
                        }
                        filterResults.count = resultData.size();
                        filterResults.values = resultData;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                sinhVienList = (List<SinhVien>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

}
