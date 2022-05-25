# CherryDrupe
A set of tools that cause only pain.
<br>
Supports Java 8+.

## Current features:

### InitAndApply
An interface that allows us to initialise and pre-setup an object.
```java
Map<String, String> aReadyMap = InitAndApply.apply(new HashMap<>(), instance -> {
    instance.put("Land", "Grass");
    instance.put("Sky", "Cloud");
});
```

### Try/TryWithReturn
Why not to make `try` as an interface?
```java
/* Initialising the "try" here. */
TryWithReturn<String, IOException> try1 = new TryWithReturn<String, IOException>() {
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
try1.apply();
```
