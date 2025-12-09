package Models;
import java.io.Serializable;

/**
 * @class ISBN
 * @brief Classe che definisce il codice ISBN e la sua verifica.
 */
public class ISBN implements Serializable {


    private final String codiceISBN;

    /**
     * @brief Costruttore.
     * @param[in] isbn l'ISBN.
     */
    public ISBN(String codiceISBN) {

        this.codiceISBN = codiceISBN;
    }

    /**
     * @brief Restituisce l'ISBN.
     * @return isbn.
     */
    public String getCodiceISBN() {
        return codiceISBN;
    }

    /**
     * @brief Verifica la correttezza del formato dell'ISBN.
     *
     * @return true se il formato dell'ISBN è valido, false altrimenti.
     */
    public static boolean verificaISBN(String isbn){
        return false;
    }
}