package com.beesightsoft.demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    private TextView etName;
    private TextView etGender;
    private TextView etAge;
    private TextView etHobbies;
    private TextView etCountry;
    private Button btnBack;
    private People user;
    private Uri uri;
    private Bitmap bitmap;
    private ImageView ivAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intent = getIntent();
        if (intent != null) {
            user = (People) intent.getSerializableExtra("user");
            bitmap = (Bitmap) intent.getParcelableExtra("avatar");
        }
        etName = (TextView) findViewById(R.id.activity_show_tv_name);
        etGender = (TextView) findViewById(R.id.activity_show_tv_gender);
        etAge = (TextView) findViewById(R.id.activity_show_tv_age);
        etCountry = (TextView) findViewById(R.id.activity_show_tv_country);
        etHobbies = (TextView) findViewById(R.id.activity_show_tv_hobbies);
        btnBack = (Button) findViewById(R.id.activity_show_btn_back);
        ivAvatar = (ImageView) findViewById(R.id.activity_show_iv_avatar);
        etName.setText(user.getName());
        if (user.getGender()) {
            etGender.setText("Male");
        }
        else {
            etGender.setText("FeMale");
        }
        etAge.setText(user.getAge() + "");
        ArrayList<String> hobbies = user.getHobbies();
        for (String tamp : hobbies) {
            etHobbies.append(" " + tamp);
        }
        etCountry.setText(user.getCountry());
        //Glide.with(this).load(uri).into(ivAvatar);
        ivAvatar.setImageBitmap(bitmap);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
