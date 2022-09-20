package com.ldt.listview_food;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView lvFood;
    ArrayList<Food> arrayFood;
    FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lê Đức Tiên - 2050531200309");

        anhXa();

        adapter = new FoodAdapter(MainActivity.this, R.layout.item_in_listview, arrayFood);
        lvFood.setAdapter(adapter);

        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
//                intent.putExtra("food", arrayFood.get(i));
                intent.putExtra("image", arrayFood.get(i).getImg());
                intent.putExtra("name", arrayFood.get(i).getName());
                intent.putExtra("description", arrayFood.get(i).getDescription());
                intent.putExtra("price", arrayFood.get(i).getPrice());
                startActivity(intent);
            }
        });

        lvFood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                    //set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                    .setTitle("Cảnh báo!")
                    //set message
                    .setMessage("Bạn có muốn xóa món " + arrayFood.get(i).getName().toString() + " ra khỏi menu?")
//                    .setMessage("Exiting will call finish() method")
                    //set positive button
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int a) {
                            //set what would happen when positive button is clicked
                            arrayFood.remove(i);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //set negative button
                    .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int a) {
                            //set what should happen when negative button is clicked
                        }
                    })
                    .show();
                return true;
            }
        });



    }

    private void anhXa() {
        lvFood = (ListView) findViewById(R.id.lvFood);
        arrayFood = new ArrayList<>();

        arrayFood.add(new Food(R.drawable.hamburger, "Hamburger", "Nhân thịt bò, phô mai", "50.000đ"));
        arrayFood.add(new Food(R.drawable.pizza, "Pizza", "Thực cẩm", "100.000đ"));
        arrayFood.add(new Food(R.drawable.steak, "Steak", "Thịt bò kobe", "500.000đ"));
        arrayFood.add(new Food(R.drawable.cream, "Cream", "Vị xoài", "45.000đ"));

    }
}