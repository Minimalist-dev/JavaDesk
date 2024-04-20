package l.fx;

/**
 *
 * @author neury-dev
 */
public class Paginacion {
    private static int paso;
    private static int pagina;

    public static int getPaso() {
        return paso;
    }

    public static void setPaso(int paso) {
        Paginacion.paso = paso;
    }
    
    public int 
    filasPorPagina() {
        return 10;
    }
    public int
    anterior() {
        if(getPaso() >= 1) {
            pagina = (getPaso() - 1) * filasPorPagina();
            setPaso(getPaso() - 1);
        }
        
        return pagina;
    }
    public int
    siguiente() {
        if(getPaso() == 0) {
            pagina = 1 * filasPorPagina();
            setPaso(1);
        } else {
            pagina = (getPaso() + 1) * filasPorPagina();
            setPaso(getPaso() + 1);
        }
        
        return pagina;
    }
}
