package bakss.computernetworks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TopicsAdapter extends ArrayAdapter<Topics> {

    private LayoutInflater inflater;
    private int layout;
    private List<Topics> topics;

    public TopicsAdapter(Context context, int resource, List<Topics> topics) {
        super(context, resource, topics);
        this.topics = topics;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView numberView = (TextView) view.findViewById(R.id.topicNumber);
        TextView nameView = (TextView) view.findViewById(R.id.topicName);

        Topics topic = topics.get(position);

        numberView.setText(topic.getNumber());
        nameView.setText(topic.getName());

        return view;
    }

}
