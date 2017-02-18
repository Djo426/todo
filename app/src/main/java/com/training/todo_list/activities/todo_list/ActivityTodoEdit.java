package com.training.todo_list.activities.todo_list;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ActivityTodoEdit extends AppCompatActivity {
    int mIColor;
    boolean mBDone = false;
    Todo mTodo = null;
    TodoType mTodoType = null;
    String mSFlag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_act_todo_edit);


        final TodoTypeManager tTodoTypeManager = new TodoTypeManager();
        final TodoManager tTodoManager = new TodoManager();
        final EditText tDiscription = (EditText)findViewById(R.id.discription);
        final EditText tImportance = (EditText)findViewById(R.id.todotypename);
        CheckBox tCheckBox = (CheckBox) findViewById(R.id.cb_done);
        ImageButton tSave = (ImageButton)findViewById(R.id.bt_save);
        final ImageButton tColorPicker = (ImageButton)findViewById(R.id.bt_edit);
        final Bundle tExtras = getIntent().getExtras();
        if (tExtras != null) {
            mSFlag = tExtras.getString("FLAG");
            if(mSFlag!= null && mSFlag.equals("EDIT_FLAG")) {

                mTodo = (Todo) tExtras.getSerializable("TODO");
                mTodoType = tTodoTypeManager.todoTypeFor(mTodo.idTodoType());
                mIColor = Color.parseColor(mTodoType.color());
                tDiscription.setText(mTodo.description());
                tImportance.setText(mTodoType.name());
                tCheckBox.setChecked(mTodo.isDone());
                tColorPicker.setBackgroundColor(Color.parseColor(mTodoType.color()));
            }
        }
        tColorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPicker colorPicker = new ColorPicker(ActivityTodoEdit.this);
                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position,int color) {
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
                if(tDiscription.getText().toString().trim().length()>0 && tImportance.getText().toString().trim().length()>0) {

                    if(mSFlag!= null && mSFlag.equals("EDIT_FLAG")) {
                        // first we remove old todo and old todotype
                        tTodoTypeManager.remove(mTodoType);
                        tTodoManager.remove(mTodo);
                    }
                    //  than we creates new and save
                    TodoType mtodoType = new TodoType(tImportance.getText().toString(), String.format("#%06X", 0xFFFFFF & mIColor), UUID.randomUUID());
                    Todo mtodo = new Todo(tDiscription.getText().toString(), new Date(), mtodoType.id(), mBDone, UUID.randomUUID());
                    tTodoTypeManager.save(mtodoType);
                    tTodoManager.save(mtodo);
                    Intent intent = new Intent(getApplication(), ActivityTodoList.class);
                    startActivity(intent);

                    }else{
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
