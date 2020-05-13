package co.edu.icesi.fi.tics.tssc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.repository.TsscAdminRepository;
@Service
public class TsscAdminServiceImp implements TsscAdminService{

	TsscAdminRepository adminRepository;
	
	@Autowired
	public TsscAdminServiceImp(TsscAdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public TsscAdmin save(TsscAdmin admin) {
		// TODO Auto-generated method stub
		return adminRepository.save(admin);
	}

	@Override
	public TsscAdmin edit(TsscAdmin admin) {
		// TODO Auto-generated method stub
		return adminRepository.save(admin);
	}

	@Override
	public void delete(TsscAdmin admin) {
		// TODO Auto-generated method stub
		adminRepository.delete(admin);;
	}

}
