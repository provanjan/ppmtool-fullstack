package in.codegram.ppmtoolapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.codegram.ppmtoolapi.domain.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long>{
	Backlog findByProjectIdentifier(String projectIdentifier);
}
