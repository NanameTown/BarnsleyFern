import java.util.*;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class BarnsleyFern extends Application{
	
	int n = 200000; //点の数
	double distX = 0, distY = 0; //中心距離
	
	FernData[] c = new FernData[n];
	Group root = new Group();
	Pane p = new Pane();
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage stage)throws Exception{
		p.setPrefWidth(1280);
		p.setPrefHeight(720);
		
		root.getChildren().add(p);
		
		sidaCalc();
		circleRadiusCalc();
		
		//シーンを設定
		Scene scene = new Scene(root, 1280, 720);
		
		//ウィンドウのタイトル
		stage.setTitle("シダ");
		
		//ステージにシーンを設定
		stage.setScene(scene);
		
		//表示
		stage.show();
		
		scene.setOnMouseClicked(e -> {
			if(e.getButton() == MouseButton.PRIMARY){
				distY = p.getLayoutY() - (360 * p.getScaleX());
				p.setScaleX(p.getScaleX() * 2);
				p.setScaleY(p.getScaleY() * 2);
				p.setLayoutY(distY);
			}else if(e.getButton() == MouseButton.SECONDARY){
				p.setScaleX(p.getScaleX() / 2);
				p.setScaleY(p.getScaleY() / 2);
				distY = p.getLayoutY() + (360 * p.getScaleX());
				p.setLayoutY(distY);
			}
			
			circleRadiusCalc();
			p.layout();
			
		});
	}
	
	//点のサイズ計算
	public void circleRadiusCalc(){
		for(int i=0; i<n; i++){
			c[i].getCircle().setRadius(1 / (p.getScaleX() * 2));
		}
	}

	//シダ曲線の計算
	public void sidaCalc(){
		Random r = new Random();
		double x = 0.0, y = 0.0, xn = 1.0, yn = 1.0;
		
		for(int i=0; i<n; i++){
        	int rand = r.nextInt(100) + 1;
			
			if(rand <= 85){
				x = (0.85 * xn) + (0.04 * yn);
				y = (-0.04 * xn) + (0.85 * yn) + 1.6;
			}else if(rand <= 92){
				x = (0.2 * xn) - (0.26 * yn);
				y = (0.23 * xn) + (0.22 * yn) + 1.6;
			}else if(rand <= 99){
				x = (-0.15 * xn) + (0.28 * yn);
				y = (0.26 * xn) + (0.24 * yn) + 0.44;
			}else{
				x = 0.0;
				y = 0.16 * yn;
			}
			
			xn = x;
			yn = y;
			
			//点座標を配列に格納
			c[i] = new FernData(xn, yn);
			p.getChildren().add(c[i].getCircle());
		}
	}
}