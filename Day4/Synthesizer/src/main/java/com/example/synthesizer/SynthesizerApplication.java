package com.example.synthesizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Label;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class SynthesizerApplication extends Application {
    AnchorPane mainCenter;
    public static ArrayList<AudioComponentWidget> widgets = new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException {


          VBox MainLayout=new VBox();

// --------------------FROM LECTURE -------------------------------------------------
        BorderPane mainLayout = new BorderPane();

        //right panel
        VBox rightpanel = new VBox();
        rightpanel.setStyle("-fx-background-color: white");
        rightpanel.setPadding(new Insets(4));

        javafx.scene.control.Button sinwaveBtn = new Button("SineWave");
        rightpanel.getChildren().add(sinwaveBtn);
       //sinwaveBtn.setOnAction(e-> createComponent(e));
        rightpanel.getChildren().add(sinwaveBtn);


        //center panel
        AnchorPane mainCenter = new AnchorPane();
        mainCenter.setStyle("-fx-background-color: black");
        Circle speaker = new Circle(400,200,15);
        speaker.setFill(Color.BLACK);

        mainCenter.getChildren().add(speaker);

        mainLayout.setCenter(mainCenter);
        mainLayout.setRight(rightpanel);




        Scene scene = new Scene(MainLayout, 800, 600);
        stage.setTitle("Synthesizer");




        Label freqLabel = new Label("Frequency of Sine wave");
        MainLayout.getChildren().add(freqLabel);
        MainLayout.setStyle("-fx-background-color: #98d398");
        VBox freqBox = new VBox();

       // freqBox.setStyle("-fx-background-color: white");
        freqBox.setPadding(new Insets(2));
        freqBox.relocate(70,200);
        freqBox.getChildren().add(freqLabel);



//        Slider freqSlider = new Slider(20,10000,320);
//      freqSlider.setOnMouseDragged(e->handleSlider(e,freqSlider, freqLabel));
//        freqBox.getChildren().add(freqSlider);

        AudioComponentWidget play = new AudioComponentWidget(new SineWave(440), MainLayout);


        VBox playBox = new VBox();
        Button playButton = new Button();
        playButton.setPrefSize(50,50);
        //playBox.setStyle("-fx-background-color: rgba(128,128,128,0.63)");
        Label playButtonLabel = new Label("Play");
      playButton.setOnAction(e-> playAudio(e, play.ac_));
        playBox.getChildren().add(playButtonLabel);
        playBox.getChildren().add(playButton);
        playBox.relocate(390,390);





        MainLayout.getChildren().add(freqBox);
        MainLayout.getChildren().add(playBox);

        stage.setScene(scene);
        stage.show();
    }




    public static void main(String[] args) {
        launch();
    }
    private void playAudio(javafx.event.ActionEvent e, AudioComponent ac) {
        try {
            AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);
            Clip c = AudioSystem.getClip(); // Not our AudioClip class
            AudioListener listener = new AudioListener(c);
            byte[] data = ac.getClip().getData();
            // Reads data from our byte array to play it.
            c.open(format16, data, 0, data.length);
            c.start(); // Actually starts playing the sound.
            // Don’t end the program until the sound finishes playing...
            c.addLineListener(listener);
        } catch (LineUnavailableException k) {
            //System.out.println(k.getMessage());
        }

    }
    private void createComponent(ActionEvent e){
        AudioComponent sinewave = new SineWave(200);
        AudioComponentWidget acw = new AudioComponentWidget(sinewave, mainCenter);
        mainCenter.getChildren().add(acw);
        widgets.add(acw);
    }
}

