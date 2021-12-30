package ui;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import business.DiemManager;
import business.MonHocManager;
import business.SinhVienManager;
import entity.BangDiem;
import entity.MonHoc;
import entity.SinhVien;
import exception.MyException;

public class SinhVienMain {

	public static void main(String[] args) throws IOException, ParseException, MyException {	
		Scanner sc = new Scanner(System.in);
		boolean kiemTra = true;
		boolean kiemTra1 = true;
		boolean kiemTra2 = true;
		int chooise = 0;
		int chucNang = 0;
		int chucNang1 = 0;
		do {
			try {
				SinhVienManager sinhVienMan = new SinhVienManager()  ;
			
				while (kiemTra) {
					showMenu();

					System.out.println("Moi ban nhap lua chon...{1,2,3 or 4 }");
					chooise = sc.nextInt();
					switch (chooise) {
					case 1:
						do {
							try {
								while (kiemTra1) {
									showMenuCase1();
									System.out.println("Moi ban chon chuc nang :{1,2,3,4,5 or 6}");
									chucNang = sc.nextInt();
									switch (chucNang) {
									case 1:// them 1 sinh vien vao danh sach ( ma sinh vien khong duoc trung voi ma sinh vien da co trong danh sach.
										SinhVien sinhVien  = sinhVienMan.themSinhVien() ;
										if(sinhVien != null) {
											sinhVienMan.addSinhVien(sinhVien);
											System.out.println("Them sinh vien thanh cong!");
										}else {
											System.out.println("sinh vien null khong ghi vao danh sach");
										}
										break;
									case 2:// sua thong tin sinh vien
								        sinhVienMan.suaThongTinSinhVien();
										System.out.println("Sua sinh vien thanh cong!");
										break;
									case 3: // xoa thong tin sinh vien
										sinhVienMan.xoaThongTinSinhVien();
										break;
									case 4:// hien thi danh sach sinh vien sap xep theo ten
										sinhVienMan.sortSinhVienTheoTen() ;
			                            System.out.println("danh sach sinh vien da duoc sap xep thanh cong !");														
										break;
									case 5:// Hien thi mon hoc duoc sap xep theo ten
										MonHocManager monHocMan = new MonHocManager() ;
										monHocMan.sortMonHocByName();
	 									System.out.println("danh sach mon hoc duoc sap xep thanh cong!");
										break;
									case 6:
										kiemTra1 = false;
										System.out.println("Chuc nang cap nhat da ket thuc....");
									}
								}
							} catch (InputMismatchException e) {
								System.out.println("Ban nhap sai dinh dang cua chuc nang...");
								sc.next();
							}
						} while (chucNang != 1 && chucNang != 2 && chucNang != 3 && chucNang != 4 && chucNang != 5
								&& chucNang != 6);
						break;
					case 2:// hien thi bang diem cua sinh vien
						
						sinhVienMan.hienThiDanhSachSinhVien();
                        System.out.println("Hien thi");
						break;
					case 3:// tim kiem sinh vien 
						
						do {
							try {
								while (kiemTra2) {
									showMenuCase3();
									System.out.println("Moi ban chon chuc nang");
									chucNang1 = sc.nextInt();
									switch (chucNang1) {
									case 1://tim kiem sinh vien theo ma sinh vien
										sinhVienMan.timKiemSinhVienTheoMa();
				                     
										break ;
									case 2:// tim kiem sinh vien theo ten
										ArrayList<SinhVien> listTen = sinhVienMan.timKiemSinhVienTheoTen() ;
										System.out.println("Danh sach sinh vien cung ten : ");
										for (SinhVien sinhVien : listTen) {
											System.out.println(sinhVien);
										}
		                                System.out.println("Ban co muon xem chi tiet diem cua sinh vien :");
		                                System.out.println("nhap neu muon nhap 'Y' , khong muon nhap 'N' :");
		                                Scanner s = new Scanner(System.in);
		                                String nhap = null ; 
		                                nhap = s.nextLine() ; 
		                                if(nhap.equalsIgnoreCase("Y")) {
		                                	sinhVienMan.timKiemSinhVienTheoMa();
		                                }else  if(nhap.equalsIgnoreCase("N")) {
		                                	break ;
		                                }
		                                
										break ;
									case 3:
										kiemTra2 = false ; 
										System.out.println("chuong trinh tim kiem da ket thuc...");
									}
								}
		
							} catch (InputMismatchException e) {
								System.out.println("Ban nhap sai dinh dang cua chuc nang1...");
								sc.next();
							}
							
						}while(chucNang1 != 1 && chucNang1 != 2 && chucNang1 != 3);
						break;
					case 4:
						kiemTra = false;
						System.out.println("chuong trinh da ket thuc");
					}
				}

			} catch (InputMismatchException e) {
				System.out.println("Ban nhap sai dinh dang cua lua chon...");
				sc.next();
			} catch (MyException e) {
				System.out.println(e.getErrorMessage());
				sc.next() ;
			}
          
		} while (chooise != 1 && chooise != 2 && chooise != 3 && chooise != 4);
		sc.close();

	}


	private static void showMenu() {
		// tao menu
		System.out.println("Chao mung ban den voi chuong trinh quan ly sinh vien");
		System.out.println("Moi ban chon chuc nang");
		System.out.println("1 . Cap nhat danh sach.");
		System.out.println("2 . Hien thi bang diem.");
		System.out.println("3 . Chuc nang tim kiem.");
		System.out.println("4 . exits");
	}

	private static void showMenuCase1() {
		System.out.println("Moi ban chon chuc nang : ");
		System.out.println("1 . Them moi 1 sinh vien(Ma sinh vien khong duoc trung).");
		System.out.println("2 . Sua thong tin cua 1 sinh vien cop trong danh sach(Nhap ma sv muon sua , khong duoc sua ma).");
		System.out.println("3 . Xoa thong tin cua 1 sinh vien (Nhap ma sv muon xoa ,sinh vien da co diem khong duoc xoa).");
		System.out.println("4 . Hien thi danh sach sinh vien sap xep theo ten.");
		System.out.println("5 . Hien thi danh sach mon hoc duoc sap xep theo ten mon hoc.");
		System.out.println("6 . exits");

	}
	private static void showMenuCase3() {
		// TODO Auto-generated method stub
		System.out.println("Moi ban chon chuc nang : ");
		System.out.println("1 . Hien thi ten sinh vien va bang diem sinh vien ( tim kiem theo ma sinh vien ) .");
		System.out.println("2 . Hien thi danh sach cac sinh vien trung voi ten( tim kiem theo ten sinh vien ) .");
		System.out.println("3 . Exits");
	}


}
