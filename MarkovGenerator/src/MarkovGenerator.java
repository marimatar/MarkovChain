import java.io.*;
import java.util.*;

public class MarkovGenerator {
    private HashMap<ArrayList<String>, ArrayList<String>> markovEntry;
    ArrayList<String> textArray;

    public MarkovGenerator()throws IOException{
        markovEntry = new HashMap<>();
        textArray = new ArrayList<String>();
        readFile("thesecretgarden.txt");
        System.out.println(generateText(helper()));
    }
//key 1 key 2 - value1 , 2 ,3, 4, 5, 6

    public String generateText(int numberOfWords){
        int rngKey = (int)(Math.random() * textArray.size());
        String output = "";
        ArrayList<String> k = new ArrayList<String>();
        k.add(textArray.get(rngKey));
        k.add(textArray.get(rngKey+1));
        output += k.get(0) + " " + k.get(1) + " ";

        for(int c = 1; c<numberOfWords;c++){
            int valueSize = markovEntry.get(k).size();
            int rngValue = (int)(Math.random() * valueSize);
            String newV =  markovEntry.get(k).get(rngValue);
            output += newV + " ";
            k.remove(0);
            k.add(newV);
        }
        return output;
    }
    public int helper(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("How many words do you want to generate?");
        int words = keyboard.nextInt();
        return words;
    }

    public void readFile(String textFile) throws IOException{
        Scanner fileReader = new Scanner(new FileInputStream(textFile));
        while(fileReader.hasNext()){
            textArray.add(fileReader.next());
        }
        fileReader.close();
        for(int i = 0; i < textArray.size()-1; i++){
            ArrayList<String>keyArray = new ArrayList();
            keyArray.add(textArray.get(i));
            keyArray.add(textArray.get(i+1));
            if(i < textArray.size()-2){
                if(!markovEntry.containsKey(keyArray)){
                    ArrayList<String>valueArray = new ArrayList();
                    valueArray.add(textArray.get(i+2));
                    markovEntry.put(keyArray,valueArray);
                }
                else{
                    markovEntry.get(keyArray).add(textArray.get(i+2));
                }
            }
            else{
                if(!markovEntry.containsKey(keyArray)){
                    ArrayList<String>valueArray = new ArrayList();
                    valueArray.add(textArray.get(0));
                    markovEntry.put(keyArray,valueArray);
                }
                else{
                    markovEntry.get(keyArray.add(textArray.get(0)));
                }

            }

        }
        fileReader.close();
    }

//            while(fileReader.hasNextLine()){
//                System.out.println(fileReader.nextLine());
//            }
            //throw new FileNotFoundException("I am Exception Alpha!");

//        catch(FileNotFoundException e){
//            System.out.println("File exception occured!");
//            e.printStackTrace();
//        }

    public static void main(String[] args) throws IOException {
        HashMap<ArrayList<String>, ArrayList<String>> hash = new HashMap<ArrayList<String>, ArrayList<String>>();
        MarkovGenerator markovGenerator = new MarkovGenerator();

    }
}



