package commitmessagetemplate;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;

import org.jetbrains.annotations.Nullable;

import commitmessagetemplate.CommitMessageTemplateConfig.CommitState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


@State(
        name = "CommitMessageTemplateConfig",
        storages = {
                @Storage("CommitMessageTemplateConfig.xml")}
)
public class CommitMessageTemplateConfig implements PersistentStateComponent<CommitState> {

    private CommitState cmState = new CommitState();

    String getCommitMessage() {
        if (getRadioStatus()) {
            return getManualTemplate();
        } else {
            return OpenFile(getTemplateFilePath());
        }
    }

    String getManualTemplate() {
        return cmState.manualTemplate;
    }

    String getTemplateFilePath() {
        return cmState.templateFilePath;
    }

    boolean getRadioStatus() {
        return cmState.radioState;
    }

    void setCommitMessage(String commitMessage) {
        cmState.manualTemplate = commitMessage;
    }

    void setRadioStatus(boolean radioStatus) {
        cmState.radioState = radioStatus;
    }

    void setTemplateFilePath(String templateFilePath) {
        cmState.templateFilePath = templateFilePath;
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

    public static class CommitState {
        public String manualTemplate = "";
        public boolean radioState = true;
        public String templateFilePath = "";
    }

    private String OpenFile(String configFilePath) {
        String template = "place_holder";
        try {
            StringBuilder sb = new StringBuilder();
            FileReader fr = new FileReader(configFilePath);
            BufferedReader textReader = new BufferedReader(fr);
            int numberOfLines = readLines(configFilePath);
            int i;
            for (i = 0; i < numberOfLines; i++) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(textReader.readLine());
                template = sb.toString();
            }
            textReader.close();
        } catch (IOException e1) {
            template = "Couldn't open file";
        }

        return template;
    }

    private int readLines(String configFilePath) throws IOException {

        FileReader file_to_read = new FileReader(configFilePath);
        BufferedReader bf = new BufferedReader(file_to_read);

        int numberOfLines = 0;

        while (bf.readLine() != null) {
            numberOfLines++;
        }
        bf.close();
        return numberOfLines;
    }
}
