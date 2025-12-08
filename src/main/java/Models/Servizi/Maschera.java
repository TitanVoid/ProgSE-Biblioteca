package Models.Servizi;

/**
 * @class Maschera
 * @brief Classe che definisce il formato di una maschera.
 *
 *  Utilizzata per verificare il formato degli attributi di un oggetto.
 */
public class Maschera{

    private final int lunghezza;
    private String maschera;

    /**
     * @brief Costruttore.
     * @param[in] lunghezza Lunghezza della maschera.
     */
    public Maschera(int lunghezza) {
        this.lunghezza = lunghezza;
    }

    /**
     * @brief Restituisce la lunghezza della maschera.
     * @return lunghezza.
     */
    public int getLunghezza() {
        return lunghezza;
    }

    /**
     * @brief Restituisce la maschera.
     * @return maschera.
     */
    public String getMaschera() {
        return maschera;
    }

    /**
     * @brief Imposta la maschera.
     * @param[in] maschera Maschera da assegnare.
     */
    public void setMaschera(String maschera) {
        this.maschera = maschera;
    }

}