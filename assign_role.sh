#!/usr/bin/env bash

if [[ "$@" == '' ]]; then
    echo "SYNOPSIS"
    echo "  $0 <role-id> <user-id>"
    echo ""
    echo "DESCRIPTION"
    echo "  Assign role to a user"
    echo ""
    echo "  Obtain role IDs using './run.sh ListEnumerationValues'."
    echo "  List assigned roles using './run.sh ListRoles'."
    exit 0
fi

echo $@
java -classpath $HOME/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar:$HOME/.m2/repository/org/hsqldb/hsqldb/2.3.4/hsqldb-2.3.4.jar:target/ozro-app-1.0-SNAPSHOT.jar pw.ozro.app.AssignRole $@
