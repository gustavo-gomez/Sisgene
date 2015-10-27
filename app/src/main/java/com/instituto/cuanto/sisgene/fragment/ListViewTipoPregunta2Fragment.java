package com.instituto.cuanto.sisgene.fragment;

import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.instituto.cuanto.sisgene.adapter.ListViewTipoPregunta1Adapter;
import com.instituto.cuanto.sisgene.entities.ListViewTipoPregunta1Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 10/10/2015.
 */
public class ListViewTipoPregunta2Fragment extends ListFragment {

    private List<ListViewTipoPregunta1Item> mItems;        // ListView items list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<ListViewTipoPregunta1Item>();
        Resources resources = getResources();
/*
        mItems.add(new ListViewTipoPregunta1Item(resources.getDrawable(R.drawable.ic_face_black_36dp), "Gustavo Gomez"));
        mItems.add(new ListViewTipoPregunta1Item(resources.getDrawable(R.drawable.ic_face_white_36dp), "Jesus Cahuana"));
        mItems.add(new ListViewTipoPregunta1Item(resources.getDrawable(R.drawable.ic_face_black_36dp), "Miguel Gomez"));
        mItems.add(new ListViewTipoPregunta1Item(resources.getDrawable(R.drawable.ic_face_white_36dp), "Gabriela Vega"));
        mItems.add(new ListViewTipoPregunta1Item(resources.getDrawable(R.drawable.ic_face_black_36dp), "Gustavo Gomez"));
        mItems.add(new ListViewTipoPregunta1Item(resources.getDrawable(R.drawable.ic_face_white_36dp), "Miguel Gomez"));
        mItems.add(new ListViewTipoPregunta1Item(resources.getDrawable(R.drawable.ic_face_black_36dp), "Gustavo Gomez"));
        mItems.add(new ListViewTipoPregunta1Item(resources.getDrawable(R.drawable.ic_face_black_36dp), "Julio Perez"));
        mItems.add(new ListViewTipoPregunta1Item(resources.getDrawable(R.drawable.ic_face_white_36dp), "Josue Rios"));
*/
        mItems.add(new ListViewTipoPregunta1Item("Miguel Gomez"));
        mItems.add(new ListViewTipoPregunta1Item("Gustavo Gomez"));
        mItems.add(new ListViewTipoPregunta1Item("Julio Perez"));
        mItems.add(new ListViewTipoPregunta1Item("Josue Rios"));
        mItems.add(new ListViewTipoPregunta1Item("Gustavo "));
        mItems.add(new ListViewTipoPregunta1Item("Jesus Cahuana"));
        mItems.add(new ListViewTipoPregunta1Item("Miguel Gomez"));
        mItems.add(new ListViewTipoPregunta1Item("Gabriela Vega"));
        mItems.add(new ListViewTipoPregunta1Item("Gustavo Gomez"));

        // initialize and set the list adapter
        setListAdapter(new ListViewTipoPregunta1Adapter(getActivity(), mItems));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        ListViewTipoPregunta1Item item = mItems.get(position);

        // do something
        Toast.makeText(getActivity(), item.nombreEntrevistado, Toast.LENGTH_SHORT).show();
    }
}
