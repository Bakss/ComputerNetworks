package bakss.computernetworks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlossaryFragment extends Fragment {

    private String[] headers, items;
    private static ExpandableListView expandableList;
    private static ExpandableListAdapter adapter;

    public GlossaryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =
                inflater.inflate(R.layout.fragment_glossary, container, false);

        expandableList = (ExpandableListView) rootView.findViewById(R.id.expandableList);
        headers = getResources().getStringArray(R.array.glossary_header);
        items = getResources().getStringArray(R.array.glossary_item);
        expandableList.setGroupIndicator(null);

        setItems();

        return rootView;
    }

    void setItems() {

        ArrayList<String> header = new ArrayList<String>();

        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

        for (int i = 0; i < items.length; i++){
            header.add(headers[i]);
            List<String> child = new ArrayList<String>();
            child.add(items[i]);
            hashMap.put(header.get(i), child);
        }

        adapter = new ExpandableListAdapter(getContext(), header, hashMap);

        expandableList.setAdapter(adapter);
    }

}
