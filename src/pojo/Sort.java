/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author USER
 */
public class Sort {
               private ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Tên sách",
        "Tác giả",
        "Năm xuất bản",
        "Thể loại"
    );

    public Sort() {
    }
               

    /**
     * @return the options
     */
    public ObservableList<String> getOptions() {
        return options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(ObservableList<String> options) {
        this.options = options;
    }
               
}
