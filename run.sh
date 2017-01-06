#!/usr/bin/env bash

if [[ "$@" == '' ]]; then
    echo "SYNOPSIS"
    echo "  $0 <class>"
    echo ""
    echo "DESCRIPTION"
    echo "  Shortcut for running Java programs."
    echo "  It sets up CLASSPATH for you before running classes from 'pw.ozro.app' namespace."
    exit 0
fi

TOOL=$1
if [[ $TOOL == '' ]]; then
    TOOL=App
fi

if [[ $TOOL != '-f' ]]; then
    java -classpath $HOME/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar:$HOME/.m2/repository/org/hsqldb/hsqldb/2.3.4/hsqldb-2.3.4.jar:target/ozro-app-1.0-SNAPSHOT.jar pw.ozro.app.$TOOL
else
    java -classpath $HOME/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar:$HOME/.m2/repository/org/hsqldb/hsqldb/2.3.4/hsqldb-2.3.4.jar:target/ozro-app-1.0-SNAPSHOT.jar $2
fi
