FROM alpine:3.6

ENV ARCH=amd64

ENV VERSION_S6=1.21.2.0
ENV VERSION_PROSODY=0.9.12-r1

# Add s6 process supervisor
ADD https://github.com/just-containers/s6-overlay/releases/download/v${VERSION_S6}/s6-overlay-${ARCH}.tar.gz /tmp/
RUN gunzip -c /tmp/s6-overlay-amd64.tar.gz | tar -xf - -C /

# Install packages
RUN apk --update --no-cache add \
  prosody=${VERSION_PROSODY} \
  mercurial

# Install additional prosody modules
RUN hg clone --insecure https://hg.prosody.im/prosody-modules/ /tmp/prosody-modules/ \
  && mv /tmp/prosody-modules/* /usr/lib/prosody/modules/ \
  && rm -rf /tmp/prosody-modules/

# Clean up packages
RUN apk del mercurial \
  && rm -rf /var/cache/apk/*

# Add service scripts and configuration
COPY resources/ /

# Create mount point
VOLUME ["/var/lib/prosody"]

EXPOSE 5222/tcp

ENTRYPOINT ["/init"]
