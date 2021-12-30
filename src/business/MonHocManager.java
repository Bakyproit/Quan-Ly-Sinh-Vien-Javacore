
package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import entity.MonHoc;
import entity.SinhVien;

public class MonHocManager {
	public static final String FILE_MONHOC = "data/monhoc_en.txt";

	public ArrayList<MonHoc> listMonHoc;

	public MonHocManager() throws IOException {
		listMonHoc = new ArrayList<>();

		// doc mon hoc
		load(FILE_MONHOC);
	}

	public void addMonHoc(MonHoc monHoc) throws IOException {
		listMonHoc.add(monHoc);
		// ghi mon hoc
		save(monHoc, FILE_MONHOC);
	}

	public void load(String fileMonhoc) throws IOException {
		FileReader fileR = null;
		BufferedReader bufferR = null;
		String line = null;

		try {
			fileR = new FileReader(fileMonhoc);
			bufferR = new BufferedReader(fileR);
			String[] words;

			while ((line = bufferR.readLine()) != null) {
				if (line.startsWith("#")) {
					continue;
				}
				words = line.split(";");
				MonHoc monHoc = new MonHoc();
				monHoc.setMaMonHoc(words[0]);
				monHoc.setTenMonHoc(words[1]);
				monHoc.setHeSoMon(Float.parseFloat(words[2]));
				listMonHoc.add(monHoc);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bufferR != null) {
				bufferR.close();
			}
			if (fileR != null) {
				fileR.close();
			}
		}

	}

	public void save(MonHoc monHoc, String fileMonhoc) throws IOException {
		FileWriter fileW = null;
		BufferedWriter bufferW = null;
		try {
			// mo file
			File file = new File(fileMonhoc);
			fileW = new FileWriter(file);
			bufferW = new BufferedWriter(fileW);
 
			for (MonHoc monHoc2 : listMonHoc) {
				String data = monHoc2.toString();
				bufferW.write(data);
				bufferW.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferW != null) {
				bufferW.close();
			}
			if (fileW != null) {
				fileW.close();
			}
		}

	}
	public void sortMonHocByName() throws IOException{

		listMonHoc.sort((a, b) -> a.getTenMonHoc().compareToIgnoreCase(b.getTenMonHoc())); 
		System.out.println("danh sach mon hoc da duoc sap xep : ");
		for (MonHoc monHoc : listMonHoc) {
			System.out.println(monHoc);
			save(monHoc, "data/monhocdasapxep.txt");
		}
	}
}
