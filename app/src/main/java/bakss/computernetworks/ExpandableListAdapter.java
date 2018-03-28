package bakss.computernetworks;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

// для раскрывающегося списка используем базовый адаптер
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> header;
    private HashMap<String, List<String>> child;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this.header = listDataHeader;
        this.child = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        // возвращаем дочерний элемент
        return this.child.get(this.header.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // получаем текст дочернего элемента
        final String childText = (String) getChild(groupPosition, childPosition);

        // задаем разметку дочернего элемента
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.childs, parent, false);
        }

        TextView child_text = (TextView) convertView.findViewById(R.id.child);
        // задаем текст дочернего элемента
        child_text.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // возвращаем количество дочерних элементов
        return this.child.get(this.header.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // получаем позицию заголовка
        return this.header.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // получаем количество заголовков
        return this.header.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        // получаем текст заголовка
        String headerTitle = (String) getGroup(groupPosition);

        // задаем разметку заголовка
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.header, parent, false);
        }

        TextView header_text = (TextView) convertView.findViewById(R.id.header);
        // задаем текст заголовку
        header_text.setText(headerTitle);

        // если заголовок раскрыт то меняем текст на жирный и меняем иконку
        if (isExpanded) {
            header_text.setTypeface(null, Typeface.BOLD);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.ic_up, 0);
        } else {
            // если заголовок не раскрыт то меняем текст на нормальный и меняем иконку

            header_text.setTypeface(null, Typeface.NORMAL);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.ic_down, 0);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}

