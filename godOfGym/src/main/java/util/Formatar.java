
package util;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;


public class Formatar {
    
    public static void formatarCPF(TextField tf){
        Pattern padrao = Pattern.compile("\\d*");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (!padrao.matcher(newText.replaceAll("[^\\d]", "")).matches() || newText.length()>14){
                return null;
            }
            
            StringBuilder formatterCpf = new StringBuilder();
            String cpfPuro = newText.replaceAll("[^\\d]", "");
            
            
            for(int i = 0; i < cpfPuro.length(); i++){
                formatterCpf.append(cpfPuro.charAt(i));
                if (i == 2 || i == 5){
                    formatterCpf.append(".");
                } else if (i == 8){
                    formatterCpf.append("-");
                }
            }
            
            change.setText(formatterCpf.toString());
            change.setRange(0, change.getControlText().length());
            int caretPosition = Math.min(formatterCpf.length(), change.getCaretPosition());
            change.setCaretPosition(caretPosition);
            change.setAnchor(caretPosition);
            return change;
        };
        
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        tf.setTextFormatter(formatter);
    }
    
    public static void formatarTelefone(TextField tf){
        Pattern padrao = Pattern.compile("\\d*");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (!padrao.matcher(newText.replaceAll("[^\\d]", "")).matches() || newText.length()>13){
                return null;
            }
            
            StringBuilder formatterTelefone = new StringBuilder();
            String telefonePuro = newText.replaceAll("[^\\d]", "");
            formatterTelefone.append("(");
            for(int i = 0; i < telefonePuro.length(); i++){
                formatterTelefone.append(telefonePuro.charAt(i));
                if (i == 1){
                    formatterTelefone.append(")");
                } else if (i == 5){
                    formatterTelefone.append("-");
                }
            }
            
            change.setText(formatterTelefone.toString());
            change.setRange(0, change.getControlText().length());
            int caretPosition = Math.min(formatterTelefone.length(), change.getCaretPosition());
            change.setCaretPosition(caretPosition);
            change.setAnchor(caretPosition);
            return change;
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        tf.setTextFormatter(formatter);
    }
    
    
}
