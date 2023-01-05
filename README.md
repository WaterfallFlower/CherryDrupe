# CherryDrupe
[![](https://jitpack.io/v/ChessChicken-KZ/CherryDrupe.svg)](https://jitpack.io/#ChessChicken-KZ/CherryDrupe)
<br>
A set of tools that cause only pain.
<br>
Supports Java 8+.
<br><br>
For the latest update, the library contains 3 modules:
- [Core module](#core-module).
- [Hijack module](#hijack-module).
- [Kiln module](#kiln-module).

## Core Module:

### [Functional](https://github.com/ChessChicken-KZ/CherryDrupe/blob/main/src/main/java/kz/chesschicken/cherrydrupe/Functional.java)
Generate a code that allows us to initialise and pre-setup an object.
```java
Map<String, String> aReadyMap = Functional.applyInit(new HashMap<>(), instance -> {
    instance.put("Land", "Grass");
    instance.put("Sky", "Cloud");
});
```

### [Try](https://github.com/ChessChicken-KZ/CherryDrupe/blob/main/src/main/java/kz/chesschicken/cherrydrupe/tryint/Try.java) / [TryReturn](https://github.com/ChessChicken-KZ/CherryDrupe/blob/main/src/main/java/kz/chesschicken/cherrydrupe/tryint/TryReturn.java)
Why not to make `try` as an interface?
```java
/* Initialising the "try" here. */
TryReturn<String, IOException> try1 = new TryReturn<String, IOException>() {
    @Override
    public String _try() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("readme.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            stringBuilder.append(s);
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    @Override
    public String _catch(IOException e) {
        System.out.println("Oops... caught an exception!");
        e.printStackTrace();
        return "";
    }
};

/* Launch it later... */
System.out.println(try1.apply());
```

### [TransformTools](https://github.com/ChessChicken-KZ/CherryDrupe/blob/main/core/src/main/java/kz/chesschicken/cherrydrupe/TransformTools.java)
Making lists and maps into simple arrays.
```java
Map<String, Float> aMap = new HashMap<>();

/* using the map... */

Double[] aNewList = TransformTools.transformFromMap(new Double[aMap.size()], aMap, entry ->
        entry.getKey().endsWith("_double") ? Math.pow(entry.getValue(), 2) : entry.getValue());
```


## Hijack Module:

### [UnsafeUtilities](https://github.com/ChessChicken-KZ/CherryDrupe/blob/main/hijack/src/main/java/kz/chesschicken/cherrydrupe/hijack/impl/UnsafeUtilities.java)
Access to dynamic/static fields and manipulate them 'safely' with `sun.misc.Unsafe` wrapper!
```java
private class AClass {
    private static int cool_int;
    private String string_val;
}

public static void accessAClass() {
    AClass aClass = /* ... */;
    
    /* Getting fields values... */
    int cool_int = UnsafeUtilities.getStaticField(AClass.class, "cool_int");
    String string_val = UnsafeUtilities.getField(AClass.class, "string_val", aClass);
    
    /* Setting fields values... */
    
    UnsafeUtilities.setStaticField(AClass.class, "cool_int", 5);
    UnsafeUtilities.setField(AClass.class, "string_val", aClass, "Hello World!");
}
```

### [MethodHandleUtilities](https://github.com/ChessChicken-KZ/CherryDrupe/blob/main/hijack/src/main/java/kz/chesschicken/cherrydrupe/hijack/impl/MethodHandleUtilities.java)
Something like UnsafeProvider, but is actually `java.lang.invoke.MethodHandle`'s wrapper and also can access to dynamic/static methods.

```java
private class AClass {
    private static void doSomething() {
        /* ... */
    }

    private String getSecretValue() {
        return "cake";
    }
}

public static void accessAClass() {
    AClass aClass = /* ... */;
    
    /* Getting fields methods and invoking them... */
    ObjectsFunction<Void> doSomethingWrapper = MethodHandleUtilities.generateStaticMethod(AClass.class, "doSomething", void.class, new Class[0]);
    doSomethingWrapper.apply(); //invoking "doSomething"...
    
    ObjectsFunction<String> getSecretValueWrapper = MethodHandleUtilities.generateMethod(AClass.class, "getSecretValue", String.class, new Class[0]);
    String gotAValue = getSecretValueWrapper.apply(aClass); //invoking "getSecretValue"...
}
```

## Kiln Module: