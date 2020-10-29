package uk.co.ben_gibson.git.link.UI.Action.Menu;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.co.ben_gibson.git.link.Git.RemoteHost;
import uk.co.ben_gibson.git.link.GitLink;
import uk.co.ben_gibson.git.link.UI.LineSelection;

public class ClipboardMenuAction extends MenuAction {

    @Override
    protected void perform(@NotNull final Project project, @NotNull final VirtualFile file, @Nullable final LineSelection lineSelection) {
        GitLink.getInstance(project).copyFile(file, null, lineSelection, false);
    }

    @Override
    protected String displayName(@NotNull final RemoteHost remoteHost)
    {
        return String.format("Copy %s link", remoteHost.toString());
    }
}
