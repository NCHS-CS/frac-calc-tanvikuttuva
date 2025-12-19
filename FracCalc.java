// Tanvi Kuttuva
// Period 6
// Fraction Calculator Project

import java.util.*;

// TODO: Description of what this program does goes here.
// Fraction Calculator conducts a variety of arithmetic calculations on fractions,
// including the +, -, /, and * operators. This program is able to utilize several
// methods to assist in performing these operations and return the final answer as 
// a reduced mixed number.
public class FracCalc {

   // It is best if we have only one console object for input
   public static Scanner console = new Scanner(System.in);
   
   // This main method will loop through user input and then call the
   // correct method to execute the user's request for help, test, or
   // the mathematical operation on fractions. or, quit.
   // DO NOT CHANGE THIS METHOD!!
   public static void main(String[] args) {
      //processExpression("4 / 0");
      // initialize to false so that we start our loop
      boolean done = false;
      
      // When the user types in "quit", we are done.
      while (!done) {
         // prompt the user for input
         String input = getInput();
         
         // special case the "quit" command
         if (input.equalsIgnoreCase("quit")) {
            done = true;
         } else if (!UnitTestRunner.processCommand(input, FracCalc::processCommand)) {
        	   // We allowed the UnitTestRunner to handle the command first.
            // If the UnitTestRunner didn't handled the command, process normally.
            String result = processCommand(input);
            
            // print the result of processing the command
            System.out.println(result);
         }
      }
      
      System.out.println("Goodbye!");
      console.close();
      
   }

   // Prompt the user with a simple, "Enter: " and get the line of input.
   // Return the full line that the user typed in.
   public static String getInput() {
      // TODO: Implement this method
      System.out.println("Enter: ");
      String input = console.nextLine();

       return input;

   }
   
   // processCommand will process every user command except for "quit".
   // It will return the String that should be printed to the console.
   // This method won't print anything.
   // DO NOT CHANGE THIS METHOD!!!
   public static String processCommand(String input) {

      if (input.equalsIgnoreCase("help")) {
         return provideHelp();
      }
      
      // if the command is not "help", it should be an expression.
      // Of course, this is only if the user is being nice.
      return processExpression(input);
   }
   
   // Lots work for this project is handled in here.
   // Of course, this method will call LOTS of helper methods
   // so that this method can be shorter.
   // This will calculate the expression and RETURN the answer.
   // This will NOT print anything!
   // Input: an expression to be evaluated
   //    Examples: 
   //        1/2 + 1/2
   //        2_1/4 - 0_1/8
   //        1_1/8 * 2
   // Return: the fully reduced mathematical result of the expression
   //    Value is returned as a String. Results using above examples:
   //        1
   //        2 1/8
   //        2 1/4
   
   //This method calls several other methods which each individually 
   // deal with the fractions from the input and execute the expected output
   public static String processExpression(String input) {
      // TODO: implement this method!
      String output = "";
      String operator = getOperator(input);
      //These following methods deal with the first operand to identify the 
      //operand itself, the whole number, the fraction after normalizing, 
      //and the numerator and denominator parts of the fraction
      String operand2 = getOperand2(input);
      String whole2 = getWhole(operand2);
      String frac2 = getFrac(operand2);
      String normalizedFrac2 = normalize(frac2);
      String numerator2 = getNumerator(normalizedFrac2);
      String denominator2 = getDenominator(normalizedFrac2);

      //These methods operate the same except they find the operand, whole number, 
      // normalized fraction, and numerator and denominator of the second operand
      String operand1 = getOperand1(input);
      String whole1 = getWhole(operand1);
      String frac1 = getFrac(operand1);
      String normalizedFrac1 = normalize(frac1);
      String numerator1 = getNumerator(normalizedFrac1);
      String denominator1 = getDenominator(normalizedFrac1);
   
      //The convert methods convert the fraction between proper and improper
      //to output the expected fraction
      String convertedOp1 = convertFrac(whole1, numerator1, denominator1);
      String convertedOp2 = convertFrac(whole2, numerator2, denominator2);

      output = processOperator(operator, convertedOp1, convertedOp2);
      // return "reduced result of expression";
       return output;
   }
   
   // Returns a string that is helpful to the user about how
   // to use the program. These are instructions to the user.
   public static String provideHelp() {
      // TODO: Update this help text!
     
      String help = "You got this! Enter mixed numbers with an underscore, you can operate with addition, subtraction, multiplication, and division!\n Enter \"quit\" to exit the program.";
      help += "Students, you need to provide actual helpful text here!";
      
      return help;
   }

