package com.example.sqliteapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText name, phone, dateOfBirth;
    Button insert, select, delete, update;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.txtName);
        phone = findViewById(R.id.txtNumber);
        dateOfBirth = findViewById(R.id.txtDate);
        insert = findViewById(R.id.btnInsert);
        select = findViewById(R.id.btnSelect);
        databaseHelper = new DatabaseHelper(this);

        insert.setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.insert(name.getText().toString(),
                    phone.getText().toString(), dateOfBirth.getText().toString());
            if (checkInsertData) {
                Toast.makeText(getApplicationContext(),
                        "Данные успешно добавлены", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Произошла ошибка", Toast.LENGTH_LONG).show();
            }
        });

        select.setOnClickListener(view -> {
            Cursor res = databaseHelper.getdata();
            if(res.getCount()==0) {
                Toast.makeText(MainActivity.this, "Нет данных", Toast.LENGTH_LONG).show();
                return;
            }

            StringBuilder buffer = new StringBuilder();
            while(res.moveToNext()) {
                buffer.append("Имя: ").append(res.getString(0)).append("\n");
                buffer.append("Телефонный номер: ").append(res.getString(1)).append("\n");
                buffer.append("Дата рождения: ").append(res.getString(2)).append("\n\n");
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Данные пользователей");
            builder.setMessage(buffer.toString());
            builder.show();
        });

        update.setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.insert(name.getText().toString(),
                    phone.getText().toString(), dateOfBirth.getText().toString());
            if (checkInsertData) {
                Toast.makeText(getApplicationContext(),
                        "Данные успешно добавлены", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Произошла ошибка", Toast.LENGTH_LONG).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String phoneTXT = phone.getText().toString();
                String date_of_birthTXT = dateOfBirth.getText().toString();

                Boolean checkUpdateData = databaseHelper.update(nameTXT, phoneTXT, date_of_birthTXT);
                if (checkUpdateData==true)
                    Toast.makeText(MainActivity.this, "Данные обновлены", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Данные не обновлены", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkDeleteData = databaseHelper.delete(nameTXT);
                if (checkDeleteData==true)
                    Toast.makeText(MainActivity.this, "Данные удалены", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Данные не удалены", Toast.LENGTH_SHORT).show();
            }
        });
    }
}