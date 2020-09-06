package com.example.myapplication.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataManager;
import com.example.myapplication.LearningRecyclerAdapter;
import com.example.myapplication.R;
import com.example.myapplication.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LearningFragment extends Fragment {
    public static final String HTTPS_GADSAPI_HEROKUAPP_COM_API_HOURS = "https://gadsapi.herokuapp.com/api/hours";
    String jsonResponse;
    private LearningRecyclerAdapter mRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        new JsonDownload().execute(HTTPS_GADSAPI_HEROKUAPP_COM_API_HOURS);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerAdapter = new LearningRecyclerAdapter(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mRecyclerAdapter);
        return root;
    }
    private class JsonDownload extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                return buffer.toString();

            }catch (MalformedURLException e){

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("Result", s);
            jsonResponse = s;
            parseJsonResponse(s);
        }
    }

    private void parseJsonResponse(String s) {
        try {
            JSONArray array = new JSONArray(s);
            for (int i=0; i < array.length(); i++)
            {
                    JSONObject oneObject = array.getJSONObject(i);
                    // Pulling items from the array
                    String name = oneObject.getString("name");
                    int score = oneObject.getInt("hours");
                    String country = oneObject.getString("country");
                    Student student = new Student(name, score, country);
                    DataManager.mStudentsLearning.add(student);
                    if(mRecyclerAdapter != null){
                        mRecyclerAdapter.notifyDataSetChanged();
                    }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
