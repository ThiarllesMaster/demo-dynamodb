#!/bin/bash
set -e

# Redireciona logs para facilitar troubleshooting
exec > >(tee /var/log/user-data.log | logger -t user-data ) 2>&1

echo "===== Atualizando sistema ====="
apt-get update -y
apt-get upgrade -y

echo "===== Instalando dependências ====="
apt-get install -y git curl unzip maven openjdk-21-jdk

echo "===== Configurando JAVA_HOME ====="
echo "export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64" >> /etc/environment
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH