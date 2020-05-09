#!/bin/bash
set -o pipefail
set -o errorexit
set -o nounset
echo "start executor crontab shell"
PWD=$(cd `diname $0`;pwd)
exec nohup python $PWD/txtutorial/txtuorial/main.py $1 >/tmp/py.log 2>&1 &