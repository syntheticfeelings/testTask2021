package com.syntheticfeelingss.demo.service;

import com.google.gson.Gson;
import com.syntheticfeelingss.demo.model.User;
import com.syntheticfeelingss.demo.repository.UserRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void startApp() {
        Gson gson = new Gson();
        List<User> users = new ArrayList<User>();
        try {
            URL url = new URL("https://randomuser.me/api/?inc=name&results=50");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                scanner.close();

                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);

                JSONArray arr = (JSONArray) data_obj.get("results");

                for (int i = 0; i < arr.size(); i++) {
                    addUser(gson.fromJson(arr.get(i).toString(), User.class));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
