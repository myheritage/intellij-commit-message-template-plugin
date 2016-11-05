package commitmessagetemplate;

/**
 * Created by matan.goren on 23-Sep-16.
 */

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class CommitMessageTemplateConfigurable implements SearchableConfigurable {

    private CommitMessageTemplateConfigurableGUI mGUI;
    private final CommitMessageTemplateConfig mConfig;

    private final Project mProject;

    public CommitMessageTemplateConfigurable(@NotNull Project project) {
        mProject = project;
        mConfig = CommitMessageTemplateConfig.getInstance(project);
    }


    @Nls
    @Override
    public String getDisplayName() {
        return "Commit Message Template Plugin";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "preference.CommitMessageTemplateConfigurable";
    }

    @NotNull
    @Override
    public String getId() {
        return "preference.CommitMessageTemplateConfigurable";
    }

    @Nullable
    @Override
    public Runnable enableSearch(String s) {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mGUI = new CommitMessageTemplateConfigurableGUI();
        mGUI.createUI(mProject);
        return mGUI.getRootPanel();
    }

    @Override
    public boolean isModified() {
        return mGUI.isModified();
    }

    @Override
    public void apply() throws ConfigurationException {
        mGUI.apply();
    }

    @Override
    public void reset() {
        mGUI.reset();
    }

    @Override
    public void disposeUIResources() {
        mGUI = null;
    }


}
