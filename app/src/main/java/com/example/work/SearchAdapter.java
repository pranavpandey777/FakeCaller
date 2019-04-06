package com.example.work;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
public class SearchAdapter extends ArrayAdapter<Search> {
    private Context context;
    private int resourceId;
    private ArrayList<Search> items, tempItems, suggestions;
    String res;
    public SearchAdapter(@NonNull Context context, int resourceId, ArrayList<Search> items,String res) {
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
        this.res=res;
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
if(res.equals("search")) {
    View view = convertView;
    try {
        if (convertView == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(resourceId, parent, false);


        }
        Search fruit = getItem(position);
        TextView name = view.findViewById(R.id.text);
        TextView num = view.findViewById(R.id.num);

        name.setText(fruit.getName());
        num.setText(fruit.getNumber());


    } catch (Exception e) {
        e.printStackTrace();
    }
    return view;
}else if(res.equals("dial")){
}
return null;
    }
    @Nullable
    @Override
    public Search getItem(int position) {
        return items.get(position);
    }
    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @NonNull
    @Override
    public Filter getFilter() {
        return fruitFilter;
    }
    private Filter fruitFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            if(res.equals("search")){

                Search fruit = (Search) resultValue;
                return fruit.getName();
            }else if(res.equals("dial")){

                Search fruit = (Search) resultValue;
                return fruit.getNumber();

            }

          return null;
        }
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if(res.equals("search")) {
                if (charSequence != null) {
                    suggestions.clear();
                    for (Search fruit : tempItems) {
                        if (fruit.getName().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                            suggestions.add(fruit);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }else if(res.equals("dial")){
                if (charSequence != null) {
                    suggestions.clear();
                    for (Search fruit : tempItems) {
                        if (fruit.getNumber().startsWith(charSequence.toString())) {
                            suggestions.add(fruit);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }


            }
            return null;
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<Search> tempValues = (ArrayList<Search>) filterResults.values;
            if (filterResults != null && filterResults.count > 0) {
                clear();
                for (Search fruitObj : tempValues) {
                    add(fruitObj);
                    notifyDataSetChanged();
                }
            } else {
                clear();
                notifyDataSetChanged();
            }
        }
    };
}