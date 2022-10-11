package com.ldt.sqlitecrude_sv;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ldt.sqlitecrude_sv.adapter.SinhVienAdapter;
import com.ldt.sqlitecrude_sv.model.SinhVien;
import com.ldt.sqlitecrude_sv.sqlite.DBHelper;
import com.ldt.sqlitecrude_sv.sqlite.SinhVienDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SinhVienAdapter sinhVienAdapter;
    private ListView lvSinhVien;
    private String idSinhVien;
    private List<SinhVien> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        database.close();
        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnInsert).setOnClickListener(this);

        lvSinhVien = findViewById(R.id.lvSinhVien);
        SinhVienDao dao = new SinhVienDao(this);
        list = dao.getAll();
        Toast.makeText(this, list.get(0).getTen(), Toast.LENGTH_SHORT).show();
        sinhVienAdapter = new SinhVienAdapter(this,list);
        lvSinhVien.setAdapter(sinhVienAdapter);
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SinhVien sv = list.get(i);
                idSinhVien = sv.getId();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();

        SinhVienDao dao = new SinhVienDao(this);
        List<SinhVien> updateList = dao.getAll();
        list.clear();
        updateList.forEach(item->list.add(item));
        sinhVienAdapter.notifyDataSetInvalidated();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AddOrEditEmployeeActivity.class);
        switch(view.getId()) {
            case R.id.btnInsert:
                startActivity(intent);
                break;
            case R.id.btnEdit:
                if(idSinhVien == null) {
                    Toast.makeText(this, "Phải chọn sinh viên trước!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("id", idSinhVien);
                intent.putExtra("data", bundle);
                startActivity(intent);
                break;
            case R.id.btnDelete:
                if(idSinhVien == null) {
                    Toast.makeText(this, "Phải chọn sinh viên trước!", Toast.LENGTH_SHORT).show();
                    return;
                }
                SinhVienDao dao = new SinhVienDao(this);
                dao.delete(idSinhVien);
                idSinhVien = null;
                onResume();
                Toast.makeText(this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}