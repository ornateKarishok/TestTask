package com.mycompany.testtask.util;

import android.content.Context;

import com.google.gson.Gson;
import com.mycompany.testtask.models.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
    public static final String FILE_NAME = "FILENAME.obj";

    public static void writeFile(List<User> list, Context context) throws IOException {
        Gson gson = new Gson();
        String objectsStr = gson.toJson(list);
        File file = new File(context.getFilesDir(), FILE_NAME);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(objectsStr);
        bufferedWriter.close();
    }

    public static List<User> readFile(Context context) {
        BufferedReader br;
        List<User> userList = new ArrayList<>();
        try {
            br = new BufferedReader(new InputStreamReader(
                    context.openFileInput(FILE_NAME)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return userList;
        }

        StringBuilder objectsStr = new StringBuilder();
        String tmp = "";
        Gson gson = new Gson();
        while (true) {
            try {
                if ((tmp = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            objectsStr.append(tmp);
        }
        userList = Arrays.asList(gson.fromJson(objectsStr.toString(), User[].class));
        return userList;
    }
}