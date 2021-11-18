
package Model;

import java.util.Date;

public class PhieuDat {
    private int MaPD;
    private int MaKH;
    private Date dateBook;
    private int soNguoi;
    private Date dateXacNhan;
    private String ghiChu;
    private String TrangThai; 

    public int getMaPD() {
        return MaPD;
    }

    public void setMaPD(int MaPD) {
        this.MaPD = MaPD;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public Date getDateBook() {
        return dateBook;
    }

    public void setDateBook(Date dateBook) {
        this.dateBook = dateBook;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public Date getDateXacNhan() {
        return dateXacNhan;
    }

    public void setDateXacNhan(Date dateXacNhan) {
        this.dateXacNhan = dateXacNhan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
