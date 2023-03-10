package com.mugoori.employerapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mugoori.employerapp.EditActivity;
import com.mugoori.employerapp.MainActivity;
import com.mugoori.employerapp.R;
import com.mugoori.employerapp.model.Employee;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {


    Context context;
    ArrayList<Employee> employeeList;
    int deleteIndex;

    public EmployeeAdapter(Context context, ArrayList<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_row, parent, false);
        return new EmployeeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {

        Employee employee = employeeList.get(position);

        // todo 데이터 셋팅 = > 가공해서 셋팅

        holder.txtName.setText(employee.name);
        holder.txtAge.setText("나이 : "+employee.age+"세");

        // 숫자에 콤마 표시하는 법
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String formattedStringPrice = decimalFormat.format(employee.salary);

        holder.txtSalary.setText("연봉 : $"+formattedStringPrice);





    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtAge;
        TextView txtSalary;
        ImageView imgDelete;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtSalary = itemView.findViewById(R.id.txtSalary);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardView = itemView.findViewById(R.id.cardView);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int index = getAdapterPosition();
                    Employee employee = employeeList.get(index);

                    // 새로운 액티비티에 위의 정보를 넘겨서 실행한다
                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("employee", employee);
                    intent.putExtra("index",index);

                    // 양방향 통신을 위해 메인 액티비티의 런쳐를 사용해서 실행한다
                    ((MainActivity)context).launcher.launch(intent);

                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    deleteIndex = getAdapterPosition();
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("정보 삭제");
                    builder.setMessage("정말 삭제하시겠습니까 ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            employeeList.remove(deleteIndex);
                            notifyDataSetChanged();

                        }
                    });
                    builder.setNegativeButton("No",null);
                    builder.show();
                }
            });






        }
    }
}
