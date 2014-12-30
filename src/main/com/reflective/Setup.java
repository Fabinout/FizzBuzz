package reflective;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * Created by fabienlamarque on 30/12/2014,
 * inspired by: Olivier Croisier : http://www.code-story.net/     .
 */
public class Setup {


    public static void main(String[] args) {
        System.out.println("Start fucking around");
        ClassPool pool = ClassPool.getDefault();
        CtClass cl = null;
        try {
            cl = pool.get("java.lang.Integer");
            CtMethod toStringMethod = cl.getDeclaredMethod("toString", new CtClass[]{CtClass.intType});
            System.out.println("CtMethod toStringMethod ");
//            toStringMethod.setBody(getCode());
            toStringMethod.setBody(getCodeOriginal());
            System.out.println("toStringMethod.setBody(getCode());");
            cl.writeFile(args[0]);
            System.out.println("cl.writeFile(args[0]);");

        } catch (CannotCompileException e) {
            System.err.println("CannotCompileException: " + e.getMessage());
            System.err.print(e.getReason());
        } catch (NotFoundException e) {
            System.err.println("NotFoundException: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {

            System.err.println("IO Excepiton: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (cl != null) {
                cl.detach();
            }
        }
    }

    private static String getCodeOriginal() {
        return
                new StringBuilder().
                        append("{").
                        append("if ($1 == Integer.MIN_VALUE) return \"-2147483648\";\n").
                        append("int size = ( $1 < 0) ? stringSize(0 - $1 ) + 1 : stringSize( $1 );\n").
                        append("char[] buf = new char[size];\n").
                        append("String FIZZ = \"Fizz\";\n").
                        append("String BUZZ = \"Buzz\";\n").
                        append("boolean isFizz = false;\n").
                        append("boolean isBuzz = false;").
                        append("getChars( $1, size, buf);\n").
                        append("StringBuilder fbqBuffer = new StringBuilder();\n").
                        append("if ( $1 % 3 == 0) isFizz = true;\n").
                        append("if ( $1 % 5 == 0) isBuzz = true;\n").
                        append("for (int idx=0; idx<buf.length; idx++) {\n").
                        append("    char c = buf[idx];\n").
                        append("    if (c=='3') isFizz = true;\n").
                        append("    if (c=='5') isBuzz = true; \n").
                        append("}\n").
                        append("if (isFizz) fbqBuffer.append(\"Fizz\");\n").
                        append("if (isBuzz) fbqBuffer.append(\"Buzz\");\n").
                        append("String result = fbqBuffer.toString();\n").
                        append("return result.isEmpty() ? new String(buf,0, size) : result;").
                        append("}").toString();
    }


}
