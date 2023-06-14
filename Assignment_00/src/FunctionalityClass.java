import java.util.Scanner;

/**
 * This class contains all functions related to Symbol table
 */
public class FunctionalityClass {
    Scanner sc = new Scanner(System.in); // Scanner object to get input from user

    // Size of symboleTable is 10
    static Symbol[] symbolTable = new Symbol[10]; // symbolTable is an array whose each element is a Symbol object and each Symbol object is a linked list
    static int[] searchInfo = new int[3]; // This is an array that contains search related information


    void insert() {
        // While inserting new Symbol must put a SPACE between Name and Type!!
        System.out.println("Enter Name & Type: ");
        String name = sc.next();
        String type = sc.next();

        // Calling search() function to check if any Symbol with same name and type already exists
        // If there's no match then the value of 2nd cell of searchInfo array is 0 otherwise 1

        search(name, type);
        if (searchInfo[2] == 0) {
            Symbol symbol = new Symbol(name, type, null);

            int indx = getHashKey(name); // Get Hash Key from getHashKey() function

            if (symbolTable[indx] != null) {
                // Executed if there's a head in INDXth cell of symbolTable
                // Making new Symbol object head of the linked list, in other words this is a head insertion
                Symbol temp = symbolTable[indx];
                symbolTable[indx] = symbol;
                symbol.nextSymbol = temp;

                System.out.println("Insertion Done");
            } else {
                // Executed if there's no Symbol in INDXth cell of symbolTable or INDXth cell is NULL
                // Directly assigning object to INDXth cell of symbolTable
                symbolTable[indx] = symbol;
                System.out.println("Insertion Done");
            }
        } else {
            // Executed when value of 2nd cell of searchInfo array is 1 otherwise a duplicate is found
            System.out.println("The symbol named " + name + " already exists at [" + searchInfo[0] + "][" + searchInfo[1] + "]");
        }

    }

    void delete(String name, String type) {
        searchMsg(name, type); // Searching the symbolTable to get the Symbol
        if (searchInfo[2] == 1) {
            int listPosCounter = 1;

            Symbol si = symbolTable[searchInfo[0] - 1];

            if (searchInfo[1] == 1) {
                symbolTable[searchInfo[0] - 1] = si.nextSymbol;
                System.gc();


            } else {
                Symbol tempPrev = null;
                while (listPosCounter != searchInfo[1]) {
                    listPosCounter++;

                    tempPrev = si;
                    si = si.nextSymbol;
                }

                tempPrev.nextSymbol = si.nextSymbol;
                System.gc(); // Calling the garbage collector of java


            }

            System.out.println("Record DELETED");
            showSymbolTable();


        }
    }

    void search(String name, String type) {
        searchInfo[0] = 0;
        searchInfo[2] = 0;


        for (int i = 0; i < 10; i++) { // Size of symboleTable is 10
            Symbol si = symbolTable[i];
            int listPos = 0;

            while (si != null) {
                if (si.symbolName.equals(name)) {
                    searchInfo[0] = i + 1; // SearchInfo[0] contains the array cell position, to start from 1, 1 is added to actual position
                    searchInfo[1] = listPos + 1;  // SearchInfo[1] contains the Linked List cell position, to start from 1, 1 is added to actual position

                    if (si.symbolType.equals(type)) {
                        searchInfo[2] = 1; // SearchInfo[2] is 1 if and only if both name and type are matched
                    }
                    break;
                } else {
                    si = si.nextSymbol;
                    listPos++;
                }

            }

        }

    }


    void searchMsg(String name, String type) {
        search(name, type);
        if (searchInfo[2] != 0) {
            System.out.println("The symbol " + name + " exists at [" + searchInfo[0] + "][" + searchInfo[1] + "]");
        } else {
            System.out.println("The symbol not found");
        }
    }


    void update(String name, String type) { // Update() function only updates type
        Scanner sc = new Scanner(System.in);
        Character choice;
        searchMsg(name, type);
        if (searchInfo[2] == 1) {
            int listPosCounter = 1;

            Symbol si = symbolTable[searchInfo[0] - 1];

            while (listPosCounter != searchInfo[1]) {
                listPosCounter++;
                si = si.nextSymbol;
            }

            do {

                System.out.println("Enter new type");
                String newType = sc.next();
                sc.nextLine();

                System.out.println("Confirm(y/n)");
                choice = sc.next().charAt(0);
                sc.nextLine(); //Clearing the scanner buffer

                if (choice.equals('y') || choice.equals('Y')) {
                    si.symbolType = newType;
                }


            } while (!choice.equals('y') && !choice.equals('Y'));

            System.out.println("Record UPDATED");
            showSymbolTable();
        }
    }


    int getHashKey(String name) {
        int hash = 1;
        for (int i = 0; i < name.length(); i++) {
            hash = hash++ * 31 + name.charAt(i);
        }
        return hash % 10;
    }


    void showSymbolTable() {

        for (Symbol si : symbolTable) {
            if (si != null) {

                do {
                    System.out.printf("(Name: %s Type: %s) ", si.symbolName, si.symbolType);
                    si = si.nextSymbol;
                } while (si != null);

                System.out.println("");

            } else {
                System.out.println("Null");
            }
        }

    }

}
