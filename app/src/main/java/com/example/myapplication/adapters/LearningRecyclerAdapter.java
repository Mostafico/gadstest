package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.R;
import com.example.myapplication.data.Student;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearningRecyclerAdapter extends RecyclerView.Adapter<LearningRecyclerAdapter.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private final List<Student> mStudents;
    public LearningRecyclerAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mStudents = DataManager.mStudentsLearning;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.learning_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = mStudents.get(position);
        holder.tvName.setText(student.getName());
        holder.tvDetails.setText(student.getHours() + " learning hours, " + student.getCountry() + ".");
        holder.showImage(student.getBadgeUrl());
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView tvName;
        public final TextView tvDetails;
        public final ImageView badge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDetails = itemView.findViewById(R.id.tv_details);
            badge = itemView.findViewById(R.id.badge_image);
        }
        public void showImage(String imageUrl){
            Picasso.get().load(imageUrl).into(badge);
        }
    }
}
