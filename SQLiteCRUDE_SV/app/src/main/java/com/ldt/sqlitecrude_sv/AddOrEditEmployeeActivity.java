package com.ldt.sqlitecrude_sv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ldt.sqlitecrude_sv.model.SinhVien;
import com.ldt.sqlitecrude_sv.sqlite.SinhVienDao;

public class AddOrEditEmployeeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtIdSinhVien, edtTenSinhVien, edtLHP, edtDTB;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_employee);

        edtIdSinhVien = findViewById(R.id.edtIdSinhVien);
        edtTenSinhVien = findViewById(R.id.edtTenSinhVien);
        edtLHP = findViewById(R.id.edtLHP);
        edtDTB = findViewById(R.id.edtDTB);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        findViewById(R.id.btnListSV).setOnClickListener(this);

        readSinhVien();
    }

    private void readSinhVien() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle == null) {
            return;
        }
        String id = bundle.getString("id");
        SinhVienDao dao = new SinhVienDao(this);
        SinhVien sv = dao.getById(id);
        edtIdSinhVien.setText(sv.getId());
        edtTenSinhVien.setText(sv.getTen());
        edtLHP.setText(sv.getLop());
        edtDTB.setText(sv.getDtb().toString());
        btnSave.setText("Update");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                SinhVienDao dao = new SinhVienDao(this);
                SinhVien sv = new SinhVien();
                sv.setId(edtIdSinhVien.getText().toString());
                sv.setTen(edtTenSinhVien.getText().toString());
                sv.setLop(edtLHP.getText().toString());
                sv.setDtb(Double.parseDouble(edtDTB.getText().toString()));

                if(btnSave.getText().equals("Save")) {
                    dao.insert(sv);
                } else {
                    dao.update(sv);
                }
                dao.insert(sv);
                Toast.makeText(this, "Lưu thành công!", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}