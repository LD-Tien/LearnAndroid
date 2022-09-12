package com.ldt.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTraiCay;
    ArrayList<String> arrayTraiCay;
    EditText edtTenTraiCay;
    Button btnThem, btnSua, btnXoa;

    int vitri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lê Đức Tiên - 2050531200309");


        anhXa();

        arrayTraiCay = new ArrayList<>();
        arrayTraiCay.add("Táo");
        arrayTraiCay.add("Cam");
        arrayTraiCay.add("Ổi");
        arrayTraiCay.add("Dừa");

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayTraiCay);
//        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, R.layout.list_item, arrayTraiCay); // Android.R.layout. simple....

        lvTraiCay.setAdapter(adapter);
        lvTraiCay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtTenTraiCay.setText(arrayTraiCay.get(i));
                vitri = i;
                batButtonSua_Xoa();
            }
        });

        lvTraiCay.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                intent.putExtra("item", arrayTraiCay.get(i));
                startActivity(intent);
                tatButtonSua_Xoa();
                return false;
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtTenTraiCay.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Bạn chưa nhập tên trái cây!", Toast.LENGTH_SHORT).show();
                    edtTenTraiCay.requestFocus();
                    return;
                }

                arrayTraiCay.add(edtTenTraiCay.getText().toString());
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                tatButtonSua_Xoa();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayTraiCay.set(vitri, edtTenTraiCay.getText().toString());
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                tatButtonSua_Xoa();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayTraiCay.remove(vitri);
                edtTenTraiCay.setText("");
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                tatButtonSua_Xoa();
            }
        });
    }

    private void anhXa() {
        lvTraiCay = (ListView) findViewById(R.id.listViewTraiCay);
        edtTenTraiCay = (EditText) findViewById(R.id.editTextTraiCay);
        btnThem = (Button) findViewById(R.id.buttonThem);
        btnSua = (Button) findViewById(R.id.buttonSua);
        btnXoa = (Button) findViewById(R.id.buttonXoa);
    }

    private void tatButtonSua_Xoa() {
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        edtTenTraiCay.setText("");
    }

    private void batButtonSua_Xoa() {
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
    }
}