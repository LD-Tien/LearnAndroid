package com.ldt.sinhviencrud_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListSinhVienActivity extends AppCompatActivity implements SinhVienAdapter.OnSinhVienListener {
    private FloatingActionButton btnAddSV;
    private DatabaseReference mData;
    private List<SinhVien> sinhVienList;
    private RecyclerView recyclerView;
    private SinhVienAdapter sinhVienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sinh_vien);
        anhXa();

        sinhVienList = new ArrayList<>();
        mData = FirebaseDatabase.getInstance().getReference();

        sinhVienList = getDSSV();
        sinhVienAdapter = new SinhVienAdapter(this, this);
        sinhVienAdapter.setData(sinhVienList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(sinhVienAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        btnAddSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListSinhVienActivity.this, AddSinhVienActivity.class);
                startActivity(intent);
            }
        });


    }

    private List<SinhVien> getDSSV() {
        List<SinhVien> dssv = new ArrayList<>();
        mData.child("DSSV").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sinhVienList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    dssv.add(dataSnapshot.getValue(SinhVien.class));
                }
                sinhVienAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return dssv;
    }

//    private void createData() {
//        List<SinhVien> sinhVienList = new ArrayList<>();
//        sinhVienList.add(new SinhVien("2050531200309", "Lê Đức Tiên", "122LTTD01", 10.0));
//        sinhVienList.add(new SinhVien("1111", "aaaaa", "aaa", 9.0));
//        sinhVienList.add(new SinhVien("2222", "bbbbb", "bbb", 8.0));
//        sinhVienList.add(new SinhVien("3333", "ccccc", "ccc", 7.0));
//
//        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
//
//        for(SinhVien sv : sinhVienList) {
//            mData.child("DSSV").push().setValue(sv);
//        }
//    }

    private void anhXa() {
        btnAddSV = findViewById(R.id.floatingActionButtonAddSV);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void onSinhVienClick(int position) {

    }
}