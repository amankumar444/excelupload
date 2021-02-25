package com.example.demo.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.EmployeeEntity;

public interface SpringReadFileService {

	public List<EmployeeEntity> getAllUsers();
	public boolean saveDataFromUploadfile(MultipartFile file);
	public Integer averageSalary();
}
