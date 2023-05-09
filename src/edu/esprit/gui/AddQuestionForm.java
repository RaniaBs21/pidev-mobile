/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;
import com.codename1.io.FileSystemStorage;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Question_ass;
import com.mycompany.myapp.services.ServiceQuestion;
import java.io.OutputStream;
/**
 *
 * @author akrem
 */
public class AddQuestionForm extends Form {
 public AddQuestionForm(Form previous) {
    setTitle("Ajouter questions");
    setLayout(BoxLayout.y());

    TextField tftype = new TextField("", "type");
    TextField tfdescription = new TextField("", "description");
    Button btnValider = new Button("Add question");
    Button btnDownload = new Button("Download PDF");

    btnValider.addActionListener(evt -> {
        if ((tftype.getText().length() == 0) || (tfdescription.getText().length() == 0))
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        else {
            try {
                Question_ass t = new Question_ass(tftype.getText(), tfdescription.getText());
                if (new ServiceQuestion().addTest(t))
                    Dialog.show("Success", "Question ajouté", new Command("Ok"));
                else
                    Dialog.show("ERROR", "Server error", new Command("OK"));
            } catch (Exception e) {
                Dialog.show("ERROR", "type and descrpition must be string", new Command("Ok"));
            }

        }
    });

    btnDownload.addActionListener(evt -> {
        try {
            downloadPDF(this);
        } catch (Exception e) {
            Dialog.show("Error", "Failed to download PDF: " + e.getMessage(), new Command("OK"));
        }
    });

    addAll(tftype, tfdescription, btnValider, btnDownload);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}

    private void downloadPDF(Form form) throws Exception {
    // Set the file path
    String filePath = FileSystemStorage.getInstance().getAppHomePath() + "questions.pdf";
    // Create an output stream to write the PDF file
    OutputStream outputStream = FileSystemStorage.getInstance().openOutputStream(filePath);

    // Create a new document
    Document document = new Document();
    // Set the PDF writer to write to the output stream
    PdfWriter.getInstance(document, outputStream);

    // Add metadata to the document
    document.addAuthor("Your Name");
    document.addCreator("Your App");
    document.addSubject("Task Questions");
    document.addTitle("Task Questions");

    // Set the document margins
    document.setMargins(36, 36, 36, 36);

    // Open the document
    document.open();

    // Add the questions to the PDF document
    for (Question_ass question : new ServiceQuestion().getAllTests()) {
        // Create a paragraph for the question
        Paragraph nameParagraph = new Paragraph(question.getType_Q_Ass());
        // Set the font size and style for the question
        nameParagraph.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.NORMAL));
        // Add the question paragraph to the document
        document.add(nameParagraph);

        // Create a list for the choices
        List list = new List(List.ORDERED);
        // Set the font size and style for the list items
        Font listItemFont = FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL);
        // Add each choice to the list
        list.add(new ListItem(question.getDescription_Q_Ass(), listItemFont));
        // Add the list to the document
        document.add(list);

        // Add some space between the questions
        document.add(new Paragraph(" "));
    }

    // Close the document
    document.close();

    // Flush and close the output stream
    outputStream.flush();
    outputStream.close();

    if (Dialog.show("PDF Downloaded", "PDF file was saved to " + filePath + ". Do you want to open it?", "Open", "Cancel")) {
        // Open the PDF file
        Display.getInstance().execute(filePath);
    }
    // Return to the previous form
    form.showBack();
}

}
