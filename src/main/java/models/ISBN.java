package models;
import java.io.Serializable;

/**
 * @class ISBN
 * @brief Classe che definisce il codice ISBN e la sua verifica.
 */
public class ISBN implements Serializable, Comparable<ISBN> {

    private final String codiceISBN;

    /**
     * @brief Costruttore.
     * @param[in] isbn l'ISBN.
     */
    public ISBN(String codiceISBN){

        this.codiceISBN = codiceISBN;
    }

    /**
     * @brief Restituisce l'ISBN.
     * @return isbn.
     */
    public String getCodiceISBN(){
        return codiceISBN;
    }

    /**
     * @brief Verifica la correttezza del formato dell'ISBN.
     *
     * @return true se il formato dell'ISBN è valido, false altrimenti.
     */
    public static boolean verificaISBN(String codiceISBN){
        if (codiceISBN == null || !codiceISBN.matches("\\d{13}") || codiceISBN.length() != 13) return false;
        
        int sommaPonderata = 0;
        for (int i = 0; i < 12; i++){
            // - '0' lo converte nel valore numerico (es. '7' - '0' = 7).
            int cifra = codiceISBN.charAt(i) - '0';
            //Posizioni dispari (1, 3, 5, ...) -> Moltiplica per 1
            if((i + 1) % 2 != 0){ 
                sommaPonderata += cifra * 1;
            //Posizioni pari (2, 4, 6, ...) -> Moltiplica per 3
            }else{
                sommaPonderata += cifra * 3;
            }
        }

        int resto = sommaPonderata % 10;
        int cifraControlloAttesa = (10 - resto) % 10;
        int cifraControlloEffettiva = codiceISBN.charAt(12) - '0';
        // Confronta la cifra calcolata con l'ultima cifra dell'ISBN fornito.
        return cifraControlloAttesa == cifraControlloEffettiva;
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(o == this) return true;
        if(o.getClass() != this.getClass()) return false;
        
        ISBN i = (ISBN) o;
        return this.codiceISBN.equals(i.codiceISBN);
    }
    
    @Override
    public int compareTo(ISBN o) {
        return this.codiceISBN.compareTo(o.codiceISBN);
    }
}