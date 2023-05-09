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
import com.codename1.io.rest.Rest;
import com.codename1.ui.events.ActionListener;
import edu.esprit.entities.Post;
import edu.esprit.entities.Commentaire;
import edu.esprit.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hedi
 */
public class PostService {
    
    public static PostService instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }
        return instance;
    }

    public PostService() {
        req = new ConnectionRequest();
    }

    public void ajoutPost(Post p) {
        String url = Statics.BASE_URL + "/post/new?desc=" + p.getDesc();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data ajout == " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Post> affichagePosts() {
        ArrayList<Post> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/post/all";
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
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        float id = Float.parseFloat(obj.get("idpost").toString());
                        String desc = obj.get("description").toString();
                        String image = obj.get("image").toString();
                        String date = obj.get("date").toString().substring(0, 10);
                        float nbCom = Float.parseFloat(obj.get("nbcom").toString());

                        Post p = new Post();
                        p.setIdPost((int) id);
                        p.setDesc(desc);
                        p.setImage(image);
                        p.setDate(date);
                        p.setNbComm((int)nbCom);

                        result.add(p);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    }

    public Post DetailPost(int id, Post p) {
        String url = Statics.BASE_URL + "/post/get/" + id;
        req.setUrl(url);        
        req.addResponseListener(((evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String desc = obj.get("description").toString();
                        String image = obj.get("image").toString();
                        String date = obj.get("date").toString();
                        String dateString = formatter.format(date);
                        float nbCom = Float.parseFloat(obj.get("nbcom").toString());
                        
                        p.setIdPost((int) id);
                        p.setDesc(desc);
                        p.setImage(image);
                        p.setDate(dateString);
                        p.setNbComm((int)nbCom);

            } catch (IOException ex) {
                System.out.println("error related to sql :( " + ex.getMessage());
            }
        }));
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }
}
