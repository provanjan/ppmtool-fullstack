package in.codegram.ppmtoolapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.codegram.ppmtoolapi.domain.ProjectTask;
@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long>{

	
	ProjectTask findByProjectTaskSequence(String projectTaskSequence);
}
