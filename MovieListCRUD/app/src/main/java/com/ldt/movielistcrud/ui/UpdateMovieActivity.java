package com.ldt.movielistcrud.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ldt.movielistcrud.R;
import com.ldt.movielistcrud.model.Category;
import com.ldt.movielistcrud.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class UpdateMovieActivity extends AppCompatActivity {
    private EditText edtName, edtDuration, edtProductionYear, edtDescription, edtUrl;
    private Spinner spinnerCategory;
    private Button btnPaste, btnBrowse, btnUpdate;
    private ImageButton btnClear;
    private ImageView thumbnailMovie;
    private List<Category> categoryList ;
    private Movie movie;
    private DatabaseReference myData = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);
        anhXa();

        setDataActivity();

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

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkEdt())
                    Toast.makeText(UpdateMovieActivity.this, "Not enter enough information!", Toast.LENGTH_SHORT).show();
                else
                    updateMovie();
            }
        });

        edtUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Glide.with(UpdateMovieActivity.this)
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

    private void updateMovie() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateMovieActivity.this);
        builder.setTitle("Are you sure");
        builder.setMessage("Update data can't be Undo.");
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myData.child("Movie").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Movie m = snapshot.getValue(Movie.class);
                        if(movie.getName().equals(m.getName()) && movie.getDuration().equals(m.getDuration()) && movie.getProductionYear() == m.getProductionYear() && movie.getCategories().equals(m.getCategories()) && movie.getRate().equals(m.getRate())) {
                            myData.child("Movie").child(snapshot.getKey()).setValue(movie);
                            Toast.makeText(UpdateMovieActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
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

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(UpdateMovieActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private boolean checkEdt() {
        if(edtName.getText().toString().equals("") || edtUrl.getText().toString().equals("") || edtDuration.getText().toString().equals("") || edtProductionYear.getText().toString().equals("") || edtDescription.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    private void setDataActivity() {
        movie = (Movie) getIntent().getSerializableExtra("movie");
        Glide.with(this)
                .load(movie.getImgUrl())
                .placeholder(R.mipmap.ic_launcher) // ảnh mặt định
                .error(R.drawable.ic_baseline_error_72) // ảnh khi lỗi
                .into(thumbnailMovie);
        edtUrl.setText(movie.getImgUrl());
        edtName.setText(movie.getName());
        edtDuration.setText(movie.getDuration());
        edtProductionYear.setText(movie.getProductionYear() + "");
        edtDescription.setText(movie.getDescription());
        categoryList = dataCategory();
        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this,R.layout.spinner_list_style,
                categoryList);
        spinnerCategory.setAdapter(adapter);
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
        btnUpdate = findViewById(R.id.btnUpdate);
    }
}