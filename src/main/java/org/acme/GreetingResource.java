package org.acme;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @Path("/ci")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Bonjour() {
        return "Bienvenue sur quarkus";
    }

    @Path("/somme")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Integer Somme (@QueryParam(value="n1") Integer nbre1, @QueryParam(value="n2") Integer nbre2) {
        int result;
        result = nbre1 - nbre2 ;
        
        return result;
    }

    @Path("/plaque")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String estPlaqueValide(@FormParam("plaque") String plaque) {

        boolean Resultat = Plaquevalide(plaque);

        if(Resultat)
        {
            return "{\"message\":\"La plaque est conforme.\",\"code\":\"200\"}" ; 
        }else
        {
            return "{\"message\":\"La plaque n'est pas conforme.\",\"code\":\"400\"}" ; 
        }
    }
    private  boolean  Plaquevalide(String plaque){
    // Vérifie si la longueur de la plaque est de 8 caractères
        if (plaque.length() != 8) {
            return false;
        }

        // Vérifie que les 4 premiers caractères sont des chiffres
        for (int i = 0; i < 4; i++) {
            if (!Character.isDigit(plaque.charAt(i))) {
                return false;
            }
        }

        // Vérifie que les caractères 5 et 6 sont des lettres
        for (int i = 4; i < 6; i++) {
            if (!Character.isLetter(plaque.charAt(i))) {
                return false;
            }
        }

        // Vérifie que les deux derniers caractères sont des chiffres
        for (int i = 6; i < 8; i++) {
            if (!Character.isDigit(plaque.charAt(i))) {
                return false;
            }
        }

        // Si toutes les vérifications sont passées, la plaque est valide
        return true;
    }

    
    @Path("/equation")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String resolution(@QueryParam("nbre1") int nbre1,@QueryParam("nbre2") int nbre2,@QueryParam("nbre3") int nbre3)
    {
        double racine, sol0, sol1, sol2, expo, disc;
        expo = Math.pow(nbre2,2);
        disc = expo - (4 * nbre1 * nbre3) ; 
        if(disc > 0 )
        {
            racine = Math.sqrt(disc) ; 
            sol1 = (-nbre2 + racine)/ 2 * nbre1 ; 
            sol2 = (-nbre2 - racine)/2 * nbre1 ; 
            return "{\"sol1\":\"" + sol1 + "\",\"sol2\":\"" + sol2 + "\",\"code\":\"1\"}" ;
        }
        else if(disc == 0)
        {
            sol0 = (- nbre2) / ( 2 * nbre1 ) ; 
            return "{\"sol0\":\"" + sol0 + "\",\"code\":\"2\"}" ; 
        }
        else
        {
            disc = -disc ;
            racine = Math.sqrt(disc) ; 
            sol1 = (-nbre2 - racine) / 2 * nbre1 ; 
            sol2 = (-nbre2 + racine) / 2 * nbre1 ;
            String echec1 = "z1 = (-" + nbre2 + "-iV" + disc + ")/" + 2 * nbre1 ; 
            String echec2 = "z2 = (-" + nbre2 + "+iV" + disc + ")/" + 2 * nbre1 ; 
            return "{\"echec1\":\"" + echec1 + "\",\"echec2\":\"" + echec2 + "\",\"code\":\"3\"}" ;
        }
    }


}