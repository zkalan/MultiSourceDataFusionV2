package com.iip.ui.ner.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import java.net.URL;

import com.iip.ui.ner.Resource.nerData;
import com.iip.textprocess.participle.Participle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PosViewController extends RootController implements Initializable {

    @FXML
    private ListView<String> LVData;
    @FXML
    private Label LBData;
    @FXML
    private TextField TFHintLoad;
    @FXML
    private Button BtnHintLoad;
    @FXML
    private TabPane TPPos;
    public TabPane getTPPos(){ return TPPos; }


    public Tab generateNewTab(String id, String text){
        Tab tab = new Tab();
        tab.setId(id);
        tab.setText(text);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ParticipleTabViewController.class.getResource("/com/iip/ui/ner/view/PosTabView.fxml"));
            BorderPane pane = loader.load();
            tab.setContent(pane);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
        return tab;
    }

    /**
     * 下方的组件跟停用词加载相关
     */
//    @FXML
//    VBox VBStopwords;
//    @FXML
//    Button BtnSwichPresent;
//    @FXML
//    TextArea TAStopwords;

//    public void refreshTAStopwords(){
//        String userStopWords = "";
//        for (String word: Participle.userStopWords)
//            userStopWords += word+"\n";
//        TAStopwords.setText(userStopWords);
//    }

//    @FXML
//    public void showStopwordsLoadViewClicked(){
//        if (VBStopwords.isVisible() == true){
//            BtnSwichPresent.setText("显示停用词加载界面");
//            VBStopwords.setVisible(false);
//        }
//        else{
//            BtnSwichPresent.setText("关闭停用词加载界面");
//            VBStopwords.setVisible(true);
//        }
//
//    }

//    @FXML
//    public void updateStopwordsClicked(){
//        String stopWords = TAStopwords.getText();
//        String [] lines = stopWords.split("\n");
//        Participle.userStopWords.clear();
//        for (String line: lines){
//            System.out.println("debug updateStopwordsClicked："+line);
//            Participle.userStopWords.add(line);
//        }
//    }
//
//    @FXML
//    public void selectStopwordsClicked(){
//        // 根据文件选择器选取文件加载要读入的数据
//        Stage stage = (Stage) TAStopwords.getScene().getWindow();
//        FileChooser fileChooser = new FileChooser();
//        File file = fileChooser.showOpenDialog(stage);
//        if(file == null) return;
//        // 从获取到的文件中读入文本数据，以行为单位
//        BufferedReader reader = null;
//        try {
//            String temp = null;
//            reader = new BufferedReader(new FileReader(file));
//            Participle.userStopWords.clear();
//            while ((temp = reader.readLine()) != null)
//                Participle.userStopWords.add(temp);
//            reader.close();
//            refreshTAStopwords();
//        } catch (IOException e) {
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
//    }

    @Override
    public void init(){
        // 数据如果已经加载过，就不显示加载数据的提示
        if( nerData.handledDataList.size() > 0 ){
            BtnHintLoad.setOpacity(0.0f);
            TFHintLoad.setOpacity(0.0f);
        }
        else{
            BtnHintLoad.setOpacity(1.0f);
            TFHintLoad.setOpacity(1.0f);
            BtnHintLoad.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    nerMainViewController controller =
                            (nerMainViewController) Context.controllers.get(nerMainViewController.class.getSimpleName());
                    controller.presentLoginView();
                }
            });
        }

        // 加载TabView fxml
        Tab tab1 = generateNewTab("Tab1", "词性标注");
        if(tab1 != null) TPPos.getTabs().add(tab1);
//        Tab tab2 = generateNewTab("Tab2", "极速词典分词");
//        if(tab2 != null) TPParticiple.getTabs().add(tab2);

        // 加载当前的停用词表
//        refreshTAStopwords();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        System.out.println("In pos initialize");
        init();
    }


}

