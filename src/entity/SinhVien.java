package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SinhVien extends BangDiem {

	// khai bao bien
	private String hoDem;
	private String ten;
	private Date ngaySinh;
	private GioiTinh gioiTinh;

	private String fullName = hoDem + " " + ten;

	public String getFullName() {
		return getHoDem() + " "+ getTen();
	}

	

	// getter -setter
	public String getHoDem() {
		return hoDem;
	}

	public void setHoDem(String hoDem) {
		this.hoDem = hoDem;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getNgaySinh() {
		// chuyen tu date sang string
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return sdf.format(ngaySinh);
		} catch (Exception e) {
			return "--/--/----";
		}

	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public GioiTinh getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(GioiTinh gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	// in thong tin
	@Override
	public String toString() {
		return getMaSinhVien() + ";" + getHoDem() + ";" + getTen() + ";" + getNgaySinh() + ";" + getGioiTinh();
	}
	
	

}
