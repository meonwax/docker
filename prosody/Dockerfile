FROM alpine:3.6

RUN apk --update --no-cache add \
  prosody=0.9.12-r1 \
  && rm -rf /var/cache/apk/*

COPY resources/ /

VOLUME /var/lib/prosody /prosody

EXPOSE 5222/tcp 5269/tcp 5280/tcp 5281/tcp

CMD ["/usr/bin/prosodyctl" "start"]
