#/bin/bash


scriptDir="$(cd "$(dirname "$0")" && pwd -P)"
echo $scriptDir

# Get dirpath of environment.sh file
envDir=`echo $scriptDir | sed -e 's/\/database.*//'`
echo $envDir
