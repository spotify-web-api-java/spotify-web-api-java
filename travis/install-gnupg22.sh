#!/bin/bash
# ---------
# Script to build and install GnuPG 2.2.x

apt-get update
apt-get -y install libgnutls-dev bzip2 make gettext texinfo gnutls-bin libgnutls28-dev build-essential libbz2-dev zlib1g-dev libncurses5-dev libsqlite3-dev libldap2-dev || apt-get -y install libgnutls28-dev bzip2 make gettext texinfo gnutls-bin build-essential libbz2-dev zlib1g-dev libncurses5-dev libsqlite3-dev libldap2-dev
mkdir -p /var/src/gnupg22 && cd /var/src/gnupg22
gpg --list-keys
gpg --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 249B39D24F25E3B6 04376F3EE0856959 2071B08A33BD3F06 8A861B1C7EFD60D9

wget -c https://www.gnupg.org/ftp/gcrypt/libgpg-error/libgpg-error-1.27.tar.gz && \
wget -c https://www.gnupg.org/ftp/gcrypt/libgpg-error/libgpg-error-1.27.tar.gz.sig && \
wget -c https://www.gnupg.org/ftp/gcrypt/libgcrypt/libgcrypt-1.8.2.tar.gz && \
wget -c https://www.gnupg.org/ftp/gcrypt/libgcrypt/libgcrypt-1.8.2.tar.gz.sig && \
wget -c https://www.gnupg.org/ftp/gcrypt/libassuan/libassuan-2.5.1.tar.bz2 && \
wget -c https://www.gnupg.org/ftp/gcrypt/libassuan/libassuan-2.5.1.tar.bz2.sig && \
wget -c https://www.gnupg.org/ftp/gcrypt/libksba/libksba-1.3.5.tar.bz2 && \
wget -c https://www.gnupg.org/ftp/gcrypt/libksba/libksba-1.3.5.tar.bz2.sig && \
wget -c https://www.gnupg.org/ftp/gcrypt/npth/npth-1.5.tar.bz2 && \
wget -c https://www.gnupg.org/ftp/gcrypt/npth/npth-1.5.tar.bz2.sig && \
wget -c https://www.gnupg.org/ftp/gcrypt/pinentry/pinentry-1.1.0.tar.bz2 && \
wget -c https://www.gnupg.org/ftp/gcrypt/pinentry/pinentry-1.1.0.tar.bz2.sig && \
wget -c https://www.gnupg.org/ftp/gcrypt/gnupg/gnupg-2.2.4.tar.bz2 && \
wget -c https://www.gnupg.org/ftp/gcrypt/gnupg/gnupg-2.2.4.tar.bz2.sig && \
gpg --verify libgpg-error-1.27.tar.gz.sig && tar -xzf libgpg-error-1.27.tar.gz && \
gpg --verify libgcrypt-1.8.2.tar.gz.sig && tar -xzf libgcrypt-1.8.2.tar.gz && \
gpg --verify libassuan-2.5.1.tar.bz2.sig && tar -xjf libassuan-2.5.1.tar.bz2 && \
gpg --verify libksba-1.3.5.tar.bz2.sig && tar -xjf libksba-1.3.5.tar.bz2 && \
gpg --verify npth-1.5.tar.bz2.sig && tar -xjf npth-1.5.tar.bz2 && \
gpg --verify pinentry-1.1.0.tar.bz2.sig && tar -xjf pinentry-1.1.0.tar.bz2 && \
gpg --verify gnupg-2.2.4.tar.bz2.sig && tar -xjf gnupg-2.2.4.tar.bz2 && \
cd libgpg-error-1.27/ && sudo ./configure && sudo make && sudo make install && cd ../ && \
cd libgcrypt-1.8.2 && sudo ./configure && sudo make && sudo make install && cd ../ && \
cd libassuan-2.5.1 && sudo ./configure && sudo make && sudo make install && cd ../ && \
cd libksba-1.3.5 && sudo ./configure && sudo make && sudo make install && cd ../ && \
cd npth-1.5 && sudo ./configure && sudo make && sudo make install && cd ../ && \
cd pinentry-1.1.0 && sudo ./configure --enable-pinentry-curses --disable-pinentry-qt4 && \
sudo make && sudo make install && cd ../ && \
cd gnupg-2.2.4 && sudo ./configure && sudo make && sudo make install && \
sudo sh -c "echo '/usr/local/lib' > /etc/ld.so.conf.d/gpg2.conf" && sudo ldconfig -v && \
echo "Complete!!!"