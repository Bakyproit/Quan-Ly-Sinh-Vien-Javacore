package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.BangDiem;
import entity.GioiTinh;
import entity.MonHoc;
import entity.SinhVien;
import exception.MyException;

public class SinhVienManager {
	public static final String FILE_NAME = "data/sinhvien_en.txt";
	private ArrayList<SinhVien> listSinhVien;

	public SinhVienManager() throws IOException, ParseException, MyException {
		listSinhVien = new ArrayList<>();
		// doc sinh vien tu file
		this.load(FILE_NAME);

	}

	public void addSinhVien(SinhVien sinhVien) throws IOException {
		listSinhVien.add(sinhVien);
		// ghi sinh vien ra file
		save(FILE_NAME);
	}

	private void load(String fileName) throws IOException, MyException {
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String[] words;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#")) {
					continue;
				}

				words = line.split(";");
				if (words.length != 5) {
					throw new MyException("Khong du du lieu...." + line);
				}
				SinhVien sinhVien = new SinhVien();
				sinhVien.setMaSinhVien(words[0].trim());
				sinhVien.setHoDem(words[1].trim());
				sinhVien.setTen(words[2].trim());
				sinhVien.setGioiTinh(GioiTinh.valueOf(words[4]));

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				// chuyen string ve dang date

				try {
					Date ns = sdf.parse(words[3]);
					sinhVien.setNgaySinh(ns);
				} catch (ParseException e) {

					e.printStackTrace();
				}

