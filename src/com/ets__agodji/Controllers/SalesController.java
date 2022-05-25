package com.ets__agodji.Controllers;

import com.ets__agodji.Models.Products;
import com.ets__agodji.Models.Sales;
import com.ets__agodji.Models.SalesProducts;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

import static com.ets__agodji.Dao.AllDao.*;

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
    private TableColumn<SalesProducts, Integer> colQuantity;

    @FXML
    private TableColumn<Sales, LocalDate> colSaleDate;

    @FXML
    private TableColumn<Products, Float> colSalePrice;

    @FXML
    private TableColumn<Products, Integer> colStock;

    @FXML
    private TableColumn<Sales, Float> colTotal;

    @FXML
    private TableView<Sales> salesTabView;

    @FXML
    private TableView<?> salesProductsTabView;

    @FXML
    private TableColumn<Sales, String> colClientName;


    /**
     * Permet de récupérer toutes les effectuées
     * @return
     * @throws SQLException
     */
    private TableView<?> getAllSales() throws SQLException {
        ObservableList<Sales> sales = FXCollections.observableArrayList();
        for (Sales sale : SaleDao()) {
            sales.add(new Sales(
                    sale.getId(), sale.getCreated_at().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate(), sale.getClient_id(), sale.getTotal_price(),
                    sale.getCreated_by_id(), sale.getAmount_paid()
            ));
        }
        salesTabView.setItems(sales);
        return salesTabView;
    }

    /**
     * Permet de récupérer les produits(nom, quantité, prix, stock) achétés au cours d'une vente
     * @param mouseEvent
     * @throws SQLException
     */
    @FXML
    private void getSalesProducts(MouseEvent mouseEvent) throws SQLException {
        Sales selected_sale = salesTabView.getSelectionModel().getSelectedItem();
        /**
         *Quand on select une vente, on prend l'id de la vente
         * et on le fait correspondre au sale_id_id de SalesProducts afin de connaître quels produits ont été achetés lors d'une vente
         * ensuite, on récupère la liste des sales_products qu'on affiche dans une table view
         *
         */
        if (selected_sale != null){


            QueryBuilder<SalesProducts, String> queryBuilder = SaleProductDao().queryBuilder();
            Where<SalesProducts, String> where = queryBuilder.where();

            where.eq("sale_id_id", selected_sale.getId());
            PreparedQuery<SalesProducts> query = queryBuilder.prepare();
            List<SalesProducts> salesProductsList = SaleProductDao().query(query);

            ObservableList productsSales = FXCollections.observableArrayList();

            for(SalesProducts product_sale : salesProductsList){
                productsSales.add(new SalesProducts(product_sale.getSale_id(), product_sale.getProductName(), product_sale.getSellPrice(),
                        product_sale.getBuyPrice(), product_sale.getQuantity(), product_sale.getStock()));
            }

            salesProductsTabView.setItems(productsSales);

        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuration sales tab
        colAmountPaid.setCellValueFactory(new PropertyValueFactory<>("amount_paid"));
        colNumSale.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        colSaleDate.setCellValueFactory(new PropertyValueFactory<>("localDate"));
        colClientName.setCellValueFactory(new PropertyValueFactory<>("client_name"));

        // Configuration products
        colNameProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colSalePrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        colBuyPrice.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));

        try {
            getAllSales();
        } catch (SQLException e) {
            System.out.println("une erreur s'est produite lors du chargements des ventes");
        }
    }
}