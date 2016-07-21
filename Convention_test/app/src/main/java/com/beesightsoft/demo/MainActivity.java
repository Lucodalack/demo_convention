package com.beesightsoft.demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ToggleButton tbGender;
    private EditText etName;
    private SeekBar sbAge;
    private CheckBox chkGame;
    private CheckBox chkMusic;
    private CheckBox chkBook;
    private CheckBox chkTravel;
    private TextView tvAge;
    private Spinner spnCountry;
    private Button btnOk;
    private ImageView ivAvatar;
    private String country = "";
    private Bitmap newProfilePic;
    private static final int PICK_IMAGE = 0;
    private static final int IMG_SIZE = 100;
    private static final String MY_COUNTRY[] = {"DakLak", "Ha noi", "HCM", "Hai Phong"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineWiget();
        prepareData();
        listenSeekbar();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferData();
            }
        });
        spnCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = MY_COUNTRY[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImg();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            Bundle extras = data.getExtras();
            newProfilePic = extras.getParcelable("data");
            ivAvatar.setImageBitmap(newProfilePic);
        }
    }

    private void defineWiget() {
        tbGender = (ToggleButton) findViewById(R.id.activity_main_tb_gender);
        etName = (EditText) findViewById(R.id.activity_main_et_name);
        sbAge = (SeekBar) findViewById(R.id.activity_main_sb_age);
        chkGame = (CheckBox) findViewById((R.id.activity_main_chk_game));
        chkMusic = (CheckBox) findViewById(R.id.activity_main_chk_music);
        chkBook = (CheckBox) findViewById(R.id.activity_main_chk_book);
        chkTravel = (CheckBox) findViewById((R.id.activity_main_chk_travel));
        tvAge = (TextView) findViewById(R.id.activity_main_tv_age);
        spnCountry = (Spinner) findViewById(R.id.activity_main_spn_country);
        btnOk = (Button) findViewById(R.id.activity_main_btn_ok);
        ivAvatar = (ImageView) findViewById(R.id.activity_show_iv_avatar);
    }

    private void prepareData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, MY_COUNTRY);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnCountry.setAdapter(adapter);
        tvAge.setText("Age: " + sbAge.getProgress());
        Glide.with(this).load(R.drawable.ic_blank_avatar).into(ivAvatar);
    }

    private void transferData() {
        People user = new People();
        user.setName(etName.getText().toString());
        user.setGender(tbGender.isChecked());
        user.setAge(sbAge.getProgress());
        user.setCountry(country);
        ArrayList<String> hobbies = new ArrayList<String>();
        if (chkTravel.isChecked()) {
            hobbies.add("Travel");
        }
        if (chkBook.isChecked()) {
            hobbies.add("Book");
        }
        if (chkMusic.isChecked()) {
            hobbies.add("Music");
        }
        if (chkGame.isChecked()) {
            hobbies.add("Game");
        }
        user.setHobbies(hobbies);
        Intent intent = new Intent(MainActivity.this, ShowActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("avatar",newProfilePic);
        startActivity(intent);
    }

    private void pickImg() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", IMG_SIZE);
        intent.putExtra("outputY", IMG_SIZE);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //intent.putExtra("return-data", true);
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, PICK_IMAGE);
    }

    private void listenSeekbar() {
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAge.setText("Age: " + sbAge.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
