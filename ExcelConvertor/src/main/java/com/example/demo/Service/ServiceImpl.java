package com.example.demo.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.EmployeeEntity;
import com.example.demo.repo.Repo;

@Service
public class ServiceImpl implements SpringReadFileService{
	
	@Autowired
	private Repo repo;

	@Override
	public List<EmployeeEntity> getAllUsers() {
		List<EmployeeEntity> eList = repo.findAll();
		return eList;
	}

	@Override
	public boolean saveDataFromUploadfile(MultipartFile file) {
		boolean isFlag = false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if(extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			try {
				isFlag = readDatafromExcel(file);
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return isFlag;
	}

	private boolean readDatafromExcel(MultipartFile file) throws EncryptedDocumentException, IOException {
		
		Path tempDir = Files.createTempDirectory("");
		File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
		file.transferTo(tempFile);
		
		Workbook workbook = WorkbookFactory.create(tempFile);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		
		rows.next();
		
		while(rows.hasNext()) {
			try {
				Row row = rows.next();			
				EmployeeEntity emplEnt = new EmployeeEntity();
				if(row.getCell(0).getCellType() == CellType.NUMERIC) {
					emplEnt.setEmpId(row.getCell(0).getNumericCellValue());
				}
				if(row.getCell(1).getCellType() == CellType.STRING) {
					emplEnt.setNamePrefix(row.getCell(1).getStringCellValue());
				}
				if(row.getCell(2).getCellType() == CellType.STRING) {
					emplEnt.setFirstName(row.getCell(2).getStringCellValue());
				}
				if(row.getCell(3).getCellType() == CellType.STRING) {
					emplEnt.setMiddleInitial(row.getCell(3).getStringCellValue());
				}
				if(row.getCell(4).getCellType() == CellType.STRING) {
					emplEnt.setLastName(row.getCell(4).getStringCellValue());
				}
				if(row.getCell(5).getCellType() == CellType.STRING) {
					emplEnt.setGender(row.getCell(5).getStringCellValue());
				}
				if(row.getCell(6).getCellType() == CellType.STRING) {
					emplEnt.setEmail(row.getCell(6).getStringCellValue());
				}
				if(row.getCell(7).getCellType() == CellType.STRING) {
					emplEnt.setFatherName(row.getCell(7).getStringCellValue());
				}
				if(row.getCell(8).getCellType() == CellType.STRING) {
					emplEnt.setMotherName(row.getCell(8).getStringCellValue());
				}
				if(row.getCell(9).getCellType() == CellType.STRING) {
					emplEnt.setMotherMaidenName(row.getCell(9).getStringCellValue());
				}
				if(row.getCell(10).getCellType() == CellType.NUMERIC) {
					emplEnt.setAgeInCompany(row.getCell(10).getNumericCellValue());
				}
				if(row.getCell(11).getCellType() == CellType.NUMERIC) {
					emplEnt.setSalary(row.getCell(11).getNumericCellValue());
				}
				if(row.getCell(12).getCellType() == CellType.STRING) {
					emplEnt.setLastHike(row.getCell(12).getStringCellValue());
				}
				repo.save(emplEnt);
			}catch(NullPointerException e){
				break;
			}
		}
		System.out.println(repo.avg());
		
		return true;
	}
	
	public Integer averageSalary() {
		return repo.avg();
	}
	
}