   //This method reads the input by the user, which is the expression
   //then reads the second token, which is the operator, then stores it in the variable
   public static String getOperator(String input){
      String operator = "";
      Scanner parser = new Scanner(input);
      parser.next();
      operator = parser.next();
      return operator;
   }
   //This method reads the input, which its the expression
   //then takes the third token, which is the second operand, then stores it in a variable
   public static String getOperand2(String input){
      String operand2 = "";
      Scanner parser = new Scanner(input);
      parser.next();
      parser.next();
      operand2 = parser.next();
      return operand2;
   }
   //The normalize method checks the sign of the fractions, then changes it accordingly
   //If both fractions are negative, both are converted to positive, if denominator is negative, numerator turns negative
   public static String normalize(String frac){
      String normalized = "";
      String numerator = getNumerator(frac);//Finds the numerator of the operand
      String denominator = getDenominator(frac);//Finds the denominator of the operand
      if((numerator.charAt(0) == '-') && (denominator.charAt(0) == '-')){
         numerator = numerator.substring(1);
         denominator = denominator.substring(1);
      }
      else if((numerator.charAt(0) != '-') && (denominator.charAt(0) == '-')){
            numerator = "-" + numerator;
            denominator = denominator.substring(1);

      }
      else{
            numerator = numerator;
            denominator = denominator;
      }
      normalized = numerator + "/" + denominator;
      return normalized;
   }
   //This method is used to find the operand of a fraction by looking at the value before the "/"
   public static String getNumerator(String frac){
      String numerator = frac;

      if(frac.contains("/")){
         numerator = frac.substring(0,frac.indexOf("/"));
      }
      return numerator;
   }
   //Similarly, this method finds the denominator of a fraction by looking at the value after the "/"
   public static String getDenominator(String frac){
      String denominator = "1";
      if(frac.contains("/")){
         denominator = frac.substring(frac.indexOf("/") + 1);
      }
      return denominator;
   }
   //getWhole is used to find the whole number of a fraction that contains a whole number
   //The if statement is used to ensure that the program only looks for a whole number if it has one
   public static String getWhole(String operand2){
      String whole = "0";
      if(operand2.indexOf("_") >= 0){
         whole = operand2.substring(0,operand2.indexOf("_"));
      }
      else if(operand2.indexOf("/") < 0){
         whole = operand2;
      }
      else{
         whole = "0";
      }
      return whole;
   }
   //getFrac is used to identify the fraction part of the operand by looking for the
   //value after the underscore, using an if statement to identify the fraction only if it exists
   public static String getFrac(String operand2){
      String frac = "0/1";
      if(operand2.indexOf("_") >= 0){
         frac = operand2.substring(operand2.indexOf("_") + 1);
      }
      else if(operand2.indexOf("/") > 0){
         frac = operand2;
      }
      else{
         frac = "0/1";
      }
      return frac;  
   }
   //This method functions similar to getOperand2, by looking for the first token, which contains operand 1
   public static String getOperand1(String input){
      String operand1 = "";
      Scanner parser = new Scanner(input);
      operand1 = parser.next();
      return operand1;
   }
   //This method uses a formula to add operand 1 and operand 2
   //Returns the value through simplifyFrac, which simplifies the fraction after adding as they are improper fractions
   public static String addFrac(int n1, int d1, int n2, int d2){//op1 and op2 must be improper fractions
      int addedNum = (n1*d2)+(n2*d1);
      int addedDen = (d1*d2);
      return simplifyFrac(addedNum, addedDen);
   }
   //subFrac subtracts the operand 1 and operand 2 of the fraction with sending them through simplifyFrac
   //Returns the value after numerator and denominator are simplified
   public static String subFrac(int n1, int d1, int n2, int d2){
      int subNum = (n1*d2)-(n2*d1);
      int subDen = (d1*d2);
      return simplifyFrac(subNum, subDen);
   }
   //multFrac multiplies the numerators and denominator to find the product of the fractions
   //Takes in both numerators and denominators as parameters to do the math
   //The product goes through normalizeFrac to adjust the signs of the fraction to fit the requirements
   public static String multFrac(int n1, int d1, int n2, int d2){
      int multNum = (n1 * n2);
      int multDen = (d1 * d2);
      String normalizedFrac = normalize(multNum + "/" + multDen);//Structures the components as a fraction
      int newNum = Integer.parseInt(getNumerator(normalizedFrac));
      int newDen = Integer.parseInt(getDenominator(normalizedFrac));
      return simplifyFrac(newNum, newDen);
   }
   //divFrac changes numerator and denominaor values to divide the fraction through multiplication
   //if statement is used to return the numerator if the denominator is equal to zero
   //Takes in both numerators and denominators to operate on
   public static String divFrac(int n1, int d1, int n2, int d2){
      String returnDiv = normalize(multFrac(n1, d1, d2, n2));
      //System.out.println("returnedDiv : "  + returnDiv);
      int newNum = Integer.parseInt(getNumerator(returnDiv));
      int newDen = Integer.parseInt(getDenominator(returnDiv));
      if(newDen == 1){
         returnDiv = Integer.toString(newNum);
      }
      return returnDiv;
   }
      
   
   //This method takes the operator as a parameter to identify which operation to conduct on 
   //both operators, which are also called as parameters
   public static String processOperator(String operator, String op1, String op2){
      //Finds and converts the numerators and denominators of both operands to integers to perform math on
      int n1 = Integer.parseInt(getNumerator(op1));
      int d1 = Integer.parseInt(getDenominator(op1));
      int n2 = Integer.parseInt(getNumerator(op2));
      int d2 = Integer.parseInt(getDenominator(op2));
      //This variable is used to store the final result of the fractions after identifying which operator is used
      //if statement helps identify the operator to decide the method to operate on the operands
      String sendBack = "";
      //Checks the value in operator variable to determine what to perform
      if(operator.equals("+")){
         sendBack = addFrac(n1, d1, n2, d2);
      }
      else if(operator.equals("-")){
         sendBack = subFrac(n1, d1, n2, d2);
      }
      else if(operator.equals("*")){
         sendBack = multFrac(n1, d1, n2, d2);
      }
      else{
         sendBack = divFrac(n1, d1, n2, d2);
      }
      return sendBack;
   }
   //simplifyFrac takes the numerator and denominator as parameters
   //This method identifies if a whole number can be simplified out of an improper fraction
   public static String simplifyFrac(int num, int den){
      String simpFrac = "";
      boolean numNegative = false;
      //This if statement ensures that positive numbers are being operated on
      if(num < 0){
         numNegative = true;
         num = Math.abs(num);
      }
      //This if statement finds how many whole numbers can be factored out of the improper fraction
      if(num>den){
         int whole = num / den;
         int newNum = num % den;
         if(newNum == 0){
            simpFrac += whole;
         }
         else{//proper fraction after extracting the whole is being simplified and properly structed 
            String reducedFrac = simplify(newNum, den);
            simpFrac = whole + " " + reducedFrac;
         }
      }
      else if(num == den){//In the case that the numerator and denominator are equal
         simpFrac = "1";
      }
      else{//In the case of numberator is less than denominator
         simpFrac = simplify(num, den);
      }
      if(numNegative){//Returns the fraction to negative state if needed
         simpFrac = "-" + simpFrac;
      }
      return simpFrac;
   }
   //This method takes in the numerator and denominator as paramters
   //Finds the gcf and makes sure that the fraction is simplified with gcf
   public static String simplify(int num, int den){
      if(num == 0){//if the numerator is zero, whole fraction will be returned as zero
         return "0";
      }
      else if(den == 1){//if denominator is 1, the numerator is returned as the whole number
         return String.valueOf(num);
      }
      else{
      int posNum = Math.abs(num);//finds numerator in positive value
      int posDen = Math.abs(den);//finds denominator in positive value
      while(posDen != 0){//Finds the gcf by looking for a value that returns 0 when dividing num by den
         int temp = posDen;
         posDen = posNum % posDen;
         posNum = temp;
      }
      int gcd = posNum;
      //Simplifies the numerator and denominator by dividing both by the gcf
      num = num / gcd;
      den = den / gcd;
      return num + "/" + den;//Returns value after structuring to form a fraction
      }
   }
   //convertFrac takes the whole number, numerator, and denominator as parameters to convert
   //between improper and proper fractions
   //Returns the value after converting
   public static String convertFrac(String whole, String num, String den){
      String convertedFrac = "";
      if(!whole.equals("0")){//if the whole number exists in the operand
         int intWhole = Integer.parseInt(whole);
         int intNum = Integer.parseInt(num);
         int intDen = Integer.parseInt(den);
         boolean wholeNegative = false;

         if(intWhole < 0){//If the whole number is negative, converts to positive
            wholeNegative = true;
            intWhole = Math.abs(intWhole);
         }
         int newNum = (intWhole * intDen) + intNum;//multiplies whole with denominator, then adds numerator to find new numerator of improper fraction
         if(wholeNegative){//adds the negative back to negative fractions
            convertedFrac += "-";
         }
         convertedFrac += newNum + "/" + den;//structures the converted fraction
      }
      else{//for the case where the whole number is not existant
         convertedFrac = num + "/" + den;
      }
      return convertedFrac;
   }
}