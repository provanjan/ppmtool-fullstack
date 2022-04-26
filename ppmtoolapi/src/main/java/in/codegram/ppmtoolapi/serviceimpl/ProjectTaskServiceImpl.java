package in.codegram.ppmtoolapi.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codegram.ppmtoolapi.domain.Backlog;
import in.codegram.ppmtoolapi.domain.ProjectTask;
import in.codegram.ppmtoolapi.exception.ProjectNotFoundException;
import in.codegram.ppmtoolapi.repository.BacklogRepository;
import in.codegram.ppmtoolapi.repository.ProjectTaskRepository;
import in.codegram.ppmtoolapi.service.ProjectTaskService;
@Service
public class ProjectTaskServiceImpl implements ProjectTaskService{

	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	@Override
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		try {
			//Exception: Project not found
			//ProjectTasks to be added to a specific project, project!=null,BacklogExist
			Backlog backlog=backlogRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
			//Set the Backlog to projectTask
			projectTask.setBacklog(backlog);
			//We want our project sequence to be like : IDPRO-1, IDPRO-2
			Integer backlogSequence=backlog.getPTSequence();
			//Update the backlog sequence
			backlogSequence++;
			backlog.setPTSequence(backlogSequence);
			//Add sequence to project task
			projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);
			//initial priority when priority is null
			if(projectTask.getPriority()==null) {
				projectTask.setPriority(3);
			}
			//initial status when status is null
			if(projectTask.getStatus()==null || projectTask.getStatus()=="") {
				projectTask.setStatus("TO_DO");
			}
			return projectTaskRepository.save(projectTask);
		}catch(Exception ex) {
			throw new ProjectNotFoundException("Project Not Found");
		}
	}
	@Override
	public ProjectTask findPTByProjectSequence(String backlog_id, String projectTaskSequence) {
		// Make sure that we are searching under existing backlog_id
		Backlog backlog=backlogRepository.findByProjectIdentifier(backlog_id);
		if(backlog==null) {
			throw new ProjectNotFoundException("Project with id: '"+backlog_id+"' does not exist");
		}
		ProjectTask projectTask= projectTaskRepository.findByProjectTaskSequence(projectTaskSequence);
		if(projectTask==null) {
			throw new ProjectNotFoundException("Project Task with id: '"+projectTaskSequence+"'does not exist");
		}
		return projectTask;
	}
	@Override
	public void deletePTByprojectTaskSequence(String backlog_id, String pt_id) {
		ProjectTask projectTask=findPTByProjectSequence(backlog_id,pt_id);
		Backlog backlog=projectTask.getBacklog();
		List<ProjectTask> projectTasks=backlog.getProjectTasks();
		projectTasks.remove(projectTask);
		backlogRepository.save(backlog);
		projectTaskRepository.delete(projectTask);
	}
	@Override
	public ProjectTask updateByProjectTaskSequence(ProjectTask updatedProjectTask, String backlog_id, String pt_id) {
		//Find existing project Task
		ProjectTask projectTask=projectTaskRepository.findByProjectTaskSequence(pt_id);
		//Replace it with updated task
		projectTask=updatedProjectTask;
		//Save projectTask
		return projectTaskRepository.save(projectTask);
	}

}
