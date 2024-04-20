package com.example.birthday_odev;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText gunEditText,ayEditText,yilEditText;
    private Button hesaplaButton;
    private TextView sonucTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gunEditText = findViewById(R.id.gunEditText);
        ayEditText = findViewById(R.id.ayEditText);
        yilEditText = findViewById(R.id.yilEditText);
        hesaplaButton = findViewById(R.id.hesaplaButton);
        sonucTextView = findViewById(R.id.sonucTextView);

        hesaplaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hesapla();
            }
        });
    }
    private  void hesapla(){
        try {
            int gun = Integer.parseInt(gunEditText.getText().toString());
            int ay = Integer.parseInt(ayEditText.getText().toString());
            int yil = Integer.parseInt(yilEditText.getText().toString());

            if (gun <1 || gun>31 || ay<1 || ay>12 || yil <1000 || yil>9999){
                throw new NumberFormatException("Hatalı tarih girişi!");
            }
            int gunSayisi = 0;

            for (int gecenYil = 1; gecenYil < yil; gecenYil++) {
                if (gecenYil % 4 == 0) {
                    gunSayisi += 366;
                } else {
                    gunSayisi += 365;
                }
            }
            for (int gecenAy = 1; gecenAy < ay; gecenAy++) {
                if (gecenAy == 2) {
                    if (yil % 4 == 0) {
                        gunSayisi += 29;
                    } else {
                        gunSayisi += 28;
                    }
                } else if (gecenAy == 4 || gecenAy == 6 || gecenAy == 9 || gecenAy == 11) {
                    gunSayisi += 30;
                } else {
                    gunSayisi += 31;
                }
            }
            gunSayisi += gun;
            sonucTextView.setText(String.format("Doğum tarihinden bugüne %d gün geçmiş.", gunSayisi));
        } catch (NumberFormatException e) {
            sonucTextView.setText("Hata: " + e.getMessage());
        }
    }
}