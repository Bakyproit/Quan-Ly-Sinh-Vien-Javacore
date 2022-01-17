

package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import entity.BangDiem;

public class DiemManager {
	public static final String FILE_DIEM = "data/diem.txt";

	public ArrayList<BangDiem> listDiem;

	public DiemManager() throws IOException {
		listDiem = new ArrayList<>();
		// doc du lieu tu file
		load(FILE_DIEM);
	}

	public void addDiem(BangDiem diem) throws IOException {
		listDiem.add(diem);
		// ghi du lieu ra file
		save(FILE_DIEM);
	}

	private void load(String fileDiem) throws IOException {

		FileReader fileR = null;
		BufferedReader bufferR = null;
		String line = null;
		try {
			fileR = new FileReader(fileDiem);
			bufferR = new BufferedReader(fileR);
			String[] words;
			while ((line = bufferR.readLine()) != null) {
				if (line.startsWith("#")) {
					continue;
				}
				words = line.split(";");
				BangDiem bangDiem = new BangDiem();
				bangDiem.setMaSinhVien(words[0]);
				bangDiem.setMaMonHoc(words[1]);
				bangDiem.setDiemSo(Float.parseFloat(words[2]));

				listDiem.add(bangDiem);
			}

		} catch (FileNotFoundException e) {
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
	private void save(String fileDiem) throws IOException {
		FileWriter fileW = null;
		BufferedWriter bufferW = null;

		try {
			File file = new File(fileDiem);
			fileW = new FileWriter(file); 
			bufferW = new BufferedWriter(fileW);
            
			for (BangDiem bangDiem : listDiem) {
				String data = bangDiem.toString();
				bufferW.write(data);
				bufferW.newLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	
}
