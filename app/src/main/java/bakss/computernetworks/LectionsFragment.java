package bakss.computernetworks;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class LectionsFragment extends Fragment {

    public LectionsFragment() {
    }

    private ArrayList<Topics> topics = new ArrayList();

    ListView topicsList;
    SearchView searchView;
    DisplayFragment displayFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView =
                inflater.inflate(R.layout.fragment_lections, container, false);
        // начальная инициализация списка
        setInitialData();
        // получаем элемент ListView
        topicsList = (ListView) rootView.findViewById(R.id.list_topics);
        // создаем адаптер
        final TopicsAdapter topicAdapter = new TopicsAdapter(rootView.getContext(),topics);
        // устанавливаем адаптер
        topicsList.setAdapter(topicAdapter);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // создаем фрагмент для отображения лекции
                displayFragment = new DisplayFragment();
                // контейнер для передачи данных во фрагмент
                Bundle bundle = new Bundle();
                // добавляем в контейнер имя файла лекции
                bundle.putString("fileName",SelectTopic((int)id));
                // ID для отображения номера лекции
                bundle.putInt("lectionID",(int)id);
                // передаем данные во фрагмент
                displayFragment.setArguments(bundle);

                if (getActivity() != null) {
                    // находим менеджер фрагментов
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.main_frame);
                    // удаляем текущий фрагмент
                    fm.beginTransaction().remove(fragment).commit();
                    // находим главную активность
                    MainActivity ma = (MainActivity) getActivity();
                    // меняем фрагмент методом из главной активности
                    ma.setFragment(displayFragment);
                }
            }
        };
        // задаем списку слушатель на выбор
        topicsList.setOnItemClickListener(itemListener);

        searchView = (SearchView) rootView.findViewById(R.id.searchView);
        // слушатель введеного текста
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                topicAdapter.filterData(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                topicAdapter.filterData(newText);
                return false;
            }
        });

        return rootView;
    }


    private void setInitialData(){
        // заполняем массив массивом с файла arrays.xml
        String[] topicsName = getResources().getStringArray(R.array.name_of_topics);
        // заполняем массив данными
        for (int i = 0; i < 20; i++){
            topics.add(new Topics (getString(R.string.lection) + " " + (i + 1),topicsName[i]));
        }
    }
    // процедура выбора лекции на основе выбранного элемента в списке
    private String SelectTopic(Integer id) {
        String fileName = "";

        switch (id) {
            case 0:
                fileName = "Lec1.html";
                break;

            case 1:
                fileName = "Lec2.html";
                break;

            case 2:
                fileName = "Lec3.html";
                break;

            case 3:
                fileName = "Lec4.html";
                break;

            case 4:
                fileName = "Lec5.html";
                break;

            case 5:
                fileName = "Lec6.html";
                break;

            case 6:
                fileName = "Lec7.html";
                break;

            case 7:
                fileName = "Lec8.htm";
                break;

            case 8:
                fileName = "Lec9.htm";
                break;

            case 9:
                fileName = "Lec10.htm";
                break;

            case 10:
                fileName = "Lec11.htm";
                break;

            case 11:
                fileName = "Lec12.htm";
                break;

            case 12:
                fileName = "Lec13.htm";
                break;

            case 13:
                fileName = "Lec14.htm";
                break;

            case 14:
                fileName = "Lec15.htm";
                break;

            case 15:
                fileName = "Lec16.htm";
                break;

            case 16:
                fileName = "Lec17.htm";
                break;

            case 17:
                fileName = "Lec18.htm";
                break;

            case 18:
                fileName = "Lec19.htm";
                break;

            case 19:
                fileName = "Lec20.htm";
                break;
        }
        return fileName;
    }

}

