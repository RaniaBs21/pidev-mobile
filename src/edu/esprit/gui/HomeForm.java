/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Question_ass;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{

    public HomeForm() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Interface Assistance"));

        Button btnAddQuestion = new Button("Add Question");
  

        Button btnListQuestions = new Button("List Questions");

        btnListQuestions.addActionListener(e-> new ListQuestionForm(this).show());
        btnAddQuestion.addActionListener(e-> new AddQuestionForm(this).show());
        addAll(btnListQuestions,btnAddQuestion);
        
        
    }
    
    
}
