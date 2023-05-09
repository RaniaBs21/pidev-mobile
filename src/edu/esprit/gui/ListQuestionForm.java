/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Question_ass;
import com.mycompany.myapp.services.ServiceQuestion;
import java.util.ArrayList;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceQuestion;

public class ListQuestionForm extends Form {
    private ArrayList<Question_ass> questions;
    private Container list;

    public ListQuestionForm(Form previous) {
        setTitle("Liste des questions");
        setLayout(BoxLayout.y());
        questions = ServiceQuestion.getInstance().getAllTests();
        list = new Container(BoxLayout.y());
        list.setScrollableY(true);

        for (Question_ass q : questions) {
            MultiButton sp = new MultiButton(q.getType_Q_Ass());
            sp.setTextLine1("type : " + q.getType_Q_Ass());
            sp.setTextLine2("description : " + q.getDescription_Q_Ass());
            list.add(sp);
            sp.addActionListener((evt) -> {
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
        add(list);

        TextField searchField = new TextField();
        Button searchButton = new Button("Rechercher");
        searchButton.addActionListener((evt) -> {
            String searchType = searchField.getText();
            ArrayList<Question_ass> filteredQuestions = ServiceQuestion.getInstance().getQuestionsByType(searchType);
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
    }
}




