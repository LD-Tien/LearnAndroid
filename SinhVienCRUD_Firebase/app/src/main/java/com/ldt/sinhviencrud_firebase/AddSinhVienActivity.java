package com.ldt.sinhviencrud_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSinhVienActivity extends AppCompatActivity {

    private Button btnCancel, btnAdd;
    private EditText edtMSV, edtHoTen, edtLHP, edtDTB;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinh_vien);

        mData = FirebaseDatabase.getInstance().getReference();

        anhXa();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msv = edtMSV.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                String lhp = edtLHP.getText().toString();
                String dtb = edtDTB.getText().toString();
                if(msv.isEmpty()) {
                    edtMSV.requestFocus();
                    return;
                }
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
                SinhVien sv = new SinhVien(msv, hoTen, lhp, Double.parseDouble(dtb));
                mData.child("DSSV").push().setValue(sv)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(AddSinhVienActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                                clearEdt();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddSinhVienActivity.this, "Thêm không thành công!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void clearEdt() {
        edtMSV.setText("");
        edtHoTen.setText("");
        edtLHP.setText("");
        edtDTB.setText("");
    }

    private void anhXa() {
        btnCancel = findViewById(R.id.btnCancel);
        btnAdd = findViewById(R.id.btnAdd);
        edtMSV = findViewById(R.id.edtMSV);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtLHP = findViewById(R.id.edtLHP);
        edtDTB = findViewById(R.id.edtDTB);
    }
}