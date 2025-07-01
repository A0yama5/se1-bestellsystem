#!/bin/bash

# compile source files
compile() {
  mkdir -p out
  javac -d out $(find src -name "*.java")
}

# compile test files
compile-tests() {
  mkdir -p out-tests
  javac -cp "lib/*:out" -d out-tests $(find tests -name "*.java")
}

# run tests
run-tests() {
  java -jar lib/junit-platform-console-standalone*.jar \
    --class-path out:out-tests \
    --scan-class-path
}

clean() {
  rm -rf out out-tests
}

"$@"
