package com.training.todo_list.model.managers;

import com.training.todo_list.model.models.TodoType;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by jgam on 20/02/2017.
 */
public class TodoTypeManagerTest {

    private static TodoTypeManager mMockedTodoTypeManager;
    private static  TodoType mTypeEmergency;
    UUID mUUID = UUID.randomUUID();

    @Before
    public void setUp() throws Exception {
        mMockedTodoTypeManager = mock(TodoTypeManager.class);
        mTypeEmergency = new TodoType("Emergency", "#b94a48", mUUID);
    }

    @Test
    public void save() throws Exception {
        mMockedTodoTypeManager.save(mTypeEmergency);
        verify(mMockedTodoTypeManager).save(mTypeEmergency);
    }

    @Test
    public void remove() throws Exception {
        mMockedTodoTypeManager.remove(mTypeEmergency);
        verify(mMockedTodoTypeManager).remove(mTypeEmergency);
    }



}