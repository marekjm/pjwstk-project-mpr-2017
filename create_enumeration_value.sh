#!/usr/bin/env bash

if [[ "$@" == '' ]]; then
    echo "SYNOPSIS"
    echo "  $0 <system-key> <human-readable-key> <value> <name>"
    echo ""
    echo "DESCRIPTION"
    echo "  Create an enumeration value"
    echo ""
    echo "  List enum values using './run.sh ListEnumerationValues'."
    exit 0
fi

echo $@
java -classpath $HOME/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar:$HOME/.m2/repository/org/hsqldb/hsqldb/2.3.4/hsqldb-2.3.4.jar:target/ozro-app-1.0-SNAPSHOT.jar pw.ozro.app.CreateEnumerationValue $@
