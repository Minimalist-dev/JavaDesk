package javafx.dev;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.JavaFX;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
//https://docs.oracle.com/javase/9/docs/specs/security/standard-names.html#messagedigest-algorithms
public class Encriptado extends JavaFX {
    protected CriptoDoc criptoDoc;
    
    private TextField req   = new TextField("Encriptado");
    private Label res       = new Label("");
    private Label decript   = new Label("");
    
    
    public StackPane 
    encriptado() {
        final String[] string = new String[] {
            "m5", "sha", "uno", "dos", "cipher"
        };
        
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(
            "M5", "SHA", "SHA-256", "SHA-512", "Cipher"
        ));
        
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
        (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            if(string[new_val.intValue()] == "m5") {
                decript.setText("M5");
                res.setText(criptoDoc.md5(req.getText()));
            } else if(string[new_val.intValue()] == "sha") {
                String sha = criptoDoc.sha1(req.getText());
                
                decript.setText("SHA-1");
                res.setText(sha);
            } else if(string[new_val.intValue()] == "uno") {
                String sha = criptoDoc.colocarSHA(req.getText());
                
                res.setText(sha);
                decript.setText(criptoDoc.obtenerSHA(sha));
            } else if(string[new_val.intValue()] == "dos") {
                String sha2 = criptoDoc.colocarSHA2(req.getText());
                
                res.setText(sha2);
                decript.setText(criptoDoc.obtenerSHA2(sha2));
            } else if(string[new_val.intValue()] == "cipher") {
                try {
                    String cipher = criptoDoc.cifra(req.getText());
                    
                    res.setText(cipher);
                    decript.setText(criptoDoc.descifra(cipher));
                } catch (Exception ex) {
                    Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
        });
        
        GridPane grid = new GridPane();
        
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        GridPane.setConstraints(decript, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(res, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(req, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(choiceBox, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER);

        grid.getStylesheets().add("/i/css/dev/expresiones.css");
        grid.getStyleClass().add("grid-pane");

        grid.getChildren().addAll(
            decript,
            res, 
            req, 
            choiceBox
        );
        borderPane.setCenter(grid);
        
        return center;
    }
    
    static class CriptoDoc {
    /* Encriptar: Retorna un hash a partir de un tipo y un texto 
    ----------------------------------------------------------------------------*/
        private static String 
        getHash(String texto, String hashType) {
            try {
                MessageDigest md    = MessageDigest.getInstance(hashType);
                byte[] array        = md.digest(texto.getBytes());
                StringBuffer sb     = new StringBuffer();

                for (int i = 0; i < array.length; ++i) {
                    sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
                }

                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e.getMessage());
            }

            return null;
        }
        public static String 
        md5(String texto) {
            return CriptoDoc.getHash(texto, "MD5");
        }
        public static String 
        sha1(String texto) {
            return CriptoDoc.getHash(texto, "SHA-1");
        }

    /* Encriptar y desencriptar #1
    ----------------------------------------------------------------------------*/
        private static SecretKeySpec 
        crearClave(String texto, String hashType) throws UnsupportedEncodingException, NoSuchAlgorithmException {
            byte[] clave                = texto.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance(hashType);
            clave                       = messageDigest.digest(clave);
            clave                       = Arrays.copyOf(clave, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave, 0, 16, "AES");

            return secretKeySpec;
        }

        private static String 
        encriptar(String texto, String clave, String hashType) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
            SecretKeySpec secretKeySpec = crearClave(clave, hashType);
            Cipher cipher               = Cipher.getInstance("AES/ECB/PKCS5Padding");  
            
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encriptar    = texto.getBytes("UTF-8");
            byte[] encriptado   = cipher.doFinal(encriptar);
            String res          = Base64.getEncoder().encodeToString(encriptado);

            return res;
        }
        private static String 
        desencriptar(String texto, String clave, String hashType) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
            SecretKeySpec secretKeySpec = crearClave(clave, hashType);
            Cipher cipher               = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] encriptado       = Base64.getDecoder().decode(texto);
            byte[] desencriptado    = cipher.doFinal(encriptado);
            String res              = new String(desencriptado);

            return res;
        }
        public static String 
        colocarSHA(String texto) {
            try {
                return CriptoDoc.encriptar(texto, "123", "SHA-256");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null; 
        }
        public static String
        obtenerSHA(String texto) {
            try {
                return CriptoDoc.desencriptar(texto, "123", "SHA-256");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        public static String 
        colocarSHA2(String texto) {
            try {
                return CriptoDoc.encriptar(texto, "123", "SHA-512");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        public static String
        obtenerSHA2(String texto) {
            try {
                return CriptoDoc.desencriptar(texto, "123", "SHA-512");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(Encriptado.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        
    /* Encriptar y desencriptar #2
    ----------------------------------------------------------------------------*/
        private static Cipher 
        obtieneCipher(boolean paraCifrar) throws Exception {
            final String texto                  = "FraseLargaConDiferentesLetrasNumerosYCaracteresEspeciales_áÁéÉíÍóÓúÚüÜñÑ1234567890!#%$&()=%_NO_USAR_ESTA_FRASE!_";
//            final String frase = "";
            final MessageDigest messageDigest   = MessageDigest.getInstance("SHA");
            
            messageDigest.update(texto.getBytes("UTF-8"));
            
            final SecretKeySpec secretKeySpec   = new SecretKeySpec(messageDigest.digest(), 0, 16, "AES");
            final Cipher cipher                 = Cipher.getInstance("AES/ECB/PKCS5Padding");
            
            if (paraCifrar) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            }

            return cipher;
        }
        public static String
        cifra(String texto) throws Exception {
            final byte[] bytes      = texto.getBytes("UTF-8");
            final Cipher cipher     = obtieneCipher(true);
            final byte[] encriptado = cipher.doFinal(bytes);
            final String res        = Base64.getEncoder().encodeToString(encriptado);
            
            return res;
        }
        public static String 
        descifra(String texto) throws Exception {
            final Cipher cipher     = obtieneCipher(false);
            final byte[] encriptado = Base64.getDecoder().decode(texto);
            final byte[] bytes      = cipher.doFinal(encriptado);
            final String res        = new String(bytes, "UTF-8");
            
            return res;
        }
    }
}