				listSinhVien.add(sinhVien);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("loi du lieu nhap vao..." + line);
		} finally {
			if (br != null) {
				br.close();
			}
			if (fr != null) {
				fr.close();
			}
		}
	}

	public void save(String fileName) throws IOException {
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			File file = new File(fileName);
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for (SinhVien sinhVien2 : listSinhVien) {
				String data = sinhVien2.toString();
				bw.write(data);
				bw.newLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Loi null them sinh vien vao file");
		} finally {
			if (bw != null) {
				bw.close();
			}
			if (fw != null) {
				fw.close();
			}
		}

	}

	public SinhVien nhapThongTinSinhVien(String maSinhVien) throws MyException {
		Scanner sc = new Scanner(System.in) ;
		// nhap ho dem
		String hoDem = null;
		do {
			try {
				System.out.println("moi ban nhap ho dem :");
				hoDem = sc.nextLine();
				if (hoDem.isEmpty()) {
					throw new MyException("Ho dem khong duoc de trong...");
				}
				if (!hoDem.matches("^[a-zA-Z\\s]+$")) {
					throw new MyException("ho dem khong duoc chua ky tu va so...");
				}
			} catch (MyException e) {
				System.out.println(e.getErrorMessage());
			}
		} while (hoDem.isEmpty() || !hoDem.matches("^[a-zA-Z\\s]+$"));
		
		// nhap ten sinh vien
		String ten = null;
		do {
			try {
				System.out.println("moi ban nhap ten :");
				ten = sc.nextLine();
				if (ten.isEmpty()) {
					throw new MyException("ten khong duoc de trong...");
				}
				if (!ten.matches("^[a-zA-Z\\s]+$")) {
					throw new MyException("ten khong duoc chua ky tu va so...");
				}
			} catch (MyException e) {
				System.out.println(e.getErrorMessage());
			}
		} while (ten.isEmpty() || !ten.matches("^[a-zA-Z\\s]+$"));
		
		// nhap ngay sinh
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String ngaySinh = null;
		do {
			try {
				System.out.println("moi ban nhap ngay sinh:");
				ngaySinh = sc.nextLine();
				if (ngaySinh.isEmpty()) {
					throw new MyException("Ngay sinh khong duoc de trong...");
				} else if (!ngaySinh.matches("^\\d{1,2}[/]\\d{1,2}[/]\\d{4}+$")) {
					throw new MyException("ban nhap sai dinh dang...dd/MM/yyyy");
				}
			} catch (MyException e) {
				System.out.println(e.getErrorMessage());
			}
		} while (ngaySinh.isEmpty() || !ngaySinh.matches("^\\d{1,2}[/]\\d{1,2}[/]\\d{4}+$"));
		// chuyen ngay sinh ve dang date
		Date ns = null;
		try {
			ns = sdf.parse(ngaySinh);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// nhap gioi tinh
		int gioiTinh = 0;
		do {
			try {
				System.out.println("Moi ban nhap gioi tinh: {1. Nam 2.Nu  }");
				gioiTinh = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("ban nhap sai dinh dang..");
				sc.next();
			}
		} while (gioiTinh != 1 && gioiTinh != 2);

		SinhVien sinhVien = new SinhVien();
		sinhVien.setMaSinhVien(maSinhVien.trim());
		sinhVien.setHoDem(hoDem.trim());
		sinhVien.setTen(ten.trim());
		sinhVien.setNgaySinh(ns);
		if (gioiTinh == 1) {
			sinhVien.setGioiTinh(GioiTinh.Nam);
		} else if (gioiTinh == 2) {
			sinhVien.setGioiTinh(GioiTinh.Nu);
		}
		return sinhVien;	
	}

	public SinhVien themSinhVien() throws MyException {
		System.out.println("Moi ban nhap thong tin sinh vien");
		Scanner sc = new Scanner(System.in);
		// nhap ma sinh vien
		String maSinhVien = null;
		do {
			try {
				System.out.println("moi ban nhap ma sinh vien : ");
				maSinhVien = sc.nextLine();
				if (maSinhVien.isEmpty()) {
					throw new MyException("Ma sinh vien khong duoc de trong...");
				} else if (!maSinhVien.matches("^[A-Z]{2}\\d{5}+$")) {
					throw new MyException("Ban can nhap dung dinh dang ma sinh vien : //SV00001...");
				}
			} catch (MyException e) {
				System.out.println(e.getErrorMessage());
				System.out.println("loi so sanh voi phan tu null...");
			}
		} while (maSinhVien.isEmpty() || !maSinhVien.matches("^[A-Z]{2}\\d{5}+$"));

		ArrayList<SinhVien> listSinhVienFound = new ArrayList<>() ;
		
		for (SinhVien sinhVien1 : listSinhVien) {
			try {
				if (maSinhVien.equalsIgnoreCase(sinhVien1.getMaSinhVien())) {
					listSinhVienFound.add(sinhVien1) ;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}
        SinhVien sinhVien = null ; 
		// kiem tra neu sinh vien ton tai thi return null
		if (listSinhVienFound.size() != 0 ) {
			System.out.println("ma sinh vien da co trong danh sach..");
		} else {
             sinhVien = nhapThongTinSinhVien(maSinhVien) ;
             System.out.print("Thong tin sinh vien ban vua them moi : ");
             System.out.println(sinhVien);
		}  
	   return sinhVien;
	}

	public void suaThongTinSinhVien() throws IOException, MyException {

		Scanner sc = new Scanner(System.in);
		// nhap ma sinh vien
		String maSinhVien = null;
		do {
			try {
				System.out.println("moi ban nhap ma sinh vien : {SV00001}");
				maSinhVien = sc.nextLine();
				if (maSinhVien.isEmpty()) {
					throw new MyException("Ma sinh vien khong duoc de trong...");
				} else if (!maSinhVien.matches("^[A-Z]{2}\\d{5}+$")) {
					throw new MyException("Ban can nhap dung dinh dang ma sinh vien : //SV00001...");
				}
			} catch (MyException e) {
				System.out.println(e.getErrorMessage());
			}
		} while (maSinhVien.isEmpty() || !maSinhVien.matches("^[A-Z]{2}\\d{5}+$"));
		
        //luu sinh vien can sua 
		ArrayList<SinhVien> listSV = new ArrayList<>() ;
		SinhVien sinhVienUpdate = null ; 
		for (int i = 0; i < listSinhVien.size(); i++) {
			if (maSinhVien.equalsIgnoreCase(listSinhVien.get(i).getMaSinhVien())) {
				// In thong tin sinh vien do
				System.out.print("Thong tin sinh vien ban can sua  :");
				System.out.println(listSinhVien.get(i));
				sinhVienUpdate = listSinhVien.get(i) ; 
				listSV.add(listSinhVien.get(i)) ;
			}
		}
		// xoa sinhvien do khoi list	
		listSinhVien.removeAll(listSV) ;
	
		//kiemtra va cap nhat lai
		if(listSV.size() != 0) {
			// nhap lai thong tin can sua
			Scanner nhap = new Scanner(System.in) ;
			String luaChon ;
			// MASV
			sinhVienUpdate.setMaSinhVien(maSinhVien) ;
			
			// sua ho dem
			System.out.println("Ban co muon sua ho dem : {'y' or 'n'}");
			luaChon = nhap.nextLine() ; 
			if(luaChon.equalsIgnoreCase("Y")) {
				// nhap ho dem
				String hoDem = null;
				do {
					try {
						System.out.println("moi ban nhap ho dem :");
						hoDem = sc.nextLine();
						if (hoDem.isEmpty()) {
							throw new MyException("Ho dem khong duoc de trong...");
						}
						if (!hoDem.matches("^[a-zA-Z\\s]+$")) {
							throw new MyException("ho dem khong duoc chua ky tu va so...");
						}
					} catch (MyException e) {
						System.out.println(e.getErrorMessage());
					}
				} while (hoDem.isEmpty() || !hoDem.matches("^[a-zA-Z\\s]+$"));
				sinhVienUpdate.setHoDem(hoDem) ; 
			}else if(luaChon.equalsIgnoreCase("N")) {
				System.out.println("Ho dem khong duoc thay doi : ");
			}
			
			// sua ten
			System.out.println("Ban co muon sua Ten: {'y' or 'n'}");
			luaChon = nhap.nextLine() ; 
			if(luaChon.equalsIgnoreCase("Y")) {
				// nhap ten sinh vien
				String ten = null;
				do {
					try {
						System.out.println("moi ban nhap ten :");
						ten = sc.nextLine();
						if (ten.isEmpty()) {
							throw new MyException("ten khong duoc de trong...");
						}
						if (!ten.matches("^[a-zA-Z\\s]+$")) {
							throw new MyException("ten khong duoc chua ky tu va so...");
						}
					} catch (MyException e) {
						System.out.println(e.getErrorMessage());
					}
				} while (ten.isEmpty() || !ten.matches("^[a-zA-Z\\s]+$"));
				sinhVienUpdate.setTen(ten)  ;
			}else if(luaChon.equalsIgnoreCase("N")) {
				System.out.println("Ten khong duoc thay doi : ");
			}
			// sua ngay sinh
			System.out.println("Ban co muon sua ngay sinh: {'y' or 'n'}");
			luaChon = nhap.nextLine() ; 
			if(luaChon.equalsIgnoreCase("Y")) {
				// nhap ngay sinh
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String ngaySinh = null;
				do {
					try {
						System.out.println("moi ban nhap ngay sinh:");
						ngaySinh = sc.nextLine();
						if (ngaySinh.isEmpty()) {
							throw new MyException("Ngay sinh khong duoc de trong...");
						} else if (!ngaySinh.matches("^\\d{1,2}[/]\\d{1,2}[/]\\d{4}+$")) {
							throw new MyException("ban nhap sai dinh dang...dd/MM/yyyy");
						}
					} catch (MyException e) {
						System.out.println(e.getErrorMessage());
					}
				} while (ngaySinh.isEmpty() || !ngaySinh.matches("^\\d{1,2}[/]\\d{1,2}[/]\\d{4}+$"));
				// chuyen ngay sinh ve dang date
				Date ns = null;
				try {
					ns = sdf.parse(ngaySinh);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				sinhVienUpdate.setNgaySinh(ns) ;
			}else if(luaChon.equalsIgnoreCase("N")) {
				System.out.println("Ngay sinh khong duoc thay doi : ");
			}
			// sua gioi tinh 
			System.out.println("Ban co muon sua gioi tinh : {'y' or 'n'}");
			luaChon = nhap.nextLine() ; 
			if(luaChon.equalsIgnoreCase("Y")) {
				// nhap gioi tinh
				int gioiTinh = 0;
				do {
					try {
						System.out.println("Moi ban nhap gioi tinh: {1. Nam 2.Nu  }");
						gioiTinh = sc.nextInt();

					} catch (InputMismatchException e) {
						System.out.println("ban nhap sai dinh dang..");
						sc.next();
					}
				} while (gioiTinh != 1 && gioiTinh != 2);
				
				if (gioiTinh == 1) {
					sinhVienUpdate.setGioiTinh(GioiTinh.Nam);
				} else if (gioiTinh == 2) {
					sinhVienUpdate.setGioiTinh(GioiTinh.Nu);
				}
			}else if(luaChon.equalsIgnoreCase("N")) {
				System.out.println("Gioi tinh  khong duoc thay doi : ");
			}
			
		}else {
			System.out.println("khong tim thay sinh vien trong danh sach...");
		}
		System.out.print("sinh vien sau khi sua :");
		System.out.println(sinhVienUpdate);
		// add vao list roi in ra file
		addSinhVien(sinhVienUpdate);
	}

	public void xoaThongTinSinhVien() throws IOException {
		// lay danh sach diem
		DiemManager diemMan = null;
		try {
			diemMan = new DiemManager();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);
		// nhap ma sinh vien can xoa
		String maSinhVien = null;
		do {
			try {
				System.out.println("moi ban nhap ma sinh vien : ");
				maSinhVien = sc.nextLine();
				if (maSinhVien.isEmpty()) {
					throw new MyException("Ma sinh vien khong duoc de trong...");
				} else if (!maSinhVien.matches("^[A-Z]{2}\\d{5}+$")) {
					throw new MyException("Ban can nhap dung dinh dang ma sinh vien : //SV00001...");
				}
			} catch (MyException e) {
				System.out.println(e.getErrorMessage());
			}
		} while (maSinhVien.isEmpty() || !maSinhVien.matches("^[A-Z]{2}\\d{5}+$"));
		
		// tao list de luu danh sach diem cua 1 sv trung voi ma nhap vao
		ArrayList<BangDiem> listFouds = new ArrayList<>();
		// duyet list diem
		for (int i = 0; i < diemMan.listDiem.size(); i++) {
			BangDiem diem = diemMan.listDiem.get(i);
			if (maSinhVien.equalsIgnoreCase(diem.getMaSinhVien())) {
				listFouds.add(diem);
			}
		}

		// KIEM TRA XEM SINH VIEN DA CO DIEM MON HOC CHUA
		// sinh vien da co diem tra ve true , sinh vien khong co diem tra ve false
		boolean kiemTraDiem = true;
		if (listFouds.size() == 0) {
			System.out.println("sinh vien do khong co diem");
			kiemTraDiem = false;
		} else {
			System.out.println("Sinh vien da co diem khong duoc xoa.");
			System.out.println("Bang diem cua sinh vien..");
			for (BangDiem bangDiem : listFouds) {
				System.out.println(bangDiem);
			}
		}
		// XOA SINH VIEN NEU SINH VIEN CHUA CO DIEM MON HOC
		ArrayList<SinhVien> listSVDelete = new ArrayList<>() ;
		if (kiemTraDiem == false) {
			for (int i = 0; i < listSinhVien.size(); i++) {
				if (maSinhVien.equalsIgnoreCase(listSinhVien.get(i).getMaSinhVien())) {
					// in ra sinh vien do
		            System.out.print("Sinh vien ban muon xoa :  " );
					System.out.println(listSinhVien.get(i));
					listSVDelete.add(listSinhVien.get(i)) ; 
				}
			}
			// xoa sinh vien do ra khoi danh sach
			System.out.println("Ban co chac muon xoa sinh vien ra khoi danh sach :{Nhap 'y' neu muon khong nhap 'n'...}");
			Scanner nhap = new Scanner(System.in) ; 
			String luaChon = nhap.nextLine() ; 
			if(luaChon.equalsIgnoreCase("y")) {
				listSinhVien.removeAll(listSVDelete) ;
				System.out.println("Ban da xoa sinh vien thanh cong:");
			}else if(luaChon.equalsIgnoreCase("n")) {
				System.out.println("xoa khong thanh cong");
			}
		    // in ra file
		    if(listSVDelete.size() != 0) {
			// ghi file
			save(FILE_NAME) ; 
		    }	
	     }
	}

	public void sortSinhVienTheoTen() throws IOException {
       
		listSinhVien.sort((a, b) -> a.getTen().compareTo(b.getTen()));
		for (SinhVien sinhVien : listSinhVien) {
			save("data/sinhviendasapxep.txt");
		}
	}

	public void hienThiDanhSachSinhVien() {
		// lay danh sach diem
		DiemManager diemMan = null;
		try {
			diemMan = new DiemManager();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		
		// lay danh sach mon hoc
		MonHocManager monHocMan = null;
		try {
			monHocMan = new MonHocManager();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// sap xep danh sach theo ma sinh vien
		listSinhVien.sort((a, b) -> a.getMaSinhVien().compareTo(b.getMaSinhVien()));

		// duyet

		for (int i = 0; i < listSinhVien.size(); i++) {
			System.out.println("---------------------------------------------");
			System.out.printf("|%-10s|%-20s|%-11s|%n", listSinhVien.get(i).getMaSinhVien(),
					listSinhVien.get(i).getFullName(), listSinhVien.get(i).getNgaySinh());
			System.out.println("---------------------------------------------");
			float sum = 0;
			float heSo = 0;
			for (int j = 0; j < diemMan.listDiem.size(); j++) {
				if (listSinhVien.get(i).getMaSinhVien().equalsIgnoreCase(diemMan.listDiem.get(j).getMaSinhVien())) {
					// System.out.printf("|%-10s|%-20s|%-11s|%n",
					// diemMan.listDiem.get(j).getMaSinhVien(),
					// diemMan.listDiem.get(j).getMaMonHoc(),diemMan.listDiem.get(j).getDiemSo());

					for (int z = 0; z < monHocMan.listMonHoc.size(); z++) {
						if (diemMan.listDiem.get(j).getMaMonHoc().equalsIgnoreCase(monHocMan.listMonHoc.get(z).getMaMonHoc())) {
							//
							System.out.printf("|%-10s|%-20s|%-11.2f|%n", monHocMan.listMonHoc.get(z).getMaMonHoc(),
									monHocMan.listMonHoc.get(z).getTenMonHoc(), diemMan.listDiem.get(j).getDiemSo()) ;
							
							//
							heSo += monHocMan.listMonHoc.get(z).getHeSoMon();
							sum += (diemMan.listDiem.get(j).getDiemSo() * monHocMan.listMonHoc.get(z).getHeSoMon());
						}
					}
				}
			}

			// System.out.println(sum +"\t"+ heSo);
			if (heSo == 0) {
				System.out.println("|Sinh vien chua co diem mon hoc nao         |");
				System.out.format("|%-31s %-11.2f|%n", "Diem Tong Ket", 0f);
			} else {
				float diemTongKet = sum / heSo;
				System.out.format("|%-31s %-11.2f|%n", "Diem Tong Ket", diemTongKet);
			}
		}

	}

	public void hienThiBangDiem1SinhVien(String maSinhVien) {
		// lay danh sach diem
		DiemManager diemMan = null;
		try {
			diemMan = new DiemManager();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// lay danh sach mon hoc
		MonHocManager monHocMan = null;
		try {
			monHocMan = new MonHocManager();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//
		for (int i = 0; i < listSinhVien.size(); i++) {
			if (maSinhVien.equalsIgnoreCase(listSinhVien.get(i).getMaSinhVien())) {
				System.out.println("---------------------------------------------");
				System.out.printf("|%-10s|%-20s|%-11s|%n", listSinhVien.get(i).getMaSinhVien(),
						listSinhVien.get(i).getFullName(), listSinhVien.get(i).getNgaySinh());
				System.out.println("---------------------------------------------");
			}
		}

		float sum = 0;
		float heSo = 0;
		for (int j = 0; j < diemMan.listDiem.size(); j++) {
			if (maSinhVien.equalsIgnoreCase(diemMan.listDiem.get(j).getMaSinhVien())) {
				// System.out.printf("|%-10s|%-20s|%-11s|%n",
				// diemMan.listDiem.get(j).getMaSinhVien(),
				// diemMan.listDiem.get(j).getMaMonHoc(),diemMan.listDiem.get(j).getDiemSo());

				for (int z = 0; z < monHocMan.listMonHoc.size(); z++) {
					if (diemMan.listDiem.get(j).getMaMonHoc()
							.equalsIgnoreCase(monHocMan.listMonHoc.get(z).getMaMonHoc())) {
						//
						System.out.printf("|%-10s|%-20s|%-11.2f|%-5.1f|%n", monHocMan.listMonHoc.get(z).getMaMonHoc(),
								monHocMan.listMonHoc.get(z).getTenMonHoc(), diemMan.listDiem.get(j).getDiemSo(),
								monHocMan.listMonHoc.get(z).getHeSoMon());
						//
						heSo += monHocMan.listMonHoc.get(z).getHeSoMon();
						sum += (diemMan.listDiem.get(j).getDiemSo() * monHocMan.listMonHoc.get(z).getHeSoMon());
					}
				}
			}
		}
		// System.out.println(sum +"\t"+ heSo);
		if (heSo == 0) {
			System.out.println("|Sinh vien chua co diem mon hoc nao         |");
			System.out.format("|%-31s %-11.2f|%n", "Diem Tong Ket", 0f);
			System.out.println("---------------------------------------------");
		} else {
			float diemTongKet = sum / heSo;
			System.out.format("|%-31s %-11.2f|%n", "Diem Tong Ket", diemTongKet);
			System.out.println("---------------------------------------------");
		}
	}

	public void timKiemSinhVienTheoMa() {
		Scanner sc = new Scanner(System.in);
		// nhap ma sinh vien
		String maSinhVien = null;
		do {
			try {
				System.out.println("moi ban nhap ma sinh vien :{ SV00001 } ");
				maSinhVien = sc.nextLine();
				if (maSinhVien.isEmpty()) {
					throw new MyException("Ma sinh vien khong duoc de trong...");
				} else if (!maSinhVien.matches("^[A-Z]{2}\\d{5}+$")) {
					throw new MyException("Ban can nhap dung dinh dang ma sinh vien : //SV00001...");
				}
			} catch (MyException e) {
				System.out.println(e.getErrorMessage());
			}

		} while (maSinhVien.isEmpty() || !maSinhVien.matches("^[A-Z]{2}\\d{5}+$"));
		System.out.println("Bang diem chi tiet cua sinh vien :");
		hienThiBangDiem1SinhVien(maSinhVien);
	}

	public ArrayList<SinhVien> timKiemSinhVienTheoTen() {
		Scanner sc = new Scanner(System.in);

		// nhap ten sinh vien
		String ten = null;
		do {
			try {
				System.out.println("moi ban nhap ten :");
				ten = sc.nextLine();
				if (ten.isEmpty()) {
					throw new MyException("ten khong duoc de trong...");
				}
				if (!ten.matches("^[a-zA-Z\\s]+$")) {
					throw new MyException("ten khong duoc chua ky tu va so...");
				}
			} catch (MyException e) {
				System.out.println(e.getErrorMessage());
			}
		} while (ten.isEmpty() || !ten.matches("^[a-zA-Z\\s]+$"));
		ArrayList<SinhVien> listFound = new ArrayList<>();
		for (SinhVien sinhVien : listSinhVien) {
			if (sinhVien.getTen().equalsIgnoreCase(ten)) {
				listFound.add(sinhVien);
			}
		}
		return listFound;
	}

}
