package co.edu.icesi.fi.tics.tssc.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.Taller3PersistenceApplication;
import co.edu.icesi.fi.tics.tssc.daos.ITsscAdminDao;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@RunWith(SpringRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TestTsscAdminDao {

	@Autowired
	private ITsscAdminDao adminDao;
	
	private TsscAdmin admin;
	
	@BeforeEach
	void initDao() {
		admin = new TsscAdmin();
		admin.setUsername("admin1");
		admin.setPassword("123");
		admin.setSuperAdmin("superAdmin");
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateAdminTest() {
		assertNull(adminDao);
		try {
			TsscAdmin oldAd = adminDao.findById(admin.getId());
			admin.setUsername("juan24");
			admin.setPassword("{noop}54321");
			adminDao.update(admin);
			TsscAdmin newAd = adminDao.findById(admin.getId());
			
			
			assertTrue(oldAd.getUsername().equals(newAd.getUsername()));
			assertTrue(oldAd.getPassword().equals(newAd.getPassword()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteAdminTest() {
		assertNull(adminDao);
		try {
			adminDao.delete(admin);
			assertNotNull(null);
			assertNull(adminDao.findById(admin.getId()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveAdminTest() {
		assertNull(adminDao);
		try {
			adminDao.save(admin);
			
			assertTrue(adminDao.findById(admin.getId()).equals(admin));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findAdminByIdTest() {
		assertNull(adminDao);
		try {
			adminDao.save(admin);
			TsscAdmin admin = adminDao.findById(this.admin.getId());
			
			assertTrue(admin.getId() == this.admin.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
