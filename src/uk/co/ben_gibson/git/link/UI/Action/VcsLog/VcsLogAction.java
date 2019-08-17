package uk.co.ben_gibson.git.link.UI.Action.VcsLog;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.vcs.log.VcsFullCommitDetails;
import com.intellij.vcs.log.VcsLog;
import com.intellij.vcs.log.VcsLogDataKeys;
import org.jetbrains.annotations.NotNull;
import uk.co.ben_gibson.git.link.Git.Commit;
import uk.co.ben_gibson.git.link.UI.Action.Action;

import java.util.List;

/**
 * An action triggered from an VCS log toolbar.
 */
abstract class VcsLogAction extends Action
{
    protected abstract void perform(@NotNull Project project, @NotNull Commit commit, @NotNull VirtualFile file);


    public void actionPerformed(Project project, AnActionEvent event)
    {
        VcsLog vcsLog = event.getData(VcsLogDataKeys.VCS_LOG);

        if (vcsLog == null) {
            return;
        }

        VcsFullCommitDetails vcsCommit = vcsLog.getSelectedDetails().get(0);

        Commit commit = new Commit(vcsCommit.getId().toShortString());

        this.perform(project, commit, vcsCommit.getRoot());
    }


    protected boolean shouldActionBeEnabled(AnActionEvent event)
    {
        VcsLog log = event.getData(VcsLogDataKeys.VCS_LOG);

        if (log == null) {
            return false;
        }

        List<VcsFullCommitDetails> commits = log.getSelectedDetails();

        return commits.size() == 1;
    }
}