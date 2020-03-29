package co.edu.icesi.fi.tics.tssc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@Repository
public interface TsscStoryRepository extends CrudRepository<TsscStory, Long>{

}
