package commitmessagetemplate;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by matan.goren on 23-Sep-16.
 */
public class CommitMessageTemplateConfigurableGUI {
    private JPanel rootPanel;
    private JTextArea commitMessageTextBox;
    private JRadioButton setTemplateRadioButton;
    private JRadioButton loadTemplateFileRadioButton;
    private TextFieldWithBrowseButton templateFilePath;
    private JTextField commentChar;
    private JLabel commentCharLabel;
    private JCheckBox append;
    private CommitMessageTemplateConfig config;
    private FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor("txt");

    void createUI(Project project) {
        config = CommitMessageTemplateConfig.getInstance(project);
        templateFilePath.addBrowseFolderListener("Point to Commit Template File", "Choose .txt file", null,
                descriptor);

        setTemplateRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commitMessageTextBox.setEnabled(true);
                templateFilePath.setEnabled(false);
                commentChar.setEnabled(false);
            }
        });

        loadTemplateFileRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                templateFilePath.setEnabled(true);
                commentChar.setEnabled(true);
                commitMessageTextBox.setEnabled(false);
            }
        });

        if (config != null) {
            commitMessageTextBox.setText(config.getManualTemplate());
            setTemplateRadioButton.setSelected(config.getRadioStatus());
            loadTemplateFileRadioButton.setSelected(!config.getRadioStatus());
            templateFilePath.setText(config.getTemplateFilePath());
            commitMessageTextBox.setEnabled(config.getRadioStatus());
            templateFilePath.setEnabled(!config.getRadioStatus());
            commentChar.setEnabled(!config.getRadioStatus());
            commentChar.setText(config.getCommentChar());
            append.setSelected(config.getAppendMode());
        }
    }

    JPanel getRootPanel() {
        return rootPanel;
    }

    boolean isModified() {
        return !commitMessageTextBox.getText().equals(config.getManualTemplate()) ||
                !setTemplateRadioButton.isSelected() == config.getRadioStatus() ||
                !templateFilePath.getText().equals(config.getTemplateFilePath()) ||
                !commentChar.getText().equals(config.getCommentChar()) ||
                !append.isSelected() == config.getAppendMode();
    }

    void apply() {
        config.setCommitMessage(commitMessageTextBox.getText());
        config.setRadioStatus(setTemplateRadioButton.isSelected());
        config.setTemplateFilePath(templateFilePath.getText());
        config.setCommentChar(commentChar.getText());
        config.setAppend(append.isSelected());
    }

    void reset() {
        commitMessageTextBox.setText(config.getManualTemplate());
        setTemplateRadioButton.setSelected(config.getRadioStatus());
        loadTemplateFileRadioButton.setSelected(!config.getRadioStatus());
        commitMessageTextBox.setEnabled(config.getRadioStatus());
        templateFilePath.setEnabled(!config.getRadioStatus());
        commentChar.setEnabled(!config.getRadioStatus());
        commentChar.setText(config.getCommentChar());
        append.setSelected(config.getAppendMode());
    }


}
