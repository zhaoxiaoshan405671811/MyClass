package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a1.myzuoyue20170407.R;

import java.util.List;

import bean.MyBean;

import static android.R.id.text1;

/**
 * data:2017/4/7
 * author:赵山(1)
 * function:
 */

public class MyAdapter extends BaseAdapter {
    private List<MyBean.ListBean> list;
    private Context context;

    public MyAdapter(List<MyBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler hodler;
        if (convertView==null){
            convertView =View.inflate(context, R.layout.item,null);
            hodler = new ViewHodler();
            hodler.text1 = (TextView) convertView.findViewById(R.id.text01);
            hodler.text2 = (TextView) convertView.findViewById(R.id.text02);
            convertView.setTag(hodler);
        }else {
            hodler = (ViewHodler) convertView.getTag();
        }
            hodler.text1.setText(list.get(position).getSite_name());
            hodler.text2.setText(list.get(position).getAddress());
        return convertView;
    }
    class ViewHodler{
        TextView text1,text2;
    }
}
