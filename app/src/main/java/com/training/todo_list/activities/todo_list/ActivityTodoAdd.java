package com.training.todo_list.activities.todo_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.training.todo_list.R;
import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.managers.TodoTypeManager;
import com.training.todo_list.model.models.Todo;
import com.training.todo_list.model.models.TodoType;

import java.util.Date;
import java.util.UUID;

import petrov.kristiyan.colorpicker.ColorPicker;


public class ActivityTodoAdd extends AppCompatActivity {


    int mIColor;
    boolean mBDone = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_act_todo_add);

        final EditText tDiscription = (EditText)findViewById(R.id.discription);
        final EditText tImportance = (EditText)findViewById(R.id.todotypename);
        CheckBox tCheckBox = (CheckBox) findViewById(R.id.cb_done);
        ImageButton tSave = (ImageButton)findViewById(R.id.bt_save);
        final ImageButton tColorPicker = (ImageButton)findViewById(R.id.bt_edit);

        tColorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPicker colorPicker = new ColorPicker(ActivityTodoAdd.this);
                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position,int color) {
                        //String strColor = String.format("#%06X", 0xFFFFFF & color);
                        mIColor = color;
                        tColorPicker.setBackgroundColor(color);
                    }

                    @Override
                    public void onCancel(){

                    }
                });
            }
        });

        tCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                   mBDone = true;
                }
                else
                {
                    mBDone = false;
                }
            }
        });
        tSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(tDiscription.getText().toString().trim().length()>0 && tImportance.getText().toString().trim().length()>0){
                   TodoType mtodoType = new TodoType(tImportance.getText().toString(),String.format("#%06X", 0xFFFFFF & mIColor),UUID.randomUUID());
                   Todo mtodo = new Todo(tDiscription.getText().toString(),new Date(),mtodoType.id(),mBDone,UUID.randomUUID());
                   TodoTypeManager tTodoTypeManager = new TodoTypeManager();
                   TodoManager tTodoManager = new TodoManager();
                   tTodoTypeManager.save(mtodoType);
                   tTodoManager.save(mtodo);
                   Intent intent = new Intent(getApplication(),ActivityTodoList.class);
                   startActivity(intent);
               }else {
                   Toast.makeText(getApplication(), "Input Error", Toast.LENGTH_SHORT).show();
               }
            }
        });



    }

    public void askCancelTodo(View pView){
        Intent tIntent = new Intent(this,ActivityTodoList.class);
        startActivity(tIntent);
    }

}
