package util;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class Formatar {

    public static void formatarCPF(TextField tf) {
        Pattern padrao = Pattern.compile("\\d*");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            
            String newText = change.getControlNewText();
            String cpfPuro = newText.replaceAll("[^\\d]", "");
            
            if (!padrao.matcher(cpfPuro).matches() || newText.length() > 14) {
                return null;
            }

            if (!cpfPuro.isEmpty()){
                String cpfFormatado = cpfPuro.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
                change.setText(cpfFormatado);
                change.setRange(0, change.getControlText().length());
                change.setCaretPosition(cpfFormatado.length());
                change.setAnchor(cpfFormatado.length());
            }

            
            return change;
        };

        TextFormatter<String> formatter = new TextFormatter<>(filter);
        tf.setTextFormatter(formatter);
    }

    public static void formatarTelefone(TextField tf) {
        Pattern padrao = Pattern.compile("\\d*");
        
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            String telefonePuro = newText.replaceAll("[^\\d]", "");
            
            if (!telefonePuro.matches("\\d*") || telefonePuro.length() > 11) {
            return null;
            }

            if (!telefonePuro.isEmpty()) {
            String telefoneFormatado = telefonePuro
                .replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1)$2-$3");
            
            change.setText(telefoneFormatado);
            change.setRange(0, change.getControlText().length());
            change.setCaretPosition(telefoneFormatado.length());
            change.setAnchor(telefoneFormatado.length());
        }
            return change;
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        tf.setTextFormatter(formatter);
    }
    
    public static void formatarDinheiro(TextField tf){
        Pattern padrao = Pattern.compile("\\d*");
        
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            String salarioPuro = newText.replaceAll("[^\\d]", "");
            
            if (!salarioPuro.matches("\\d*") || salarioPuro.length() > 7) {
            return null;
            }

            if (!salarioPuro.isEmpty()) {
                
            while(salarioPuro.length() < 3){
                salarioPuro = "0" + salarioPuro; // centavos
            }
            
            String reais = salarioPuro.substring(0, salarioPuro.length() - 2);
            String centavos = salarioPuro.substring(salarioPuro.length()-2);
            
            reais = reais.replaceFirst("^0+(?!$)", "");
            
            StringBuilder reaisFormatados = new StringBuilder();
            int conta = 0;
            
            for(int i = reais.length() - 1; i>= 0; i--){
                if (conta > 0 && conta % 3 == 0){
                    reaisFormatados.insert(0, ".");
                }
                reaisFormatados.insert(0, reais.charAt(i));
                conta++;
            }
            
            String salarioFormatado = reaisFormatados.toString() + "," + centavos;
            
            change.setText(salarioFormatado);
            change.setRange(0, change.getControlText().length());
            change.setCaretPosition(salarioFormatado.length());
            change.setAnchor(salarioFormatado.length());
        }
            return change;
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        tf.setTextFormatter(formatter);
    }
    
    public static void formatarHorario(TextField tf){
        Pattern padrao = Pattern.compile("\\d*");
        
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            String horarioPuro = newText.replaceAll("[^\\d]", "");
            
            if (!horarioPuro.matches("\\d*") || horarioPuro.length() > 4) {
            return null;
            }

            if (!horarioPuro.isEmpty()) {
            String horarioFormatado = horarioPuro
                .replaceFirst("(\\d{2})(\\d{2})", "$1:$2");
            
            change.setText(horarioFormatado);
            change.setRange(0, change.getControlText().length());
            change.setCaretPosition(horarioFormatado.length());
            change.setAnchor(horarioFormatado.length());
        }
            return change;
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        tf.setTextFormatter(formatter);
    }
}
