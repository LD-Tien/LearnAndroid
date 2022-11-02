package com.ldt.movielistcrud.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.ldt.movielistcrud.R;
import com.ldt.movielistcrud.model.Category;
import com.ldt.movielistcrud.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddMovieActivity extends AppCompatActivity {
    private EditText edtName, edtDuration, edtProductionYear, edtDescription, edtUrl;
    private Spinner spinnerCategory;
    private Button btnPaste, btnBrowse, btnAdd;
    private ImageButton btnClear;
    private ImageView thumbnailMovie;
    private List<Category> categoryList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        anhXa();
        categoryList = dataCategory();
        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this,R.layout.spinner_list_style,
                categoryList);
        spinnerCategory.setAdapter(adapter);

        btnPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipBoard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = clipBoard.getPrimaryClip();
                ClipData.Item item = clipData.getItemAt(0);
                edtUrl.setText(item.getText().toString());
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtUrl.setText("");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkEdt())
                    Toast.makeText(AddMovieActivity.this, "Not enter enough information!", Toast.LENGTH_SHORT).show();
                else
                    insertData();
            }
        });

        edtUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Glide.with(AddMovieActivity.this)
                        .load(edtUrl.getText().toString())
                        .placeholder(R.mipmap.ic_launcher) // ảnh mặt định
                        .error(R.drawable.ic_baseline_error_72) // ảnh khi lỗi
                        .into(thumbnailMovie);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void insertData() {
        Movie movie = getDataMovieFromActivity();
        FirebaseDatabase.getInstance().getReference().child("Movie").push()
                .setValue(movie)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddMovieActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                        clearEdt();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddMovieActivity.this, "Error While Insertion", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    
    private boolean checkEdt() {
        if(edtName.getText().toString().equals("") || edtUrl.getText().toString().equals("") || edtDuration.getText().toString().equals("") || edtProductionYear.getText().toString().equals("") || edtDescription.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    private void clearEdt() {
        edtUrl.setText("");
        edtName.setText("");
        edtDuration.setText("");
        edtProductionYear.setText("");
        edtDescription.setText("");
    }

    private Movie getDataMovieFromActivity() {
        Movie movie = new Movie();
        movie.setName(edtName.getText().toString());
        movie.setCategories(spinnerCategory.getSelectedItem().toString());
        movie.setDuration(edtDuration.getText().toString());
        movie.setProductionYear(Integer.parseInt(edtProductionYear.getText().toString()));
        movie.setDescription(edtDescription.getText().toString());
        movie.setImgUrl(edtUrl.getText().toString());
        movie.setFavorite(false);
        movie.setRate(0.0);
        return movie;
    }

    private List<Category> dataCategory () {
        List<Category> categoryList;
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Action"));
        categoryList.add(new Category("Sci-fi"));
        categoryList.add(new Category("Thrillers"));
        return categoryList;
    }

    private void anhXa() {
        edtName = findViewById(R.id.edtName);
        edtDuration = findViewById(R.id.edtDuration);
        edtProductionYear = findViewById(R.id.edtProductionYear);
        edtDescription = findViewById(R.id.edtDescription);
        btnPaste = findViewById(R.id.btnPaste);
        btnBrowse = findViewById(R.id.btnBrowse);
        btnClear = findViewById(R.id.btnClear);
        thumbnailMovie = findViewById(R.id.thumbnailMovie);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        edtUrl = findViewById(R.id.edtUrl);
        btnAdd = findViewById(R.id.btnAdd);
    }
}