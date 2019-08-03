#!/bin/bash

# copy from https://gist.github.com/irazasyed/a7b0a079e7727a4315b9

# PATH TO YOUR HOSTS FILE
ETC_HOSTS=/etc/hosts

# DEFAULT IP FOR HOSTNAME
IP="127.0.0.1"

# Hostname to add/remove.
HOSTNAME=$2

removehost() {
    echo "removing host";
    if [ -n "$(grep $HOSTNAME /etc/hosts)" ]
    then
        echo "$HOSTNAME Found in your $ETC_HOSTS, Removing now...";
        sudo sed -i".bak" "/$HOSTNAME/d" $ETC_HOSTS
    else
        echo "$HOSTNAME was not found in your $ETC_HOSTS";
    fi
}

addhost() {
    HOSTS_LINE="$IP\t$HOSTNAME"
    if [ -n "$(grep $HOSTNAME /etc/hosts)" ]
        then
            echo "$HOSTNAME already exists : $(grep $HOSTNAME $ETC_HOSTS) -> OK!"
        else
            echo "Adding $HOSTNAME to your $ETC_HOSTS";

            #timeout 1 sudo id && (echo sudo access granted) || (echo "Could not add '$HOSTS_LINE' to hosts. Run again with 'sudo'!" && exit 1)

            sudo -- sh -c -e "echo '$HOSTS_LINE' >> /etc/hosts";

            if [ -n "$(grep $HOSTNAME /etc/hosts)" ]
                then
                    echo "$HOSTNAME was added succesfully \n $(grep $HOSTNAME /etc/hosts)";
                else
                    RED='\033[0;31m'
                    NC='\033[0m'

                    echo -e "\n${RED}FAILED ${NC}to add '$HOSTNAME' to /etc/hosts. Run again with 'sudo' or add it mannually to /etc/hosts!\n"
                    exit 1
            fi

    fi
}


$@