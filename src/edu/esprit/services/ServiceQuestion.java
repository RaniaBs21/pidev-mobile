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
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.entities.Question_ass;
/**
 *
 * @author akrem
 */
public class ServiceQuestion {
    public ArrayList<Question_ass> Questions;
    public static ServiceQuestion instance = null;
    public boolean resultOK;
    public ConnectionRequest req;


    public ServiceQuestion(){
        req = new ConnectionRequest();

    }
    public static ServiceQuestion getInstance() {
        if (instance == null) {
            instance = new ServiceQuestion();
        }
        return instance;
    }


    public boolean addTest(Question_ass q) {

        String type = q.getType_Q_Ass();
        String description =  q.getDescription_Q_Ass();

        String url = Statics.BASE_URL + "/questionJSON/new?typeQAss=" + q.getType_Q_Ass() + "&descriptionQAss=" + q.getDescription_Q_Ass();
        //String url = Statics.BASE_URL + "create/" + status + "/" + name;
        ConnectionRequest req = new ConnectionRequest(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
public ArrayList<Question_ass> parseTests(String jsonText) {
    ArrayList<Question_ass> Questions = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> testsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) testsListJson.get("root");
        for (Map<String, Object> obj : list) {
            Question_ass q = new Question_ass();
            float id = Float.parseFloat(obj.get("idQAss").toString());
            q.setId_Q_Ass((int)id);
            q.setType_Q_Ass(obj.get("typeQAss").toString());
            q.setDescription_Q_Ass(obj.get("descriptionQAss").toString());
            Questions.add(q);
            System.out.println(Questions);
        }
    } catch (IOException ex) {
        System.out.println("Error parsing JSON: " + ex.getMessage());
    } catch (NumberFormatException ex) {
        System.out.println("Error parsing question ID: " + ex.getMessage());
    } catch (NullPointerException ex) {
        System.out.println("Error retrieving 'root' element from JSON: " + ex.getMessage());
    }


    return Questions;
}


    public ArrayList<Question_ass> getAllTests() {
        String url = Statics.BASE_URL + "/questionJSON/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Questions = parseTests(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Questions;
    }

public boolean updateTest(Question_ass q)
    {
String url = Statics.BASE_URL + "/questionJSON/" + q.getId_Q_Ass() + "/edit?typeQAss=" + q.getType_Q_Ass() + "&descriptionQAss=" + q.getDescription_Q_Ass();
        req.setUrl(url);
    req.addResponseListener((e) -> {
                        resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
        String str = new String(req.getResponseData());
        //System.out.println("data"+str);
    });
        NetworkManager.getInstance().addToQueueAndWait(req);
return resultOK;
    }


    public boolean deleteTest(int id) {
        String url = Statics.BASE_URL + "/questionJSON/"+ id +"/delete" ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
public ArrayList<Question_ass> getQuestionsByType(String type) {
    ArrayList<Question_ass> questions = getAllTests();
    ArrayList<Question_ass> filteredQuestions = new ArrayList<>();
    for (Question_ass q : questions) {
        if (q.getType_Q_Ass().equals(type)) {
            filteredQuestions.add(q);
        }
    }
    return filteredQuestions;
}

}
