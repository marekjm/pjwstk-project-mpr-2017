#!/usr/bin/env bash

if [[ "$@" == '' ]]; then
    echo "SYNOPSIS"
    echo "  $0 <login> <password>"
    echo ""
    echo "DESCRIPTION"
    echo "  Crate a user"
    echo ""
    echo "  List users using './run.sh ListUsers'."
    exit 0
fi

echo $@
java -classpath $HOME/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar:$HOME/.m2/repository/org/hsqldb/hsqldb/2.3.4/hsqldb-2.3.4.jar:target/ozro-app-1.0-SNAPSHOT.jar pw.ozro.app.CreateUser $@
