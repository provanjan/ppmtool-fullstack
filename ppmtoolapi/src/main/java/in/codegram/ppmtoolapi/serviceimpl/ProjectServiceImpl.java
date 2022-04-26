package in.codegram.ppmtoolapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codegram.ppmtoolapi.domain.Backlog;
import in.codegram.ppmtoolapi.domain.Project;
import in.codegram.ppmtoolapi.exception.ProjectIdException;
import in.codegram.ppmtoolapi.repository.BacklogRepository;
import in.codegram.ppmtoolapi.repository.ProjectRepository;
import in.codegram.ppmtoolapi.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Override
	public Project saveOrUpdate(Project project) {
		//Business logic
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			//1.When project is created, backlog should be created
			if(project.getId()==null) {
				Backlog backlog=new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			//2. When project is updated, backlog should not be null
			if(project.getId()!=null) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			return projectRepository.save(project);
		}catch(Exception ex) {
			throw new ProjectIdException("Project Id: "+project.getProjectIdentifier().toUpperCase()+"already exists");
		}
		
	}

	@Override
	public Project findProjectByprojectIdentifier(String projectId) {
		Project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null) {
			throw new ProjectIdException("Project Identifier : "+projectId.toUpperCase()+ "Does not exist");
		}
		return project;
	}

	@Override
	public Iterable<Project> findAllProject() {
		return projectRepository.findAll();
	}

	@Override
	public void deleteProjectByProjectIdetifier(String projectId) {
		Project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null) {
			throw new ProjectIdException("Project Identifier : "+projectId.toUpperCase()+ "Does not exist");
		}
		projectRepository.delete(project);
	}

}
