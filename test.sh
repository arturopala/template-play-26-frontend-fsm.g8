#!/usr/bin/env bash

if [[ -f ./build.sbt ]] && [[ -d ./src/main/g8 ]]; then

    export TEMPLATE=`pwd | xargs basename`
    echo ${TEMPLATE}
    mkdir -p target/sandbox
    cd target/sandbox
    sudo rm -r new-shiny-service-26-fsm
    g8 file://../../../${TEMPLATE} --servicename="New Shiny Service 26 FSM" --package=uk.gov.hmrc.newshinyservice26fsmfrontend --serviceTargetPort="9999" -o new-shiny-service-26-fsm "$@"
    cd new-shiny-service-26-fsm
    git init
	git add .
	git commit -m start
    sbt test it:test

else

    echo "WARNING: run the script ./test.sh in the template root folder"
    exit -1

fi