package commitmessagetemplate;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;

import org.jetbrains.annotations.Nullable;

import commitmessagetemplate.CommitMessageTemplateConfig.CommitState;


@State(
        name = "CommitMessageTemplateConfig",
        storages = {
                @Storage("CommitMessageTemplateConfig.xml")}
)
public class CommitMessageTemplateConfig implements PersistentStateComponent<CommitState> {

    private CommitState cmState = new CommitState();

    String getCommitMessage() {
        return cmState.commitMessage;
    }

    void setCommitMessage(String commitMessage) {
        cmState.commitMessage = commitMessage;
    }

    @Nullable
    @Override
    public CommitState getState() {
        return cmState;
    }

    @Override
    public void loadState(CommitState state) {
        cmState = state;
    }

    @Nullable
    static CommitMessageTemplateConfig getInstance(Project project) {
        return ServiceManager.getService(project, CommitMessageTemplateConfig.class);
    }

    public static class CommitState{
        public String commitMessage = "";
    }
}
