package UsingApi;

import org.json.JSONObject;
import org.json.XML;

import java.io.*;


// on va utiliser l'api org.json (JSON-Java) pour convertir xml vers json
public class ConvertisseurXmlJsonUsingJsonApi {
//    public File f;
    public String xmlTexte;

//    public ConvertisseurXmlJsonWithApi(File f) {
//        this.f = f;
//    }

    public ConvertisseurXmlJsonUsingJsonApi(String  xmlTexte) {
        this.xmlTexte = xmlTexte;
    }
    
    public String toJson() throws IOException {
//        String xmlTexte = getTexte();
        JSONObject jsonObject = XML.toJSONObject(xmlTexte);

        return jsonObject.toString(4);// 4 c'est le nombre des espaces pour formater ( pour la visibilite )
    }
    

//    private String getTexte() throws IOException {
//        FileInputStream fis = new FileInputStream(this.f);
//        DataInputStream dis = new DataInputStream(fis);
//        StringBuilder sb= new StringBuilder();
//        while (dis.available() > 0) {
//            sb.append("\n"+dis.readLine());
//        }
//        return sb.toString();
//    }
}
