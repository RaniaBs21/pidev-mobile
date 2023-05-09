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

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Painter;
import com.codename1.ui.RadioButton;
import com.codename1.ui.geom.GeneralPath;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import edu.esprit.entities.Quiz;
import edu.esprit.services.QuizService;
import java.util.List;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class QuizForm extends BaseForm {

    List<Quiz> quizes = QuizService.getInstance().afficheQuizes();

    public QuizForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    class BgPainter implements Painter {

        private Container parent;
        private Image pic = createTextImage("Quiz", 100, 100);
        private int textHeight;

        public BgPainter(Container parent, int textHeight) {
            this.parent = parent;
            this.textHeight = textHeight;
        }

        @Override
        public void paint(Graphics g, Rectangle rect) {
            int mm1 = Display.getInstance().convertToPixels(2);

            GeneralPath gp = new GeneralPath();
            float x = parent.getX() + mm1;
            float radius = mm1;
            float y = parent.getY() + mm1;
            float widthF = parent.getWidth() - (2 * mm1);
            float heightF = parent.getHeight() - (2 * mm1);
            gp.moveTo(x + radius, y);
            gp.lineTo(x + widthF - radius, y);
            gp.quadTo(x + widthF, y, x + widthF, y + radius);
            gp.lineTo(x + widthF, y + heightF - radius);
            gp.quadTo(x + widthF, y + heightF, x + widthF - radius, y + heightF);
            gp.lineTo(x + radius, y + heightF);
            gp.quadTo(x, y + heightF, x, y + heightF - radius);
            gp.lineTo(x, y + radius);
            gp.quadTo(x, y, x + radius, y);
            gp.closePath();

            g.setColor(0xffffff);
            g.setAntiAliased(true);
            int[] clip = g.getClip();
            if (g.isShapeClipSupported()) {
                g.setClip(gp);
            } else {
                // we won't have a round rect but at least we will respect its bounds
                Rectangle r = gp.getBounds();
                g.setClip(r.getX(), r.getY(), r.getWidth(), r.getHeight());
            }
            int pw = pic.getWidth();
            float ratio = ((float) pw) / ((float) pic.getHeight());
            int width = parent.getWidth();
            float height = ((float) width) * ratio;
            int hh = (mm1 * 2) + textHeight;
            if (height < parent.getHeight() - hh) {
                hh = (int) (parent.getHeight() - height);
            }

            g.drawImage(pic, parent.getX(), parent.getY(), width, (int) height);
            g.setColor(0xffffff);
            g.setAlpha(255);
            g.fillRect(parent.getX(), parent.getY() + parent.getHeight() - hh, parent.getWidth(), hh);
            g.fillTriangle(parent.getX(), parent.getY() + parent.getHeight() - hh,
                    parent.getX() + parent.getWidth(), parent.getY() + parent.getHeight() - hh,
                    parent.getX() + parent.getWidth(), parent.getY() + parent.getHeight() - hh - (mm1 * 2));
            g.setClip(clip);
        }

    }

    private Image createTextImage(String text, int width, int height) {
        Image image = Image.createImage(width, height);
        Graphics graphics = image.getGraphics();

        graphics.setColor(0x000000); // Set color to black
        graphics.fillRect(0, 0, width, height); // Fill the entire image with the background color

        graphics.setColor(0xFFFFFF); // Set color to white
        graphics.drawRect(0, 0, width - 1, height - 1); // Draw the rectangle

        Font font = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        graphics.setFont(font);
        graphics.setColor(0xFFFFFF); // Set color to white

        int textX = (width - graphics.getFont().stringWidth(text)) / 2;
        int textY = (height - graphics.getFont().getHeight()) / 2;
        graphics.drawString(text, textX, textY); // Draw the text centered inside the rectangle

        graphics.drawImage(image, 0, 0); // Draw the image onto itself to apply the changes

        return image;
    }

    public QuizForm(Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        installSidemenu(resourceObjectInstance);
        gui_Tabs_1.hideTabs();
        gui_Tabs_1.getContentPane().setUIID("Container");
    }

    @Override
    protected Component createStatusBar() {
        Component c = super.createStatusBar();
        c.getUnselectedStyle().setPadding(0, 0, 0, 0);
        return c;
    }

    private void disableAllButtons(Container container) {
        for (int i = 0; i < container.getComponentCount(); i++) {
            if (container.getComponentAt(i) instanceof Button) {
                Button button = (Button) container.getComponentAt(i);
                button.setEnabled(false);
            }
        }
    }

    public Container create(Resources resourceObjectInstance, Quiz q) {
        Container gui_tab1Root = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        gui_tab1Root.setLayout(new BorderLayout());
        Container gridContainer = new Container(new GridLayout(2, 2));
        Button button1 = new Button(q.getOpt1());
        Button button2 = new Button(q.getOpt2());
        Button button3 = new Button(q.getOpt3());
        Button button4 = new Button(q.getOpt4());
        gridContainer.add(button1);
        gridContainer.add(button2);
        gridContainer.add(button3);
        gridContainer.add(button4);
        button1.addActionListener(e -> {
            if (q.getOpt1().equals(q.getOptC())) {
                button1.getAllStyles().setFgColor(0x00FF00);
            }else
                button1.getAllStyles().setFgColor(0xFF0000);
            button1.repaint();
            disableAllButtons(gridContainer);
        });

        button2.addActionListener(e -> {            
            if (q.getOpt2().equals(q.getOptC())) {
                button2.getAllStyles().setFgColor(0x00FF00);
            }else
                button2.getAllStyles().setFgColor(0xFF0000);
            button2.repaint();
            disableAllButtons(gridContainer);
        });

        button3.addActionListener(e -> {
            if (q.getOpt3().equals(q.getOptC())) {
                button3.getAllStyles().setFgColor(0x00FF00);
            }else
                button3.getAllStyles().setFgColor(0xFF0000);
            button3.repaint();
            disableAllButtons(gridContainer);
        });

        button4.addActionListener(e -> {
            if (q.getOpt4().equals(q.getOptC())) {
                button4.getAllStyles().setFgColor(0x00FF00);
            }else
                button4.getAllStyles().setFgColor(0xFF0000);
            button4.repaint();
            disableAllButtons(gridContainer);
        });
        Container content1 = BoxLayout.encloseY(
                new Label(q.getQuestion(), "WelcomeTitle"),
                new Label(resourceObjectInstance.getImage("welcome-separator.png"), "WelcomeTitle"),
                gridContainer
        );
        content1.setUIID("WelcomeContent");
        gui_tab1Root.add(BorderLayout.SOUTH, content1);
        gui_tab1Root.getUnselectedStyle().setBgPainter(new BgPainter(gui_tab1Root, content1.getPreferredH()
                + content1.getUnselectedStyle().getPaddingTop()
                + content1.getUnselectedStyle().getPaddingBottom()
                + content1.getUnselectedStyle().getMarginTop()
                + content1.getUnselectedStyle().getMarginBottom()));
        return gui_tab1Root;
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Tabs gui_Tabs_1 = new com.codename1.ui.Tabs();
    private com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Container gui_Container_6 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_1.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {

        private com.codename1.ui.Component cmp;

        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if (sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }
            if (sourceComponent == gui_Button_1) {
                onButton_1ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setUIID("Welcome");
        setTitle("");
        setName("WalkthruForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Tabs_1);
        ButtonGroup bg = new ButtonGroup();
        gui_Tabs_1.setName("Tabs_1");
        for (Quiz q : quizes) {
            gui_Tabs_1.addTab("Tab", create(resourceObjectInstance, q));
        }
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_4);
        gui_Container_4.setName("Container_4");
        gui_Container_4.addComponent(gui_Container_6);
        gui_Container_6.setUIID("GetStartedButton");
        gui_Container_6.setName("Container_6");
        ((com.codename1.ui.layouts.FlowLayout) gui_Container_6.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        ((com.codename1.ui.layouts.FlowLayout) gui_Container_6.getLayout()).setValign(com.codename1.ui.Component.CENTER);
        gui_Container_6.addComponent(gui_Button_1);
        gui_Container_6.addComponent(gui_Label_1);
        gui_Button_1.setText("Jump to the results.");
        gui_Button_1.setUIID("GetStartedButton");
        gui_Button_1.setName("Button_1");
        gui_Button_1.setTextPosition(com.codename1.ui.Component.LEFT);
        gui_Label_1.setUIID("GetStartedRedArrow");
        gui_Label_1.setName("Label_1");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Label_1, "".charAt(0));
        gui_Container_6.setUIID("GetStartedButton");
        gui_Container_6.setName("Container_6");
        ((com.codename1.ui.layouts.FlowLayout) gui_Container_6.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        ((com.codename1.ui.layouts.FlowLayout) gui_Container_6.getLayout()).setValign(com.codename1.ui.Component.CENTER);
        gui_Container_4.setName("Container_4");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void ontab3ActionEvent(com.codename1.ui.events.ActionEvent ev) {
    }

    public void onButton_1ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        new SignInForm().show();
    }

}
