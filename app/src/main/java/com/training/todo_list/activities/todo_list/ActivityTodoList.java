package com.training.todo_list.activities.todo_list;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.training.todo_list.R;
import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.managers.TodoTypeManager;
import com.training.todo_list.model.models.Todo;
import com.training.todo_list.model.models.TodoType;

public class ActivityTodoList extends ListActivity {

    ListView todoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_act_todo_list);
        final TodoManager tTodoManager = new TodoManager();
        final TodoTypeManager tTodoTypeManager = new TodoTypeManager();
        todoList = (ListView)findViewById(android.R.id.list);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_2, android.R.id.text1, tTodoManager.all()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(tTodoManager.all().get(position).description());
                text2.setText(tTodoManager.all().get(position).timeCreation().toString());
                view.setBackgroundColor(Color.parseColor(tTodoTypeManager.todoTypeFor(tTodoManager.all().get(position).idTodoType()).color()));
                return view;
            }
        };
        todoList.setAdapter(adapter);

        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Todo todo = tTodoManager.all().get(i);
                Intent intent = new Intent(getApplication(),ActivityTodoEdit.class);
                intent.putExtra("TODO",todo);
                startActivity(intent);



            }
        });

    }


    public void askAddTodo(View pView) {
        //Toast.makeText(this, "Ask add todo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,ActivityTodoAdd.class);
        startActivity(intent);
    }


    public void askSurprise(View pView) {
        AlertDialog.Builder tBuilder = new AlertDialog.Builder(this);
        tBuilder.setTitle(R.string.dialog_title_surprise);
        tBuilder.setMessage(R.string.dialog_message_surprise);
        tBuilder.setPositiveButton(R.string.confirm, null);
        tBuilder.show();
    }
}
