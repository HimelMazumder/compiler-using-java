/**
 * Created by Himel Mazumder on 6/24/2018.
 * In java objects are reference type variable, so it will correspond to Structure in C
 * Literally SymbolInfo Class here works same as it would do in C
 */
package table;

public class SymbolInfo {
    String symbol;
    String symbolType;

    SymbolInfo next;


    public SymbolInfo(String symbol, String symbolType, SymbolInfo next) {
        this.symbol = symbol;
        this.symbolType = symbolType;
        this.next = next;
    }
}
