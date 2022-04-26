package in.codegram.ppmtoolapi.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.codegram.ppmtoolapi.domain.Project;
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
	//No need to add CRUD operations over here, if any customisation is required we can customize the CRUD methods
	Project findByProjectIdentifier(String projectIdentifier);
	Iterable<Project> findAll();
}
