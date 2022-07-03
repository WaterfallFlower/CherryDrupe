# CherryDrupe
[![](https://jitpack.io/v/ChessChicken-KZ/CherryDrupe.svg)](https://jitpack.io/#ChessChicken-KZ/CherryDrupe)
<br>
A set of tools that cause only pain.
<br>
Supports Java 8+.
## Current features:

### [InitAndApply](https://github.com/ChessChicken-KZ/CherryDrupe/blob/main/src/main/java/kz/chesschicken/cherrydrupe/InitAndApply.java)
An interface that allows us to initialise and pre-setup an object.
```java
Map<String, String> aReadyMap = InitAndApply.apply(new HashMap<>(), instance -> {
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

