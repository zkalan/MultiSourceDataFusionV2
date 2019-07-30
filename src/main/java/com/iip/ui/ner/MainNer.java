package com.iip.ui.ner;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainNer extends Application {
    private double xOffset;
    private double yOffset;

    @Override
    public void init() throws Exception {
        // 初始化参数配置
        super.init();
        xOffset = 0.0;
        yOffset = 0.0;

//        // 初始化连接池 - 这句话不要删掉
//        ConnectionPool.getInstance();
//        // 初始化数据服务
//        DataService.getInstance();
//
//        // test - add SourceSeverConnection
//        ConnectionConfigBean connectionConfigBean = new ConnectionConfigBean();
//        connectionConfigBean.setPort("3306");
//        connectionConfigBean.setUsername("root");
////        connectionConfigBean.setPassword("root");
//        connectionConfigBean.setNickName("education-system");
//        connectionConfigBean.setHostIP("127.0.0.1");
////        connectionConfigBean.setHostIP("114.212.83.171");
//        connectionConfigBean.setDriver(DriverType.MYSQL);
//        connectionConfigBean.setTargetDB(false);
//        connectionConfigBean.setName("education_system");
//        ConnectionPool.getInstance().getSourceConnections().put("education-system", new ServerConnection(connectionConfigBean));
//        for (String dbName: ConnectionPool.getInstance().getSourceConnections().keySet()){
//            System.out.println("DBNAME:" + dbName);
//            System.out.println("TABLES:");
//            for (String tableName: ConnectionPool.getInstance().getSourceConnections().get(dbName).getTables().keySet()
//                    ) {
//                System.out.print(tableName + " :");
//                for (String fieldName: ConnectionPool.getInstance().getSourceConnections().get(dbName).getTables().get(tableName).getFields().keySet()
//                        ) {
//                    System.out.print(" " + fieldName);
//                }
//                System.out.print("\n");
//            }
//        }
    }


    @Override
    public void start(final Stage primaryStage) throws Exception {
        // 主程序开始
        try {
            // 读取fxml文件
            Parent root = FXMLLoader.load(getClass().getResource("/com/iip/ui/ner/view/nerMainView.fxml"));

            // 设定stage可以通过鼠标拖动到屏幕的其他地方
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    event.consume();
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    event.consume();
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            });
            // 主窗口加载的场景，场景里面的描述文件
            Scene scene = new Scene(root);
//            scene.getStylesheets().add(getClass().getResource("view/stylesheet/ComboBox.css").toExternalForm());
            primaryStage.setScene(scene);
            // 窗口风格设置 白色背景，没有操作系统平台装饰
            primaryStage.initStyle(StageStyle.UNDECORATED);

//            primaryStage.setX(Screen.getPrimary().getVisualBounds().getMinX());
//            primaryStage.setY(Screen.getPrimary().getVisualBounds().getMinY());
//            primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
//            primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());

            //最后就是show time
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void show_dialog(String message) {
        Alert _alert = new Alert(Alert.AlertType.INFORMATION);
        _alert.setTitle("警告");
        _alert.setHeaderText("错误");
        _alert.setContentText(message);
        _alert.show();
    }
    Stage stage=new Stage();
    public void  showWindow() throws Exception {
        stage.initModality(Modality.APPLICATION_MODAL);
        start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
