package com.instituto.cuanto.sisgene.fragment;

import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.instituto.cuanto.sisgene.adapter.ListViewTipoPregunta2Adapter;
import com.instituto.cuanto.sisgene.entities.ListViewTipoPregunta2Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 10/10/2015.
 */
public class ListViewTipoPregunta2Fragment extends ListFragment {

    private List<ListViewTipoPregunta2Item> mItems;        // ListView items list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<>();
        Resources resources = getResources();

        mItems.add(new ListViewTipoPregunta2Item("Miguel Gomez"));
        mItems.add(new ListViewTipoPregunta2Item("Gustavo Gomez"));
        mItems.add(new ListViewTipoPregunta2Item("Julio Perez"));
        mItems.add(new ListViewTipoPregunta2Item("Josue Rios"));
        mItems.add(new ListViewTipoPregunta2Item("Gustavo "));
        mItems.add(new ListViewTipoPregunta2Item("Jesus Cahuana"));
        mItems.add(new ListViewTipoPregunta2Item("Miguel Gomez"));
        mItems.add(new ListViewTipoPregunta2Item("Gabriela Vega"));
        mItems.add(new ListViewTipoPregunta2Item("Gustavo Gomez"));

        // initialize and set the list adapter
        setListAdapter(new ListViewTipoPregunta2Adapter(getActivity(), mItems));
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
        ListViewTipoPregunta2Item item = mItems.get(position);

        // do something
        Toast.makeText(getActivity(), item.nombreEntrevistado, Toast.LENGTH_SHORT).show();
    }
}
