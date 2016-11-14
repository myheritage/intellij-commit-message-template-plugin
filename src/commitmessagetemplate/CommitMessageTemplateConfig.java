package commitmessagetemplate;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;


@State(
        name = "CommitMessageTemplateConfig",
        storages = {
                @Storage("CommitMessageTemplateConfig.xml")}
)
public class CommitMessageTemplateConfig implements PersistentStateComponent<CommitMessageTemplateConfig> {

    private String commitMessage = "";

    String getCommitMessage() {
        if (commitMessage == null) {
            commitMessage = "";
        }
        return commitMessage;
    }

    void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }

    @Nullable
    @Override
    public CommitMessageTemplateConfig getState() {
        return this;
    }

    @Override
    public void loadState(CommitMessageTemplateConfig commitMessageTemplateConfig) {
        XmlSerializerUtil.copyBean(commitMessageTemplateConfig, this);
    }

    @Nullable
    static CommitMessageTemplateConfig getInstance(Project project) {
        return ServiceManager.getService(project, CommitMessageTemplateConfig.class);
    }
}
