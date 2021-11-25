
package Model;

public class LoaiMon {
    private String MaLoaiMon;
    private String tenLoaiMon;

    @Override 
    public String toString(){
        return this.tenLoaiMon;
    }

    public LoaiMon() {
    }

    public LoaiMon(String MaLoaiMon, String tenLoaiMon) {
        this.MaLoaiMon = MaLoaiMon;
        this.tenLoaiMon = tenLoaiMon;
    }
    
    public String getMaLoaiMon() {
        return MaLoaiMon;
    }

    public void setMaLoaiMon(String MaLoaiMon) {
        this.MaLoaiMon = MaLoaiMon;
    }

    public String getTenLoaiMon() {
        return tenLoaiMon;
    }

    public void setTenLoaiMon(String tenLoaiMon) {
        this.tenLoaiMon = tenLoaiMon;
    }
    
    
}
