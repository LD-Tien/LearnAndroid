package com.ldt.sinhviencrud_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateSinhVienActivity extends AppCompatActivity {
    private Button btnCancel, btnUpdate;
    private EditText edtMSV, edtHoTen, edtLHP, edtDTB;
    private DatabaseReference mData;
    private SinhVien sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sinh_vien);
        mData = FirebaseDatabase.getInstance().getReference();

        anhXa();
        setDataActivity();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen = edtHoTen.getText().toString();
                String lhp = edtLHP.getText().toString();
                String dtb = edtDTB.getText().toString();
                if(hoTen.isEmpty()) {
                    edtHoTen.requestFocus();
                    return;
                }
                if(lhp.isEmpty()) {
                    edtLHP.requestFocus();
                    return;
                }
                if(dtb.isEmpty()) {
                    edtDTB.requestFocus();
                    return;
                }
                sv.setHoTen(hoTen);
                sv.setLhp(lhp);
                sv.setDtb(Double.parseDouble(dtb));
                mData.child("DSSV").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        String msv = snapshot.getValue(SinhVien.class).getMsv();
                        if(msv.equals(sv.getMsv())) {
                            mData.child("DSSV").child(snapshot.getKey()).setValue(sv)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(UpdateSinhVienActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(UpdateSinhVienActivity.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
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
    }

    private void setDataActivity() {
        sv = (SinhVien) getIntent().getSerializableExtra("SinhVien");
        edtMSV.setText(sv.getMsv());
        edtHoTen.setText(sv.getHoTen());
        edtLHP.setText(sv.getLhp());
        edtDTB.setText(sv.getDtb().toString());
    }

    private void anhXa() {
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
        edtMSV = findViewById(R.id.edtMSV);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtLHP = findViewById(R.id.edtLHP);
        edtDTB = findViewById(R.id.edtDTB);
    }
}