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
import edu.esprit.entities.Cours;
import edu.esprit.utils.Statics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CoursService {
    
    
    //creation d'instance
    public static CoursService instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static CoursService getInstance() {
        if (instance == null) {
            instance = new CoursService();
        }
        return instance;
    }

    public CoursService() {
        req = new ConnectionRequest();
    }

    public ArrayList<Cours> affichageCours() {
        ArrayList<Cours> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/cours/all";
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
                        float id = Float.parseFloat(obj.get("idC").toString());
                        String titre = obj.get("titre").toString();
                        String cat = obj.get("cat").toString();
                        String desc = obj.get("desc").toString();
                        String sousCat = obj.get("souscat").toString();
                        float niveau = Float.parseFloat(obj.get("niveau").toString());
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        String date = obj.get("date").toString().substring(0, 10);
                        Cours p = new Cours((int)id,(int)niveau,prix,cat,titre,sousCat,desc,date);

                        result.add(p);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        //executer la requete
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }      
}
