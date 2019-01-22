package org.telegram.telegrammanager.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.telegram.telegrammanager.R;

public class NewPostFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_new_post,
                container, false);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.hide();
        return view;
    }


}
