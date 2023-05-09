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
import edu.esprit.entities.Commentaire;
import edu.esprit.entities.Post;
import edu.esprit.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CommentService {
    
    public static CommentService instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }

    public CommentService() {
        req = new ConnectionRequest();
    }

    public void ajoutComment(Commentaire c) {
        String url = Statics.BASE_URL + "/comment/new?contenu=" + c.getContenu();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data ajout == " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Commentaire> affichageComms() {
        ArrayList<Commentaire> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/comment/all";
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
                        float id = Float.parseFloat(obj.get("idcom").toString());
                        String desc = obj.get("contenu").toString();
                        float nblike = Float.parseFloat(obj.get("nblike").toString());
                        float nbdislike = Float.parseFloat(obj.get("nbdislike").toString());
                        String date = obj.get("date").toString();
                        String dateString = formatter.format(date);

                        Commentaire p = new Commentaire();
                        p.setIdPost((int) id);
                        p.setContenu(desc);
                        p.setNbLike((int)nblike);
                        p.setNbDislike((int)nbdislike);
                        p.setDate(dateString);

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
}
