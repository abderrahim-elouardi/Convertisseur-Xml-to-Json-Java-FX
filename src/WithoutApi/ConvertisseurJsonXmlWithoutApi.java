package WithoutApi;



import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConvertisseurJsonXmlWithoutApi {
    public String textJson;

    public ConvertisseurJsonXmlWithoutApi(String textJson){
        this.textJson = textJson;
    }

    private String traiterTexte() throws IOException {
        String jsonText =this.textJson;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<jsonText.length();i++){
            if(!(jsonText.charAt(i) ==' ') && !(jsonText.charAt(i) =='\n')){
                sb.append(jsonText.charAt(i));
            }
        }
        jsonText = sb.toString();
        return sb.toString();
    }

    public String toXml() throws IOException {
//       String jsonTexte = this.textJson;
//       jsonTexte = traiterTexte();
//
//        if(jsonTexte.startsWith("{") && jsonTexte.endsWith("}")){
//            StringBuilder jsonInternbuilder = new StringBuilder();
//            for(int i=1;i<jsonTexte.length()-1;i++){
//                jsonInternbuilder.append(jsonTexte.charAt(i));
//            }
//            return convertir(jsonInternbuilder.toString().split(":|,"));
//        }
//        return convertir(jsonTexte.split(":|,"));
        return null;
    }

    private String convertir(String[] jsonTexteTableau) {
//
//        for(String s:jsonTexteTableau){
//            System.out.println(s);
//        }
//
//        for(int i=0;i<jsonTexteTableau.length;){
//            String tagName = "";
//            if(jsonTexteTableau[i].startsWith("{")){
//                StringBuilder jsonInternbuilder = new StringBuilder();
//                for(int j=1;j<jsonTexteTableau[i].length();j++){
//                    jsonInternbuilder.append(jsonTexteTableau[i].charAt(j));
//                }
//                tagName = jsonInternbuilder.toString();
//                List<String> ll = new ArrayList<>();
//                int j=i+1;
//                for(;j<jsonTexteTableau.length;j++){
//                    if(jsonTexteTableau[j].equals(tagName)){
//                        ll.add(jsonTexteTableau[j]);
//                    }
//                    else{
//                        i=j;
//                        break;
//                    }
//                }
//                i++;
//
//            }
//            else{
//                tagName = jsonTexteTableau[i];
//                List<String> ll = new ArrayList<>();
//                for(int j=i+1;j<jsonTexteTableau.length;j++){
//                    if(!jsonTexteTableau[j].equals(tagName)){
//                        ll.add(jsonTexteTableau[j]);
//                    }
//                    else{
//                        i=j;
//                        break;
//                    }
//                }
//
//            }
//        }
//        return null;
        return null;
    }
}
