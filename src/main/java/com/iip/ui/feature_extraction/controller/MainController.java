package com.iip.ui.feature_extraction.controller;

import com.iip.ui.feature_extraction.Main;
import com.iip.ui.feature_extraction.execute.connection.DatabaseOperations;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * Created by YLX on 2018/12/3
 */

public class MainController {

    @FXML
    private BorderPane feMainViewPane;

    @FXML
    private Label feMainTitle;

    @FXML
    private AnchorPane feStep1;

    @FXML
    private AnchorPane feStep2;

    @FXML
    private AnchorPane feStep3;

    @FXML
    private AnchorPane feStep4;

    @FXML
    private AnchorPane feStep5;

    @FXML
    private Label keyword, vectorize, summary, un;
    @FXML
    private TextField user;
    @FXML
    private PasswordField pw;
    private String u, p = "";
    private boolean isLogin = false;

    /**
     * 标记当前所选择的菜单栏中的按钮
     */
    private AnchorPane currentMenuButton;
    /**
     * 缓存连接配置界面
     */
    private AnchorPane paneStep1, paneStep2, paneStep3, paneStep4, paneConfig;

//    @FXML
//    private void login(MouseEvent mouseEvent){
//        if(isLogin){
//            Main.f_alert_informationDialog("已登录!", "请进入配置界面进行数据源的配置!");
//            return;
//        }
//        u = user.getText().trim();
//        p = pw.getText().trim();
//        if(u.equals("")||p.equals("")){
//            DatabaseOperations.print("用户名或者密码为空");
//            Main.f_alert_informationDialog("用户名和密码为空!", "请检查用户名和密码!");
//        }else if(u.equals("root")&&p.equals("123456")){
//            DatabaseOperations.print("登录成功");
//            Main.f_alert_informationDialog("已登录!", "请进入配置界面进行数据源的配置!");
//            isLogin = true;
//        }else{
//            DatabaseOperations.print("用户名或者密码错误");
//            Main.f_alert_informationDialog("用户名或密码错误!", "请检查用户名和密码!");
//        }
//    }

    public void initialize(){
        keyword.setText("关键词");
        vectorize.setText("向量化");
    }

    @FXML
    private void menuButtonClicked(MouseEvent mouseEvent) {
        if(false){
            DatabaseOperations.print("未登录");
            Main.f_alert_informationDialog("未登录!", "请先登录!");
            return;
        }
        mouseEvent.consume();
        AnchorPane selectedMenuButton = (AnchorPane) mouseEvent.getTarget();

        if (currentMenuButton == selectedMenuButton){
            return;
        }

        if (currentMenuButton != null){
            currentMenuButton.getStyleClass().clear();
            currentMenuButton.getStyleClass().add("menuButton");
        }

        if (selectedMenuButton == feStep1){
            feStep1.getStyleClass().clear();
            feStep1.getStyleClass().add("menuButtonSelected");
            feMainTitle.setText("FE-分词");
            if (paneStep1 == null){
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainController.class.getResource("/com/iip/ui/feature_extraction/view/FE1ParticipleView.fxml"));
                    paneStep1 = loader.load();
                    paneStep1.setPrefSize(feMainViewPane.getWidth()-60, feMainViewPane.getHeight()-60);
                    FE1ParticipleController controller = loader.getController();
                    controller.initialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            feMainViewPane.setCenter(paneStep1);
            currentMenuButton = selectedMenuButton;
        }

        if (selectedMenuButton == feStep2){
            feStep2.getStyleClass().clear();
            feStep2.getStyleClass().add("menuButtonSelected");
            feMainTitle.setText("FE-关键词");
            if (paneStep2 == null){
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainController.class.getResource("/com/iip/ui/feature_extraction/view/FE2KeywordsView.fxml"));
                    paneStep2 = loader.load();
                    paneStep2.setPrefSize(feMainViewPane.getWidth()-60, feMainViewPane.getHeight()-60);
                    FE2KeywordsController controller = loader.getController();
                    controller.initialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            feMainViewPane.setCenter(paneStep2);
            currentMenuButton = selectedMenuButton;
        }

        if (selectedMenuButton == feStep3){
            feStep3.getStyleClass().clear();
            feStep3.getStyleClass().add("menuButtonSelected");
            feMainTitle.setText("FE-向量化");

            if (paneStep3 == null){
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainController.class.getResource("/com/iip/ui/feature_extraction/view/FE3Doc2vecView.fxml"));
                    paneStep3 = loader.load();
                    paneStep3.setPrefSize(feMainViewPane.getWidth()-60, feMainViewPane.getHeight()-60);
                    FE3Doc2vecController controller = loader.getController();
                    controller.initialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            feMainViewPane.setCenter(paneStep3);
            currentMenuButton = selectedMenuButton;
        }

        if (selectedMenuButton == feStep4){
            feStep4.getStyleClass().clear();
            feStep4.getStyleClass().add("menuButtonSelected");
            feMainTitle.setText("FE-文本摘要");

            if (paneStep4 == null){
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainController.class.getResource("/com/iip/ui/feature_extraction/view/FE4TextsummyView.fxml"));
                    paneStep4 = loader.load();
                    paneStep4.setPrefSize(feMainViewPane.getWidth()-60, feMainViewPane.getHeight()-60);
                    FE4TextsummyController controller = loader.getController();
                    controller.initialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            feMainViewPane.setCenter(paneStep4);
            currentMenuButton = selectedMenuButton;
        }

        if (selectedMenuButton == feStep5){
            feStep5.getStyleClass().clear();
            feStep5.getStyleClass().add("menuButtonSelected");
            feMainTitle.setText("FE-配置");

            if (paneConfig == null){
                try {
                    FXMLLoader loader = new FXMLLoader();
//                    loader.setResources(ResourceBundle.getBundle("my", Locale.CHINA));
                    loader.setLocation(MainController.class.getResource("/com/iip/ui/feature_extraction/view/FE5ConfigView.fxml"));
                    paneConfig = loader.load();
                    paneConfig.setPrefSize(feMainViewPane.getWidth()-60, feMainViewPane.getHeight()-60);
                    FE5ConfigController controller = loader.getController();
                    controller.initialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            feMainViewPane.setCenter(paneConfig);
            currentMenuButton = selectedMenuButton;
        }
    }

    @FXML
    private void exit(MouseEvent mouseEvent){
        DatabaseOperations.disconnect();
        mouseEvent.consume();
        Stage stg = (Stage)((AnchorPane)mouseEvent.getSource()).getScene().getWindow();
        stg.close();
        //Platform.exit();
    }

}
