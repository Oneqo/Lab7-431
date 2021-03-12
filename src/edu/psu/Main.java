package edu.psu;

import java.lang.reflect.*;
import java.util.Arrays;

public class Main {

    private static String className;

    public static void main(String[] args) {
        try{
            Class c = Class.forName(args[0]);
            System.out.println(getSkeleton(c));
        }
        catch (Exception e){
            System.out.println("Couldn't locate the class. Make sure to utilize full name (including the package(s)).");
        }
    }

    private static String getSkeleton(Class source) throws ClassNotFoundException{
        if(source.isInterface()){
            return getInterfaceSkeleton(source);
        }else{
            return getClassSkeleton(source);
        }
    }

    private static String getInterfaceSkeleton(Class source) {
        StringBuilder skeleton = new StringBuilder();
        className = parseClassName(source.getName());
        String modifiers = Modifier.toString(source.getModifiers());
        skeleton.append(modifiers);
        if(!modifiers.isEmpty())
            skeleton.append(' ');
        skeleton.append("interface ");
        skeleton.append(className);

        //Get all interfaces
        Class[] interfaces = source.getInterfaces();

        //Interfaces
        if(interfaces.length > 0){
            skeleton.append(" extends ");
            for (Class i : interfaces) {
                skeleton.append(parseClassName(i.getName()));
                skeleton.append(',');
            }
            skeleton.deleteCharAt(skeleton.length()-1);
        }
        skeleton.append("{\n");

        //Fields
        Field[] fields = source.getDeclaredFields();
        appendFields(skeleton, fields);

        //Methods
        Method[] methods = source.getDeclaredMethods();
        appendMethods(skeleton, methods);
        skeleton.append('}');
        return skeleton.toString();
    }

    private static void appendMethods(StringBuilder skeleton, Method[] methods) {
        if (methods.length > 0) {
            Arrays.sort(methods, (Method m1, Method m2) -> {
                return -1 * m1.toString().compareTo(m2.toString());
            });
            skeleton.append("\\\\Methods:\n");
            for (Method m : methods) {
                skeleton.append("\t" + getModifiers(m));
                skeleton.append(m.getAnnotatedReturnType() + " ");
                skeleton.append(m.getName());
                skeleton.append(getParameterList(m));
                skeleton.append(getExceptionList(m));
                skeleton.append(";\n");
            }
        }
    }

    private static String getClassSkeleton(Class source) throws ClassNotFoundException {
        StringBuilder skeleton = new StringBuilder();
        className = parseClassName(source.getName());
        String modifiers = Modifier.toString(source.getModifiers());
        skeleton.append(modifiers);
        if(!modifiers.isEmpty())
            skeleton.append(' ');
        skeleton.append("class ");
        skeleton.append(className);

        //Get superclass & interfaces
        Class superClass = source.getSuperclass();
        Class[] interfaces = source.getInterfaces();

        //Superclass
        if(superClass.getName() != Class.forName("java.lang.Object").getName()){
            skeleton.append(" extends ");
            skeleton.append(parseClassName(superClass.getName()));
        }

        //Interfaces
        if(interfaces.length > 0){
            skeleton.append(" implements ");
            for (Class i : interfaces) {
                skeleton.append(parseClassName(i.getName()));
                skeleton.append(',');
            }
            skeleton.deleteCharAt(skeleton.length()-1);
        }
        skeleton.append("{\n");

        //Fields
        Field[] fields = source.getDeclaredFields();
        appendFields(skeleton, fields);

        //Constructors
        Constructor[] constructors = source.getDeclaredConstructors();
        if(constructors.length > 0){
            Arrays.sort(constructors, (Constructor c1, Constructor c2) -> {return -1*c1.toString().compareTo(c2.toString());});
            skeleton.append("\\\\Constructors:\n");
            for(Constructor c : constructors){
                skeleton.append("\t"+ getModifiers(c));
                skeleton.append(className);
                skeleton.append(getParameterList(c));
                skeleton.append(getExceptionList(c));
                skeleton.append(";\n");
            }
        }

        //Methods
        Method[] methods = source.getDeclaredMethods();
        appendMethods(skeleton, methods);
        skeleton.append('}');
        return skeleton.toString();
    }

    private static void appendFields(StringBuilder skeleton, Field[] fields) {
        if (fields.length > 0) {
            Arrays.sort(fields, (Field f1, Field f2) -> {
                return -1 * f1.toString().compareTo(f2.toString());
            });
            skeleton.append("\\\\Fields:\n");
            for (Field f : fields) {
                skeleton.append("\t" + getModifiers(f) + f.getAnnotatedType() + " " + f.getName());
                skeleton.append(";\n");
            }
        }
    }

    private static String getExceptionList(Executable obj) {
        StringBuilder result = new StringBuilder();
        Class[] exceptions = obj.getExceptionTypes();
        if(exceptions.length>0){
            result.append(" throws ");
            for(Class e : exceptions){
                result.append(e.getName());
                result.append(',');
            }
            result.deleteCharAt(result.length()-1);
        }
        return result.toString();
    }

    private static String parseClassName(String name) {
        return name.substring(name.lastIndexOf('.') + 1,name.length());
    }

    private static String getModifiers(Member obj){
        StringBuilder result = new StringBuilder();
        int modifiers = obj.getModifiers();
        result.append(Modifier.toString(modifiers));
        if(!result.toString().isEmpty()){
            result.append(' ');
        }
        return result.toString();
    }
    private static String getParameterList(Executable obj){
        StringBuilder result = new StringBuilder();
        Class[] parameters = obj.getParameterTypes();
        result.append('(');
        if(parameters.length>0){
            for(int i = 0; i!=parameters.length; i++){
                Class temp = parameters[i];
                result.append(temp.getName() + " ");
                result.append(temp.getName().charAt(0)+""+(i+1));
                result.append(',');
            }
            result.deleteCharAt(result.length()-1);//remove last coma
        }
        result.append(")");
        return result.toString();
    }
}