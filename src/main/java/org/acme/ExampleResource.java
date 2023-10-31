package org.acme;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.sourceforge.tess4j.Tesseract;

@Path("/hello")
public class ExampleResource {


	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
    
	//@ConfigProperty(name = "tessaract_data_folder")
	//String tessarctDataFolder;
	
    @POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response uploadFile(byte[] octects) {
        if (octects == null) {
        	System.out.println("octect non chargé");
            return Response.status(Response.Status.BAD_REQUEST).entity("Le formulaire est invalide.").build();
            
        }
        System.out.println("Octect bien chargé");
        int fileSize = octects.length;
        

        
		try {
	        Tesseract tesseract = new Tesseract();
	        System.out.println("Tesseract instantié"); 
	        System.out.println("Tessaract data folder :/deployments/tessaract/tessdata") ; 
	        tesseract.setDatapath("C:\\workspace\\eclipse\\2023-09\\tesseract\\Tess4J\\tessdata");
	        System.out.println("Tesseract set data path");
	        String text="aucune image";
	        InputStream is = new ByteArrayInputStream(octects);
	        System.out.println("new byteArrayInputStream");
	        BufferedImage bi = ImageIO.read(is);
	        System.out.println("BufferedImage");
			text = tesseract.doOCR(bi);
			System.out.println("Texte recuperer 000"+text);
			return Response.ok("Le texte de l'image est : " + text, MediaType.TEXT_PLAIN)
		            .build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur : " + e) ; 
			e.printStackTrace();
		}
        return Response.ok("La taille du fichier est de " + fileSize + " octet.", MediaType.TEXT_PLAIN)
            .build();
    }
    
    /**@POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response uploadFile(File octects) {
        if (octects == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Le formulaire est invalide.").build();
        }
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\workspace\\eclipse\\2023-09\\tesseract\\Tess4J\\tessdata");
        String text="aucune image";
		try {
			text = tesseract.doOCR(octects);
			return Response.ok("Le texte de l'image est : " + text + " octet.", MediaType.TEXT_PLAIN)
		            .build();
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.print(text);
		return Response.ok(text).build(); 
    }*/
}
