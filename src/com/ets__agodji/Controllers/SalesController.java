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
    private TableView<SalesProducts> salesProductsTabView;

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
     *
     * @param selected_sale il s'agit de la vente selectionné
     *                      logique: on prend l'id de la vente selectionné et on récupère les ventes_produits associés
     * @return cette fonction retourne un tableau des ventes_produits(id, sale_id_id, product_id,quantity)
     * @throws SQLException
     */
    private List<SalesProducts> getSalesProducts(Sales selected_sale) throws SQLException {
        QueryBuilder<SalesProducts, String> queryBuilder = SaleProductDao().queryBuilder();
        Where<SalesProducts, String> where = queryBuilder.where();

        where.eq("sale_id_id", selected_sale.getId());
        PreparedQuery<SalesProducts> query = queryBuilder.prepare();
        List<SalesProducts> salesProducts = SaleProductDao().query(query);

        return salesProducts;
    }

    /**
     *
     * @param sale_product  il s'agit de chaque vente_produit
     *                      logique: on prend chaque product_id_id de vente_produit et on récupère les informations
     *                      du produit associé
     * @return cette fonction nous retourne tous les produits d'une vente
     * @throws SQLException
     */
    private List<Products> getProductsOfSales(SalesProducts sale_product) throws SQLException {
        List<Products> productsList = null;

        QueryBuilder<Products, String> prodQueryBuilder = ProductDao().queryBuilder();
        Where<Products, String> prodWhere = prodQueryBuilder.where();
        prodWhere.eq("id", sale_product.getProduct_id().getId());
        PreparedQuery<Products> preparedQuery = prodQueryBuilder.prepare();
        productsList = ProductDao().query(preparedQuery);

        return productsList;
    }

    /**
     * Permet de récupérer les produits(nom, quantité, prix, stock) achétés au cours d'une vente
     * @param mouseEvent
     * @throws SQLException
     */
    @FXML
    private void getSalesProducts(MouseEvent mouseEvent) throws SQLException {
        Sales selected_sale = salesTabView.getSelectionModel().getSelectedItem();
        ObservableList<SalesProducts> salesProductsView = FXCollections.observableArrayList();

        if (selected_sale != null){

            //WARNING: La première boucle s'exécute complètement avant la deuxième d'ou le break :)
            for (SalesProducts sale_product: getSalesProducts(selected_sale)){
                for(Products product:getProductsOfSales(sale_product)){
                    salesProductsView.add(new SalesProducts(product.getName(),sale_product.getQuantity(), product.getSell_price(), product.getBuy_price(), product.getStock()));
                }
                break;
            }


            salesProductsTabView.setItems(salesProductsView);


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
        colNameProduct.setCellValueFactory(new PropertyValueFactory<>("product_name"));
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