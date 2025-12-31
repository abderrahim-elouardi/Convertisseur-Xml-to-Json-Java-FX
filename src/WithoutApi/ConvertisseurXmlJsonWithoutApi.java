package WithoutApi;



import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


// on va utiliser l'api org.json (JSON-Java) pour convertir xml vers json
public class ConvertisseurXmlJsonWithoutApi {
    public File f;
    public String textXml = "";

    public ConvertisseurXmlJsonWithoutApi(File f) {
        this.f = f;
    }
    
    public ConvertisseurXmlJsonWithoutApi(String textXml) {
//        this.f = f;
        this.textXml = textXml;
    }

    private List<String> traiterTexte(String xmlText) throws IOException {

        List<String> xmlTextProcessed = Arrays.stream(xmlText.split("<|>")).filter(s -> !s.equals("\n")).filter(s->!s.equals("")).filter(s->!s.equals("\t\t\t\t")).collect(Collectors.toList());
        xmlTextProcessed.removeIf(s->s.equals("?xml version=\"1.0\" encoding=\"UTF-8\"?"));

        List<String> ll= new ArrayList<>();

        for(int i =0;i<xmlTextProcessed.size();i++){
            if(!xmlTextProcessed.get(i).matches(" *") && !xmlTextProcessed.get(i).equals("\n")){
                ll.add(xmlTextProcessed.get(i));
            }
        }
        for(int i=0;i<ll.size();i++){
            ll.set(i,ll.get(i).trim());
        }

        // traitement des atts
        for(int i=0;i<ll.size();i++){
            for(String sss:ll.get(i).split(" ")){
                if(sss.contains("=")){
                    String[] splits = sss.split("=");
                    ll.add(i+1,splits[0]);
                    ll.add(i+2,splits[1]);
                    ll.add(i+3,"/"+splits[0]);
                }
                else{
                    ll.set(i,sss);
                }
            }
        }
        return ll;
    }
    public String corriger(String json){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<json.length()-1;i++){
            if(!(json.charAt(i)==',' && json.charAt(i+1)=='}')){
                sb.append(json.charAt(i));
            }
        }
        return sb.toString();
    }

    public String toJson(String xmlText) throws IOException {
        List<String> ll = traiterTexte(xmlText);

        String jsonText ="{";
        for(int i=0;i<ll.size();) {
            if (!ll.get(i).startsWith("/")) {
                List<String> newString = new ArrayList<>();
                String tagName =ll.get(i);
                int j=i+1;
                for (; j < ll.size(); j++) {
                    if (ll.get(j).startsWith("/"+tagName)) {
                        break;
                    }
                    newString.add(ll.get(j));
                }

                i = j + 1;
                boolean test = false;
                for (String s : newString) {
                    if (s.startsWith("/")) {
                        test = true;
                    }
                }
                if (test) {
                    jsonText+= "\"" + tagName + "\"" + ":{" + internal(newString) + "}";
                } else {
                    if(newString.size()>0){
                        if(newString.get(0).startsWith("\"")){
                            jsonText+= "\"" + tagName + "\"" + ":"  + newString.get(0)  + ",";
                        }
                        else{
                            jsonText+= "\"" + tagName + "\"" + ":" + "\"" + newString.get(0) + "\",";
                        }
                        jsonText+="";

                    }
                }


            }
            else{
                i++;
            }
        }
        return this.corriger(jsonText+"}");
    }

    public String internal(List<String> newStringg){
        List<String> ll= newStringg;
        String jsonText ="";
        for(int i=0;i<ll.size();) {
            if (!ll.get(i).startsWith("/")) {
                List<String> newString = new ArrayList<>();
                String tagName =ll.get(i);
                int j=i+1;
                for (; j < ll.size(); j++) {
                    if (ll.get(j).startsWith("/"+tagName)) {
                        break;
                    }
                    newString.add(ll.get(j));
                }

                i = j + 1;
                boolean test = false;
                for (String s : newString) {
                    if (s.startsWith("/")) {
                        test = true;
                    }
                }
                if (test) {
                    jsonText+= "\"" + tagName + "\"" + ":{" + internal(newString) + "}";
                } else {
                    if(newString.size()>0){
                        if(newString.get(0).startsWith("\"")){
                            jsonText+= "\"" + tagName + "\"" + ":"  + newString.get(0)  + ",";
                        }
                        else{
                            jsonText+="\"" + tagName + "\"" + ":" + "\"" + newString.get(0) + "\",";
                        }
                        jsonText+="";

                    }
                }

            }
            else{
                i++;
            }
        }
        return jsonText+"}";
    }

    private String getTexteBetween(String s, long position) {
        return null;
    }

    public String getTexte() throws IOException {
        FileInputStream fis = new FileInputStream(this.f);
        DataInputStream dis = new DataInputStream(fis);
        StringBuilder sb= new StringBuilder();
        while (dis.available() > 0) {
            sb.append(dis.readLine());
        }
        return sb.toString();
    }
}
