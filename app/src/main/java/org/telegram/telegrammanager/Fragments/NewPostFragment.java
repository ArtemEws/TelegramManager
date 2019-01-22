package org.telegram.telegrammanager.Fragments;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import org.telegram.telegrammanager.APi.APiHelper;
import org.telegram.telegrammanager.MainActivity;
import org.telegram.telegrammanager.R;

import java.util.Calendar;

public class NewPostFragment extends Fragment {

    Calendar dateAndTime = Calendar.getInstance();
    ReceiversListFragment rvlf = new ReceiversListFragment();

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_post,
                container, false);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.hide();

        FloatingActionButton chooseRecFab = view.findViewById(R.id.receivers_fab);

        EditText messageField = view.findViewById(R.id.message_text);

        Button delayButton = view.findViewById(R.id.delay_button);

        delayButton.setOnClickListener(view12 -> {
//            rvlf.delayed = !rvlf.delayed;
            Toast.makeText(getActivity(), "delayed",
                    Toast.LENGTH_LONG).show();
            new TimePickerDialog(view12.getContext(), t,
                    dateAndTime.get(Calendar.HOUR_OF_DAY),
                    dateAndTime.get(Calendar.MINUTE), true)
                    .show();
        });

        chooseRecFab.setOnClickListener(view1 -> {
            rvlf.message = messageField.getText().toString();
            FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
            fragmentManager.replace(R.id.main_fragment_container, rvlf);
            fragmentManager.commit();
        });
        return view;
    }

    TimePickerDialog.OnTimeSetListener t = (view, hourOfDay, minute) -> {
        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dateAndTime.set(Calendar.MINUTE, minute);
        Toast.makeText(getActivity(), dateAndTime.toString(),
                Toast.LENGTH_LONG).show();
    };

}
