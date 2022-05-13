package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Products;
import com.ets__agodji.Models.Sales;
import com.ets__agodji.Models.SalesProducts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class SalesController implements Initializable {

    @FXML
    private TableColumn<Sales, Float> colAmountPaid;

    @FXML
    private TableColumn<Products, Float> colBuyPrice;

    @FXML
    private TableColumn<Products,String> colNameProduct;

    @FXML
    private TableColumn<Sales, Integer> colNumSale;

    @FXML
    private TableColumn<SalesProducts, Integer> colNumSale2;
    @FXML
    private TableColumn<SalesProducts, Integer> colQuantity;

    @FXML
    private TableColumn<Sales, Date> colSaleDate;

    @FXML
    private TableColumn<Products, Float> colSalePrice;

    @FXML
    private TableColumn<Products,Integer> colStock;

    @FXML
    private TableColumn<Sales, Float> colTotal;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
