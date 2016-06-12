#!/usr/bin/env bash

SCRIPT_LOCATION=$0
while [ -L "$SCRIPT_LOCATION" ]; do
  SCRIPT_LOCATION=$(readlink -e "$SCRIPT_LOCATION")
done
TOP=$(dirname "$SCRIPT_LOCATION")

convert -density 300 -resize "1024x1024" -colorspace SRGB -transparent white -flatten assets/logo.eps /tmp/$$.1.png
convert /tmp/$$.1.png -fuzz 2% -transparent white /tmp/$$.2.png

convert /tmp/$$.2.png -resize "512x512" public/img/favicon-512.png
optipng -o 7 public/img/favicon-512.png

# Touch icon for iOS 2.0+ and Android 2.1+
convert /tmp/$$.2.png -resize "152x152" public/img/favicon-152.png
optipng -o 7 public/img/favicon-152.png

# IE 10 Metro tile icon (Metro equivalent of apple-touch-icon)
convert /tmp/$$.2.png -resize "144x144" public/img/favicon-144.png
optipng -o 7 public/img/favicon-144.png

# IE 11 Tile for Windows 8.1 Start Screen
convert /tmp/$$.2.png -resize "70x70" public/img/favicon-70.png
optipng -o 7 public/img/favicon-70.png
convert /tmp/$$.2.png -resize "150x150" public/img/favicon-150.png
optipng -o 7 public/img/favicon-150.png
convert /tmp/$$.2.png -resize "310x310" public/img/favicon-310.png
optipng -o 7 public/img/favicon-310.png

convert -size 310x150 xc:transparent /tmp/$$.3.png
#convert /tmp/$$.3.png public/img/favicon-150.png -gravity center -geometry +80+0 public/img/favicon-310x150.png
cp /tmp/$$.3.png public/img/favicon-310x150.png
optipng -o 7 public/img/favicon-310x150.png
