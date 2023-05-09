/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

/**
 *
 * @author akrem
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Question_ass;
import com.mycompany.myapp.services.ServiceQuestion;



public class UpdateQuestionForm extends Form{

    public UpdateQuestionForm(Form previous, Question_ass que,Form ListQuestionForm) {
        setTitle("modifier question");
        setLayout(BoxLayout.y());
        TextField tftype = new TextField(que.getType_Q_Ass(), "type");            
        TextField tfdescritpion = new TextField(que.getDescription_Q_Ass(), "description");
        Button btnValider = new Button("modifier test");
       btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tftype.getText().length()==0) ||(tfdescritpion.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try{
                        que.setId_Q_Ass(que.getId_Q_Ass());
                        que.setType_Q_Ass(tftype.getText());
                        que.setDescription_Q_Ass(tfdescritpion.getText());
                        if ( new ServiceQuestion().getInstance().updateTest(que))
                            Dialog.show("Success", "Connection accepted", new Command("Ok"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    }catch(Exception e){
                        Dialog.show("ERROR", "name and type must be string", new Command("Ok"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tftype,tfdescritpion,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
            
    
}