package com.github.ppokorski.transport_organizer;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.github.ppokorski.transport_organizer.models.AvailableHours;
import com.github.ppokorski.transport_organizer.models.Car;
import com.github.ppokorski.transport_organizer.models.Donor;
import com.github.ppokorski.transport_organizer.models.Event;
import com.github.ppokorski.transport_organizer.models.Guest;
import com.github.ppokorski.transport_organizer.models.MyObjectBox;
import com.github.ppokorski.transport_organizer.models.Transport;
import com.github.ppokorski.transport_organizer.models.Volunteer;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

public class DatabaseHelper {
    private static DatabaseHelper ourInstance;

    BoxStore boxStore;

    public static DatabaseHelper getInstance(Context context) {
        if (ourInstance == null)
        {
            synchronized (DatabaseHelper.class)
            {
                if (ourInstance == null)
                {
                    ourInstance = new DatabaseHelper(context.getApplicationContext());
                }
            }
        }
        return ourInstance;
    }

    private DatabaseHelper(Context context) {
        if (ourInstance != null)
        {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
        boxStore = MyObjectBox.builder().androidContext(context.getApplicationContext()).build();
        if (BuildConfig.DEBUG) {
            boolean started = new AndroidObjectBrowser(boxStore).start(context.getApplicationContext());
            Log.i("ObjectBrowser", "Started: " + started);
        }
    }

    public <T> List<T> getAllObjects(Class<T> tClass) {
        Box<T> box = boxStore.boxFor(tClass);

        return box.getAll();
    }

    public <T> T getObject(Class<T> tClass, long id) {
        Box<T> box = boxStore.boxFor(tClass);

        return box.get(id);
    }

    public <T> long updateOrCreateObject(Class<T> tClass, T object) {
        Box<T> box = boxStore.boxFor(tClass);

        return box.put(object);
    }

    public <T> void deleteObject(Class<T> tClass, T object) {
        Box<T> box = boxStore.boxFor(tClass);

        box.remove(object);
    }

    public <T> void deleteObjectIfNotNull(Class<T> tClass, T object) {
        if (object == null)
        {
            return;
        }
        else
        {
            deleteObject(tClass, object);
        }
    }
}
