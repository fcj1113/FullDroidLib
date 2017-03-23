package cn.fulldroid.lib.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by MDZZ on 2017/3/22.
 */

public class ViewHolder {

    /**
     * ViewHolder模式超简洁写法。用法
     * if (convertView == null) {
     *     convertView = LayoutInflater.from(context).inflate(R.layout.banana_phone, parent, false);
     * }
     * ImageView bananaView = ViewHolder.get(convertView, R.id.banana);
     *
     * @param view
     * @param id
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
