package commitmessagetemplate;

import com.intellij.openapi.project.Project;

import javax.swing.*;

/**
 * Created by matan.goren on 23-Sep-16.
 */
public class CommitMessageTemplateConfigurableGUI {
    private JPanel rootPanel;
    private JTextArea textArea1;
    private CommitMessageTemplateConfig mConfig;

    CommitMessageTemplateConfigurableGUI() {

    }

    public void createUI(Project project) {
        mConfig = CommitMessageTemplateConfig.getInstance(project);
        textArea1.setText(mConfig.getTemplateName());
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JTextArea getTemplateName() {
        return textArea1;
    }

    public boolean isModified() {
        boolean modified = false;
        modified |= !textArea1.getText().equals(mConfig.getTemplateName());
        return modified;
    }

    public void apply() {
        mConfig.setTemplateName(textArea1.getText());
    }

    public void reset() {
        textArea1.setText(mConfig.getTemplateName());
    }




}
