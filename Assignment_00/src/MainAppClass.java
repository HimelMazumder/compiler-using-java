import java.util.Scanner;

public class MainAppClass {
        public static void main(String[] args) {
            Character choice;
            FunctionalityClass pc = new FunctionalityClass();

            do{
                System.out.println("1 - Insert || 2 - Delete || 3 - Search || 4 - Update || 5 - Show Table ||  X - Terminate");
                Scanner sc = new Scanner(System.in);
                choice = sc.next().charAt(0);
                sc.nextLine();

                switch (choice) {
                    case '1':
                        //System.out.println("insert");
                        pc.insert();
                        break;
                    case '2':
                        System.out.println("What symbol you'd like to delete");
                        String name;
                        String type;

                        name = sc.next();
                        type = sc.next();

                        pc.delete(name, type);

                        break;
                    case '3':
                        System.out.println("What symbol you'd like to search");

                        name = sc.next();
                        type = sc.next();

                        pc.searchMsg(name, type);
                        break;
                    case '4':
                        System.out.println("What symbol you'd like to update (Enter both name & type)");

                        name = sc.next();
                        type = sc.next();

                        pc.update(name, type);
                        break;
                    case '5':
                        pc.showSymbolTable();
                        break;
                    case 'x':
                    case 'X':
                        System.out.println("Terminated");
                        break;
                    default:
                        System.out.println("Wrong Choice");

                }

            }while (!choice.equals('x') && !choice.equals('X'));

        }




}
