package commitmessagetemplate;

import com.intellij.openapi.project.Project;

import javax.swing.*;

/**
 * Created by matan.goren on 23-Sep-16.
 */
public class CommitMessageTemplateConfigurableGUI {
    private JPanel rootPanel;
    private JTextArea commitMessageTextBox;
    private CommitMessageTemplateConfig config;

    void createUI(Project project) {
        config = CommitMessageTemplateConfig.getInstance(project);
        if (config != null) {
            commitMessageTextBox.setText(config.getCommitMessage());
        }
    }

    JPanel getRootPanel() {
        return rootPanel;
    }

//    public JTextArea getTemplateName() {
//        return commitMessageTextBox;
//    }

    boolean isModified() {
        return !commitMessageTextBox.getText().equals(config.getCommitMessage());
    }

    void apply() {
        config.setCommitMessage(commitMessageTextBox.getText());
    }

    void reset() {
        commitMessageTextBox.setText(config.getCommitMessage());
    }




}
