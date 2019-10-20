#!/bin/bash
echo $tips  >> /usr/share/nginx/html/index.html
nginx -g 'daemon off;'


