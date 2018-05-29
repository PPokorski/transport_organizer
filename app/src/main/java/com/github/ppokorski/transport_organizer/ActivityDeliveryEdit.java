package com.github.ppokorski.transport_organizer;

import android.content.DialogInterface;
import android.content.Intent;
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
    int reporter_type = DONOR_COMPANY;
    private Intent result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_edit);

        transport = new Transport();
        transport = getIntent().getExtras().getParcelable("transport");
        boolean new_delivery = (transport == null);

        TextView headline = (TextView) findViewById(R.id.headline_deliveries_edit);
        if(new_delivery){
            headline.setText(getResources().getString(R.string.edit_activity_new));
        }
        else
        {
            headline.setText(getResources().getString(R.string.edit_activity_edit));
        }

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

        if (!new_delivery)
        {
            txt_name.setText(transport.getName());
            txt_from.setText(transport.getPickupAddress());
            txt_to.setText(transport.getTargetAddress());
            rg_size.setSelectedItem(transport.getSize());
            rg_status.setSelectedItem(transport.getStatus());

            String company_name, person_name, address, phone_number;

            if (transport.getGuest() != null)
            {
                txt_person_name.setText(transport.getGuest().getName());
                txt_address.setText(transport.getGuest().getAddress());
                txt_phone_number.setText(transport.getGuest().getPhoneNumber());

                ((RadioButton) findViewById(R.id.rb_guest)).performClick();
            }
            else if (transport.getDonor() != null)
            {
                txt_person_name.setText(transport.getDonor().getContactName());
                txt_address.setText(transport.getDonor().getAddress());
                txt_phone_number.setText(transport.getDonor().getPhoneNumber());

                ((RadioButton) findViewById(R.id.rb_donor)).performClick();
                if (!transport.getDonor().getCompanyName().isEmpty())
                {
                    txt_company_name.setText(transport.getDonor().getCompanyName());

                    ((RadioButton) findViewById(R.id.rb_company)).performClick();
                }
                else
                {
                    ((RadioButton) findViewById(R.id.rb_person)).performClick();
                }
            }
        }

        result = new Intent();
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
                        result.putExtra("transport", transport);
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
        transport.setName(((TextView) findViewById(R.id.transport_name)).getText().toString());
        transport.setPickupAddress(((TextView) findViewById(R.id.transport_from)).getText().toString());
        transport.setTargetAddress(((TextView) findViewById(R.id.transport_to)).getText().toString());

        switch (reporter_type) {
            case GUEST:
                Guest guest = new Guest();
                guest.setName(((TextView) findViewById(R.id.txt_person_name)).getText().toString());
                guest.setAddress(((TextView) findViewById(R.id.txt_address)).getText().toString());
                guest.setPhoneNumber(((TextView) findViewById(R.id.txt_phone_number)).getText().toString());
                transport.setDonor(null);
                transport.setGuest(guest);
                break;

            case DONOR_COMPANY:
                Donor company = new Donor();
                company.setCompanyName(((TextView) findViewById(R.id.txt_company_name)).getText().toString());
                company.setContactName(((TextView) findViewById(R.id.txt_person_name)).getText().toString());
                company.setAddress(((TextView) findViewById(R.id.txt_address)).getText().toString());
                company.setPhoneNumber(((TextView) findViewById(R.id.txt_phone_number)).getText().toString());
                transport.setGuest(null);
                transport.setDonor(company);
                break;

            case DONOR_PERSON:
                Donor person = new Donor();
                person.setCompanyName("");
                person.setContactName(((TextView) findViewById(R.id.txt_person_name)).getText().toString());
                person.setAddress(((TextView) findViewById(R.id.txt_address)).getText().toString());
                person.setPhoneNumber(((TextView) findViewById(R.id.txt_phone_number)).getText().toString());
                transport.setGuest(null);
                transport.setDonor(person);
                break;
        }
    }
}
