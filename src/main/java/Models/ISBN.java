package Models;
import java.io.Serializable;

/**
 * @class ISBN
 * @brief Classe che definisce il codice ISBN e la sua verifica.
 */
public class ISBN implements Serializable {


    private final String isbn;

    /**
     * @brief Costruttore.
     * @param[in] isbn l'ISBN.
     */
    public ISBN(String isbn) {

        this.isbn = isbn;
    }

    /**
     * @brief Restituisce l'ISBN.
     * @return isbn.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @brief Verifica la correttezza del formato dell'ISBN.
     *
     * @return true se il formato dell'ISBN è valido, false altrimenti.
     */
    public static boolean verificaIsbn(){
        return false;
    }
}