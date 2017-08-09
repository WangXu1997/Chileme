package com.example.chileme;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstPageFragment extends Fragment {


    private TextView textfirst;
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_first_page, container, false);
        textfirst=(TextView)view.findViewById(R.id.textfirst);
        textfirst.setText(LoginActivity.username);
        return view;
    }

}
