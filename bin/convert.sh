#!/usr/bin/env bash

command -v convert >/dev/null 2>&1 || { echo >&2 "Command \"convert\" required, but not installed.  Aborting."; exit 1; }
command -v composite >/dev/null 2>&1 || { echo >&2 "Command \"composite\" required, but not installed.  Aborting."; exit 1; }
command -v optipng >/dev/null 2>&1 || { echo >&2 "Command \"optipng\" required, but not installed.  Aborting."; exit 1; }

TOP="$(readlink -f $( cd -P "$( dirname "${BASH_SOURCE[0]}" )" && pwd )/..)"

echo "Processing logo.eps to png[1024x1024] ..."
convert -density 300 -resize "1024x1024" -colorspace SRGB -transparent white -flatten assets/logo.eps /tmp/$$.1.png
convert /tmp/$$.1.png -fuzz 2% -transparent white /tmp/$$.2.png

echo "Processing png[1024x1024] to png[512x512] ..."
convert /tmp/$$.2.png -resize "512x512" ${TOP}/public/img/logo-512.png
optipng --quiet -o7 ${TOP}/public/img/logo-512.png

# Touch icon for iOS 2.0+ and Android 2.1+
echo "Processing png[1024x1024] to png[152x152] ..."
convert /tmp/$$.2.png -resize "152x152" ${TOP}/public/img/logo-152.png
optipng --quiet -o7 ${TOP}/public/img/logo-152.png

# IE 10 Metro tile icon (Metro equivalent of apple-touch-icon)
echo "Processing png[1024x1024] to png[144x144] ..."
convert /tmp/$$.2.png -resize "144x144" ${TOP}/public/img/logo-144.png
optipng --quiet -o7 ${TOP}/public/img/logo-144.png

# IE 11 Tile for Windows 8.1 Start Screen
echo "Processing png[1024x1024] to png[70x70] ..."
convert /tmp/$$.2.png -resize "70x70" ${TOP}/public/img/logo-70.png
optipng --quiet -o7 ${TOP}/public/img/logo-70.png

echo "Processing png[1024x1024] to png[150x150] ..."
convert /tmp/$$.2.png -resize "150x150" ${TOP}/public/img/logo-150.png
optipng --quiet -o7 ${TOP}/public/img/logo-150.png

echo "Processing png[1024x1024] to png[310x310] ..."
convert /tmp/$$.2.png -resize "310x310" ${TOP}/public/img/logo-310.png
optipng --quiet -o7 ${TOP}/public/img/logo-310.png

echo "Processing png[1024x1024] to png[310x150] ..."
convert -size 310x150 xc:transparent /tmp/$$.3.png
composite -gravity center ${TOP}/public/img/logo-150.png /tmp/$$.3.png ${TOP}/public/img/logo-310x150.png
optipng --quiet -o7 ${TOP}/public/img/logo-310x150.png
