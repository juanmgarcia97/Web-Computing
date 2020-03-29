package co.edu.icesi.fi.tics.tssc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscSprint;

@Repository
public interface TsscSprintRepository extends CrudRepository<TsscSprint, Long>{

}
