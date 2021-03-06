package com.example.myapplication.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.R;
import com.example.myapplication.adapters.SkillIQRecyclerAdapter;
import com.example.myapplication.data.Student;
import com.example.myapplication.services.ServiceBuilder;
import com.example.myapplication.services.SkillIQService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillFragment extends Fragment {
    private SkillIQRecyclerAdapter mRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerAdapter = new SkillIQRecyclerAdapter(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mRecyclerAdapter);

        getListFromAPI();
        return root;
    }

    private void getListFromAPI() {
        SkillIQService taskService = ServiceBuilder.builderService(SkillIQService.class);
        final Call<List<Student>> call = taskService.getStudents();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                DataManager.mStudentsSkillIQ.addAll(response.body());
                mRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(getContext(), "Cannot retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
