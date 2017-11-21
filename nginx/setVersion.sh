#!/usr/bin/env bash
echo $1 > /usr/share/nginx/html/version.txt
nginx -g 'daemon off;'
