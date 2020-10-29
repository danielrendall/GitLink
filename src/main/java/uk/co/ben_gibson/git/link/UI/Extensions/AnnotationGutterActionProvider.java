package uk.co.ben_gibson.git.link.UI.Extensions;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.vcs.annotate.FileAnnotation;
import com.intellij.openapi.vcs.annotate.UpToDateLineNumberListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.co.ben_gibson.git.link.UI.Action.Annotation.Commit.BrowserCommitAnnotationAction;
import uk.co.ben_gibson.git.link.UI.Action.Annotation.Commit.ClipboardCommitAnnotationAction;
import uk.co.ben_gibson.git.link.UI.Action.Annotation.File.BrowserBranchFileAnnotationAction;
import uk.co.ben_gibson.git.link.UI.Action.Annotation.File.BrowserFileAnnotationAction;
import uk.co.ben_gibson.git.link.UI.Action.Annotation.File.ClipboardBranchFileAnnotationAction;
import uk.co.ben_gibson.git.link.UI.Action.Annotation.File.ClipboardFileAnnotationAction;

public class AnnotationGutterActionProvider implements com.intellij.openapi.vcs.annotate.AnnotationGutterActionProvider {

    @NotNull
    @Override
    public AnAction createAction(@NotNull FileAnnotation annotation) {
        return new FileAndCommitGroup(annotation);
    }

    private static class FileAndCommitGroup extends ActionGroup implements UpToDateLineNumberListener {
        private BrowserCommitAnnotationAction browserCommitAnnotationAction;
        private ClipboardCommitAnnotationAction clipboardCommitAnnotationAction;
        private BrowserFileAnnotationAction browserFileAnnotationAction;
        private BrowserBranchFileAnnotationAction browserBranchFileAnnotationAction;
        private ClipboardFileAnnotationAction clipboardFileAnnotationAction;
        private ClipboardBranchFileAnnotationAction clipboardBranchFileAnnotationAction;
        private Group fileGroup;
        private Group commitGroup;

        FileAndCommitGroup(@NotNull FileAnnotation annotation) {
            super("GitLink", true);

            browserCommitAnnotationAction    = new BrowserCommitAnnotationAction(annotation);
            clipboardCommitAnnotationAction  = new ClipboardCommitAnnotationAction(annotation);
            browserFileAnnotationAction      = new BrowserFileAnnotationAction(annotation);
            browserBranchFileAnnotationAction      = new BrowserBranchFileAnnotationAction(annotation);
            clipboardFileAnnotationAction    = new ClipboardFileAnnotationAction(annotation);
            clipboardBranchFileAnnotationAction    = new ClipboardBranchFileAnnotationAction(annotation);

            fileGroup = new Group(
                "File",
                new AnAction[] {
                    browserFileAnnotationAction,
                    browserBranchFileAnnotationAction,
                    clipboardFileAnnotationAction,
                    clipboardBranchFileAnnotationAction
                }
            );

            commitGroup = new Group(
                "Commit",
                new AnAction[] {
                    browserCommitAnnotationAction,
                    clipboardCommitAnnotationAction
                }
            );
        }

        @NotNull
        @Override
        public AnAction[] getChildren(@Nullable AnActionEvent e) {
            return new AnAction[]{fileGroup, commitGroup};
        }

        @Override
        public void consume(Integer integer) {
            browserCommitAnnotationAction.consume(integer);
            clipboardCommitAnnotationAction.consume(integer);
            browserFileAnnotationAction.consume(integer);
            browserBranchFileAnnotationAction.consume(integer);
            clipboardFileAnnotationAction.consume(integer);
            clipboardBranchFileAnnotationAction.consume(integer);
        }
    }

    private static class Group extends ActionGroup {
        private final AnAction[] childGroups;

        Group(@NotNull String name, @NotNull AnAction[] children) {
            super(name, true);

            childGroups = children;
        }

        @NotNull
        @Override
        public AnAction[] getChildren(@Nullable AnActionEvent e) {
            return childGroups;
        }
    }
}
