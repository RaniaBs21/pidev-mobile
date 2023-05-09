/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import edu.esprit.entities.Cours;
import edu.esprit.entities.Event;
import edu.esprit.entities.Quiz;
import edu.esprit.utils.Statics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hedi
 */
public class QuizService {

    public static QuizService instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static QuizService getInstance() {
        if (instance == null) {
            instance = new QuizService();
        }
        return instance;
    }

    public QuizService() {
        req = new ConnectionRequest();
    }

    public ArrayList<Quiz> afficheQuizes() {
        ArrayList<Quiz> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/quiz/all";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapCourss = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCourss.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        float id = Float.parseFloat(obj.get("idQuiz").toString());
                        String question = obj.get("question").toString();
                        String opt1 = obj.get("option1").toString();
                        String opt2 = obj.get("option2").toString();
                        String opt3 = obj.get("option3").toString();
                        String opt4 = obj.get("option4").toString();
                        String optC = obj.get("reponseCorrecte").toString();

                        Quiz q = new Quiz((int) id, opt1,opt2,opt3,opt4,optC,question);

                        result.add(q);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
}
