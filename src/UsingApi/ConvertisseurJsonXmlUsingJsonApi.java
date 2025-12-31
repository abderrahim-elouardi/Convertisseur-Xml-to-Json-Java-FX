package UsingApi;

import org.json.JSONObject;
import org.json.XML;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ConvertisseurJsonXmlUsingJsonApi {
//    public File f;
    String jsonTexte;

//    public ConvertisseurJsonXmlWithApi(File f) {
//        this.f = f;
//    }

    public ConvertisseurJsonXmlUsingJsonApi(String jsonTexte) {
        this.jsonTexte = jsonTexte;
    }
    public String toXml() throws IOException {
//        String jsonText = getTexte();

        JSONObject jsonObject = new JSONObject(this.jsonTexte);

        return XML.toString(jsonObject);
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
