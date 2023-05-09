/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package edu.esprit.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.sun.mail.smtp.SMTPTransport;
import edu.esprit.entities.Cours;
import edu.esprit.entities.Post;
import edu.esprit.services.CoursService;
import edu.esprit.services.PostService;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class ListCoursForm extends BaseForm {

    List<Cours> cours = CoursService.getInstance().affichageCours();

    public ListCoursForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public ListCoursForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        gui_separator1.setShowEvenIfBlank(true);
        gui_Label_1_1_1.setShowEvenIfBlank(true);

        ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        gui_imageContainer1.add(BorderLayout.CENTER, sl);
        sl = new ScaleImageLabel(resourceObjectInstance.getImage("bridge.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        gui_imageContainer2.add(BorderLayout.CENTER, sl);

        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });

        FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
        gui_LA.setIconPosition(BorderLayout.EAST);

        FontImage.setMaterialIcon(gui_newYork, FontImage.MATERIAL_LOCATION_ON);
        gui_newYork.setIconPosition(BorderLayout.EAST);

        gui_Text_Area_2.setRows(2);
        gui_Text_Area_2.setColumns(100);
        gui_Text_Area_2.setGrowByContent(false);
        gui_Text_Area_2.setEditable(false);
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setGrowByContent(false);
        gui_Text_Area_1.setEditable(false);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_LA = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_separator1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_newYork = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Liste des cours");
        setName("TrendingForm");
        for (Cours c : cours) {
            Component but = createItem(resourceObjectInstance, c);
            addComponent(but);
        }
    }// </editor-fold>

    public Component createItem(com.codename1.ui.util.Resources resourceObjectInstance, Cours c) {
        MultiButton gui_Multi_Button_1 = new MultiButton();
        Container gui_imageContainer1 = new Container(new BorderLayout());
        Container gui_Container_2 = new Container(new BorderLayout());
        TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
        Label gui_Button_1 = new Label(String.valueOf(c.getPrix()) + " DT");
        Label gui_separator1 = new com.codename1.ui.Label();
        MultiButton gui_LA = new MultiButton();
        Container gui_Container_1 = new Container(new BorderLayout());
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);
        gui_Multi_Button_1.setUIID("Label");
        gui_Multi_Button_1.setName("Multi_Button_1");
        gui_Multi_Button_1.setIcon(resourceObjectInstance.getImage("contact-c.png"));
        gui_Multi_Button_1.setPropertyValue("line1", c.getTitre());
        gui_Multi_Button_1.setPropertyValue("line2", "Niveau: " + String.valueOf(c.getNiveau()));
        gui_Multi_Button_1.addActionListener(e -> {
            Dialog alert = new Dialog("Reclamer ce cours");
            TextField mail = new TextField();
            Button okButton = new Button("Envoyer");
            okButton.addActionListener((evt) -> {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog ipDialog = ip.showInfiniteBlocking();
                sendMail(resourceObjectInstance, mail.getText(), c);
                ipDialog.dispose();
                Dialog.show("Merci", "Mail envoyé avec succès", new Command("OK"));
                new ListCoursForm().show();
            });
            alert.setLayout(new BorderLayout());
            alert.add(BorderLayout.NORTH, mail);
            alert.add(BorderLayout.SOUTH, okButton);
            alert.show();
        });
        gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
        gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
        gui_LA.setUIID("Label");
        gui_LA.setName("LA");
        gui_LA.setPropertyValue("line1", c.getDate());
        gui_LA.setPropertyValue("line2", c.getCat());
        gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        gui_LA.setPropertyValue("uiid2", "RedLabelRight");
        addComponent(gui_imageContainer1);
        gui_imageContainer1.setName("imageContainer1");
        gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
        gui_Text_Area_1.setText(c.getDesc());
        gui_Text_Area_1.setEditable(false);
        gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_1.setName("Text_Area_1");
        FontImage.setMaterialIcon(gui_Button_1, "0xE227".charAt(0));
        gui_Container_2.setName("Container_2");
        addComponent(gui_separator1);
        return gui_Container_1;
    }

    public void sendMail(Resources res, String mail, Cours c) {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtps.host", "smtp.gmail.com");
            props.put("mail.smtps.auth", "true");
            Session session = Session.getInstance(props, null);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("Demande d'un cours <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, mail.toString());
            msg.setSubject("Application ArtPlus  : Réclamation reçue ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            String txt = "Votre réclamation à propos du cours : " + c.getTitre() + " a été enregistrée avce succès.";
            msg.setText(txt);
            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
            st.connect("smtp.gmail.com", 465, "ploutos.connecte@gmail.com", "zglkfvozmiltnbba");
            st.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //TextField searchField = new TextField();
      //  Button searchButton = new Button("Rechercher");
        //searchButton.addActionListener((evt) -> {
          //  String searchType = searchField.getText();
            /*ArrayList<Question_ass> filteredQuestions = ServiceQuestion.getInstance().getQuestionsByType(searchType);
            list.removeAll();
            if (filteredQuestions.isEmpty()) {
                Dialog.show("Aucune question trouvée", "Aucune question ne correspond à la recherche.", "OK", null);
            } else {
                for (Question_ass q : filteredQuestions) {
                    MultiButton sp = new MultiButton(q.getType_Q_Ass());
                    sp.setTextLine1("type : " + q.getType_Q_Ass());
                    sp.setTextLine2("description : " + q.getDescription_Q_Ass());
                    list.add(sp);
                    sp.addActionListener((evt2) -> {
                        if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
                            if (ServiceQuestion.getInstance().deleteTest(q.getId_Q_Ass())) {
                                Dialog.show("Success", "La question " + q.getType_Q_Ass() + " a été supprimé avec succées", new Command("OK"));
                                previous.showBack();
                            }
                        } else {
                            new UpdateQuestionForm(previous, q, this).show();
                        }
                    });
                }
            }
            revalidate();
        });
        add(searchField);
        add(searchButton);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }*/hhhhhhhhhhhhhhhhhhhhhhhhhhhhh
//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected boolean isCurrentTrending() {
        return true;
    }
}
