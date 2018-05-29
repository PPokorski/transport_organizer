package com.github.ppokorski.transport_organizer.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class EnumRadioGroup extends RadioGroup {

    private ArrayList<Enum<?>> list;

    EnumRadioGroup(Context context) {
        super(context);
        this.initAdapter();
    }

    EnumRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initAdapter();
    }

    private void initAdapter() {
        list = new ArrayList<>();
    }

    public <T extends Enum<T>> void setEnum(Class<T> e) {
        this.removeAllViews();
        for (T t : e.getEnumConstants())
        {
            this.list.add(t);
            RadioButton button = new RadioButton(getContext());
            button.setText(t.toString());
            button.setId(t.ordinal());
            this.addView(button);
        }
    }

    public <T extends Enum<T>> T getSelectedItem(Class<T> e) {
        return (T) list.get(this.getCheckedRadioButtonId());
    }

    public void setSelectedItem(Enum<?> e) {
        this.check(this.list.indexOf(e));
    }
}
