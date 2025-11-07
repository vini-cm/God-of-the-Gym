
package util;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Visibilidade {
    
    public static void visibilidadeTexto(CheckBox cb, TextField tf){
        cb.setOnAction(e -> {
            if(cb.isSelected()){
                tf.setVisible(true);
            } else {
                tf.setVisible(false);
            }
        });
    }
    
    public static void visibilidadeTextArea(CheckBox cb, TextArea ta){
        cb.setOnAction(e -> {
            if(cb.isSelected()){
                ta.setVisible(true);
            } else {
                ta.setVisible(false);
            }
        });
    }
    
      public static void visibilidadeChoiceBox(CheckBox cb, ChoiceBox choice){
        cb.setOnAction(e -> {
            if(cb.isSelected()){
                choice.setVisible(true);
            } else {
                choice.setVisible(false);
            }
        });
    }
      
     public static void visibilidadeDatePicker(CheckBox cb, DatePicker dp){
        cb.setOnAction(e -> {
            if(cb.isSelected()){
                dp.setVisible(true);
            } else {
                dp.setVisible(false);
            }
        });
    }
     
     
     public static void visibilidadeHorario(CheckBox cb, TextField comeco, TextField fim){
         cb.setOnAction(e -> {
            if(cb.isSelected()){
                comeco.setVisible(true);
                fim.setVisible(true);
            } else {
                comeco.setVisible(false);
                fim.setVisible(false);
            }
        });
     }
     
     public static void visibilidadeGenero(CheckBox cb, RadioButton rf,RadioButton rm){
         cb.setOnAction(e -> {
             if(cb.isSelected()){
                 rf.setVisible(true);
                 rm.setVisible(true);
             } else {
                 rf.setVisible(false);
                 rm.setVisible(false);
             }
         });
     }
}
