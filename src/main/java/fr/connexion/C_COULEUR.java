package fr.connexion;

public enum C_COULEUR {
    C_BASIC ("\033[0m"),
    C_RED ("\033[31m"),
    C_GREEN ("\033[32m"),
    C_YELLOW ("\033[33m"),
    C_BLUE ("\033[34m");

    private String codeCouleur;

    C_COULEUR(String codeCouleur) {
        this.codeCouleur = codeCouleur;
    }

    @Override
    public String toString() {
        return codeCouleur;
    }
}
