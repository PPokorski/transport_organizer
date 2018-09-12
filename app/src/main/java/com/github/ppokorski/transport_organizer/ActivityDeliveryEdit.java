package com.github.ppokorski.transport_organizer;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.ppokorski.transport_organizer.models.Donor;
import com.github.ppokorski.transport_organizer.models.Guest;
import com.github.ppokorski.transport_organizer.models.Size;
import com.github.ppokorski.transport_organizer.models.Status;
import com.github.ppokorski.transport_organizer.models.Transport;
import com.github.ppokorski.transport_organizer.ui.EnumRadioGroup;

public class ActivityDeliveryEdit extends AppCompatActivity {
    private static final int GUEST = 0;
    private static final int DONOR_PERSON = 1;
    private static final int DONOR_COMPANY = 2;

    private Transport transport;
    private int reporter_type = DONOR_COMPANY;
    private Intent result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_edit);

        long id = getIntent().getExtras().getLong("transport");
        transport = id != 0? DatabaseHelper.getInstance(this).getObject(Transport.class, id) : null;

        TextView headline = (TextView) findViewById(R.id.headline_deliveries_edit);
        TextView txt_name = (TextView) findViewById(R.id.transport_name);
        TextView txt_from = (TextView) findViewById(R.id.transport_from);
        TextView txt_to = (TextView) findViewById(R.id.transport_to);
        final EnumRadioGroup rg_size = (EnumRadioGroup) findViewById(R.id.transport_size);
        rg_size.setEnum(Size.class);
        rg_size.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                transport.setSize(rg_size.getSelectedItem(Size.class));
            }
        });
        final EnumRadioGroup rg_status = (EnumRadioGroup) findViewById(R.id.transport_status);
        rg_status.setEnum(Status.class);
        rg_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                transport.setStatus(rg_status.getSelectedItem(Status.class));
            }
        });

        TextView txt_company_name = (TextView) findViewById(R.id.txt_company_name);
        TextView txt_person_name = (TextView) findViewById(R.id.txt_person_name);
        TextView txt_address = (TextView) findViewById(R.id.txt_address);
        TextView txt_phone_number = (TextView) findViewById(R.id.txt_phone_number);

        if (transport == null){
            headline.setText(getResources().getString(R.string.edit_activity_new));
            transport = new Transport();
        }
        else
        {
            headline.setText(getResources().getString(R.string.edit_activity_edit));
            txt_name.setText(transport.getName());
            txt_from.setText(transport.getPickupAddress());
            txt_to.setText(transport.getTargetAddress());
            rg_size.setSelectedItem(transport.getSize());
            rg_status.setSelectedItem(transport.getStatus());

            String company_name, person_name, address, phone_number;

            if (transport.getGuestObject() != null)
            {
                txt_person_name.setText(transport.getGuestObject().getName());
                txt_address.setText(transport.getGuestObject().getAddress());
                txt_phone_number.setText(transport.getGuestObject().getPhoneNumber());

                ((RadioButton) findViewById(R.id.rb_guest)).performClick();
            }
            else if (transport.getDonorObject() != null)
            {
                txt_person_name.setText(transport.getDonorObject().getContactName());
                txt_address.setText(transport.getDonorObject().getAddress());
                txt_phone_number.setText(transport.getDonorObject().getPhoneNumber());

                ((RadioButton) findViewById(R.id.rb_donor)).performClick();
                if (!transport.getDonorObject().getCompanyName().isEmpty())
                {
                    txt_company_name.setText(transport.getDonorObject().getCompanyName());

                    ((RadioButton) findViewById(R.id.rb_company)).performClick();
                }
                else
                {
                    ((RadioButton) findViewById(R.id.rb_person)).performClick();
                }
            }
        }

        result = new Intent();
        result.putExtra("transport", transport.getId());
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_donor:
                if (checked)
                {
                    findViewById(R.id.rg_donor).setVisibility(View.VISIBLE);
                    if (((RadioButton) findViewById(R.id.rb_company)).isChecked())
                    {
                        reporter_type = DONOR_COMPANY;
                        findViewById(R.id.txt_company_name).setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        reporter_type = DONOR_PERSON;
                        findViewById(R.id.txt_company_name).setVisibility(View.GONE);
                    }
                }
                break;

            case R.id.rb_guest:
                if (checked)
                {
                    reporter_type = GUEST;
                    findViewById(R.id.rg_donor).setVisibility(View.GONE);
                    findViewById(R.id.txt_company_name).setVisibility(View.GONE);
                }
                break;

            case R.id.rb_company:
                if (checked)
                {
                    reporter_type = DONOR_COMPANY;
                    findViewById(R.id.txt_company_name).setVisibility(View.VISIBLE);
                }
                break;

            case R.id.rb_person:
                if (checked)
                {
                    reporter_type = DONOR_PERSON;
                    findViewById(R.id.txt_company_name).setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Save changes?")
                .setMessage("Do you want to save the changes?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        save();
                        setResult(RESULT_OK, result);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(RESULT_CANCELED, result);
                        finish();
                    }
                })
                .show();
    }

    private void save() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);
        transport.setName(((TextView) findViewById(R.id.transport_name)).getText().toString());
        transport.setPickupAddress(((TextView) findViewById(R.id.transport_from)).getText().toString());
        transport.setTargetAddress(((TextView) findViewById(R.id.transport_to)).getText().toString());

        switch (reporter_type) {
            case GUEST:
                Guest guest = transport.getGuestObject();
                if (guest == null)
                {
                     guest = new Guest();
                }
                guest.setName(((TextView) findViewById(R.id.txt_person_name)).getText().toString());
                guest.setAddress(((TextView) findViewById(R.id.txt_address)).getText().toString());
                guest.setPhoneNumber(((TextView) findViewById(R.id.txt_phone_number)).getText().toString());
                databaseHelper.deleteObjectIfNotNull(Donor.class, transport.getDonorObject());
                transport.setDonorObject(null);
                databaseHelper.updateOrCreateObject(Guest.class, guest);
                transport.setGuestObject(guest);
                break;

            case DONOR_COMPANY:
                Donor company = transport.getDonorObject();
                if (company == null)
                {
                    company = new Donor();
                }
                company.setCompanyName(((TextView) findViewById(R.id.txt_company_name)).getText().toString());
                company.setContactName(((TextView) findViewById(R.id.txt_person_name)).getText().toString());
                company.setAddress(((TextView) findViewById(R.id.txt_address)).getText().toString());
                company.setPhoneNumber(((TextView) findViewById(R.id.txt_phone_number)).getText().toString());
                databaseHelper.deleteObjectIfNotNull(Guest.class, transport.getGuestObject());
                transport.setGuestObject(null);
                databaseHelper.updateOrCreateObject(Donor.class, company);
                transport.setDonorObject(company);
                break;

            case DONOR_PERSON:
                Donor person = transport.getDonorObject();
                if (person == null)
                {
                    person = new Donor();
                }
                person.setCompanyName("");
                person.setContactName(((TextView) findViewById(R.id.txt_person_name)).getText().toString());
                person.setAddress(((TextView) findViewById(R.id.txt_address)).getText().toString());
                person.setPhoneNumber(((TextView) findViewById(R.id.txt_phone_number)).getText().toString());
                databaseHelper.deleteObjectIfNotNull(Guest.class, transport.getGuestObject());
                transport.setGuestObject(null);
                databaseHelper.updateOrCreateObject(Donor.class, person);
                transport.setDonorObject(person);
                break;
        }

        DatabaseHelper.getInstance(this).updateOrCreateObject(Transport.class, transport);
        result.putExtra("transport", transport.getId());
    }
}
