package in.codegram.ppmtoolapi.service;

import in.codegram.ppmtoolapi.domain.ProjectTask;

public interface ProjectTaskService {
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask);
	ProjectTask findPTByProjectSequence(String backlog_id,String projectTaskSequence);
	public void deletePTByprojectTaskSequence(String backlog_id,String pt_id);
	public ProjectTask updateByProjectTaskSequence(ProjectTask updatedProjectTask,String backlog_id,String pt_id);
}
