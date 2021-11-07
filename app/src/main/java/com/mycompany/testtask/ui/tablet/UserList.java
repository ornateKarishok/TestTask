package com.mycompany.testtask.ui.tablet;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.mycompany.testtask.R;
import com.mycompany.testtask.api.RetrofitBuilder;
import com.mycompany.testtask.models.User;
import com.mycompany.testtask.util.FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserList extends Fragment {

    private RecyclerView recyclerView;
    private BufferedWriter bw;
    private Context context;
    private View view;

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
        this.view = view;
        recyclerView = (RecyclerView) getView().findViewById(R.id.list);
        context = getActivity().getApplicationContext();
    }

    private void printUsers() {
//        Call<List<User>> listCall = new RetrofitBuilder().getApi().getUsers();
//        listCall.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        FileUtil.writeFile(response.body(), getContext());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    TabletUserAdapter adapter = new TabletUserAdapter(context, response.body(), getActivity());
//                    recyclerView.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                try {
//                    BufferedReader br = new BufferedReader(new InputStreamReader(
//                            view.getContext().openFileInput(FileUtil.FILE_NAME)));
//                    StringBuilder objectsStr = new StringBuilder();
//                    String tmp;
//                    Gson gson = new Gson();
//                    while ((tmp = br.readLine()) != null) {
//                        objectsStr.append(tmp);
//                    }
//                    TabletUserAdapter adapter = new TabletUserAdapter(context, Arrays.asList(gson.fromJson(objectsStr.toString(), User[].class)), getActivity());
//                    recyclerView.setAdapter(adapter);
//                } catch (IOException e) {
//                    Toast.makeText(context, "Failure " + t, Toast.LENGTH_LONG).show();
//                    e.printStackTrace();
//                }
//            }
//        });
//
    }
}
