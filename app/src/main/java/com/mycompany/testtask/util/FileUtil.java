package com.mycompany.testtask.util;

import android.content.Context;

import com.google.gson.Gson;
import com.mycompany.testtask.models.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtil {
    private static final String FILE_NAME = "FILENAME.obj";
    public static void writeFile(List<User> list, Context context) throws IOException {
        Gson gson = new Gson();
        String objectsStr = gson.toJson(list);
        File file = new File(context.getFilesDir(), FILE_NAME);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(objectsStr);
        bufferedWriter.close();
    }
}