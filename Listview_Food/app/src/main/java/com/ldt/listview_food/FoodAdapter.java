package com.ldt.listview_food;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FoodAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Food> foodList;

    public FoodAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView tvName, tvDescription, tvPrice;
        ImageView image;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view == null) { // không có trong màn hình
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();

            // ánh xạ view
            holder.tvName = (TextView) view.findViewById(R.id.tvName);
            holder.tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            holder.tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            holder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);// truyền vào trang thái ánh xạ của view
        } else {
            holder = (ViewHolder) view.getTag(); // lấy trạng thái ánh xạ của biến view
        }

        // gán giá trị
        Food food = foodList.get(i);

        holder.tvName.setText(food.getName());
        holder.tvDescription.setText(food.getDescription());
        holder.tvPrice.setText(food.getPrice());
        holder.image.setImageResource(food.getImg());
        return view;
    }
}
