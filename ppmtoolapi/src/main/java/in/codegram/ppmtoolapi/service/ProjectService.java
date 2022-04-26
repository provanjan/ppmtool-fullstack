package in.codegram.ppmtoolapi.service;

import in.codegram.ppmtoolapi.domain.Project;

public interface ProjectService {
	public Project saveOrUpdate(Project project);
	public Project findProjectByprojectIdentifier(String projectId);
	public Iterable<Project> findAllProject();
	public void deleteProjectByProjectIdetifier(String projectId);
}
