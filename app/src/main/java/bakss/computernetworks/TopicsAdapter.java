package bakss.computernetworks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TopicsAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Topics> topics, topicsCopy;

    public TopicsAdapter(Context context, ArrayList<Topics> topics) {
        this.topics = topics;
        this.topicsCopy = new ArrayList<Topics>();
        this.topicsCopy.addAll(topics);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public Topics getItem(int position) {
        // возвращаем элемент
        return topics.get(position);
    }

    @Override
    public long getItemId(int position) {
        // возвращаем ID элемента
        return position;
    }

    @Override
    public int getCount() {
        return topics.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // задаем разметку
        View view = inflater.inflate(R.layout.list_item, null);

        TextView numberView = (TextView) view.findViewById(R.id.topicNumber);
        TextView nameView = (TextView) view.findViewById(R.id.topicName);
        // получаем лекцию
        Topics topic = topics.get(position);
        // задаем номер лекции
        numberView.setText(topic.getNumber());
        // задаем имя лекции
        nameView.setText(topic.getName());

        return view;
    }

    // фильтр записей
    public void filterData(String query) {
        query.toLowerCase();
        // очищаем массив
        topics.clear();
        // если строка поиска пуста, то возвращаем записи с массива копии
        if (query.isEmpty()) {
            topics.addAll(topicsCopy);
        } else {
            // перебираем все лекции
            for (Topics topic : topicsCopy) {
                // если строка поиска содержится в имени лекции, то добавляем эту лекцию
                if (topic.getName().toLowerCase().contains(query)) {
                    topics.add(topic);
                }
            }
        }
        // обновляем список
        notifyDataSetChanged();
    }

}
