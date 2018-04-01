package bakss.computernetworks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlossaryFragment extends Fragment {

    private String[] headers, items;
    private static ExpandableListView expandableList;
    private static ExpandableListAdapter adapter;
    SearchView search;

    public GlossaryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =
                inflater.inflate(R.layout.fragment_glossary, container, false);

        expandableList = (ExpandableListView) rootView.findViewById(R.id.expandableList);
        // получаем массивы с файла arrays.xml
        headers = getResources().getStringArray(R.array.glossary_header);
        items = getResources().getStringArray(R.array.glossary_item);
        // убираем обычную иконку у элементов списка
        expandableList.setGroupIndicator(null);
        // заполняем массивы и устанавливаем адаптер
        setItems();

        search = (SearchView) rootView.findViewById(R.id.search);
        // фильтр
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filterData(query);
                search.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filterData(newText);
                return true;
            }
        });

        return rootView;
    }

    void setItems() {

        ArrayList<String> header = new ArrayList<String>();

        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
        // заполняем массивы
        for (int i = 0; i < items.length; i++){
            header.add(headers[i]);
            List<String> child = new ArrayList<String>();
            child.add(items[i]);
            hashMap.put(header.get(i), child);
        }
        // создаем адаптер
        adapter = new ExpandableListAdapter(getContext(), header, hashMap);
        // устанавливаем адаптер
        expandableList.setAdapter(adapter);
    }

}
