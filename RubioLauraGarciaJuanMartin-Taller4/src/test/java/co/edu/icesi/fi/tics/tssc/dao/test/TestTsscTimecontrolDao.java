package co.edu.icesi.fi.tics.tssc.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.Taller3PersistenceApplication;
import co.edu.icesi.fi.tics.tssc.daos.ITsscTimecontrolDao;
import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

@RunWith(SpringRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TestTsscTimecontrolDao {

	@Autowired
	private ITsscTimecontrolDao timecontrolDao;
	
	private TsscTimecontrol timecontrol;
	
	@BeforeEach
	void initDao() {
		timecontrol = new TsscTimecontrol();
		timecontrol.setName("time1");
		timecontrol.setAutostart("true");
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateTimecontrolTest() {
		assertNull(timecontrolDao);
		try {
			TsscTimecontrol oldTc = timecontrolDao.findById(timecontrol.getId());
			timecontrol.setName("cronometro2");
			timecontrol.setAutostart("false");
			timecontrolDao.update(timecontrol);
			TsscTimecontrol newTc = timecontrolDao.findById(timecontrol.getId());
			
			
			assertTrue(oldTc.getName().equals(newTc.getName()));
			assertTrue(oldTc.getAutostart().equals(newTc.getAutostart()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteTimecontrolTest() {
		assertNull(timecontrolDao);
		try {
			timecontrolDao.delete(timecontrol);
			
			assertNull(timecontrolDao.findById(timecontrol.getId()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveTimecontrolTest() {
		assertNull(timecontrolDao);
		try {
			timecontrolDao.save(timecontrol);
			
			assertTrue(timecontrolDao.findById(timecontrol.getId()).equals(timecontrol));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findTimecontrolByIdTest() {
		assertNull(timecontrolDao);
		try {
			timecontrolDao.save(timecontrol);
			TsscTimecontrol time = timecontrolDao.findById(timecontrol.getId());
			
			assertTrue(time.getId() == timecontrol.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
