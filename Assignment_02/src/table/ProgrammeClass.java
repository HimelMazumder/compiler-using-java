/**
 * Created by Himel Mazumder on 6/25/2018.
 */
package table;
import java.util.Scanner;

public class ProgrammeClass {
    static SymbolInfo[] symbolTable = new SymbolInfo[15]; //Creating a array of objects
    static int[] searchInfo = new int[3];
    Scanner sc = new Scanner(System.in);

    public boolean insert(String name, String type) {

        search(name, type);
        if (searchInfo[2] == 0) {
            SymbolInfo symbol = new SymbolInfo(name, type, null);

            int indx = getHashKey(name);
            //System.out.println("the index number is " + indx);

            if (symbolTable[indx] != null) {
                SymbolInfo temp = symbolTable[indx];
                symbolTable[indx] = symbol;
                symbol.next = temp;

                //System.out.println("Insertion Done");
            } else {
                symbolTable[indx] = symbol;
                //System.out.println("Insertion Done");
            }

            return true;
        } else {
            System.out.println("Symbol already exists");
            return false;
        }

    }

    void delete(String name, String type) {
        searchMsg(name, type);
        if (searchInfo[2] == 1) {
            int listPosCounter = 1;

            SymbolInfo si = symbolTable[searchInfo[0] - 1];

            if (searchInfo[1] == 1) {
                symbolTable[searchInfo[0] - 1] = si.next;
                System.gc();


            } else {
                SymbolInfo tempPrev = null;
                while (listPosCounter != searchInfo[1]) {
                    listPosCounter++;

                    tempPrev = si;
                    si = si.next;
                }

                tempPrev.next = si.next;
                System.gc();


            }

            System.out.println("Record DELETED");
            showSymbolTable();


        }
    }

    void search(String name, String type) {
        searchInfo[0] = 0;
        searchInfo[2] = 0;


        for (int i = 0; i < 15; i++) {
            SymbolInfo si = symbolTable[i];
            int listPos = 0;

            while (si != null) {
                if (si.symbol.equals(name)) {
                    searchInfo[0] = i + 1;
                    searchInfo[1] = listPos + 1;

                    if (si.symbolType.equals(type)) {
                        searchInfo[2] = 1;
                    }
                    break;
                } else {
                    si = si.next;
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


    void update(String name, String type) {
        Scanner sc = new Scanner(System.in);
        Character choice;
        searchMsg(name, type);
        if (searchInfo[2] == 1) {
            int listPosCounter = 1;

            SymbolInfo si = symbolTable[searchInfo[0] - 1];

            while (listPosCounter != searchInfo[1]) {
                listPosCounter++;
                si = si.next;
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
        //System.out.println("String is " + name);
        int hash = 1;
        for (int i = 0; i < name.length(); i++) {
            hash += hash++ * 13 + name.charAt(i);
            //System.out.println("hash at " + i + " is " + hash);
        }

        //System.out.println("hash is " + hash);
        hash = Math.abs(hash);
        return hash % 15;
    }

    public void showSymbolTable() {

        for (SymbolInfo si : symbolTable) {
            if (si != null) {

                do {
                    System.out.printf("(Name: %s || Type: %s)-->", si.symbol, si.symbolType);
                    si = si.next;
                } while (si != null);

                System.out.println("");

            } else {
                System.out.println("(Null)");
            }
        }

    }

}
