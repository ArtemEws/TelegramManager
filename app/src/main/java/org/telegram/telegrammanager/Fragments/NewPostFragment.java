package org.telegram.telegrammanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

        return view;
    }


}
