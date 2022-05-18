package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Products;
import com.ets__agodji.Models.Sales;
import com.ets__agodji.Models.SalesProducts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import static com.ets__agodji.Dao.AllDao.SaleDao;

public class SalesController implements Initializable {

    @FXML
    private TableColumn<Sales, Float> colAmountPaid;

    @FXML
    private TableColumn<Products, Float> colBuyPrice;

    @FXML
    private TableColumn<Products, String> colNameProduct;

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
    private TableColumn<Products, Integer> colStock;

    @FXML
    private TableColumn<Sales, Float> colTotal;

    @FXML
    private TableView<Sales> salesTabView;

    @FXML
    private TableView<SalesProducts> salesProductsTabView;

    @FXML
    private TableColumn<Sales, String> colClientName;

    private TableView<?> getAllSales() throws SQLException {
        ObservableList sales = FXCollections.observableArrayList();
        for (Sales sale : SaleDao()) {
            sales.add(new Sales(
                    sale.getId(), sale.getCreated_at(), sale.getClient_id(), sale.getTotal_price(),
                    sale.getCreated_by_id(), sale.getAmount_paid(), sale.getClient_name()
            ));
        }
        salesTabView.setItems(sales);
        return salesTabView;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuration sales tab
        colAmountPaid.setCellValueFactory(new PropertyValueFactory<>("amount_paid"));
        colNumSale.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        colSaleDate.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        colClientName.setCellValueFactory(new PropertyValueFactory<>("client_name"));

        // Configuration sales products tab
        colNumSale2.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Configuration products
        colNameProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colSalePrice.setCellValueFactory(new PropertyValueFactory<>("sell_price"));
        colBuyPrice.setCellValueFactory(new PropertyValueFactory<>("buy_price"));

        try {
            getAllSales();
        } catch (SQLException e) {
            System.out.println("une erreur s'est produite lors du chargements des ventes");
        }
    }
}