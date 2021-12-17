package entity;

public class MonHoc extends BangDiem{
	 // khai bao bien
       private String tenMonHoc ; 
       private float heSoMon ;
       
    //getter-setter  
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	public float getHeSoMon() {
		return heSoMon;
	}
	public void setHeSoMon(float heSoMon) {
		this.heSoMon = heSoMon;
	}
	
	// in thong tin
	@Override
	public String toString() {
		return  getMaMonHoc() +";"+ getTenMonHoc() +";" + getHeSoMon() ; 
					
	}

	
     
}
