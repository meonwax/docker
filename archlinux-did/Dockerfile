#
# Latest Arch Linux image with docker and docker-compose installed
#
FROM base/archlinux
MAINTAINER Sebastian Wolf <sebastian.wolf@etecture.de>

RUN curl -o /etc/pacman.d/mirrorlist "https://www.archlinux.org/mirrorlist/?country=DE&protocol=https&ip_version=4&use_mirror_status=on" && \
    sed -i 's/^#//' /etc/pacman.d/mirrorlist

RUN pacman -Sy --noprogressbar --noconfirm && \
    pacman -S pacman --noprogressbar --noconfirm && \
    pacman-db-upgrade && \
    pacman -Syyu --noprogressbar --noconfirm

RUN pacman -S --noprogressbar --noconfirm docker wget fakeroot

RUN useradd -m aur

USER aur
RUN cd && \
    wget -qO- https://aur.archlinux.org/cgit/aur.git/snapshot/docker-compose-bin.tar.gz | tar xvz && \
    cd docker-compose-bin && \
    makepkg --clean

USER root
RUN pacman -U --noconfirm /home/aur/docker-compose-bin/*.pkg.tar.xz
RUN userdel --remove aur

RUN pacman -Scc --noconfirm

