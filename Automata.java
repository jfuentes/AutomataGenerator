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
      for(int i=1; i<height; i++){
        newLine=0;
        for(int j=30; j>0; j--){
          //for every 3-bit
          threeBit = firstLine >> j;
          result = pivot & threeBit;
          for(Integer r: rules){
            if(result==r){
              newLine |= 1 <<(j+1);
              break;
            }
          }

        }
        finalResult[i]=newLine;
        firstLine = newLine;
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

    public static void printAutomata(int [] automata){
      for(int i=0; i<automata.length; i++){
        System.out.println(String.format("%32s", Integer.toBinaryString(automata[i])).replace(' ', '0'));
      }
    }

    public static void main(String [] args){
      if(args.length==0){
        System.out.println("Rule number is missing. Please use: $java Automata ruleNumber");
        System.exit(1);
      }
      int rule = Integer.parseInt(args[0]);
      int firstLine = 0;
      firstLine |= 1<<16;
      int [] automata = generator(rule, 16, firstLine);
      printAutomata(automata);
    }
}
