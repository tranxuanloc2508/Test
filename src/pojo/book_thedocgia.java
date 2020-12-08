/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author LocNe
 */
public class book_thedocgia {
    private String iddocgia,ngaymuon,ngaytra;
    private int idbook,tien;

    public book_thedocgia(int idbook,String iddocgia,String ngaymuon,String ngaytra,int tien){
        this.idbook=idbook;
        this.iddocgia=iddocgia;
        this.ngaymuon=ngaymuon;
        this.ngaytra=ngaytra;
        this.tien =tien;
    }
    /**
     * @return the iddocgia
     */
    public String getIddocgia() {
        return iddocgia;
    }

    /**
     * @param iddocgia the iddocgia to set
     */
    public void setIddocgia(String iddocgia) {
        this.iddocgia = iddocgia;
    }

    /**
     * @return the ngaymuon
     */
    public String getNgaymuon() {
        return ngaymuon;
    }

    /**
     * @param ngaymuon the ngaymuon to set
     */
    public void setNgaymuon(String ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    /**
     * @return the ngaytra
     */
    public String getNgaytra() {
        return ngaytra;
    }

    /**
     * @param ngaytra the ngaytra to set
     */
    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }

    /**
     * @return the tien

    /**
     * @return the idbook
     */
    public int getIdbook() {
        return idbook;
    }

    /**
     * @param idbook the idbook to set
     */
    public void setIdbook(int idbook) {
        this.idbook = idbook;
    }

    /**
     * @return the tien
     */
    public int getTien() {
        return tien;
    }

    /**
     * @param tien the tien to set
     */
    public void setTien(int tien) {
        this.tien = tien;
    }
}
