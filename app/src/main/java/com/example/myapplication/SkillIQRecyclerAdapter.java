package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SkillIQRecyclerAdapter extends RecyclerView.Adapter<SkillIQRecyclerAdapter.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private final List<Student> mStudents;
    public SkillIQRecyclerAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mStudents = DataManager.mStudentsSkillIQ;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.skilliq_card, parent, false);
        return new SkillIQRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = mStudents.get(position);
        holder.tvName.setText(student.getName());
        holder.tvDetails.setText(student.getScore() + " skill IQ Score, " + student.getCountry() + ".");
    }


    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvName;
        public final TextView tvDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDetails = itemView.findViewById(R.id.tv_details);
        }
    }
}
