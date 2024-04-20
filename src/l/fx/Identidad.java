package l.fx;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Identidad {
    public static String 
    pc() {
        String pc = null;
        try {
            pc = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Identidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pc;
    }
    public static String 
    usuario() {
        String usuario = System.getProperty("user.name");
//        String usuario = System.getenv("USERNAME");//Window
        return usuario; 
    }
    public static String 
    ipLocal() {
        String ipLocal = null;
        try {
            ipLocal = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Identidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ipLocal; 
    }
    public static String 
    idioma() {
        Locale idiomaLocale = Locale.getDefault();
        String idioma = idiomaLocale.toString();
        return idioma;
    }
    public static String
    origen() {
        String origen = Locale.getDefault().getDisplayCountry();
        return origen;
    }
    public static String 
    ipv4(){ 
        String ipv4 = null; 
        Enumeration<NetworkInterface> networkInterface = null; 
        
        try { 
            networkInterface = NetworkInterface.getNetworkInterfaces(); 
        } catch (SocketException ex) { 
            throw new RuntimeException(ex); 
        } 
        
        while(networkInterface.hasMoreElements()){ 
            NetworkInterface element = networkInterface.nextElement(); 
            Enumeration<InetAddress> InetAddress = element.getInetAddresses(); 
            
            while (InetAddress.hasMoreElements()){ 
                InetAddress ip = InetAddress.nextElement(); 
                if (ip instanceof Inet4Address){ 
                    if (ip.isSiteLocalAddress()){ 
                        ipv4 = ip.getHostAddress(); 
                    } 
                } 
            } 
        } 
        return ipv4; 
    }
    public static String 
    ipv6(){ 
        String ipv6 = null; 
        Enumeration<NetworkInterface> networkInterface = null; 
        
        try { 
            networkInterface = NetworkInterface.getNetworkInterfaces(); 
        } catch (SocketException ex) { 
            throw new RuntimeException(ex); 
        } 
        
        while(networkInterface.hasMoreElements()){ 
            NetworkInterface element = networkInterface.nextElement(); 
            Enumeration<InetAddress> inetAddress = element.getInetAddresses(); 
            
            while (inetAddress.hasMoreElements()){ 
                InetAddress ip = inetAddress.nextElement(); 
                if (ip instanceof Inet6Address){ 
                    if (ip.isLinkLocalAddress()){ 
                        ipv6 = ip.getHostAddress(); 
                    } 
                } 
            } 
        } 
        return ipv6; 
    }
    public static String 
    macName(){ 
        Enumeration<NetworkInterface> networkInterface = null; 
        
        try { 
            networkInterface = NetworkInterface.getNetworkInterfaces(); 
        } catch (SocketException ex) { 
            throw new RuntimeException(ex); 
        } 
        
        NetworkInterface current = networkInterface.nextElement();
 
        return current.getDisplayName();
    }
}
