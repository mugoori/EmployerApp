package com.mugoori.employerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mugoori.employerapp.model.Employee;

public class EditActivity extends AppCompatActivity {

    EditText editAge;
    EditText editSalary;
    Button btnSave;
    Employee employee;
    int index;

    public static final int EDIT = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setTitle("직원 수정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editAge = findViewById(R.id.editAge);
        editSalary = findViewById(R.id.editSalary);
        btnSave = findViewById(R.id.btnSave);

        // 데이터 받아오기
        employee = (Employee) getIntent().getSerializableExtra("employee");
        index = getIntent().getIntExtra("index",-1);

        // 받아온 데이터 화면에 표시
        editAge.setText(employee.age+"");
        editSalary.setText(employee.salary+"");

        // 버튼 눌렀을때 처리
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 유저가 수정한 데이터 받아오기
                String strAge = editAge.getText().toString().trim();
                String strSalary = editSalary.getText().toString().trim();

                if ( strAge.isEmpty() || strSalary.isEmpty() ) {
                    Toast.makeText(EditActivity.this, "필수 항목을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 숫자로 바꿔서 위에서 받아온 객체에 셋팅해준다
                int age = Integer.valueOf(strAge).intValue();
                int salary = Integer.valueOf(strSalary).intValue();


                // 받아온 객체에 유저가 입력한 데이터 넣어주기
                employee.age = age;
                employee.salary = salary;

                // employee 객체 보내주기
                Intent intent = new Intent();
                intent.putExtra("employee",employee);
                intent.putExtra("index",index);

                // intent 실행
                setResult(EDIT,intent);

                // 액티비티 닫기
                finish();
            }
        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}