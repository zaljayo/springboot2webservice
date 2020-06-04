#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"
    #하나의 문장을 만들어 파이프라인(|) 으로 넘겨주기 위해 echo 사용
    #쌍따옴표 (") : 변수의 실제 값을 출력
    #홀따옴표 (') : 변수명을 그대로 출력
    #여기서는 쌍따옴표 사용해야함
    #앞에서 넘겨준 문장을 service-url.inc에 덮어씀
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

    echo "> 엔진엑스 Reload"
    sudo service nginx reload
}

