package com.instituto.cuanto.sisgene.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.instituto.cuanto.sisgene.R;

/**
 * Created by Gustavo on 10/10/2015.
 */
    public class PreguntasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activitypreguntas, container, false);
    }
}
