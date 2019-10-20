FROM nginx:stable
COPY index.html /usr/share/nginx/html
COPY entrypoint.sh /usr/local/bin
ENTRYPOINT ["entrypoint.sh"]



