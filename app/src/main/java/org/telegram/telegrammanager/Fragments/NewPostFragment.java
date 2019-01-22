package org.telegram.telegrammanager.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import org.telegram.telegrammanager.R;

public class NewPostFragment extends Fragment {

    ReceiversListFragment rvlf = new ReceiversListFragment();
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_new_post,
                container, false);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.hide();

        FloatingActionButton chooseRecFab = view.findViewById(R.id.receivers_fab);

        EditText messageField = view.findViewById(R.id.message_text);

        chooseRecFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rvlf.message = messageField.getText().toString();
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.main_fragment_container, rvlf);
                fragmentManager.commit();
            }
        });
        return view;
    }


}
