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
import edu.esprit.utils.Statics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hedi
 */
public class EventService {

    public static EventService instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public EventService() {
        req = new ConnectionRequest();
    }

    public ArrayList<Event> affichageEvents() {
        ArrayList<Event> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/event/all";
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
                        float id = Float.parseFloat(obj.get("idEv").toString());
                        String titre = obj.get("titre").toString();
                        String cat = obj.get("cat").toString();
                        String desc = obj.get("desc").toString();
                        String address = obj.get("address").toString();
                        String region = obj.get("region").toString();
                        float nbPlace = Float.parseFloat(obj.get("nbPlace").toString());
                        String date = obj.get("date").toString().substring(0, 10);

                        Event ev = new Event((int) id, (int) nbPlace, titre, cat, desc, address, region, date);

                        result.add(ev);
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
