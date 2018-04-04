import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Collections;


public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Slot Machine");

        // スロット部のHBoxの設定
        HBox slot_box = new HBox(20d);
        slot_box.setAlignment(Pos.CENTER);

        ArrayList<JavaSlot> slotArr = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            slotArr.add(new JavaSlot("1"));
        }

        slot_box.getChildren().addAll(slotArr);

        // Start・Stopボタンを設定するHBoxの設定
        HBox buttonBox = new HBox(10d);
        buttonBox.setAlignment(Pos.CENTER);
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        buttonBox.getChildren().add(startButton);
        buttonBox.getChildren().add(stopButton);

        // スロット部とStart・Stopボタンを設定するVBoxの設定
        VBox all_box = new VBox(20d);
        all_box.setAlignment(Pos.CENTER);
        all_box.getChildren().add(slot_box);
        all_box.getChildren().add(buttonBox);

        // StartボタンのAction設定
        startButton.setOnAction(event -> {
            for (int i = 0; i < slotArr.size(); i++) {
                slotArr.get(i).setSlotStarted();
            }
        });

        // StopボタンのAction設定
        stopButton.setOnAction(event -> {
            // まずはボタンのアニメーションを止める
            for(int i = 0; i < slotArr.size() ;i++){
                slotArr.get(i).stopSlot();
            }

            // 結果の判定を行う
            // まずはスロットから全ての値を取得してresという名前をArrayListに入れる
            ArrayList<String> res = new ArrayList<>();
            for(int i = 0; i < slotArr.size() ;i++){
                res.add(slotArr.get(i).getText());
            }
            // resの0番目の値がres全体で何個かを調べる
            // それが配列全体と同じだったら、スロットの値がすべて一致しているとみなす
            // Collection.frequency(配列やリスト名, 検索対象の値)でその値が配列のどのくらい含まれているか確認できる
            if (Collections.frequency(res, res.get(0)) == res.size()) {
                // 新しいウインドウを生成
                Stage newStage = new Stage();

                // モーダルウインドウに設定
                newStage.initModality(Modality.APPLICATION_MODAL);
                // オーナーを設定
                newStage.initOwner(primaryStage);

                // 新しいウインドウ内に配置するコンテンツを生成
                HBox hbox = new HBox();
                Label label = new Label("おめでとう！");
                label.setFont(new Font(20d));
                hbox.getChildren().add(label);

                newStage.setScene(new Scene(hbox));

                // 新しいウインドウを表示
                newStage.show();

            }
        });

        Scene scene = new Scene(all_box, 320, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}