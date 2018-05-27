package com.github.ppokorski.transport_organizer.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.ppokorski.transport_organizer.models.Size;

public class SizeSpinner extends Spinner {
    private ArrayAdapter<Size> adapter_size;

    public SizeSpinner(Context context) {
        super(context);
        this.initAdapter();
    }

    public SizeSpinner(Context context, AttributeSet attribute_set) {
        super(context, attribute_set);
        this.initAdapter();
    }

    public SizeSpinner(Context context, int mode) {
        super(context, mode);
        this.initAdapter();
    }

    public SizeSpinner(Context context, AttributeSet attribute_set, int def_style) {
        super(context, attribute_set, def_style);
        this.initAdapter();
    }

    public SizeSpinner(Context context, AttributeSet attribute_set, int def_style, int mode) {
        super(context, attribute_set, def_style, mode);
        this.initAdapter();
    }

    private void initAdapter() {
        this.adapter_size = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, Size.values());
        this.setAdapter(this.adapter_size);
    }

    @Override
    public Size getSelectedItem() {
        return (Size) super.getSelectedItem();
    }

    public void setSelection(Size size) {
        this.setSelection(this.adapter_size.getPosition(size));
    }
}
