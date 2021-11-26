
package Model;

import java.util.Date;

public class PhieuDat {
    private int MaPD;
    private int MaKH;
    private Date dateBook;
    private Date TimeBook;
    private int soNguoi;
    private Date TimeXacNhan;
    private String ghiChu;

    public Date getTimeBook() {
        return TimeBook;
    }

    public void setTimeBook(Date TimeBook) {
        this.TimeBook = TimeBook;
    }

    public Date getTimeXacNhan() {
        return TimeXacNhan;
    }

    public void setTimeXacNhan(Date TimeXacNhan) {
        this.TimeXacNhan = TimeXacNhan;
    }

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
