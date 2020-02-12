package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        String pathURL = "http://gencat.cat/llengua/cinema/provacin.xml";
        URL url = new URL(pathURL);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(url.openStream()));

        NodeList nodeList = doc.getElementsByTagName("FILM");

        List<Pelicula> filmList = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                try{
                    String Prioritat = eElement.getElementsByTagName("PRIORITAT").item(0).getTextContent();
                    String Titol = eElement.getElementsByTagName("TITOL").item(0).getTextContent();
                    String Situacio = eElement.getElementsByTagName("SITUACIO").item(0).getTextContent();
                    String Cartell = eElement.getElementsByTagName("CARTELL").item(0).getTextContent();
                    String Original = eElement.getElementsByTagName("ORIGINAL").item(0).getTextContent();
                    String Direccio = eElement.getElementsByTagName("DIRECCIO").item(0).getTextContent();
                    String Interprets = eElement.getElementsByTagName("INTERPRETS").item(0).getTextContent();
                    String Sinopsi = eElement.getElementsByTagName("SINOPSI").item(0).getTextContent();
                    String Versio = eElement.getElementsByTagName("VERSIO").item(0).getTextContent();
                    String Idioma = eElement.getElementsByTagName("IDIOMA_x0020_ORIGINAL").item(0).getTextContent();
                    String Qualificacio = eElement.getElementsByTagName("QUALIFICACIO").item(0).getTextContent();
                    String Trailer = eElement.getElementsByTagName("TRAILER").item(0).getTextContent();
                    String Web = eElement.getElementsByTagName("WEB").item(0).getTextContent();
                    int ID = Integer.parseInt(eElement.getElementsByTagName("IDFILM").item(0).getTextContent());
                    int Any = Integer.parseInt(eElement.getElementsByTagName("ANY").item(0).getTextContent());
                    String Estrena = String.valueOf(eElement.getElementsByTagName("ESTRENA").item(0).getTextContent());


                    Pelicula pelicula = new Pelicula(Prioritat, Titol, Situacio, Cartell, Original, Direccio, Interprets, Sinopsi, Versio, Idioma, Qualificacio, Trailer, Web, ID, Any, Estrena);
                    filmList.add(pelicula);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        Scanner sc = new Scanner(System.in);


        System.out.println("Elige una opcion: ");
        System.out.println("1.Filtrar por año");
        System.out.println("2.Filtrar por titulo");
        System.out.println("3.Filtrar por director");
        System.out.println("4.Numero de pelis de un director");

        int option = sc.nextInt();

        switch (option){
            case 1:
                System.out.println("Inserte año: ");
                List<Pelicula> añoList = null;
                int año = sc.nextInt();
                sc.nextLine();
                for (int i = 0; i < filmList.size(); i++) {
                    Node nNode = nodeList.item(i);
                    Element eElement = (Element)nNode;
                    int Any = Integer.parseInt(eElement.getElementsByTagName("ANY").item(0).getTextContent());
                    if(Any == año){
                        añoList = (List<Pelicula>) filmList.stream().filter(a ->a.Any==año).collect(Collectors.toList());
                    }
                }
                System.out.println(añoList);
                break;
            case 2:
                System.out.println("Inserte el titulo: ");
                List<Pelicula> nombreList = null;
                sc.nextLine();
                String  titolPeli = sc.nextLine();
                for (int i = 0; i < filmList.size(); i++) {
                    Node nNode = nodeList.item(i);
                    Element eElement = (Element)nNode;
                    String nombre = String.valueOf(eElement.getElementsByTagName("TITOL").item(0).getTextContent());
                    if(titolPeli.equals(nombre) ){
                        nombreList = (List<Pelicula>) filmList.stream().filter(a ->a.Titol.equals(nombre)).collect(Collectors.toList());
                    }
                }
                System.out.println(nombreList);
                break;
            case 3:
                System.out.println("Inserte nombre del director: ");
                List<Pelicula> directorPelis = null;
                sc.nextLine();
                String  director = sc.nextLine();
                for (int i = 0; i < filmList.size(); i++) {
                    Node nNode = nodeList.item(i);
                    Element eElement = (Element)nNode;
                    String direccio = String.valueOf(eElement.getElementsByTagName("DIRECCIO").item(0).getTextContent());
                    if(director.equals(direccio) ){
                        directorPelis = (List<Pelicula>) filmList.stream().filter(a ->a.Direccio.equals(director)).collect(Collectors.toList());
                    }
                }
                System.out.println(directorPelis);
                break;

            case 4:
                System.out.println("Inserte nombre del director: ");
                List<Pelicula> direcPelis = null;
                sc.nextLine();
                String  direc = sc.nextLine();
                for (int i = 0; i < filmList.size(); i++) {
                    Node nNode = nodeList.item(i);
                    Element eElement = (Element)nNode;
                    String direccio = String.valueOf(eElement.getElementsByTagName("DIRECCIO").item(0).getTextContent());
                    if(direc.equals(direccio) ){
                        direcPelis = (List<Pelicula>) filmList.stream().filter(a ->a.Direccio.equals(direc)).collect(Collectors.toList());
                    }
                }
                System.out.println(direcPelis.size());
                break;

        }

    }
}
