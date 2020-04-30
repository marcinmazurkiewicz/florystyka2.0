#!/bin/bash
for i in *.png; do
echo -n $i";" >> images.txt
echo $(base64 -w0 $i) >> images.txt
done
