package com.ldt.sqlitecrude_sv.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ldt.sqlitecrude_sv.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienDao {
    private SQLiteDatabase db;

    public SinhVienDao(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase(); // mở CSDL thực hiện crude
    }

    @SuppressLint("Range")
    public List<SinhVien> get(String sql, String ...selectArgs) {
        List<SinhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs); //Đối tượng cursor cho phép lấy dữ liệu khi thực hiện truy vấn
        while(cursor.moveToNext()) {
            SinhVien sv = new SinhVien();
            sv.setId(cursor.getString(cursor.getColumnIndex("id")));
            sv.setTen(cursor.getString(cursor.getColumnIndex("ten")));
            sv.setLop(cursor.getString(cursor.getColumnIndex("lop")));
            sv.setDtb(cursor.getDouble(cursor.getColumnIndex("dtb")));

            list.add(sv);
        }
        return list;
    }

    public List<SinhVien> getAll() {
        String sql = "SELECT * FROM SinhVien";
        return get(sql);
    }

    public SinhVien getById(String id) {
        String sql = "SELECT * FROM SinhVien WHERE id = ?";
        List<SinhVien> list = get(sql, id);
        return list.get(0);
    }

    public long insert(SinhVien sv) {
        ContentValues values = new ContentValues();
        values.put("id", sv.getId());
        values.put("ten", sv.getTen());
        values.put("lop", sv.getLop());
        values.put("dtb", sv.getDtb());

        return db.insert("SinhVien", null, values);
    }

    public long update(SinhVien sv) {
        ContentValues values = new ContentValues();
        values.put("ten", sv.getTen());
        values.put("lop", sv.getLop());
        values.put("dtb", sv.getDtb());

        return db.update("SinhVien", values, "id = ?", new String[]{sv.getId()});
    }

    public int delete(String id) {
        return db.delete("SinhVien", "id = ?", new String[]{id});
    }
}
