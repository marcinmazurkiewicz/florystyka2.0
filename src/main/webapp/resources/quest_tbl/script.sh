#!/bin/bash
for i in *.png; do
echo -n $i";" >> tables.b64
echo $(base64 -w0 $i) >> tables.b64
done
