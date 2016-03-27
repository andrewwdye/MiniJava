import Scanner.*;
import Parser.*;
import AST.*;
import AST.Visitor.*;
import java_cup.runtime.Symbol;
import java.util.*;
import java.io.*;

public class MiniJava {
    public static void main(String [] args)
    {
        int exitCode = 0;

        switch (args[0])
        {
            case "-S":
                if (args.length != 2) { throw new IllegalArgumentException("Incorrect number of arguments"); }
                exitCode = Scan(args[1]);
                break;
            case "-TS":
                TestScanner();
                break;
            default:
                throw new IllegalArgumentException("Unrecognized parameters '" + args[0] + "'");
        }

        System.exit(exitCode);
    }

    public static int Scan(String file)
    {
        return Scan(file, true);
    }

    public static int Scan(String file, boolean printTokens)
    {
        try {
            // create a scanner on the input file
            scanner s = new scanner(new BufferedReader(new FileReader(file)));
            Symbol t = s.next_token();
            while (t.sym != sym.EOF){
                // print each token that we scan
                if (printTokens) System.out.print(s.symbolToString(t) + " ");
                t = s.next_token();
            }
        } catch (Exception e) {
            // yuck: some kind of error in the compiler implementation
            // that we're not expecting (a bug!)
            System.err.println("Unexpected internal compiler error: " +
                               e.toString());
            // print out a stack dump
            e.printStackTrace();

            return 1;
        }

        return 0;
    }

    public static void TestScanner()
    {
        for (File file : (new File("SamplePrograms/SampleMiniJavaPrograms")).listFiles())
        {
            System.out.println("Scanning " + file.getName());
            Scan(file.getPath(), false);
        }
    }
}
