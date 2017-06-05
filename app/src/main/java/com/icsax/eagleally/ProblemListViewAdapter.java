package com.icsax.eagleally;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by icsax on 5/25/2017.
 */

public class ProblemListViewAdapter extends ArrayAdapter<Problem>{
    private Context context;
    private List<Problem> problems;
    private int resource;

    public ProblemListViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Problem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.problems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, parent, false);

        TextView idTextView = (TextView) view.findViewById(R.id.problemIdTextView);
        TextView statusTextView = (TextView) view.findViewById(R.id.statusTextView);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.problemIdTextView);
        TextView nameTextView = (TextView) view.findViewById(R.id.nameIdView);

        idTextView.setText(problems.get(position).getId());
        descriptionTextView.setText(problems.get(position).getProblem());
        nameTextView.setText(problems.get(position).getName());

        return view;
    }
}
