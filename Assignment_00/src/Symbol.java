/**
 * In java objects are reference type variable, so it will correspond to Structure in C
 * Literally SymbolInfo Class here works same as it would do in
 * each object of this class acts like nodes of a linked list
 */
public class Symbol {
    String symbolName;
    String symbolType;

    Symbol nextSymbol; // stores the address of the next object


    // this constructor assigns values to instance variable at the time of object creation
    public Symbol(String symbol, String symbolType, Symbol nextSymbol) {
        this.symbolName = symbol;
        this.symbolType = symbolType;
        this.nextSymbol = nextSymbol;
    }
}
