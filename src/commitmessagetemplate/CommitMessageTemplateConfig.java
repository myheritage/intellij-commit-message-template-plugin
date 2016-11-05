package commitmessagetemplate;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Created by matan.goren on 24-Sep-16.
 */
@State(
        name = "CommitMessageTemplateConfig",
        storages = {
                @Storage("CommitMessageTemplateConfig.xml")}
)
public class CommitMessageTemplateConfig implements PersistentStateComponent<CommitMessageTemplateConfig> {

    public String templateName = "";

    CommitMessageTemplateConfig() { }

    public String getTemplateName() {
        if (templateName == null) {
            templateName = "";
        }
        return templateName;
    }

    void setTemplateName(String templateName) {
        this.templateName = templateName;
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
    public static CommitMessageTemplateConfig getInstance(Project project) {
        CommitMessageTemplateConfig sfec = ServiceManager.getService(project, CommitMessageTemplateConfig.class);
        return sfec;
    }
}
