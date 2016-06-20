#!/usr/bin/env bash

projectversion=$(git describe --tags --long)
vers1=$(echo "${projectversion}" | cut -f1 -d- | sed 's/[^0-9.]//')
vers3=$(echo "${projectversion}" | cut -f2 -d-)
vers4=$(echo "${projectversion}" | cut -f3 -d-)

vers2=$(git rev-list HEAD | wc -l)

echo "${vers1}.${vers2}.${vers3}.r${vers4}"
