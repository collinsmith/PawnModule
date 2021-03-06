package org.alliedmodders.pawn.project.action;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.prefs.Preferences;
import javax.swing.AbstractAction;
import org.alliedmodders.pawn.project.PawnProject;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.DynamicMenuContent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

@ActionID(
	category = "Project",
	id = "org.alliedmodders.pawn.actions.BuildProject"
)
@ActionRegistration(
	displayName = "#CTL_BuildProject"
)
@ActionReference(
	path = "Projects/org-alliedmodders-pawn-project")
@Messages("CTL_BuildProject=Build AMXX Project")
public final class BuildProjectAction extends AbstractAction {

    private final Project project;

    public BuildProjectAction(Project project) {
	this.project = project;
	setEnabled(project instanceof PawnProject);
	putValue(DynamicMenuContent.HIDE_WHEN_DISABLED, true);
	putValue(NAME, "Build AMXX Project");
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
	InputOutput io = IOProvider.getDefault().getIO(ProjectUtils.getInformation(project).getDisplayName(), false);
	io.getOut().printf("Building '%s' . . .", ProjectUtils.getInformation(project).getDisplayName());
	
	FileObject src = project.getProjectDirectory().getFileObject("src");
	for (FileObject fo : src.getChildren()) {
	    if (fo.isFolder() || !"sma".equals(fo.getExt())) {
		continue;
	    }
	    
	    buildFile(fo);
	}
    }
    
    private void buildFile(FileObject context) {
	InputOutput io = IOProvider.getDefault().getIO(ProjectUtils.getInformation(project).getDisplayName(), false);
	Preferences prefs = Preferences.userNodeForPackage(PawnProject.class);
	String compilerPath = prefs.get("compilerPath", "");
	if (compilerPath.isEmpty()) {
	    io.getOut().println("Path to amxxpc.exe not set.");
	    return;
	}

	String fileName = context.getName();
	String projectPath = project.getProjectDirectory().getPath();
	String buildPath = String.format("%s/build/%s.amxx", projectPath, fileName);
        //String defaultInclude = FileUtil.normalizePath("src/org/alliedmodders/pawn/file/pawn/default.inc");
	
	io.getOut().println("------------------------------------------------");
	io.getOut().printf("Building '%s' . . .%n", context.getPath());

	try {
	    String[] cmd = {
		compilerPath,
		String.format("\"%s\"", context.getPath()),
		"-\\",
                "-;",
		//"-h",
                //String.format("-p\"%s\"", defaultInclude),
		String.format("-o\"%s\"", buildPath),
		String.format("-i\"%s/src\"", projectPath),
	    };
	    
	    Process p = Runtime.getRuntime().exec(cmd);
	    InputStreamReader isr = new InputStreamReader(p.getInputStream());
	    BufferedReader br = new BufferedReader(isr);
	    
	    String line = null;
	    while ((line = br.readLine()) != null) {
		io.getOut().println(line);
	    }
	    
	    p.waitFor();
	    p.destroy();
	    
	    io.getOut().println();
	    io.getOut().println("Copying file to plugins directory. . .");
	    String pluginsDir = prefs.get("pluginsDir", "");
	    if (pluginsDir.isEmpty()) {
		io.getOut().println("Plugins directory not set. Skipping.");
		return;
	    }
	    
	    Files.copy(Paths.get(buildPath), Paths.get(pluginsDir, fileName + ".amxx"), StandardCopyOption.REPLACE_EXISTING);
	    io.getOut().println("Done.");
	} catch (IOException e) {
	    io.getErr().println("Cannot find " + e.getMessage());
	    //e.printStackTrace(io.getErr());
	} catch (InterruptedException e) {
	    e.printStackTrace(io.getErr());
	}
    }
}
