package com.training.todo_list.model.managers;

import com.training.todo_list.model.models.Todo;
import com.training.todo_list.model.models.TodoType;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by jgam on 20/02/2017.
 */
public class TodoManagerTest {

    private static TodoManager mMockedTodoManager;
    private static Todo  mTodo1;
    private static  Todo mTodo2;
    private static  TodoType mTypeEmergency;
    private static  TodoType mTypeNormal;
    UUID mUUID = UUID.randomUUID();


    @Before
    public void setUp() throws Exception {

        mMockedTodoManager = mock(TodoManager.class);
        mTypeEmergency = new TodoType("Emergency", "#b94a48", mUUID);

        mTypeNormal = new TodoType("Normal", "#3a87ad", UUID.randomUUID());
        mTodo1 = new Todo("Buy milk and eggs",
                new Date(1420106400000L), mTypeNormal.id(), true, mUUID);
        mTodo2 = new Todo("Call Superman to repair the internet",
                new Date(1443693600000L), mTypeEmergency.id(), false, UUID.randomUUID());



        when(mMockedTodoManager.all()).thenReturn(Arrays.asList(mTodo1, mTodo2));
        when(mMockedTodoManager.todoFor(mUUID)).thenReturn(mTodo1);

    }

    @Test
    public void all() throws Exception {

        List<Todo> tTodos = mMockedTodoManager.all();
        assertEquals(2, tTodos.size());
        Todo tTodo = tTodos.get(0);
        assertEquals(tTodo,mTodo1);
    }

    @Test
    public void todoFor() throws Exception {
        Todo tTodo = mMockedTodoManager.todoFor(mUUID);
        assertNotNull(tTodo);
        assertEquals(mTodo1,tTodo);
    }



    @Test
    public void save() throws Exception {

        mMockedTodoManager.save(mTodo1);
        verify(mMockedTodoManager).save(mTodo1);
    }

    @Test
    public void remove() throws Exception {
        mMockedTodoManager.remove(mTodo1);
        verify(mMockedTodoManager).remove(mTodo1);
    }



}