package com.mycompany.testtask.ui.tablet;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.mycompany.testtask.Adapter;
import com.mycompany.testtask.R;
import com.mycompany.testtask.TabletAdapter;
import com.mycompany.testtask.api.RetrofitBuilder;
import com.mycompany.testtask.models.Users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserList extends Fragment {

    RecyclerView recyclerView;
    BufferedWriter bw;
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        printUsers();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.users_list_tablet, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) getView().findViewById(R.id.list);
        try {
            bw = new BufferedWriter(new OutputStreamWriter(
                    getView().getContext().openFileOutput("FILENAME.obj", MODE_PRIVATE)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        context = getActivity().getApplicationContext();
    }

//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }

    private void printUsers() {
        Call<List<Users>> listCall = new RetrofitBuilder().getApi().getUsers();
        listCall.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (response.isSuccessful()) {
                    writeFile(response.body());
                    FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                    TabletAdapter adapter = new TabletAdapter(context, response.body(), manager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            getView().getContext().openFileInput("FILENAME.obj")));
                    List<Users> usersList;
                    StringBuilder objectsStr = new StringBuilder();
                    String tmp;
                    Gson gson = new Gson();
                    while ((tmp = br.readLine()) != null) {
                        objectsStr.append(tmp);
                    }
                    usersList = Arrays.asList(gson.fromJson(objectsStr.toString(), Users[].class));
                    Adapter adapter = new Adapter(context, usersList);
                    recyclerView.setAdapter(adapter);
                } catch (IOException e) {
                    Toast.makeText(context, "Failure " + t, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

    }

    void writeFile(List<Users> list) {
        Gson gson = new Gson();
        String objectsStr = gson.toJson(list);
        try {
            bw.write(objectsStr);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
