package com.training.todo_list;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.content.pm.PackageInfo;
import android.test.MoreAsserts;

import com.training.todo_list.activities.todo_list.ActivityTodoList;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    private Application application;
    public ApplicationTest() {
        super(Application.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
        application = getApplication();

    }


}