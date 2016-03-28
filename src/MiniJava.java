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
            case "-A":
                if (args.length != 2) { throw new IllegalArgumentException("Incorrect number of arguments"); }
                exitCode = Parse(args[1]);
                break;
            case "-TA":
                TestParser();
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
            // TODO: detect scan error
            while (t.sym != sym.EOF){
                // print each token that we scan
                if (printTokens) System.out.print(s.symbolToString(t) + " ");
                t = s.next_token();
            }

            int errors = s.num_scan_errors();
            System.out.println("Found " + errors + " scan error(s)");
            return (errors > 0) ? 1 : 0;
        } catch (Exception e) {
            // yuck: some kind of error in the compiler implementation
            // that we're not expecting (a bug!)
            System.err.println("Unexpected internal compiler error: " +
                               e.toString());
            // print out a stack dump
            e.printStackTrace();

            return 1;
        }
    }

    public static void TestScanner()
    {
        for (File file : (new File("SamplePrograms/SampleMiniJavaPrograms")).listFiles())
        {
            System.out.println("Scanning " + file.getName());
            Scan(file.getPath(), false);
        }
    }

    public static int Parse(String file)
    {
        try {
            // create a scanner on the input file
            scanner s = new scanner(new BufferedReader(new FileReader(file)));
            parser p = new parser(s);

    	    // replace p.parse() with p.debug_parse() in next line to see trace of
    	    // parser shift/reduce actions during parse
            Symbol root = p.debug_parse();
            List<Statement> program = (List<Statement>)root.value;
            for (Statement statement: program) {
                statement.accept(new PrettyPrintVisitor());
				System.out.print("\n");
            }

            return 0;
        } catch (Exception e) {
            // yuck: some kind of error in the compiler implementation
            // that we're not expecting (a bug!)
            System.err.println("Unexpected internal compiler error: " +
                               e.toString());
            // print out a stack dump
            e.printStackTrace();

            return 1;
        }
    }

    public static void TestParser()
    {
        for (File file : (new File("SamplePrograms/SampleMiniJavaPrograms")).listFiles())
        {
            System.out.println("Parsing " + file.getName());
            Parse(file.getPath());
        }
    }
}
