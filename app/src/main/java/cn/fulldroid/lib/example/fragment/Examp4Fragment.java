package cn.fulldroid.lib.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Calendar;

import cn.fulldroid.lib.datetimepicker.date.DatePickerDialog;
import cn.fulldroid.lib.datetimepicker.time.RadialPickerLayout;
import cn.fulldroid.lib.datetimepicker.time.TimePickerDialog;
import cn.fulldroid.lib.example.R;

/**
 * Created by MDZZ on 2017/3/22.
 */

public class Examp4Fragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public static final String DATEPICKER_TAG = "datepicker";
    public static final String TIMEPICKER_TAG = "timepicker";

    CheckBox checkBoxVibrate;
    CheckBox checkBoxCloseOnSingleTapDay;
    CheckBox checkBoxCloseOnSingleTapMinute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_examp4, null);
        checkBoxVibrate = (CheckBox) v.findViewById(R.id.checkBoxVibrate);
        checkBoxCloseOnSingleTapDay = (CheckBox) v.findViewById(R.id.checkBoxCloseOnSingleTapDay);
        checkBoxCloseOnSingleTapMinute = (CheckBox) v.findViewById(R.id.checkBoxCloseOnSingleTapMinute);
        initView(v, savedInstanceState);
        return v;
    }

    private void initView(View v, Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), isVibrate());
        final TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false, false);

        v.findViewById(R.id.dateButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePickerDialog.setVibrate(isVibrate());
                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.setCloseOnSingleTapDay(isCloseOnSingleTapDay());
                datePickerDialog.show(getActivity().getSupportFragmentManager(), DATEPICKER_TAG);
            }
        });

        v.findViewById(R.id.timeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.setVibrate(isVibrate());
                timePickerDialog.setCloseOnSingleTapMinute(isCloseOnSingleTapMinute());
                timePickerDialog.show(getActivity().getSupportFragmentManager(), TIMEPICKER_TAG);
            }
        });

        if (savedInstanceState != null) {
            DatePickerDialog dpd = (DatePickerDialog) getActivity().getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener(this);
            }

            TimePickerDialog tpd = (TimePickerDialog) getActivity().getSupportFragmentManager().findFragmentByTag(TIMEPICKER_TAG);
            if (tpd != null) {
                tpd.setOnTimeSetListener(this);
            }
        }
    }


    private boolean isVibrate() {
        return checkBoxVibrate.isChecked();
    }

    private boolean isCloseOnSingleTapDay() {
        return checkBoxCloseOnSingleTapDay.isChecked();
    }

    private boolean isCloseOnSingleTapMinute() {
        return checkBoxCloseOnSingleTapMinute.isChecked();
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        Toast.makeText(getActivity(), "new date:" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        Toast.makeText(getActivity(), "new time:" + hourOfDay + "-" + minute, Toast.LENGTH_LONG).show();
    }
}
