# There are some labs needed to complete in a university.

### Lab4. Website ShoeStore with MVC architecture and SQLite Database

#### How to run?

**Step 1: Compile the project**

```bash
javac -d out -cp "lib/sqlite-jdbc.jar" src/Main.java src/controller/ShoeStoreController.java src/model/Product.java src/model/DatabaseHelper.java src/view/ProductCard.java src/view/ShoeStoreView.java
```

This command compiles all Java source files in the `src` folder.

- `-d out` tells Java to put compiled `.class` files into the `out` folder.
- `-cp "lib/sqlite-jdbc.jar"` adds the SQLite JDBC driver to the compile classpath.
- `src/Main.java ...` lists all Java files that need to be compiled.

**Step 2: Run the application**

```bash
java -cp "out;lib/sqlite-jdbc.jar" Main
```

This command starts the ShoeStore application.

- `out` contains the compiled `.class` files.
- `lib/sqlite-jdbc.jar` allows Java to connect to the SQLite database.
- `Main` is the entry point of the program.

**Step 3: Use the SQLite database**

When the application starts, it creates a `shoestore.db` file automatically if the file does not exist.

The database stores product information in the `products` table, including:

- product name
- price
- brand
- description
- image path

If the `products` table is empty, the application inserts sample products automatically.

**Note for Windows**

Use `;` between classpath items:

```bash
java -cp "out;lib/sqlite-jdbc.jar" Main
```

**Note for macOS/Linux**

Use `:` between classpath items:

```bash
java -cp "out:lib/sqlite-jdbc.jar" Main
```
