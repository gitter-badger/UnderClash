#!/usr/bin/env bash

top=$(git rev-parse --show-toplevel)
lock="${top}/.git-hooks.lock"
if [ ! -e "${lock}" ];
then
  version=$("${top}/bin/git-revision.sh")
  date=$(date +"%d/%m/%Y %H:%M")
  echo "build.version=${version}" > "${top}/conf/version.conf"
  echo "build.date=\"${date}\"" >> "${top}/conf/version.conf"
fi
