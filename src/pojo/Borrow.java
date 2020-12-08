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
public class Borrow {
    private String ngaymuon,ngaytra;
    private int idbook,tien,id, iddocgia;

    public Borrow(int docgia,int id, int idbook) {
        this.id = id;
        this.idbook= idbook;
        this.iddocgia = docgia;
     
    }

    public Borrow(String ngaymuon, String ngaytra, int idbook, int tien, int id, int iddocgia) {
        this.ngaymuon = ngaymuon;
        this.ngaytra = ngaytra;
        this.idbook = idbook;
        this.tien = tien;
        this.id = id;
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the iddocgia
     */
    public int getIddocgia() {
        return iddocgia;
    }

    /**
     * @param iddocgia the iddocgia to set
     */
    public void setIddocgia(int iddocgia) {
        this.iddocgia = iddocgia;
    }


    
}
