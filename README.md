Pre-requisites install codeql

```shell
brew install --cask codeql
```

Create a codeql database

```shell
codeql database create ./db-demo --language=java
```

To run the CodeQL analysis on the `db-demo` database using the custom Java queries in the `codeql-custom-queries-java` folder, you can use the following command:

```shell
codeql database analyze ./db-demo ./codeql-custom-queries-java/tocomment.ql --format=sarif-latest --output=./results.sarif
```

Feel free to replace `./results.sarif` with the desired output file path.
