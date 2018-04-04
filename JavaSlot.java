import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;


import java.util.Random;

public class JavaSlot extends Label {
    // アニメーションを行うためのTimeLineオブジェクト
    private Timeline timer;
    // スロットが回っている状態かを管理するフィールド
    private boolean isSlotStarted;

    JavaSlot(String text){
        super(text);
        isSlotStarted = false;
        // この下にthis.~という形で数字の見た目・揃える位置を記載しましょう
        // まずは大きさを80,80あたりに設定
        this.setPrefSize(80,80);
        // 数字が真ん中にくるよう設定
        this.setAlignment(Pos.CENTER);
        // Borderを設定、これはこのような形であると覚えておきましょう！
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
    }

    public void setSlotStarted(){
        // この中に各数字のスロットが回り始めた時の処理を記載しましょう

        // 連打された時に何回もアニメーションが設定されないように
        // isSlotStarted = trueだった場合は、何も処理しない
        if (isSlotStarted) {
            return;
        }
        // スロットが回っているのでisSlotStartedをtrueに変える
        isSlotStarted = true;

        // 乱数の設定を行うオブジェクトを宣言
        Random rand = new Random();

        // フィールドに設定されているtimerにtimelineオブジェクトを設定

        // 重要：この際、Timeline timer という形で宣言してしまうと
        // timerがこのsetSlotStarted内でしか使えなくなってしまい
        // stopSlotで使えなくなってしまうので気をつけましょう！
        timer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            int randomNumber = rand.nextInt(9) + 1;
            this.setText(String.valueOf(randomNumber));
        }));

        // アニメーションをスタート
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

    }

    public void stopSlot() {
        // この中に各数字のスロットが止まった時の処理を記載しましょう

        if (isSlotStarted){
            timer.stop();
            isSlotStarted = false;
        }
    }

}