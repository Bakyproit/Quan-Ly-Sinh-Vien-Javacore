package entity;

public class BangDiem {
	// khai bao bien
	private String maSinhVien; // SV00000
	private String maMonHoc; // 000
	private float diemSo;
	
	
	
	
	//getter - setter
	public String getMaSinhVien() {
		return maSinhVien;
	}
	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
	}
	public String getMaMonHoc() {
		return maMonHoc;
	}
	public void setMaMonHoc(String maMonHoc) {
		this.maMonHoc = maMonHoc;
	}
	public float getDiemSo() {
		return diemSo;
	}
	public void setDiemSo(float diemSo) {
		this.diemSo = diemSo;
	}
	@Override
	public String toString() {
		return  getMaSinhVien() + ";" + getMaMonHoc() + ";"+ getDiemSo() ;
	}


}
