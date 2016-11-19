import java.util.LinkedList;

public class Automata{

    public static int[] generator( int rule, int height, int firstLine){
      int pivot = 7; // pivot 111
      int result = 0;
      int threeBit = 0;
      int line = firstLine;
      int newLine;

      LinkedList<Integer> rules = getRules(rule);
      int [] finalResult = new int[height];
      finalResult[0] = firstLine;
      System.out.println(Integer.toBinaryString(firstLine));
      for(int i=1; i<height; i++){
        newLine=0;
        for(int j=30; j>0; j--){
          //for every 3-bit
          threeBit = firstLine >> j;
          result = pivot & threeBit;
          System.out.print(Integer.toBinaryString(result)+ " ");
          System.out.println();
          for(Integer r: rules){
            if(result==r){
              newLine |= 1 <<j;
              break;
            }
          }

        }
        finalResult[i]=newLine;
        firstLine = newLine;
        System.out.println(Integer.toBinaryString(newLine));
      }




      return finalResult;
    }

    public static LinkedList<Integer> getRules(int rule){
      LinkedList<Integer> rules = new LinkedList<Integer>();
      int pivot = 1;
      for(int i=0; i<8; i++){
        if((rule & (pivot<<i)) != 0) rules.add(i);
      }
      return rules;
    }

    public static void main(String [] args){
      int firstLine = 0;
      firstLine |= 1<<16;
      generator(30, 4, firstLine);
    }
}
