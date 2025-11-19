package util;

import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class Formatar {

    public static void formatarCPF(TextField tf) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            
            String newText = change.getControlNewText();
            String cpfPuro = newText.replaceAll("[^\\d]", "");
            
            if (!cpfPuro.matches("\\d*") || newText.length() > 14) {
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
    
    public static void formatarEmail(TextField tf) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!emailValido(newValue)) {
                    tf.setStyle("-fx-border-color:yellow; -fx-text-fill:yellow");
                } else {
                    tf.setStyle("");
                }
            }
        });
    }

    private static boolean emailValido(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
    
    public static void apenasNumero(TextField tf){
        TextFormatter<String> numeroFormatter = new TextFormatter<> (change -> {
            String texto = change.getControlNewText();
            if(!texto.matches("\\d*")){
                return null;
            }
            return change;
        });
        tf.setTextFormatter(numeroFormatter);
    }
                
    public static void apenasLetras(TextField tf){
        TextFormatter<String> letrasFormatter = new TextFormatter<> (change -> {
            String texto = change.getControlNewText();
            if(texto.matches("\\d*")){
                return null;
            }
            return change;
        });
        tf.setTextFormatter(letrasFormatter);
    }
    
    public static void formatarPeso(TextField tf) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            String pesoPuro = newText.replaceAll("[^\\d]", "");

            if (!pesoPuro.matches("\\d*") || pesoPuro.length() > 4) {
                return null;
            }

            if (!pesoPuro.isEmpty()) {
                while (pesoPuro.length() < 3) {
                    pesoPuro = "0" + pesoPuro;
                }
                

                String quilos = pesoPuro.substring(0, pesoPuro.length() - 2);
                String mili = pesoPuro.substring(pesoPuro.length() - 2);
                
                quilos = quilos.replaceFirst("^0+(?!$)", "");
                
                StringBuilder quilosBuilder = new StringBuilder();
                int conta = 0;

                for (int i = quilos.length() - 1; i >= 0; i--) {
                    if (conta > 0 && conta % 3 == 0) {
                        quilosBuilder.insert(0, ".");
                    }
                    quilosBuilder.insert(0, quilos.charAt(i));
                    conta++;
                }

                String pesoFormatado = quilosBuilder.toString() + "," + mili;

                change.setText(pesoFormatado);
                change.setRange(0, change.getControlText().length());
                change.setCaretPosition(pesoFormatado.length());
                change.setAnchor(pesoFormatado.length());
            }
            return change;
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        tf.setTextFormatter(formatter);
    }
    
    public static void formatarAltura(TextField tf) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            String alturaPuro = newText.replaceAll("[^\\d]", "");

            if (!alturaPuro.matches("\\d*") || alturaPuro.length() > 3) {
                return null;
            }

            if (alturaPuro.length() > 2) {

                String metro = alturaPuro.substring(0, alturaPuro.length() - 2);
                String cm = alturaPuro.substring(alturaPuro.length() - 2);
                
                metro = metro.replaceFirst("^0+(?!$)", "");
                
                StringBuilder metroBuilder = new StringBuilder();
                int conta = 0;

                for (int i = metro.length() - 1; i >= 0; i--) {
                    if (conta > 0 && conta % 3 == 0) {
                        metroBuilder.insert(0, ".");
                    }
                    metroBuilder.insert(0, metro.charAt(i));
                    conta++;
                }

                String alturaFormatado = metroBuilder.toString() + "," + cm;

                change.setText(alturaFormatado);
                change.setRange(0, change.getControlText().length());
                change.setCaretPosition(alturaFormatado.length());
                change.setAnchor(alturaFormatado.length());
            }
            return change;
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        tf.setTextFormatter(formatter);
    }
}
